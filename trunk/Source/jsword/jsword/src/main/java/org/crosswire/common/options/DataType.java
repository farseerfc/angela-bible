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
 * Copyright: 2008
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: org.eclipse.jdt.ui.prefs 1178 2006-11-06 12:48:02Z dmsmith $
 */
package org.crosswire.common.options;

import org.crosswire.common.util.Convert;

/**
 * A DataType provides the ability to marshal a String value to an object.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public enum DataType {
    /**
     * A string argument.
     */
    STRING  ("String") {
        @Override
        public Object convertFromString(String value) {
            return value;
        }
    },

    /**
     * An integer argument.
     */
    INTEGER  ("Integer") {
        @Override
        public Object convertFromString(String value) {
            return Integer.valueOf(Convert.string2Int(value));
        }
    },

    /**
     * An boolean argument that allows various values for 'true'.
     */
    BOOLEAN ("Boolean") {
        @Override
        public Object convertFromString(String value) {
            return Boolean.valueOf(Convert.string2Boolean(value));
        }
    };

    /**
     * @param name
     *            The name of the DataType
     */
    private DataType(String name) {
        this.name = name;
    }

    /**
     * Convert a String to an DataType's expected value.
     * @param input the string to convert
     * @return the converted value
     */
    public abstract Object convertFromString(String input);

    /**
     * Lookup method to convert from a String
     */
    public static DataType fromString(String name) {
        for (DataType v : values()) {
            if (v.name().equalsIgnoreCase(name)) {
                return v;
            }
        }
        // cannot get here
        assert false;
        return null;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * The name of the DataType
     */
    private String name;
}
