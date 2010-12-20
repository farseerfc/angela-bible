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
 * Copyright: 2005
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: PassageListType.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.passage;

import java.io.Serializable;

/**
 * Types of Passage Lists.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public abstract class PassageListType implements Serializable
{
    /**
     * Passage to be interpreted as a list of verses.
     */
    public static final PassageListType VERSES = new PassageListType("VERSES") //$NON-NLS-1$
    {
        public Object getElementAt(Passage ref, int index, RestrictionType restrict)
        {
            if (ref == null)
            {
                return null;
            }
            return ref.getVerseAt(index);
        }

        public int count(Passage ref, RestrictionType restrict)
        {
            if (ref == null)
            {
                return 0;
            }
            return ref.countVerses();
        }

        /**
         * Serialization ID
         */
        private static final long serialVersionUID = 4050199730607109682L;
    };

    /**
     *  Passage to be interpreted as a list of ranges.
     */
    public static final PassageListType RANGES = new PassageListType("RANGES") //$NON-NLS-1$
    {
        public Object getElementAt(Passage ref, int index, RestrictionType restrict)
        {
            if (ref == null)
            {
                return null;
            }
            return ref.getRangeAt(index, restrict);
        }

        public int count(Passage ref, RestrictionType restrict)
        {
            if (ref == null)
            {
                return 0;
            }
            return ref.countRanges(restrict);
        }

        /**
         * Serialization ID
         */
        private static final long serialVersionUID = 3834030242750083129L;
    };

    /**
     * Simple ctor
     */
    public PassageListType(String name)
    {
        this.name = name;
    }

    public abstract Object getElementAt(Passage ref, int index, RestrictionType restrict);
    public abstract int count(Passage ref, RestrictionType restrict);

    /**
     * Lookup method to convert from a String
     */
    public static PassageListType fromString(String name)
    {
        for (int i = 0; i < VALUES.length; i++)
        {
            PassageListType o = VALUES[i];
            if (o.name.equalsIgnoreCase(name))
            {
                return o;
            }
        }
        // cannot get here
        assert false;
        return null;
    }

    /**
     * Lookup method to convert from an integer
     */
    public static PassageListType fromInteger(int i)
    {
        return VALUES[i];
    }

    /**
     * Prevent subclasses from overriding canonical identity based Object methods
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public final boolean equals(Object o)
    {
        return super.equals(o);
    }

    /**
     * Prevent subclasses from overriding canonical identity based Object methods
     * @see java.lang.Object#hashCode()
     */
    public final int hashCode()
    {
        return super.hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return name;
    }

    /**
     * The name of the PassageListType
     */
    private String name;

    // Support for serialization
    private static int nextObj;
    private final int obj = nextObj++;

    Object readResolve()
    {
        return VALUES[obj];
    }

    private static final PassageListType[] VALUES =
    {
        VERSES,
        RANGES,
    };
}
