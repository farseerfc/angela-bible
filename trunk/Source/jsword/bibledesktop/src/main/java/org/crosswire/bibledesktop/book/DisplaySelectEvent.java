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
 * ID: $Id: DisplaySelectEvent.java 1966 2009-10-30 01:15:14Z dmsmith $
 */
package org.crosswire.bibledesktop.book;

import java.util.EventObject;

import org.crosswire.jsword.book.BookProvider;
import org.crosswire.jsword.passage.Key;

/**
 * A DisplaySelectEvent happens whenever a user makes a command.
 * 
 * @see gnu.gpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class DisplaySelectEvent extends EventObject {
    /**
     * For when a command has been made
     * 
     * @param source
     *            The thing that started this off
     * @param key
     *            The selected Key
     */
    public DisplaySelectEvent(BookProvider source, Key key) {
        super(source);

        this.key = key;
    }

    /**
     * Get the type of command
     * 
     * @return The type of command
     */
    public Key getKey() {
        return key;
    }

    /**
     * Get the type of command
     * 
     * @return The type of command
     */
    public BookProvider getBookProvider() {
        return (BookProvider) getSource();
    }

    /**
     * The new passage
     */
    private Key key;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3689068456540910136L;
}
