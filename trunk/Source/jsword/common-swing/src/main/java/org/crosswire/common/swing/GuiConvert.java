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
 * ID: $Id: GuiConvert.java 1996 2010-10-23 20:40:54Z dmsmith $
 */
package org.crosswire.common.swing;

import java.awt.Color;
import java.awt.Font;

import org.crosswire.common.util.Logger;
import org.crosswire.common.util.StringUtil;

/**
 * Conversions between various types and Strings.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public final class GuiConvert {
    /**
     * We don't want anyone doing this ...
     */
    private GuiConvert() {
    }

    /**
     * Convert a String to a Font. Accepts one of two inputs:
     * FamilyName-STYLE-size, where STYLE is either PLAIN, BOLD, ITALIC or
     * BOLDITALIC<br/>
     * or<br/>
     * FamilyName,style,size, where STYLE is 0 for PLAIN, 1 for BOLD, 2 for
     * ITALIC or 3 for BOLDITALIC.
     * 
     * @param value
     *            the thing to convert
     * @return the converted data
     */
    public static Font string2Font(String value) {
        if (value == null || value.equals("")) {
            return null;
        }

        // new way
        if (value.indexOf(',') == -1) {
            return Font.decode(value);
        }

        // old way
        String[] values = StringUtil.split(value, ",");
        if (values.length != 3) {
            log.warn("Illegal font name: " + value);
            return null;
        }
        return new Font(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
    }

    /**
     * Convert a Font to a String. Produces a format that can be read with
     * <code>Font.decode(String)</code>.
     * 
     * @param font
     *            the thing to convert
     * @return the converted data
     */
    public static String font2String(Font font) {
        if (font == null) {
            return "";
        }

        String strStyle = "plain";

        if (font.isBold()) {
            strStyle = font.isItalic() ? "bolditalic" : "bold";
        } else if (font.isItalic()) {
            strStyle = "italic";
        }

        return font.getName() + "-" + strStyle + "-" + font.getSize();
    }

    /**
     * Create a font just like the another with regard to style and size, but
     * differing in font family.
     * 
     * @param fontspec
     *            the font to model
     * @param fontName
     *            the font to use
     * @return the font
     */
    public static Font deriveFont(String fontspec, String fontName) {
        Font font = string2Font(fontspec);
        Font derived = null;
        if (font != null && fontName != null) {
            derived = new Font(fontName, font.getStyle(), font.getSize());
        }
        return derived;
    }

    /**
     * Convert a String to a Color
     * 
     * @param value
     *            the thing to convert
     * @return the converted data
     */
    public static Color string2Color(String value) {
        if (value == null || value.equals("")) {
            return null;
        }

        if (value.length() != 7) {
            log.warn("Illegal colour name: " + value);
            return null;
        }

        // log.fine("input=" + value);
        String red = value.substring(1, 3);
        String green = value.substring(3, 5);
        String blue = value.substring(5, 7);
        // log.fine("red=" + red + " green=" + green + " blue=" + blue);

        return new Color(Integer.parseInt(red, 16), Integer.parseInt(green, 16), Integer.parseInt(blue, 16));
    }

    /**
     * Convert a Color to a String
     * 
     * @param color
     *            the thing to convert
     * @return the converted data
     */
    public static String color2String(Color color) {
        if (color == null) {
            return "";
        }

        String red = "00" + Integer.toHexString(color.getRed());
        String green = "00" + Integer.toHexString(color.getGreen());
        String blue = "00" + Integer.toHexString(color.getBlue());

        red = red.substring(red.length() - 2);
        green = green.substring(green.length() - 2);
        blue = blue.substring(blue.length() - 2);

        return "#" + red + green + blue;
    }

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(GuiConvert.class);
}
