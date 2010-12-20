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
 * ID: $Id: DistinctPassage.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.passage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A Passage that is implemented using a TreeSet of Verses.
 * The attributes of the style are:<ul>
 * <li>Fairly fast manipulation
 * <li>Slow getName()
 * <li>Bloated for storing large numbers of Verses
 * </ul>
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class DistinctPassage extends AbstractPassage
{
    /**
     * Create an empty DistinctPassage. There are no ctors from either
     * Verse or VerseRange so you need to do new
     * <code>DistinctPassage().add(...);</code>
     */
    protected DistinctPassage()
    {
    }

    /**
     * Create a Verse from a human readable string. The opposite
     * of toString(), Given any DistinctPassage v1, and the following
     * <code>DistinctPassage v2 = new DistinctPassage(v1.toString());</code>
     * Then <code>v1.equals(v2);</code>
     * Theoretically, since there are many ways of representing a
     * DistinctPassage as text string comparision along the lines of:
     * <code>v1.toString().equals(v2.toString())</code> could be false.
     * Practically since toString() is standardized this will be true
     * however. We don't need to worry about thread safety in a ctor since
     * we don't exist yet.
     * @param refs A String containing the text of the DistinctPassage
     * @throws NoSuchVerseException If the string is not valid
     */
    protected DistinctPassage(String refs) throws NoSuchVerseException
    {
        super(refs);

        store = Collections.synchronizedSortedSet(new TreeSet());
        addVerses(refs);
    }

    /**
     * Get a copy of ourselves. Points to note:<ul>
     * <li>Call clone() not new() on member Objects, and on us.
     * <li>Do not use Copy Constructors! - they do not inherit well.
     * <li>Think about this needing to be synchronized
     * <li>If this is not cloneable then writing cloneable children is harder
     * </ul>
     * @return A complete copy of ourselves
     */
    public Object clone()
    {
        // This gets us a shallow copy
        DistinctPassage copy = (DistinctPassage) super.clone();

        // I want to just do the following
        //   copy.store = (SortedSet) store.clone();
        // However SortedSet is not Clonable so I can't
        // Watch out for this, I'm not sure if it breaks anything.
        copy.store = new TreeSet();
        copy.store.addAll(store);

        return copy;
    }

    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator iterator()
    {
        return store.iterator();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#isEmpty()
     */
    public boolean isEmpty()
    {
        return store.isEmpty();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#countVerses()
     */
    public int countVerses()
    {
        return store.size();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#contains(org.crosswire.jsword.passage.VerseBase)
     */
    public boolean contains(Key obj)
    {
        Iterator iter = obj.iterator();
        while (iter.hasNext())
        {
            if (!store.contains(iter.next()))
            {
                return false;
            }
        }

        return true;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#add(org.crosswire.jsword.passage.VerseBase)
     */
    public void add(Key obj)
    {
        optimizeWrites();

        Verse firstVerse = null;
        Verse lastVerse = null;
        Iterator iter = obj.iterator();
        while (iter.hasNext())
        {
            lastVerse = (Verse) iter.next();
            if (firstVerse == null)
            {
                firstVerse = lastVerse;
            }
            store.add(lastVerse);
        }

        // we do an extra check here because the cost of calculating the
        // params is non-zero an may be wasted
        if (suppressEvents == 0)
        {
            fireIntervalAdded(this, firstVerse, lastVerse);
        }
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#remove(org.crosswire.jsword.passage.VerseBase)
     */
    public void remove(Key obj)
    {
        optimizeWrites();

        Verse firstVerse = null;
        Verse lastVerse = null;
        Iterator iter = obj.iterator();
        while (iter.hasNext())
        {
            lastVerse = (Verse) iter.next();
            if (firstVerse == null)
            {
                firstVerse = lastVerse;
            }
            store.remove(lastVerse);
        }

        // we do an extra check here because the cost of calculating the
        // params is non-zero an may be wasted
        if (suppressEvents == 0)
        {
            fireIntervalAdded(this, firstVerse, lastVerse);
        }
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#clear()
     */
    public void clear()
    {
        optimizeWrites();

        store.clear();
        fireIntervalRemoved(this, null, null);
    }

    /**
     * Call the support mechanism in AbstractPassage
     * @param out The stream to write our state to
     * @throws IOException if the read fails
     * @serialData Write the ordinal number of this verse
     * @see AbstractPassage#writeObjectSupport(ObjectOutputStream)
     */
    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();

        writeObjectSupport(out);
    }

    /**
     * Call the support mechanism in AbstractPassage
     * @param in The stream to read our state from
     * @throws IOException if the read fails
     * @throws ClassNotFoundException If the read data is incorrect
     * @serialData Write the ordinal number of this verse
     * @see AbstractPassage#readObjectSupport(ObjectInputStream)
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        optimizeWrites();

        store = new TreeSet();

        in.defaultReadObject();

        readObjectSupport(in);
    }

    /**
     * To make serialization work across new versions
     */
    static final long serialVersionUID = 817374460730441662L;

    /**
     * The place the real data is stored
     */
    private transient SortedSet store = new TreeSet();
}
