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
 * ID: $Id: ViewGenerator.java 1966 2009-10-30 01:15:14Z dmsmith $
 */
package org.crosswire.common.swing.desktop;

import java.awt.Component;

/**
 * A ViewGenerator is able to create an object which can be used as a view.
 * Typically it extends JPanel. Note, this design allows for only one kind of
 * view.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [ dmsmith555 at yahoo dot com]
 */
public interface ViewGenerator {
    /**
     * Constructs an application appropriate view for the desktop.
     * 
     * @return a view that can be used as a "window"
     */
    Component createView();
}
