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
 * ID: $Id: ConfigEntry.java 1605 2007-08-03 21:34:46Z dmsmith $
 */
package org.crosswire.jsword.book.sword;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crosswire.common.util.Histogram;
import org.crosswire.common.util.Logger;
import org.crosswire.common.util.StringUtil;
import org.crosswire.common.xml.XMLUtil;
import org.crosswire.jsword.book.OSISUtil;
import org.jdom.Element;

/**
 * A ConfigEntry holds the value(s) for an entry of ConfigEntryType.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @see gnu.lgpl.License
 * @author DM Smith [ dmsmith555 at yahoo dot com]
 */
public final class ConfigEntry
{

    /**
     * Create a ConfigEntry whose type is not certain
     * and whose value is not known.
     * @param bookName the internal name of the book
     * @param aName the name of the ConfigEntry.
     */
    public ConfigEntry(String bookName, String aName)
    {
        internal = bookName;
        name = aName;
        type = ConfigEntryType.fromString(aName);
    }

    /**
     * Create a ConfigEntry directly with an initial value.
     * @param bookName the internal name of the book
     * @param aType the kind of ConfigEntry
     * @param aValue the initial value for the ConfigEntry
     */
    public ConfigEntry(String bookName, ConfigEntryType aType, String aValue)
    {
        internal = bookName;
        name = aType.getName();
        type = aType;
        addValue(aValue);
    }

    /**
     * Get the key of this ConfigEntry
     */
    public String getName()
    {
        if (type != null)
        {
            return type.getName();
        }
        return name;
    }

    /**
     * Get the type of this ConfigEntry
     */
    public ConfigEntryType getType()
    {
        return type;
    }

    /**
     * Determines whether the string is allowed. For some config entries,
     * the value is expected to be one of a group, for others the format is defined.
     *
     * @param aValue
     * @return true if the string is allowed
     */
    public boolean isAllowed(String aValue)
    {
        if (type != null)
        {
            return type.isAllowed(aValue);
        }
        return true;
    }

    /**
     * RTF is allowed in a few config entries.
     * @return true if rtf is allowed
     */
    public boolean allowsRTF()
    {
        if (type != null)
        {
            return type.allowsRTF();
        }
        return true;
    }

    /**
     * While most fields are single line or single value, some allow continuation.
     * A continuation mark is a backslash at the end of a line. It is not to be followed by whitespace.
     * @return true if continuation is allowed
     */
    public boolean allowsContinuation()
    {
        if (type != null)
        {
            return type.allowsContinuation();
        }
        return true;
    }

    /**
     * Some keys can repeat. When this happens each is a single value pick from a list of choices.
     * @return true if this ConfigEntryType can occur more than once
     */
    public boolean mayRepeat()
    {
        if (type != null)
        {
            return type.mayRepeat();
        }
        return true;
    }

    /**
     *
     */
    public boolean reportDetails()
    {
        if (type != null)
        {
            return type.reportDetails();
        }
        return true;
    }
    /**
     * Determine whether this config entry is supported.
     * @return true if this ConfigEntry has a type.
     */
    public boolean isSupported()
    {
        return type != null;
    }

    /**
     * Get the value(s) of this ConfigEntry.
     * If mayRepeat() == true then it returns a List.
     * Otherwise it returns a string.
     * @return a list, value or null.
     */
    public Object getValue()
    {
        if (value != null)
        {
            return value;
        }
        if (values != null)
        {
            return values;
        }
        return type.getDefault();
    }

    /**
     * Determine whether this Config entry matches the value.
     *
     * @param search the value to match against
     * @return true if this ConfigEntry matches the value
     */
    public boolean match(Object search)
    {
        if (value != null)
        {
            return value.equals(search);
        }
        if (values != null)
        {
            return values.contains(search);
        }
        Object def = type.getDefault();
        return def != null && def.equals(search);
    }
    /**
     * Add a value to the list of values for this ConfigEntry
     */
    public void addValue(String val)
    {
        String aValue = val;
        String confEntryName = getName();
        // Filter known types of entries
        if (type != null)
        {
            aValue = type.filter(aValue);
        }

        // call handleRTF for error reporting, but don't use the result
        handleRTF(aValue);

        if (mayRepeat())
        {
            if (values == null)
            {
                histogram.increment(confEntryName);
                values = new ArrayList();
            }
            if (reportDetails())
            {
                histogram.increment(confEntryName + '.' + aValue);
            }
            if (!isAllowed(aValue))
            {
                log.info(report("Ignoring unknown config value for", confEntryName, aValue)); //$NON-NLS-1$
                return;
            }
            values.add(aValue);
        }
        else
        {
            if (value != null)
            {
                log.info(report("Ignoring unexpected additional entry for", confEntryName, aValue)); //$NON-NLS-1$
            }
            else
            {
                histogram.increment(confEntryName);
                if (type.hasChoices())
                {
                    histogram.increment(confEntryName + '.' + aValue);
                }
                if (!isAllowed(aValue))
                {
                    log.info(report("Ignoring unknown config value for", confEntryName, aValue)); //$NON-NLS-1$
                    return;
                }
                value = type.convert(aValue);
            }
        }
    }

