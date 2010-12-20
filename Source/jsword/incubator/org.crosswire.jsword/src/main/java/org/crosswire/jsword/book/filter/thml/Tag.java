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
 * ID: $Id: Tag.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.book.filter.thml;

import org.jdom.Element;
import org.xml.sax.Attributes;

/**
 * THML Tag interface - there should be one implementation of this class for
 * each THML tag.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public interface Tag
{
    /**
     * What element does this class represent.
     * For example the Tag that represents the &gtfont ...> element would return
     * the string "font".
     */
    String getTagName();

    /**
     * Make changes to the specified OSIS element given the attributes passed
     * in the source document.
     * @param ele The OSIS element to use as a parent
     * @param attrs The source document attributes.
     * @return the element to which content is attached
     */
    Element processTag(Element ele, Attributes attrs);

    /**
     * Do additional processing of the tag after the element has been created.
     * @param ele the created element to process
     */
    void processContent(Element ele);
}
