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
 * ID: $Id: BookComparators.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.book;

import java.util.Comparator;

import org.crosswire.common.util.Logger;

/**
 * Provides different ways to sort Books.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public final class BookComparators
{
    /**
     * Ensure we cant be created
     */
    private BookComparators()
    {
    }

    /**
     * Order by default Book ordering
     */
    public static Comparator getDefault()
    {
        return new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Book) o1).compareTo(o2);
            }
        };
    }

    /**
     * Order by Initials.
     */
    public static Comparator getInitialComparator()
    {
        return new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                Book b1 = (Book) o1;
                Book b2 = (Book) o2;
                return b1.getInitials().compareTo(b2.getInitials());
            }
        };
    }

    /**
     * The log stream
     */
    static final Logger log = Logger.getLogger(BookComparators.class);
}
