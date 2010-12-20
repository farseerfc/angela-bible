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
 * ID: $Id: SynchronizedPassage.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.passage;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;

/**
 * This is a simple proxy to a real Passage object that makes all accesses
 * synchronized. It is final to give the VM as much hope as possible at
 * being able to inline stuff.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
final class SynchronizedPassage implements Passage
{
    /**
     * Construct a SynchronizedPassage from a real Passage to which we proxy.
     * @param ref The real Passage
     */
    protected SynchronizedPassage(Passage ref)
    {
        this.ref = ref;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#add(org.crosswire.jsword.passage.Key)
     */
    public synchronized void addAll(Key key)
    {
        ref.addAll(key);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#remove(org.crosswire.jsword.passage.Key)
     */
    public synchronized void removeAll(Key key)
    {
        ref.removeAll(key);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#retain(org.crosswire.jsword.passage.Key)
     */
    public synchronized void retainAll(Key key)
    {
        ref.retainAll(key);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#contains(org.crosswire.jsword.passage.Key)
     */
    public synchronized boolean contains(Key key)
    {
        return ref.contains(key);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#getChildCount()
     */
    public synchronized int getChildCount()
    {
        return ref.getChildCount();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#getCardinality()
     */
    public synchronized int getCardinality()
    {
        return ref.getCardinality();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#canHaveChildren()
     */
    public synchronized boolean canHaveChildren()
    {
        return ref.canHaveChildren();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#iterator()
     */
    public synchronized Iterator iterator()
    {
        return ref.iterator();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#get(int)
     */
    public synchronized Key get(int index)
    {
        return ref.get(index);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#indexOf(org.crosswire.jsword.passage.Key)
     */
    public synchronized int indexOf(Key that)
    {
        return ref.indexOf(that);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#getParent()
     */
    public synchronized Key getParent()
    {
        return ref.getParent();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#getName()
     */
    public synchronized String getName()
    {
        return ref.getName();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#getName(org.crosswire.jsword.passage.Key)
     */
    public synchronized String getName(Key base)
    {
        return ref.getName(base);
    }

    public synchronized String getRootName()
    {
        return ref.getRootName();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#getOSISName()
     */
    public synchronized String getOsisRef()
    {
        return ref.getOsisRef();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#getOSISId()
     */
    public synchronized String getOsisID()
    {
        return ref.getOsisID();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#getOverview()
     */
    public synchronized String getOverview()
    {
        return ref.getOverview();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#isEmpty()
     */
    public synchronized boolean isEmpty()
    {
        return ref.isEmpty();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#countVerses()
     */
    public synchronized int countVerses()
    {
        return ref.countVerses();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#hasRanges(org.crosswire.jsword.passage.RestrictionType)
     */
    public synchronized boolean hasRanges(RestrictionType restrict)
    {
        return ref.hasRanges(restrict);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#countRanges(org.crosswire.jsword.passage.RestrictionType)
     */
    public synchronized int countRanges(RestrictionType restrict)
    {
        return ref.countRanges(restrict);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#trimVerses(int)
     */
    public synchronized Passage trimVerses(int count)
    {
        return ref.trimVerses(count);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#trimRanges(int, org.crosswire.jsword.passage.RestrictionType)
     */
    public synchronized Passage trimRanges(int count, RestrictionType restrict)
    {
        return ref.trimRanges(count, restrict);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#booksInPassage()
     */
    public synchronized int booksInPassage()
    {
        return ref.booksInPassage();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#chaptersInPassage(int)
     */
    public synchronized int chaptersInPassage(int book) throws NoSuchVerseException
    {
        return ref.chaptersInPassage(book);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#versesInPassage(int, int)
     */
    public synchronized int versesInPassage(int book, int chapter) throws NoSuchVerseException
    {
        return ref.versesInPassage(book, chapter);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#getVerseAt(int)
     */
    public synchronized Verse getVerseAt(int offset) throws ArrayIndexOutOfBoundsException
    {
        return ref.getVerseAt(offset);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#getVerseRangeAt(int, int)
     */
    public synchronized VerseRange getRangeAt(int offset, RestrictionType restrict) throws ArrayIndexOutOfBoundsException
    {
        return ref.getRangeAt(offset, restrict);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#rangeIterator(int)
     */
    public synchronized Iterator rangeIterator(RestrictionType restrict)
    {
        return ref.rangeIterator(restrict);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#add(org.crosswire.jsword.passage.VerseBase)
     */
    public synchronized void add(Key that)
    {
        ref.add(that);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#remove(org.crosswire.jsword.passage.VerseBase)
     */
    public synchronized void remove(Key that)
    {
        ref.remove(that);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#containsAll(org.crosswire.jsword.passage.Passage)
     */
    public synchronized boolean containsAll(Passage that)
    {
        return ref.containsAll(that);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#clear()
     */
    public synchronized void clear()
    {
        ref.clear();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Key#blur(int)
     */
    public synchronized void blur(int by, RestrictionType restrict)
    {
        ref.blur(by, restrict);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#readDescription(java.io.Reader)
     */
    public synchronized void readDescription(Reader in) throws IOException, NoSuchVerseException
    {
        ref.readDescription(in);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#writeDescription(java.io.Writer)
     */
    public synchronized void writeDescription(Writer out) throws IOException
    {
        ref.writeDescription(out);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#optimizeReads()
     */
    public synchronized void optimizeReads()
    {
        ref.optimizeReads();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#addPassageListener(org.crosswire.jsword.passage.PassageListener)
     */
    public synchronized void addPassageListener(PassageListener li)
    {
        ref.addPassageListener(li);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.Passage#removePassageListener(org.crosswire.jsword.passage.PassageListener)
     */
    public synchronized void removePassageListener(PassageListener li)
    {
        ref.removePassageListener(li);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    /* @Override */
    public synchronized Object clone()
    {
        SynchronizedPassage clone = null;
        try
        {
            clone = (SynchronizedPassage) super.clone();
            clone.ref = (Passage) ref.clone();
        }
        catch (CloneNotSupportedException e)
        {
            assert false : e;
        }
        return clone;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public synchronized int hashCode()
    {
        return ref.hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(Object obj)
    {
        return ref.equals(obj);
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public synchronized int compareTo(Object o)
    {
        return ref.compareTo(o);
    }

    /**
     * The object we are proxying to
     */
    private Passage ref;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3833181441264531251L;
}
