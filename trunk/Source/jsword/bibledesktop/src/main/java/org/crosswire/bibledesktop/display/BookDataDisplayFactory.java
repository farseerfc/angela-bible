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
 * ID: $Id: BookDataDisplayFactory.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.bibledesktop.display;

import java.io.IOException;

import org.crosswire.common.util.PluginUtil;

/**
 * Factory for OSIS renderers.
 * 
 * @see gnu.gpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public final class BookDataDisplayFactory {
    /**
     * Prevent Use
     */
    private BookDataDisplayFactory() {
    }

    /**
     * Create a new Files
     */
    public static BookDataDisplay createBookDataDisplay() {
        Exception ex = null;
        try {
            return PluginUtil.getImplementation(BookDataDisplay.class);
        } catch (ClassCastException e) {
            ex = e;
        } catch (IOException e) {
            ex = e;
        } catch (ClassNotFoundException e) {
            ex = e;
        } catch (InstantiationException e) {
            ex = e;
        } catch (IllegalAccessException e) {
            ex = e;
        }

        assert false : ex;
        return null;
    }
}
