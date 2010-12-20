/**
 * Distribution License:
 * BibleDesktop is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2 as published by
 * the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/gpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2005
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: BibleNameCellRenderer.java 2050 2010-12-09 15:31:45Z dmsmith $
 */
package org.crosswire.bibledesktop.book;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import org.crosswire.common.swing.GuiUtil;
import org.crosswire.jsword.versification.BookName;

/**
 * Render a list of Bible Book names with their full name as a tooltip
 * 
 * @see gnu.gpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class BibleNameCellRenderer extends DefaultListCellRenderer {
    /**
     * Constructs a default renderer object for an item in a list, using full
     * names.
     */
    public BibleNameCellRenderer() {
        this(false);
    }

    /**
     * Constructs a renderer object for an item in a list, using abbreviated
     * names if desired.
     * 
     * @param abbreviated
     *            use the initials in the list.
     */
    public BibleNameCellRenderer(boolean abbreviated) {
        super();
        this.abbreviated = abbreviated;
        GuiUtil.applyDefaultOrientation(this);
    }

    /**
     * @return the abbreviated
     */
    public boolean isAbbreviated() {
        return abbreviated;
    }

    /**
     * @param newAbbreviated
     *            the abbreviated to set
     */
    public void setAbbreviated(boolean newAbbreviated) {
        this.abbreviated = newAbbreviated;
    }

    /* (non-Javadoc)
     * @see javax.swing.DefaultListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean focus) {
        // Do the default rendering
        Component comp = super.getListCellRendererComponent(list, value, index, selected, focus);

        // Do our rendering
        setToolTipText(null);

        if (value == null) {
            // TRANSLATOR: This is the replacement text for a blank book name in a list.
            setText(Msg.gettext("None"));
            setEnabled(false);
        }

        // Hack to allow us to use PROTOTYPE_BOOK_NAME as a prototype value
        if (value instanceof BookName) {
            BookName bookName = (BookName) value;
            String name = bookName.getLongName();

            setText(abbreviated ? bookName.getShortName() : name);
            setToolTipText(name);
        }

        return comp;
    }

    /**
     * If true then the initials of a book are shown, otherwise the full name.
     */
    private boolean abbreviated;

    /**
     * Make sure that book names are not too wide
     */
    public static final String PROTOTYPE_BOOK_NAME = "0123456789";

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3978138859576308017L;
}
