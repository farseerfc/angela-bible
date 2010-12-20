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
 * ID: $Id: FontField.java 1966 2009-10-30 01:15:14Z dmsmith $
 */
package org.crosswire.common.config.swing;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import org.crosswire.common.config.Choice;
import org.crosswire.common.swing.FontChooser;
import org.crosswire.common.swing.GuiConvert;
import org.crosswire.common.swing.GuiUtil;

/**
 * A swing view of a FontChoice.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class FontField extends FontChooser implements Field {
    /**
     *
     */
    public FontField() {
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        GuiUtil.applyDefaultOrientation(this);

    }

    /**
     * Some fields will need some extra info to display properly like the
     * options in an options field. FieldMap calls this method with options
     * provided by the choice.
     * 
     * @param param
     *            The options provided by the Choice
     */
    public void setChoice(Choice param) {
    }

    /**
     * Return a string version of the current value
     * 
     * @return The current value
     */
    public String getValue() {
        return GuiConvert.font2String(getStyle());
    }

    /**
     * Set the current value
     * 
     * @param value
     *            The new text
     */
    public void setValue(String value) {
        setStyle(GuiConvert.string2Font(value));
    }

    /**
     * Get the actual component that we can add to a Panel. (This can well be
     * this in an implementation).
     */
    public JComponent getComponent() {
        return this;
    }

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3258411729237848625L;
}
