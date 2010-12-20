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
 * ID: $Id: PatchEntry.java 1462 2007-07-02 02:32:23Z dmsmith $
 */
package org.crosswire.common.diff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A PatchEntry is a single "instruction" in a Patch, consisting of a interval over which differences
 * are applied and the differences that should be applied.
 *
 * Based on the LGPL Diff_Match_Patch v1.5 javascript of Neil Fraser, Copyright (C) 2006
 * <a href="http://neil.fraser.name/software/diff_match_patch/">http://neil.fraser.name/software/diff_match_patch/</a>
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class PatchEntry
{
    //  Constructor for a patch object.
    public PatchEntry()
    {
        this.diffs = new ArrayList();
        this.sourceStart = 0;
        this.targetStart = 0;
        this.sourceLength = 0;
        this.targetLength = 0;
    }

    //  Constructor for a patch object.
    public PatchEntry(String patchText)
    {
        this();
        fromText(patchText);
    }

    /**
     * @return the sourceStart
     */
    public int getSourceStart()
    {
        return sourceStart;
    }

    /**
     * @param start the sourceStart to set
     */
    public void setSourceStart(int start)
    {
        this.sourceStart = start;
    }

    /**
     * @param adjustment the adjustment to sourceStart
     */
    public void adjustSourceStart(int adjustment)
    {
        this.sourceStart += adjustment;
    }

    /**
     * @return the targetStart
     */
    public int getTargetStart()
    {
        return targetStart;
    }

    /**
     * @param start the targetStart to set
     */
    public void setTargetStart(int start)
    {
        this.targetStart = start;
    }

    /**
     * @param adjustment the adjustment to targetStart
     */
    public void adjustTargetStart(int adjustment)
    {
        this.targetStart += adjustment;
    }

    /**
     * @return the sourceLength
     */
    public int getSourceLength()
    {
        return sourceLength;
    }

    /**
     * @param length the sourceLength to set
     */
    public void setSourceLength(int length)
    {
        this.sourceLength = length;
    }

    /**
     * @param adjustment the adjustment to sourceLength
     */
    public void adjustSourceLength(int adjustment)
    {
        this.sourceLength += adjustment;
    }

    /**
     * @return the targetLength
     */
    public int getTargetLength()
    {
        return targetLength;
    }

    /**
     * @param length the targetLength to set
     */
    public void setTargetLength(int length)
    {
        this.targetLength = length;
    }

    /**
     * @param adjustment the adjustment to targetLength
     */
    public void adjustTargetLength(int adjustment)
    {
        this.targetLength += adjustment;
    }

    //  Emmulate GNU diff's format.
    //  Header: @@ -382,8 +481,9 @@
    //  Indicies are printed as 1-based, not 0-based.
    public String toString()
    {
        StringBuffer txt = new StringBuffer();
        txt.append("@@ -"); //$NON-NLS-1$
        txt.append(getCoordinates(sourceStart, sourceLength));
        txt.append(" +"); //$NON-NLS-1$
        txt.append(getCoordinates(targetStart, targetLength));
        txt.append(" @@\n"); //$NON-NLS-1$

        Iterator iter = diffs.iterator();
        while (iter.hasNext())
        {
            Difference diff = (Difference) iter.next();
            txt.append(diff.getEditType().getSymbol());
            txt.append(diff.getText());
            txt.append('\n');
        }
        return txt.toString();
    }

    /**
     * Parse a textual representation of a patch entry and populate this patch entry.
     * @param input Text representation of this patch entry
     * @return this patch entry
     */
    public PatchEntry fromText(String input)
    {
        diffs.clear();
        String[] text = newlinePattern.split(input);
        char sign = '\0';
        String line = ""; //$NON-NLS-1$

        Matcher matcher = patchPattern.matcher(text[0]);
        matcher.matches();
        assert matcher.groupCount() == 4 : "Invalid patch string:\n" + text[0]; //$NON-NLS-1$
        // m = text[0].match(/^@@ -(\d+),?(\d*) \+(\d+),?(\d*) @@$/);

        sourceStart = Integer.parseInt(matcher.group(1));

        if (matcher.group(2).length() == 0)
        {
            sourceStart--;
            sourceLength = 1;
        }
        else if (matcher.group(2).charAt(0) == '0')
        {
            setSourceLength(0);
        }
        else
        {
            sourceStart--;
            sourceLength = Integer.parseInt(matcher.group(2));
        }

        targetStart = Integer.parseInt(matcher.group(3));
        if (matcher.group(4).length() == 0)
        {
            targetStart--;
            targetLength = 1;
        }
        else if (matcher.group(4).charAt(0) == '0')
        {
            targetLength = 0;
        }
        else
        {
            targetStart--;
            targetLength = Integer.parseInt(matcher.group(4));
        }

        for (int lineCount = 1; lineCount < text.length; lineCount++)
        {
            if (text[lineCount].length() > 0)
            {
                sign = text[lineCount].charAt(0);
                line = text[lineCount].substring(1);
                diffs.add(new Difference(EditType.fromSymbol(sign), line));
            }
        }
        return this;
    }

    //  Compute and return the source text (all equalities and deletions).
    public String getSourceText()
    {
        StringBuffer txt = new StringBuffer();
        Iterator iter = diffs.iterator();
        while (iter.hasNext())
        {
            Difference diff = (Difference) iter.next();
            if (!EditType.INSERT.equals(diff.getEditType()))
            {
                txt.append(diff.getText());
            }
        }
        return txt.toString();
    }

    // Compute and return the destination text (all equalities and insertions).
    public String getTargetText()
    {
        StringBuffer txt = new StringBuffer();
        Iterator iter = diffs.iterator();
        while (iter.hasNext())
        {
            Difference diff = (Difference) iter.next();
            if (!EditType.DELETE.equals(diff.getEditType()))
            {
                txt.append(diff.getText());
            }
        }
        return txt.toString();
    }

    public void addContext(String text)
    {
        int maxPatternLength = new Match().maxPatternLength();
        int padding = 0;
        String pattern = text.substring(targetStart, targetStart + sourceLength);
        int textLength = text.length();

        // Increase the context until we're unique
        // (but don't let the pattern expand beyond the maximum length our Locator can handle).
        int end = maxPatternLength - PatchEntry.margin - PatchEntry.margin;
        while (text.indexOf(pattern) != text.lastIndexOf(pattern) && pattern.length() < end)
        {
            padding += PatchEntry.margin;
            pattern = text.substring(Math.max(0, targetStart - padding), Math.min(textLength, targetStart + sourceLength + padding));
        }

        // Add one chunk for good luck.
        padding += PatchEntry.margin;

        // Add the prefix.
        String prefix = text.substring(Math.max(0, targetStart - padding), targetStart);
        int prefixLength = prefix.length();
        if (prefixLength > 0)
        {
            diffs.add(0, new Difference(EditType.EQUAL, prefix));
        }

        // Add the suffix
        String suffix = text.substring(targetStart + sourceLength, Math.min(textLength, targetStart + sourceLength + padding));
        int suffixLength = suffix.length();
        if (suffixLength > 0)
        {
            diffs.add(new Difference(EditType.EQUAL, suffix));
        }

        // Roll back the start points.
        sourceStart -= prefixLength;
        targetStart -= prefixLength;

        // Extend the lengths.
        sourceLength += prefixLength + suffixLength;
        targetLength += prefixLength + suffixLength;
    }

    public void addDifference(Difference diff)
    {
        diffs.add(diff);
    }

    public int getDifferenceCount()
    {
        return diffs.size();
    }

    public boolean hasDifferences()
    {
        return diffs.size() != 0;
    }

    public Iterator iterator()
    {
        return diffs.iterator();
    }

    public Difference getFirstDifference()
    {
        if (diffs.size() == 0)
        {
            return null;
        }
        return (Difference) diffs.get(0);
    }

    public Difference removeFirstDifference()
    {
        if (diffs.size() == 0)
        {
            return null;
        }
        return (Difference) diffs.remove(0);
    }

    public Difference getLastDifference()
    {
        if (diffs.size() == 0)
        {
            return null;
        }
        return (Difference) diffs.get(diffs.size() - 1);
    }

    protected void setDifferences(List newDiffs)
    {
        diffs = newDiffs;
    }

    /**
     * @param newMargin the margin to set
     */
    public static void setMargin(int newMargin)
    {
        PatchEntry.margin = newMargin;
    }

    /**
     * @return the margin
     */
    public static int getMargin()
    {
        return margin;
    }

    private String getCoordinates(int start, int length)
    {
        StringBuffer buf = new StringBuffer();

        if (length == 0)
        {
            buf.append(start);
            buf.append(",0"); //$NON-NLS-1$
        }
        else if (length == 1)
        {
            buf.append(sourceStart + 1);
        }
        else
        {
            buf.append(start + 1);
            buf.append(',');
            buf.append(length);
        }

        return buf.toString();
    }

    /**
     * Chunk size for context length.
     */
    private static final int MARGIN = 4;
    private static int margin = MARGIN;
    private static Pattern newlinePattern = Pattern.compile("\n"); //$NON-NLS-1$
    private static Pattern patchPattern = Pattern.compile("^@@ -(\\d+),?(\\d*) \\+(\\d+),?(\\d*) @@$"); //$NON-NLS-1$

    private List diffs;
    private int sourceStart;
    private int targetStart;
    private int sourceLength;
    private int targetLength;
}
