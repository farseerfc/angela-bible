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
 * ID: $Id: BookSet.java 1605 2007-08-03 21:34:46Z dmsmith $
 */
package org.crosswire.jsword.book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.crosswire.common.util.Filter;

/**
 * BookSet represents a collection of descriptions about Books
 * which may be subsetted into other BookMetaDataSets.
 * Each set is naturally ordered.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class BookSet extends ArrayList implements Set
{
    public BookSet()
    {
    }

    public BookSet(Collection books)
    {
        this();
        addAll(books);
    }

    /**
     * Gets the sorted set of all keys which can be used for groupings.
     * These are all the property keys across the BookMetaDatas in this list.
     * @return the set of all keys which can be used for grouping.
     */
    public Set getGroups()
    {
        Set results = new TreeSet();
        Iterator iter = iterator();
        while (iter.hasNext())
        {
            Book book = (Book) iter.next();
            results.addAll(book.getProperties().keySet());
        }
        return results;
    }

    /**
     * Get the sorted set of all values for a particular key.
     * If there is a BookMetaData that does not have a value
     * for that key, then null will be in the set. This can be use
     * to categorize books that don't have that key.
     * For example, "Language" will return all the languages
     * for this BookMetaDataList and null for which the language
     * is unknown.
     * @param key
     * @return the values for a particular key.
     */
    public Set getGroup(String key)
    {
        Set results = new TreeSet();
        Iterator iter = iterator();
        while (iter.hasNext())
        {
            Book book = (Book) iter.next();
            Object property = book.getProperty(key);
            if (property != null)
            {
                results.add(property);
            }
        }
        return results;
    }

    public BookSet filter(String key, Object value)
    {
        return filter(new GroupFilter(key, value));
    }

    /* (non-Javadoc)
     * @see java.util.List#add(int, java.lang.Object)
     */
    public void add(int index, Object element)
    {
        // ignore the requested index
        add(element);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#add(java.lang.Object)
     */
    public final boolean add(Object book)
    {
        // Add the item only if it is not in the list.
        // Add it into the list so that it is in sorted order.
        int pos = Collections.binarySearch(this, book);
        if (pos < 0)
        {
            super.add(-pos - 1, book);
            return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    public final boolean addAll(Collection c)
    {
        // Might be better to add the list to the end
        // and then sort the list.
        // This can be revisited if the list performs badly.
        boolean added = false;
        Iterator iter = c.iterator();
        while (iter.hasNext())
        {
            Book book = (Book) iter.next();
            if (add(book))
            {
                added = true;
            }
        }
        return added;
    }

    /* (non-Javadoc)
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    public final boolean addAll(int index, Collection c)
    {
        // Ignore the index
        return addAll(c);
    }

    /* (non-Javadoc)
     * @see java.util.List#set(int, java.lang.Object)
     */
    public Object set(int index, Object element)
    {
        // remove the item at the index (keep it to return it),
        // then insert the item into the sorted list.
        Book item = (Book) remove(index);
        add(element);
        return item;
    }

    public BookSet filter(Filter filter)
    {
        // create a copy of the list and
        // remove everything that fails the test.
        BookSet listSet = (BookSet) clone();
        Iterator iter = listSet.iterator();
        while (iter.hasNext())
        {
            Book obj = (Book) iter.next();
            if (!filter.test(obj))
            {
                iter.remove();
            }
        }
        return listSet;
    }

    /**
     * GroupFilter does the SQL traditional group by.
     */
    private static final class GroupFilter implements Filter
    {
        public GroupFilter(String aKey, Object aValue)
        {
            key = aKey;
            value = aValue;
        }

        public boolean test(Object obj)
        {
            Book book = (Book) obj;
            Object property = book.getProperty(key);
            return property != null && property.equals(value);
        }
        private String key;
        private Object value;
    }

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3258688806185154867L;

}