    public Element toOSIS()
    {
        OSISUtil.OSISFactory factory = OSISUtil.factory();

        Element rowEle = factory.createRow();

        Element nameEle = factory.createCell();
        Element hiEle = factory.createHI();
        hiEle.setAttribute("rend", "bold"); //$NON-NLS-1$ //$NON-NLS-2$
        nameEle.addContent(hiEle);
        Element valueElement = factory.createCell();
        rowEle.addContent(nameEle);
        rowEle.addContent(valueElement);

        // I18N(DMS): use name to lookup translation.
        hiEle.addContent(getName());

        if (value != null)
        {
            String text = value.toString();
            if (allowsRTF())
            {
                text = handleRTF(text);
            }

            String expandedValue = XMLUtil.escape(text);
            if (allowsContinuation() || allowsRTF())
            {
                valueElement.addContent(processLines(factory, expandedValue));
            }
            else
            {
                valueElement.addContent(expandedValue);
            }
        }

        if (values != null)
        {
            Element listEle = factory.createLG();
            valueElement.addContent(listEle);

            Iterator iter = values.iterator();
            while (iter.hasNext())
            {
                String text = (String) iter.next();
                if (allowsRTF())
                {
                    text = handleRTF(text);
                }
                Element itemEle = factory.createL();
                listEle.addContent(itemEle);
                itemEle.addContent(XMLUtil.escape(text));
            }
        }
        return rowEle;
    }

    public static void resetStatistics()
    {
        histogram.clear();
    }

    public static void dumpStatistics()
    {
        // Uncomment the following line to produce statistics
        //System.out.println(histogram.toString());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    /* @Override */
    public boolean equals(Object obj)
    {
        // Since this can not be null
        if (obj == null)
        {
            return false;
        }

        // Check that that is the same as this
        // Don't use instanceOf since that breaks inheritance
        if (!obj.getClass().equals(this.getClass()))
        {
            return false;
        }

        ConfigEntry that = (ConfigEntry) obj;
        return that.getName().equals(this.getName());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    /* @Override */
    public int hashCode()
    {
        return getName().hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /* @Override */
    public String toString()
    {
        return getName();
    }

    /**
     * Build's a SWORD conf file as a string. The result is not identical
     * to the original, cleaning up problems in the original and re-arranging
     * the entries into a predictable order.
     * @return the well-formed conf.
     */
    public String toConf()
    {
        StringBuffer buf = new StringBuffer();

        if (value != null)
        {
            buf.append(getName());
            buf.append('=');
            if (allowsContinuation())
            {
                String text = value.toString();
                String [] lines = StringUtil.splitAll(text, '\n');
                for (int i = 0; i < lines.length; i++)
                {
                    if (i > 0)
                    {
                        buf.append("\\\n"); //$NON-NLS-1$
                    }
                    buf.append(lines[i]);
                }
                buf.append('\n');
            }
            else
            {
                buf.append(value.toString());
                buf.append('\n');
            }
        }
        // CipherKey is empty to indicate that it is encrypted and locked.
        else if (type.equals(ConfigEntryType.CIPHER_KEY))
        {
            buf.append(getName());
            buf.append('=');
        }


        if (values != null)
        {
            // History values begin with the history value, e.g. 1.2
            // followed by a space.
            // These are to joined to the key.
            if (type.equals(ConfigEntryType.HISTORY))
            {
                Iterator iter = values.iterator();
                while (iter.hasNext())
                {
                    String text = (String) iter.next();
                    buf.append(getName());
                    buf.append('_');
                    buf.append(text.replaceFirst(" ", "=")); //$NON-NLS-1$ //$NON-NLS-2$;
                    buf.append('\n');
                }
            }
            else
            {
                Iterator iter = values.iterator();
                while (iter.hasNext())
                {
                    String text = (String) iter.next();
                    buf.append(getName());
                    buf.append('=');
                    buf.append(text);
                    buf.append('\n');
                }
            }
        }
        return buf.toString();
    }

    private String handleRTF(String aValue)
    {
        String copy = aValue;
        // This method is a hack! It could be made much nicer.

        // strip \pard
        copy = copy.replaceAll("\\\\pard ?", ""); //$NON-NLS-1$ //$NON-NLS-2$

        // replace rtf newlines
        copy = copy.replaceAll("\\\\pa[er] ?", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

        // strip whatever \qc is.
        copy = copy.replaceAll("\\\\qc ?", ""); //$NON-NLS-1$ //$NON-NLS-2$

        // strip bold and italic
        copy = copy.replaceAll("\\\\[bi]0? ?", ""); //$NON-NLS-1$ //$NON-NLS-2$

        // strip unicode characters
        copy = copy.replaceAll("\\\\u-?[0-9]{4,6}+\\?", ""); //$NON-NLS-1$ //$NON-NLS-2$

        // strip { and } which are found in {\i text }
        copy = copy.replaceAll("[{}]", ""); //$NON-NLS-1$ //$NON-NLS-2$

        // This check is here rather than at the top, so we can find the problems
        // and fix the source.
        if (!allowsRTF())
        {
            if (!copy.equals(aValue))
            {
                log.info(report("Ignoring unexpected RTF for", getName(), aValue)); //$NON-NLS-1$
            }
            return aValue;
        }

        return copy;
    }

    private List processLines(OSISUtil.OSISFactory factory, String aValue)
    {
        List list = new ArrayList();
        String [] lines = StringUtil.splitAll(aValue, '\n');
        for (int i = 0; i < lines.length; i++)
        {
            Element lineElement = factory.createL();
            lineElement.addContent(lines[i]);
            list.add(lineElement);
        }
        return list;
    }

    private String report(String issue, String confEntryName, String aValue)
    {
        StringBuffer buf = new StringBuffer(100);
        buf.append(issue);
        buf.append(' ');
        buf.append(confEntryName);
        buf.append(" in "); //$NON-NLS-1$
        buf.append(internal);
        buf.append(": "); //$NON-NLS-1$
        buf.append(aValue);

        return buf.toString();
    }

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(ConfigEntry.class);
    /**
     * A histogram for debugging.
     */
    private static Histogram histogram = new Histogram();

    private ConfigEntryType type;
    private String internal;
    private String name;
    private List values;
    private Object value;
}
