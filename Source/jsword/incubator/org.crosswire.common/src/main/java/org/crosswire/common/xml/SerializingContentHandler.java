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
 * ID: $Id: SerializingContentHandler.java 1607 2007-08-04 17:13:24Z dmsmith $
 */
package org.crosswire.common.xml;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;

/**
 * Class to convert a SAX stream into a simple String.
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class SerializingContentHandler implements ContentHandler
{
    /**
     * Default ctor that does not insert newlines.
     */
    public SerializingContentHandler()
    {
        newlines = false;
    }

    /**
     * Default ctor that does not insert newlines.
     */
    public SerializingContentHandler(boolean newlines)
    {
        this.newlines = newlines;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /* @Override */
    public String toString()
    {
        return buffer.toString();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
     */
    public void setDocumentLocator(Locator locator)
    {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument()
    {
        //buffer.append("<?xml version=\"1.0\"?>");

        if (newlines)
        {
            buffer.append('\n');
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    public void endDocument()
    {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
     */
    public void startPrefixMapping(String prefix, String uri)
    {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
     */
    public void endPrefixMapping(String prefix)
    {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localname, String qname, Attributes attrs)
    {
        buffer.append('<');
        if (qname != null)
        {
            buffer.append(qname);
        }
        else
        {
            buffer.append(localname);
        }

        for (int i = 0; i < attrs.getLength(); i++)
        {
            buffer.append(' ');
            buffer.append(XMLUtil.getAttributeName(attrs, i));
            buffer.append("=\""); //$NON-NLS-1$
            buffer.append(attrs.getValue(i));
            buffer.append('\"');
        }

        buffer.append('>');

        if (newlines)
        {
            buffer.append('\n');
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localname, String qname)
    {
        buffer.append("</"); //$NON-NLS-1$
        if (qname != null)
        {
            buffer.append(qname);
        }
        else
        {
            buffer.append(localname);
        }

        buffer.append('>');

        if (newlines)
        {
            buffer.append('\n');
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    public void characters(char[] chars, int start, int length)
    {
        String s = new String(chars, start, length);
        buffer.append(s);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
     */
    public void ignorableWhitespace(char[] chars, int start, int length)
    {
        String s = new String(chars, start, length);
        buffer.append(s);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
     */
    public void processingInstruction(String target, String data)
    {
        buffer.append("<!"); //$NON-NLS-1$
        buffer.append(target);
        buffer.append(' ');
        buffer.append(data);
        buffer.append("!>"); //$NON-NLS-1$

        if (newlines)
        {
            buffer.append('\n');
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    public void skippedEntity(String name)
    {
    }

    private boolean newlines;
    private StringBuffer buffer = new StringBuffer();
}
