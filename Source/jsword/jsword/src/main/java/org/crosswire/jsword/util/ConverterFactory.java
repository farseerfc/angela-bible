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
 * ID: $Id: ConverterFactory.java 2050 2010-12-09 15:31:45Z dmsmith $
 */
package org.crosswire.jsword.util;

import java.util.Map;

import org.crosswire.common.util.PluginUtil;
import org.crosswire.common.xml.Converter;

/**
 * A factory for Converters.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @see org.crosswire.common.xml.Converter
 */
public final class ConverterFactory {
    /**
     * Prevent instantiation
     */
    private ConverterFactory() {
    }

    /**
     * Generate a converter for the current converter name
     */
    public static Converter getConverter() {
        try {
            Class<Converter> clazz = PluginUtil.getImplementorsMap(Converter.class).get(name);
            assert clazz != null : Msg.NO_CONVERTER.toString(name);
            return clazz.newInstance();
        } catch (InstantiationException e) {
            assert false : e;
        } catch (IllegalAccessException e) {
            assert false : e;
        }
        return null;
    }

    /**
     * Get a map of the known converters, by looking up the answers in Project
     */
    public static Map<String,Class<Converter>> getKnownConverters() {
        return PluginUtil.getImplementorsMap(Converter.class);
    }

    /**
     * For config to set the currently preferred converter implementation
     */
    public static void setCurrentConverterName(String name) {
        ConverterFactory.name = name;
    }

    /**
     * For config to read the currently preferred converter implementation
     */
    public static String getCurrentConverterName() {
        return name;
    }

    /**
     * Current default converter implementation
     */
    private static String name = "Configurable";
}
