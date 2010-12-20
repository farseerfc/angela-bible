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
 * ID: $Id: DisplaySelectListener.java 1966 2009-10-30 01:15:14Z dmsmith $
 */
package org.crosswire.bibledesktop.book;

import java.util.EventListener;

/**
 * Implement DisplaySelectListener to recieve CommandEvents whenever someone
 * makes a command for you to execute.
 * 
 * @see gnu.gpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public interface DisplaySelectListener extends EventListener {
    /**
     * This method is called to indicate that a command has been made.
     * 
     * @param ev
     *            Describes the change
     */
    void passageSelected(DisplaySelectEvent ev);

    /**
     * This method is called to indicate that a command has been made.
     * 
     * @param ev
     *            Describes the change
     */
    void bookChosen(DisplaySelectEvent ev);
}
