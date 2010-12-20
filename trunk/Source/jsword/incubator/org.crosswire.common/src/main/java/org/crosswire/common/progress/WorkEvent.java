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
 * ID: $Id: WorkEvent.java 1462 2007-07-02 02:32:23Z dmsmith $
 */
package org.crosswire.common.progress;

import java.util.EventObject;

/**
 * A WorkEvent happens whenever a MutableBook makes some progress
 * in generating a new Bible.
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class WorkEvent extends EventObject
{
    /**
     * Initialize a WorkEvent
     */
    public WorkEvent(Progress source)
    {
        super(source);
    }

    /**
     * Accessor for the Job
     */
    public Progress getJob()
    {
        return (Progress) getSource();
    }

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3976736990807011378L;
}
