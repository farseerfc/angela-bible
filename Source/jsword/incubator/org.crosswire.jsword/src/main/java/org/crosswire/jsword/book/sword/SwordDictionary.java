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
 * ID: $Id: SwordDictionary.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.book.sword;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.crosswire.common.activate.Activator;
import org.crosswire.common.activate.Lock;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.OSISUtil;
import org.crosswire.jsword.book.basic.AbstractBook;
import org.crosswire.jsword.book.filter.FilterException;
import org.crosswire.jsword.passage.DefaultKeyList;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.crosswire.jsword.passage.ReadOnlyKeyList;
import org.jdom.Element;

/**
 * A Sword version of Dictionary.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class SwordDictionary extends AbstractBook
{
    /**
     * Start and to as much checking as we can without using memory.
     * (i.e. actually reading the indexes)
     */
    protected SwordDictionary(SwordBookMetaData sbmd, AbstractBackend backend)
    {
        super(sbmd);

        this.sbmd = sbmd;
        this.backend = backend;
        map = null;
        set = null;
        global = null;
        active = false;
    }

    /* (non-Javadoc)
     * @see org.crosswire.common.activate.Activatable#activate(org.crosswire.common.activate.Lock)
     */
    /* @Override */
    public final void activate(Lock lock)
    {
        super.activate(lock);

        set = backend.readIndex();

        map = new HashMap();
        Iterator iter = set.iterator();
        while (iter.hasNext())
        {
            Key key = (Key) iter.next();
            map.put(key.getName(), key);
        }

        global = new ReadOnlyKeyList(set, false);

        active = true;

        // We don't need to activate the backend because it should be capable
        // of doing it for itself.
    }

    /* (non-Javadoc)
     * @see org.crosswire.common.activate.Activatable#deactivate(org.crosswire.common.activate.Lock)
     */
    /* @Override */
    public final void deactivate(Lock lock)
    {
        super.deactivate(lock);

        map = null;
        set = null;
        global = null;

        Activator.deactivate(backend);

        active = false;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.Book#getOsisIterator(org.crosswire.jsword.passage.Key, boolean)
     */
    public Iterator getOsisIterator(Key key, boolean allowEmpty) throws BookException
    {
        checkActive();

        assert key != null;
        assert backend != null;

        try
        {
            List content = new ArrayList();
            Element title = OSISUtil.factory().createTitle();
            title.addContent(key.getName());
            content.add(title);

            String txt = backend.getRawText(key);

            List osisContent = sbmd.getFilter().toOSIS(this, key, txt);
            content.addAll(osisContent);

            return content.iterator();
        }
        catch (FilterException ex)
        {
            throw new BookException(Msg.FILTER_FAIL, ex);
        }
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.Book#getRawText(org.crosswire.jsword.passage.Key)
     */
    public String getRawText(Key key) throws BookException
    {
        checkActive();

        assert key != null;
        assert backend != null;

        return backend.getRawText(key);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.Book#isWritable()
     */
    public boolean isWritable()
    {
        return backend.isWritable();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.basic.AbstractPassageBook#setRawText(org.crosswire.jsword.passage.Key, java.lang.String)
     */
    public void setRawText(Key key, String rawData) throws BookException
    {
        throw new BookException(Msg.DRIVER_READONLY);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.Book#setAliasKey(org.crosswire.jsword.passage.Key, org.crosswire.jsword.passage.Key)
     */
    public void setAliasKey(Key alias, Key source) throws BookException
    {
        throw new BookException(Msg.DRIVER_READONLY);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.KeyFactory#getGlobalKeyList()
     */
    public Key getGlobalKeyList()
    {
        checkActive();

        return global;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.KeyFactory#isValidKey(java.lang.String)
     */
    public Key getValidKey(String name)
    {
        try
        {
            return getKey(name);
        }
        catch (NoSuchKeyException e)
        {
            return createEmptyKeyList();
        }
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.KeyFactory#getKey(java.lang.String)
     */
    public Key getKey(String text) throws NoSuchKeyException
    {
        checkActive();

        Key key = (Key) map.get(text);
        if (key != null)
        {
            return key;
        }

        // So we need to find a matching key.
        // TODO(DM): This is a hack.
        key = getStrongsKey(text);

        if (key != null)
        {
            return key;
        }

        // First check for keys that match ignoring case
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext())
        {
            String keyName = (String) iter.next();
            if (keyName.equalsIgnoreCase(text))
            {
                return (Key) map.get(keyName);
            }
        }

        // Next keys that start with the given text
        iter = map.keySet().iterator();
        while (iter.hasNext())
        {
            String keyName = (String) iter.next();
            if (keyName.startsWith(text))
            {
                return (Key) map.get(keyName);
            }
        }

        // Next try keys that contain the given text
        iter = map.keySet().iterator();
        while (iter.hasNext())
        {
            String keyName = (String) iter.next();
            if (keyName.indexOf(text) != -1)
            {
                return (Key) map.get(keyName);
            }
        }

        throw new NoSuchKeyException(Msg.NO_KEY, new Object[] { text, getInitials() });
    }

    // TODO(DM): Hack alert!!! This is not in the right place!!!
    private Key getStrongsKey(String txt)
    {
        String text = txt;
        // Is the string all digits?
        Matcher m = STRONGS_PATTERN.matcher(text);
        if (!m.matches())
        {
            return null;
        }

        // Hack alert!!! NASB has trailing letters!!!!
        int pos = text.length() - 1;
        if (Character.isLetter(text.charAt(pos)))
        {
            text = text.substring(0, pos);
        }
        // Get the number after the G or H
        int strongsNumber = Integer.parseInt(text.substring(1));

        Key key = null;
        String internalName = sbmd.getInitials();
        if ("StrongsGreek".equals(internalName)) //$NON-NLS-1$
        {
            key = (Key) map.get(ZERO_PAD.format(strongsNumber));
        }
        else if ("StrongsHebrew".equals(internalName)) //$NON-NLS-1$
        {
            key = (Key) map.get(ZERO_PAD.format(strongsNumber));
        }
        return key;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.KeyFactory#getEmptyKeyList()
     */
    public Key createEmptyKeyList()
    {
        return new DefaultKeyList();
    }

    /**
     * Helper method so we can quickly activate ourselves on access
     */
    private void checkActive()
    {
        if (!active)
        {
            Activator.activate(this);
        }
    }

    // This should move along with getStrongsKey
    private static final Pattern STRONGS_PATTERN = Pattern.compile("^[GH]\\d+[a-z]?$"); //$NON-NLS-1$
    private static final DecimalFormat ZERO_PAD = new DecimalFormat("00000"); //$NON-NLS-1$

    /**
     * The global key list
     */
    private Key global;

    /**
     * Are we active
     */
    private boolean active;

    /**
     * So we can quickly find a Key given the text for the key
     */
    private Map map;

    /**
     * So we can implement getIndex() easily
     */
    private Key set;

    /**
     * To read the data from the disk
     */
    private AbstractBackend backend;

    /**
     * The Sword configuration file
     */
    private SwordBookMetaData sbmd;
}
