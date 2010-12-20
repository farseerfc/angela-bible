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
 * ID: $Id: FontTag.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.book.filter.thml;

import org.crosswire.common.xml.XMLUtil;
import org.crosswire.jsword.book.DataPolice;
import org.crosswire.jsword.book.OSISUtil;
import org.jdom.Element;
import org.xml.sax.Attributes;

/**
 * THML Tag to process the font element.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class FontTag extends AbstractTag
{
    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.filter.thml.Tag#getTagName()
     */
    public String getTagName()
    {
        return "font"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.filter.thml.Tag#processTag(org.jdom.Element, org.xml.sax.Attributes)
     */
    /* @Override */
    public Element processTag(Element ele, Attributes attrs)
    {
        Element seg = OSISUtil.factory().createSeg();
        StringBuffer buf = new StringBuffer();

        String color = attrs.getValue("color"); //$NON-NLS-1$
        if (color != null)
        {
            buf.append(OSISUtil.SEG_COLORPREFIX);
            buf.append(color);
            buf.append(';');
        }

        String size = attrs.getValue("size"); //$NON-NLS-1$
        if (size != null)
        {
            buf.append(OSISUtil.SEG_SIZEPREFIX);
            buf.append(size);
            buf.append(';');
        }

        String type = buf.toString();
        if (type.length() > 0)
        {
            seg.setAttribute(OSISUtil.OSIS_ATTR_TYPE, type);
        }
        else
        {
            DataPolice.report("Missing color/size attribute."); //$NON-NLS-1$
            XMLUtil.debugSAXAttributes(attrs);
        }

        if (ele != null)
        {
            ele.addContent(seg);
        }

        return seg;
    }
}
