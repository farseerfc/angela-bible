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
 * ID: $Id: OSISFilter.java 2050 2010-12-09 15:31:45Z dmsmith $
 */
package org.crosswire.jsword.book.filter.osis;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.crosswire.common.util.Logger;
import org.crosswire.common.xml.XMLUtil;
import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.DataPolice;
import org.crosswire.jsword.book.OSISUtil;
import org.crosswire.jsword.book.filter.Filter;
import org.crosswire.jsword.passage.Key;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * Filter to convert an OSIS XML string to OSIS format.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class OSISFilter implements Filter {
    /**
     * Default constructor of an OSISFilter
     */
    public OSISFilter() {
        builder = new SAXBuilder();
        builder.setFastReconfigure(true);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.filter.Filter#toOSIS(org.crosswire.jsword.book.Book, org.crosswire.jsword.passage.Key, java.lang.String)
     */
    public List<Content> toOSIS(Book book, Key key, String plain) {
        DataPolice.setKey(key);
        Element ele = null;
        Exception ex = null;
        String clean = plain;

        // FIXME(dms): this is a major HACK handling a problem with a badly
        // encoded module.
        if (book.getInitials().startsWith("NET") && plain.endsWith("</div>")) {
            clean = clean.substring(0, plain.length() - 6);
        }

        try {
            ele = parse(clean);
        } catch (JDOMException e) {
            ex = e;
        } catch (IOException e) {
            ex = e;
        }

        if (ele == null) {
            clean = XMLUtil.cleanAllEntities(clean);

            try {
                ele = parse(clean);
            } catch (JDOMException e) {
                ex = e;
            } catch (IOException e) {
                ex = e;
            } finally {
                // Make sure that other places don't report this problem
                DataPolice.setKey(null);
            }
        }

        if (ex != null) {
            DataPolice.report("Parse " + book.getInitials() + "(" + key.getName() + ") failed: " + ex.getMessage() + "\non: " + plain);
            ele = cleanTags(book, key, clean);
        }

        if (ele == null) {
            ele = OSISUtil.factory().createP();
        }

        return ele.removeContent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            assert false : e;
        }
        return null;
    }

    private Element cleanTags(Book book, Key key, String plain) {
        // So just try to strip out all XML looking things
        String shawn = XMLUtil.cleanAllTags(plain);
        Exception ex = null;
        try {
            return parse(shawn);
        } catch (JDOMException e) {
            ex = e;
        } catch (IOException e) {
            ex = e;
        }

        log.warn("Could not fix " + book.getInitials() + "(" + key.getName() + ")  by cleaning tags: " + ex.getMessage());

        return null;
    }

    /**
     * If the string is invalid then we might want to have more than one crack
     * at parsing it
     */
    private Element parse(String plain) throws JDOMException, IOException {
        // create a root element to house our document fragment
        StringReader in = new StringReader("<div>" + plain + "</div>");
        InputSource is = new InputSource(in);
        Document doc = builder.build(is);
        Element div = doc.getRootElement();

        return div;
    }

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(OSISFilter.class);

    /**
     * A reusable SAX Builder
     */
    private SAXBuilder builder;
}
