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
 * ID: $Id: Lock.java 1966 2009-10-30 01:15:14Z dmsmith $
 */
package org.crosswire.common.activate;

/**
 * This class only exists to dissuade you from calling activate() directly on an
 * Activatable object.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class Lock {
    /**
     * You might be wanting to construct a Lock if you want to call
     * Activatable.activate() directly, in which case you stand a chance of
     * breaking the Activator, so let the activator call activate(), and just
     * ask the Activator to do the job for you.
     */
    Lock() {
        // no instantiation needed
    }
}
