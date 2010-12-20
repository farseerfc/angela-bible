/**
 * Distribution License:
 * JSword is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 as published by
 * the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/lgpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2007
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: Patch.java 1462 2007-07-02 02:32:23Z dmsmith $
 */
package org.crosswire.common.diff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

/**
 * Marshals a patch to a list of Differences, Differences to a patch and applies a list of differences to text to patch it.
 *
 * Based on the LGPL Diff_Match_Patch v1.5 javascript of Neil Fraser, Copyright (C) 2006
 * <a href="http://neil.fraser.name/software/diff_match_patch/">http://neil.fraser.name/software/diff_match_patch/</a>
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class Patch
{
    /**
     * Create an empty patch.
     */
    public Patch()
    {
        patches = new ArrayList();
        margin = PatchEntry.getMargin();
    }

    /**
     * Create a Patch from a textual representation,
     * @param input Text representation of patches
     */
    public Patch(String input)
    {
        this();
        fromText(input);
    }

    /**
     * Create a patch that can turn text1 into text2.
     * @param source Old text
     * @param target New text
     */
    public Patch(String source, String target)
    {
        this(source, target, null);
    }

    /**
     * Create a patch that can turn text1 into text2.
     * Use the diffs provided, if not null. Compute diffs otherwise.
     * @param source Old text
     * @param target New text
     * @param diffs Optional array of diff tuples for text1 to text2.
     */
    public Patch(String source, String target, List diffs)
    {
        this();
        make(source, target, diffs);
    }

    /**
     * Compute a list of patches to turn text1 into text2.
     * Use the diffs provided.
     * @param source Old text
     * @param target New text
     * @param diffList Optional array of diff tuples for text1 to text2.
     * @return this patch
     */
    public Patch make(String source, String target, List diffList)
    {
        List diffs = diffList;
        if (diffs == null)
        {
            Diff diff = new Diff(source, target);
            diffs = diff.compare();
            if (diffs.size() > 2)
            {
                DiffCleanup.cleanupSemantic(diffs);
                DiffCleanup.cleanupEfficiency(diffs);
            }
        }

        patches.clear();

        if (diffs.size() == 0)
        {
            return this; // Get rid of the null case.
        }

        PatchEntry patch = new PatchEntry();
        int charCount1 = 0; // Number of characters into the text1 string.
        int charCount2 = 0; // Number of characters into the text2 string.
        // Recreate the patches to determine context info.
        String prePatchText = source;
        String postPatchText = source;
        Iterator iter = diffs.iterator();
        int x = 0;
        while (iter.hasNext())
        {
            Difference diff = (Difference) iter.next();
            EditType editType = diff.getEditType();
            String diffText = diff.getText();
            int len = diffText.length();

            if (!patch.hasDifferences() && !EditType.EQUAL.equals(editType))
            {
                // A new patch starts here.
                patch.setSourceStart(charCount1);
                patch.setTargetStart(charCount2);
            }

            if (EditType.INSERT.equals(editType))
            {
                // Insertion
                patch.addDifference(diff);
                patch.adjustTargetLength(len);
                postPatchText = postPatchText.substring(0, charCount2) + diffText + postPatchText.substring(charCount2);
            }
            else if (EditType.DELETE.equals(editType))
            {
                // Deletion.
                patch.adjustSourceLength(len);
                patch.addDifference(diff);
                postPatchText = postPatchText.substring(0, charCount2) + postPatchText.substring(charCount2 + len);
            }
            else if (EditType.EQUAL.equals(editType) && len <= 2 * margin && patch.hasDifferences() && diffs.size() != x + 1)
            {
                // Small equality inside a patch.
                patch.addDifference(diff);
                patch.adjustSourceLength(len);
                patch.adjustTargetLength(len);
            }

            // Time for a new patch.
            if (EditType.EQUAL.equals(editType) && len >= 2 * margin && patch.hasDifferences())
            {
                patch.addContext(prePatchText);
                patches.add(patch);
                patch = new PatchEntry();
                prePatchText = postPatchText;
            }

            // Update the current character count.
            if (!EditType.INSERT.equals(editType))
            {
                charCount1 += len;
            }

            if (!EditType.DELETE.equals(editType))
            {
                charCount2 += len;
            }

            x++;
        }

        // Pick up the leftover patch if not empty.
        if (patch.hasDifferences())
        {
            patch.addContext(prePatchText);
            patches.add(patch);
        }

        return this;
    }


    /**
     * Merge this patch onto the text.  Return a patched text, as well
     * as an array of true/false values indicating which patches were applied.
     * @param text Old text
     * @return the patch result
     */
    public PatchResults apply(String text)
    {
        splitMax();
        boolean[] results = new boolean[patches.size()];
        String resultText = text;
        int delta = 0;
        int expectedLoc = 0;
        int startLoc = -1;
        String text1 = ""; //$NON-NLS-1$
        String text2 = ""; //$NON-NLS-1$
        List diffs;
        int index1 = 0;
        int index2 = 0;
        int x = 0;
        Iterator patchIter = patches.iterator();
        while (patchIter.hasNext())
        {
            PatchEntry aPatch = (PatchEntry) patchIter.next();
            expectedLoc = aPatch.getTargetStart() + delta;
            text1 = aPatch.getSourceText();
            Match match = new Match(resultText, text1, expectedLoc);
            startLoc = match.locate();
            if (startLoc == -1)
            {
                // No match found.  :(
                results[x] = false;
            }
            else
            {
                // Found a match.  :)
                results[x] = true;
                delta = startLoc - expectedLoc;
                text2 = resultText.substring(startLoc, startLoc + text1.length());
                if (text1.equals(text2))
                {
                    // Perfect match, just shove the replacement text in.
                    resultText = resultText.substring(0, startLoc) + aPatch.getTargetText() + resultText.substring(startLoc + text1.length());
                }
                else
                {
                    // Imperfect match.  Run a diff to get a framework of equivalent indicies.
                    Diff diff = new Diff(text1, text2, false);
                    diffs = diff.compare();
                    index1 = 0;
                    Iterator diffIter = aPatch.iterator();
                    while (diffIter.hasNext())
                    {
                        Difference aDiff = (Difference) diffIter.next();
                        EditType editType = aDiff.getEditType();
                        if (!EditType.EQUAL.equals(editType))
                        {
                            index2 = diff.xIndex(diffs, index1);
                        }

                        if (EditType.INSERT.equals(editType)) // Insertion
                        {
                            resultText = resultText.substring(0, startLoc + index2) + aDiff.getText() + resultText.substring(startLoc + index2);
                        }
                        else if (EditType.DELETE.equals(editType)) // Deletion
                        {
                            resultText = resultText.substring(0, startLoc + index2) + resultText.substring(startLoc + diff.xIndex(diffs, index1 + aDiff.getText().length()));
                        }

                        if (!EditType.DELETE.equals(editType))
                        {
                            index1 += aDiff.getText().length();
                        }
                    }
                }
            }
            x++;
        }
        return new PatchResults(resultText, results);
    }

    /**
     * Look through the patches and break up any which are longer than the maximum
     * limit of the match algorithm.
     */
    public void splitMax()
    {
        int maxPatternLength = new Match().maxPatternLength();
        ListIterator pointer = patches.listIterator();
        PatchEntry bigPatch = pointer.hasNext() ? (PatchEntry) pointer.next() : null;
        while (bigPatch != null)
        {
            if (bigPatch.getSourceLength() <= maxPatternLength)
            {
                if (!pointer.hasNext())
                {
                    break;
                }

                bigPatch = (PatchEntry) pointer.next();
            }

            // Remove the big old patch.
            pointer.remove();
            int patchSize = maxPatternLength;
            int start1 = bigPatch.getSourceStart();
            int start2 = bigPatch.getTargetStart();
            String preContext = ""; //$NON-NLS-1$
            while (bigPatch.hasDifferences())
            {
                // Create one of several smaller patches.
                PatchEntry patch = new PatchEntry();
                boolean empty = true;

                int len = preContext.length();
                patch.setSourceStart(start1 - len);
                patch.setTargetStart(start2 - len);
                if (len > 0)
                {
                    patch.setSourceLength(len);
                    patch.setTargetLength(len);
                    patch.addDifference(new Difference(EditType.EQUAL, preContext));
                }

                while (bigPatch.hasDifferences() && patch.getSourceLength() < patchSize - margin)
                {
                    Difference bigDiff = bigPatch.getFirstDifference();
                    EditType editType = bigDiff.getEditType();
                    String diffText = bigDiff.getText();
                    if (EditType.INSERT.equals(editType))
                    {
                        // Insertions are harmless.
                        len = diffText.length();
                        patch.adjustTargetLength(len);
                        start2 += len;
                        patch.addDifference(bigPatch.removeFirstDifference());
                        empty = false;
                    }
                    else
                    {
                        // Deletion or equality.  Only take as much as we can stomach.
                        diffText = diffText.substring(0, Math.min(diffText.length(), patchSize - patch.getSourceLength() - margin));
                        len = diffText.length();
                        patch.adjustSourceLength(len);
                        start1 += len;
                        if (EditType.EQUAL.equals(editType))
                        {
                            patch.adjustTargetLength(len);
                            start2 += len;
                        }
                        else
                        {
                            empty = false;
                        }

                        patch.addDifference(new Difference(editType, diffText));

                        if (diffText.equals(bigDiff.getText()))
                        {
                            bigPatch.removeFirstDifference();
                        }
                        else
                        {
                            bigDiff.setText(bigDiff.getText().substring(len));
                        }
                    }
                }

                // Compute the head context for the next patch.
                preContext = patch.getTargetText();
                preContext = preContext.substring(Math.max(0, preContext.length() - margin));

                // Append the end context for this patch.
                String postcontext = null;
                if (bigPatch.getSourceText().length() > margin)
                {
                    postcontext = bigPatch.getSourceText().substring(0, margin);
                }
                else
                {
                    postcontext = bigPatch.getSourceText();
                }
                if (postcontext.length() > 0)
                {
                    patch.adjustSourceLength(postcontext.length());
                    patch.adjustTargetLength(postcontext.length());
                    if (patch.getDifferenceCount() > 0 && EditType.EQUAL.equals(patch.getLastDifference().getEditType()))
                    {
                        Difference diff = patch.getLastDifference();
                        diff.appendText(postcontext);
                    }
                    else
                    {
                        patch.addDifference(new Difference(EditType.EQUAL, postcontext));
                    }
                }

                if (!empty)
                {
                    pointer.add(patch);
                }
            }

            bigPatch = pointer.hasNext() ? (PatchEntry) pointer.next() : null;
        }
    }

    /**
     * Take a list of patches and return a textual representation.
     * @return Text representation of patches.
     */
    public String toText()
    {
        StringBuffer text = new StringBuffer();
        Iterator iter = patches.iterator();
        while (iter.hasNext())
        {
            text.append(iter.next());
        }
        return text.toString();
    }

    /**
     * Parse a textual representation of patches and return a List of Patch
     * objects.
     * @param input Text representation of patches
     * @return List of Patch objects
     */
    public Patch fromText(String input)
    {
        patches.clear();
        String[] text = patchBoundaryPattern.split(input);
        for (int patchCount = 0; patchCount < text.length; patchCount++)
        {
            patches.add(new PatchEntry(text[patchCount]));
        }
        return this;
    }

    /**
     * A holder of the results of a patch, with a results indicating
     * which patch entries were able to be applied.
     */
    public static class PatchResults
    {
        /**
         * @param text
         * @param results
         */
        public PatchResults(String text, boolean[] results)
        {
            this.text = text;
            this.results = (boolean[]) results.clone();
        }

        /**
         * @return the results
         */
        public boolean[] getResults()
        {
            return (boolean[]) results.clone();
        }

        /**
         * @return the text
         */
        public String getText()
        {
            return text;
        }

        private String text;
        private boolean[] results;
    }

    private static Pattern patchBoundaryPattern = Pattern.compile("\n@@"); //$NON-NLS-1$

    private List patches;
    private int margin;
}
