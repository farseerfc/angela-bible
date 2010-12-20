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
 * ID: $Id: DifferenceEngine.java 1462 2007-07-02 02:32:23Z dmsmith $
 */
package org.crosswire.common.diff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Builds a map of a baseline/source text and a changed/target text, navigating it to determine differences.
 *
 * Based on the LGPL Diff_Match_Patch v1.5 javascript of Neil Fraser, Copyright (C) 2006
 * <a href="http://neil.fraser.name/software/diff_match_patch/">http://neil.fraser.name/software/diff_match_patch/</a>
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class DifferenceEngine
{
    /**
     * Empty Difference Engine, which won't find anything.
     */
    public DifferenceEngine()
    {
        this("", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Find the differences between two texts.  Simplifies the problem by
     * stripping any common prefix or suffix off the texts before diffing.
     * @param source Old string to be diffed
     * @param target New string to be diffed
     */
    public DifferenceEngine(final String source, final String target)
    {
        this.source = source;
        this.target = target;
    }

    /**
     * @param newSource the source to set
     */
    public void setSource(String newSource)
    {
        this.source = newSource;
    }

    /**
     * @param newTarget the target to set
     */
    public void setTarget(String newTarget)
    {
        this.target = newTarget;
    }

    /**
     * Explore the intersection points between the two texts.
     * @return List of Difference objects or null if no diff available
     */
    public List generate()
    {
        long msEnd = System.currentTimeMillis() + (long) (timeout * 1000);
        int maxD = (source.length() + target.length()) / 2;
        List vMap1 = new ArrayList();
        List vMap2 = new ArrayList();
        Map v1 = new HashMap();
        Map v2 = new HashMap();
        v1.put(new Integer(1), new Integer(0));
        v2.put(new Integer(1), new Integer(0));
        int x;
        int y;
        String footstep; // Used to track overlapping paths.
        Map footsteps = new HashMap();
        boolean done = false;
        // If the total number of characters is odd, then the front path will
        // collide with the reverse path.
        boolean front = (source.length() + target.length()) % 2 != 0;
        for (int d = 0; d < maxD; d++)
        {
            // Bail out if timeout reached.
            if (timeout > 0 && System.currentTimeMillis() > msEnd)
            {
                return null;
            }

            // Walk the front path one step.
            vMap1.add(new HashSet()); // Adds at index 'd'.
            for (int k = -d; k <= d; k += 2)
            {
                Integer kPlus1Key = new Integer(k + 1);
                Integer kPlus1Value = (Integer) v1.get(kPlus1Key);
                Integer kMinus1Key = new Integer(k - 1);
                Integer kMinus1Value = (Integer) v1.get(kMinus1Key);
                if (k == -d || k != d && kMinus1Value.intValue() < kPlus1Value.intValue())
                {
                    x = kPlus1Value.intValue();
                }
                else
                {
                    x = kMinus1Value.intValue() + 1;
                }
                y = x - k;
                footstep = x + "," + y; //$NON-NLS-1$
                if (front && (footsteps.containsKey(footstep)))
                {
                    done = true;
                }
                if (!front)
                {
                    footsteps.put(footstep, new Integer(d));
                }
                while (!done && x < source.length() && y < target.length() && source.charAt(x) == target.charAt(y))
                {
                    x++;
                    y++;
                    footstep = x + "," + y; //$NON-NLS-1$
                    if (front && footsteps.containsKey(footstep))
                    {
                        done = true;
                    }
                    if (!front)
                    {
                        footsteps.put(footstep, new Integer(d));
                    }
                }
                v1.put(new Integer(k), new Integer(x));
                Set s = (Set) vMap1.get(d);
                s.add(x + "," + y); //$NON-NLS-1$
                if (done)
                {
                    // Front path ran over reverse path.
                    Integer footstepValue = (Integer) footsteps.get(footstep);
                    vMap2 = vMap2.subList(0, footstepValue.intValue() + 1);
                    List a = path1(vMap1, source.substring(0, x), target.substring(0, y));
                    a.addAll(path2(vMap2, source.substring(x), target.substring(y)));
                    return a;
                }
            }

            // Walk the reverse path one step.
            vMap2.add(new HashSet()); // Adds at index 'd'.
            for (int k = -d; k <= d; k += 2)
            {
                Integer kPlus1Key = new Integer(k + 1);
                Integer kPlus1Value = (Integer) v2.get(kPlus1Key);
                Integer kMinus1Key = new Integer(k - 1);
                Integer kMinus1Value = (Integer) v2.get(kMinus1Key);
                if (k == -d || k != d && kMinus1Value.intValue() < kPlus1Value.intValue())
                {
                    x = kPlus1Value.intValue();
                }
                else
                {
                    x = kMinus1Value.intValue() + 1;
                }
                y = x - k;
                footstep = (source.length() - x) + "," + (target.length() - y); //$NON-NLS-1$
                if (!front && (footsteps.containsKey(footstep)))
                {
                    done = true;
                }
                if (front)
                {
                    footsteps.put(footstep, new Integer(d));
                }
                while (!done && x < source.length() && y < target.length() && source.charAt(source.length() - x - 1) == target.charAt(target.length() - y - 1))
                {
                    x++;
                    y++;
                    footstep = (source.length() - x) + "," + (target.length() - y); //$NON-NLS-1$
                    if (!front && (footsteps.containsKey(footstep)))
                    {
                        done = true;
                    }
                    if (front)
                    {
                        footsteps.put(footstep, new Integer(d));
                    }
                }

                v2.put(new Integer(k), new Integer(x));
                Set s = (Set) vMap2.get(d);
                s.add(x + "," + y); //$NON-NLS-1$
                if (done)
                {
                    // Reverse path ran over front path.
                    Integer footstepValue = (Integer) footsteps.get(footstep);
                    vMap1 = vMap1.subList(0, footstepValue.intValue() + 1);
                    List a = path1(vMap1, source.substring(0, source.length() - x), target.substring(0, target.length() - y));
                    a.addAll(path2(vMap2, source.substring(source.length() - x), target.substring(target.length() - y)));
                    return a;
                }
            }
        }

        // Number of diffs equals number of characters, no commonality at all.
        return null;
    }

    /**
     * Work from the middle back to the start to determine the path.
     * @param vMap List of path sets.
     * @param newSource Old string fragment to be diffed
     * @param newTarget New string fragment to be diffed
     * @return List of Difference objects
     */
    protected List path1(final List vMap, final String newSource, final String newTarget)
    {
        List path = new ArrayList();
        int x = newSource.length();
        int y = newTarget.length();
        EditType lastEditType = null;
        for (int d = vMap.size() - 2; d >= 0; d--)
        {
            while (true)
            {
                Set set = (Set) vMap.get(d);
                if (set.contains((x - 1) + "," + y)) //$NON-NLS-1$
                {
                    x--;
                    if (EditType.DELETE.equals(lastEditType))
                    {
                        Difference firstDiff = (Difference) path.get(0);
                        firstDiff.prependText(newSource.charAt(x));
                    }
                    else
                    {
                        path.add(0, new Difference(EditType.DELETE, newSource.substring(x, x + 1)));
                    }
                    lastEditType = EditType.DELETE;
                    break;
                }
                else if (set.contains(x + "," + (y - 1))) //$NON-NLS-1$
                {
                    y--;
                    if (EditType.INSERT.equals(lastEditType))
                    {
                        Difference firstDiff = (Difference) path.get(0);
                        firstDiff.prependText(newTarget.charAt(y));
                    }
                    else
                    {
                        path.add(0, new Difference(EditType.INSERT, newTarget.substring(y, y + 1)));
                    }
                    lastEditType = EditType.INSERT;
                    break;
                }
                else
                {
                    x--;
                    y--;
                    assert newSource.charAt(x) == newTarget.charAt(y) : "No diagonal.  Can't happen. (path1)"; //$NON-NLS-1$
                    if (EditType.EQUAL.equals(lastEditType))
                    {
                        Difference firstDiff = (Difference) path.get(0);
                        firstDiff.prependText(newSource.charAt(x));
                    }
                    else
                    {
                        path.add(0, new Difference(EditType.EQUAL, newSource.substring(x, x + 1)));
                    }
                    lastEditType = EditType.EQUAL;
                }
            }
        }
        return path;
    }

    /**
     * Work from the middle back to the end to determine the path.
     * @param vMap List of path sets.
     * @param newSource Old string fragment to be diffed
     * @param newTarget New string fragment to be diffed
     * @return List of Difference objects
     */
    protected List path2(final List vMap, final String newSource, final String newTarget)
    {
        List path = new ArrayList();
        int x = newSource.length();
        int y = newTarget.length();
        EditType lastEditType = null;
        for (int d = vMap.size() - 2; d >= 0; d--)
        {
            while (true)
            {
                Set set = (Set) vMap.get(d);
                if (set.contains((x - 1) + "," + y)) //$NON-NLS-1$
                {
                    x--;
                    if (EditType.DELETE.equals(lastEditType))
                    {
                        Difference lastDiff = (Difference) path.get(path.size() - 1);
                        lastDiff.appendText(newSource.charAt(newSource.length() - x - 1));
                    }
                    else
                    {
                        path.add(new Difference(EditType.DELETE, newSource.substring(newSource.length() - x - 1, newSource.length() - x)));
                    }
                    lastEditType = EditType.DELETE;
                    break;
                }
                else if (set.contains(x + "," + (y - 1))) //$NON-NLS-1$
                {
                    y--;
                    if (EditType.INSERT.equals(lastEditType))
                    {
                        Difference lastDiff = (Difference) path.get(path.size() - 1);
                        lastDiff.appendText(newTarget.charAt(newTarget.length() - y - 1));
                    }
                    else
                    {
                        path.add(new Difference(EditType.INSERT, newTarget.substring(newTarget.length() - y - 1, newTarget.length() - y)));
                    }
                    lastEditType = EditType.INSERT;
                    break;
                }
                else
                {
                    x--;
                    y--;
                    assert newSource.charAt(newSource.length() - x - 1) == newTarget.charAt(newTarget.length() - y - 1) : "No diagonal.  Can't happen. (path2)"; //$NON-NLS-1$

                    if (EditType.EQUAL.equals(lastEditType))
                    {
                        Difference lastDiff = (Difference) path.get(path.size() - 1);
                        lastDiff.appendText(newSource.charAt(newSource.length() - x - 1));
                    }
                    else
                    {
                        path.add(new Difference(EditType.EQUAL, newSource.substring(newSource.length() - x - 1, newSource.length() - x)));
                    }
                    lastEditType = EditType.EQUAL;
                }
            }
        }
        return path;
    }

    /**
     * Set the timeout for the diff operation. The default is 1 second. Use 0 for infinity.
     *
     * @param newTimeout
     */
    public static void setTimeout(float newTimeout)
    {
        timeout = newTimeout;
    }

    /**
     * Number of seconds to map a diff before giving up. Use 0 for infinity.
     */
    private static final float TIMEOUT   = 1.0f;
    private static float timeout = TIMEOUT;
    /**
     * The baseline text.
     */
    private String source;

    /**
     * The changed text.
     */
    private String target;
}
