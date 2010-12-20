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
 * ID: $Id: BooksEvent.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.book;

import java.util.EventObject;

/**
 * A BooksEvent is fired whenever a Bible is added or removed from the
 * system.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class BooksEvent extends EventObject
{
    /**
     * Basic constructor
     * @param book The book of the changed Bible, or null if there is more than one change.
     * @param added True if the changed Bible is an addition.
     */
    public BooksEvent(Object source, Book book, boolean added)
    {
        super(source);

        this.book = book;
        this.added = added;
    }

    /**
     * Get the name of the changed Book
     * @return The Book
     */
    public Book getBook()
    {
        return book;
    }

    /**
     * Is this an addition event?
     */
    public boolean isAddition()
    {
        return added;
    }

    /**
     * Is this an addition event?
     */
    private boolean added;

    /**
     * The name of the changed Bible
     */
    private transient Book book;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3834876879554819894L;
}
