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
 * ID: $Id: BookDriver.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.book;

/**
 * The BibleDriver class allows creation of new Books.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public interface BookDriver extends BookProvider
{
    /**
     * Is this name capable of creating writing data in the correct format
     * as well as reading it?
     * @return true/false to indicate ability to write data
     */
    boolean isWritable();

    /**
     * Create a new Book based on a source.
     * @param source The Book from which to copy data
     * @return The new WritableBible
     * @exception BookException If the name is not valid
     */
    Book create(Book source) throws BookException;

    /**
     * Is this book able to be deleted.
     * @param dead the book to be deleted
     * @return whether the book can be deleted.
     */
    boolean isDeletable(Book dead);

    /**
     * Delete this Book from the system.
     * Take care with this method for obvious reasons. For most implemenations
     * of Book etc, this method will throw up because most will be read-only.
     * @throws BookException If the Book can't be deleted.
     */
    void delete(Book dead) throws BookException;

    /**
     * A short name for this BookDriver
     */
    String getDriverName();
}
