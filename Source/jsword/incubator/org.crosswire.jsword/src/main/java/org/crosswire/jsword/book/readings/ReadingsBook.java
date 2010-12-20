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
 * ID: $Id: ReadingsBook.java 1605 2007-08-03 21:34:46Z dmsmith $
 */
package org.crosswire.jsword.book.readings;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TreeMap;

import org.crosswire.common.util.CWClassLoader;
import org.crosswire.common.util.Language;
import org.crosswire.common.util.Logger;
import org.crosswire.jsword.book.BookCategory;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.FeatureType;
import org.crosswire.jsword.book.OSISUtil;
import org.crosswire.jsword.book.basic.AbstractBook;
import org.crosswire.jsword.book.basic.DefaultBookMetaData;
import org.crosswire.jsword.passage.DefaultKeyList;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.KeyFactory;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.crosswire.jsword.passage.Passage;
import org.crosswire.jsword.passage.PassageKeyFactory;
import org.crosswire.jsword.passage.PreferredKey;
import org.crosswire.jsword.passage.RestrictionType;
import org.crosswire.jsword.passage.SetKeyList;
import org.crosswire.jsword.passage.VerseRange;
import org.jdom.Element;

/**
 * A Dictionary that displays daily Readings.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class ReadingsBook extends AbstractBook implements PreferredKey
{
    /**
     * Constructor for ReadingsBook.
     */
    public ReadingsBook(ReadingsBookDriver driver, String setname, BookCategory type)
    {
        super(null); // set the book metadata later

        hash = new TreeMap();

        Locale defaultLocale = Locale.getDefault();
        ResourceBundle prop = ResourceBundle.getBundle(setname, defaultLocale, CWClassLoader.instance(ReadingsBookDriver.class));

        String name = Msg.TITLE.toString();
        try
        {
            name = prop.getString("title"); //$NON-NLS-1$
        }
        catch (MissingResourceException e)
        {
            log.warn("Missing resource: 'title' while parsing: " + setname); //$NON-NLS-1$
        }

        DefaultBookMetaData bmd = new DefaultBookMetaData(driver, name, type);
        bmd.setInitials(setname);
        bmd.setLanguage(new Language(Locale.getDefault().getLanguage()));
        setBookMetaData(bmd);

        // Go through the current year
        java.util.Calendar greg = new java.util.GregorianCalendar();
        greg.set(java.util.Calendar.DAY_OF_MONTH, 1);
        greg.set(java.util.Calendar.MONDAY, java.util.Calendar.JANUARY);
        int currentYear = greg.get(java.util.Calendar.YEAR);

        while (greg.get(java.util.Calendar.YEAR) == currentYear)
        {
            String internalKey = ReadingsKey.external2internal(greg);
            String readings = ""; //$NON-NLS-1$

            try
            {
                readings = prop.getString(internalKey);
                hash.put(new ReadingsKey(greg.getTime()), readings);
            }
            catch (MissingResourceException e)
            {
                log.warn("Missing resource: " + internalKey + " while parsing: " + setname); //$NON-NLS-1$ //$NON-NLS-2$
            }

            greg.add(java.util.Calendar.DATE, 1);
        }

        global = new SetKeyList(hash.keySet(), getName());
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.PreferredKey#getPreferred()
     */
    public Key getPreferred()
    {
        return new ReadingsKey(new Date());
    }

    public Iterator getOsisIterator(Key key, boolean allowEmpty) throws BookException
    {
        if (!(key instanceof ReadingsKey))
        {
            throw new BookException(Msg.NOT_FOUND, new Object[] { key.getName() });
        }

        // TODO(DMS): make the iterator be demand driven
        List content = new ArrayList();

        Element title = OSISUtil.factory().createTitle();
        title.addContent(key.getName());
        content.add(title);

        String readings = (String) hash.get(key);
        if (readings == null)
        {
            throw new BookException(Msg.NOT_FOUND, new Object[] { key.getName() });
        }

        try
        {
            KeyFactory keyf = PassageKeyFactory.instance();
            Passage ref = (Passage) keyf.getKey(readings);

            Element list = OSISUtil.factory().createList();
            content.add(list);
            for (Iterator it = ref.rangeIterator(RestrictionType.NONE); it.hasNext(); )
            {
                VerseRange range = (VerseRange) it.next();

                Element reading = OSISUtil.factory().createReference();
                reading.setAttribute(OSISUtil.OSIS_ATTR_REF, range.getOsisRef());
                reading.addContent(range.getName());

                Element item = OSISUtil.factory().createItem();
                item.addContent(reading);
                list.addContent(item);
            }
        }
        catch (NoSuchKeyException ex)
        {
            content.add(OSISUtil.factory().createText(Msg.DECODE_ERROR.toString(readings)));
        }

        return content.iterator();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.Book#getRawText(org.crosswire.jsword.passage.Key)
     */
    public String getRawText(Key key) throws BookException
    {
        return ""; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.Book#isWritable()
     */
    public boolean isWritable()
    {
        return false;
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
    public Key getKey(String name) throws NoSuchKeyException
    {
        DefaultKeyList reply = new DefaultKeyList();
        reply.addAll(new ReadingsKey(name, name, global));
        return reply;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.KeyFactory#getGlobalKeyList()
     */
    public Key getGlobalKeyList()
    {
        return global;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.passage.KeyFactory#getEmptyKeyList()
     */
    public Key createEmptyKeyList()
    {
        return new DefaultKeyList();
    }

    /* @Override */
    public boolean hasFeature(FeatureType feature)
    {
        return feature == FeatureType.DAILY_DEVOTIONS;
    }

    /**
     * The global key list
     */
    private Key global;

    /**
     * The store of keys and data
     */
    private Map hash;

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(ReadingsBook.class);
}
