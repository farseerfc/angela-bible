/**
 * Distribution License:
 * This is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 as published
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/llgpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2005
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: ConfigEvent.java 1462 2007-07-02 02:32:23Z dmsmith $
 */
package org.crosswire.common.config;

import java.util.EventObject;

/**
 * An event indicating that an exception has happened.
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class ConfigEvent extends EventObject
{
    /**
     * Constructs an ConfigEvent object.
     * @param source The event originator, or log stream
     */
    public ConfigEvent(Object source, String key, Choice model)
    {
        super(source);

        this.key = key;
        this.model = model;
    }

    /**
     * Returns the key.
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * Returns the choice.
     * @return the choice
     */
    public Choice getChoice()
    {
        return model;
    }

    /**
     * Returns the choice.
     * @return the choice
     */
    public Choice getPath()
    {
        return model;
    }

    /**
     * The name of the choice
     */
    private String key;

    /**
     * The Choice
     */
    private transient Choice model;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3257006561900376375L;
}
