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
 * ID: $Id: ReadingsBookDriver.java 1409 2007-06-15 21:57:17Z dmsmith $
 */
package org.crosswire.jsword.book.readings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.crosswire.common.util.NetUtil;
import org.crosswire.common.util.ResourceUtil;
import org.crosswire.common.util.URIFilter;
import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookCategory;
import org.crosswire.jsword.book.BookDriver;
import org.crosswire.jsword.book.basic.AbstractBookDriver;

/**
 * A driver for the readings dictionary.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class ReadingsBookDriver extends AbstractBookDriver
{
    /**
     * Setup the array of BookMetaDatas
     */
    public ReadingsBookDriver()
    {
        List bookList = new ArrayList();
        String[] installedBooks = getInstalledReadingsSets();
        for (int i = 0; i < installedBooks.length; i++)
        {
            bookList.add(new ReadingsBook(this, installedBooks[i], BookCategory.DAILY_DEVOTIONS));
        }

        books = (Book[]) bookList.toArray(new Book[bookList.size()]);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.BookDriver#getBooks()
     */
    public Book[] getBooks()
    {
        return books == null ? null : (Book[]) books.clone();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.BookDriver#getDriverName()
     */
    public String getDriverName()
    {
        return "Readings"; //$NON-NLS-1$
    }

    /**
     * Get the singleton instance of this driver.
     * @return this driver instance
     */
    public static BookDriver instance()
    {
        return INSTANCE;
    }

    /**
     * Get a list of the available readings sets
     */
    public String[] getInstalledReadingsSets()
    {
        try
        {
            URL index = ResourceUtil.getResource(ReadingsBookDriver.class, "readings.txt"); //$NON-NLS-1$
            return NetUtil.listByIndexFile(NetUtil.toURI(index), new ReadingsFilter());
        }
        catch (IOException ex)
        {
            return new String[0];
        }
    }

    /**
     * Get all files mentioned by readings.txt
     */
    static final class ReadingsFilter implements URIFilter
    {
        public boolean accept(String name)
        {
            return true;
        }
    }

    /**
     * The meta data array
     */
    private Book[] books;

    /**
     * Resources subdir for readings sets
     */
    public static final String DIR_READINGS = "readings"; //$NON-NLS-1$

    /**
     * A shared instance of this driver.
     */
    private static final BookDriver INSTANCE = new ReadingsBookDriver();
}
