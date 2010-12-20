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
 * ID: $Id: NoSuchKeyException.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.passage;

import org.crosswire.common.util.LucidException;
import org.crosswire.common.util.MsgBase;

/**
 * When something tries to use a key that we don't understand.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class NoSuchKeyException extends LucidException
{
    /**
     * Construct the Exception with a message
     * @param msg The resource id to read
     */
    public NoSuchKeyException(MsgBase msg)
    {
        super(msg);
    }

    /**
     * Construct the Exception with a message and a nested Exception
     * @param msg The resource id to read
     * @param ex The nested Exception
     */
    public NoSuchKeyException(MsgBase msg, Throwable ex)
    {
        super(msg, ex);
    }

    /**
     * Construct the Exception with a message and some i18n params
     * @param msg The resource id to read
     * @param params An array of parameters
     */
    public NoSuchKeyException(MsgBase msg, Object[] params)
    {
        super(msg, params);
    }

    /**
     * Construct the Exception with a message, a nested Exception
     * and some i18n params
     * @param msg The resource id to read
     * @param ex The nested Exception
     * @param params An array of parameters
     */
    public NoSuchKeyException(MsgBase msg, Throwable ex, Object[] params)
    {
        super(msg, ex, params);
    }

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3257288032582185777L;
}
