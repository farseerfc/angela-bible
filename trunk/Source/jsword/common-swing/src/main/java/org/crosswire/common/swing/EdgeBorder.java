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
 * ID: $Id: EdgeBorder.java 1966 2009-10-30 01:15:14Z dmsmith $
 */
package org.crosswire.common.swing;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * EdgeBorder.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker
 * @author Claude Duguay Copyright (c) 1998
 */
public class EdgeBorder implements Border, SwingConstants {
    /**
     * Create an EdgeBorder showing a northern border
     */
    public EdgeBorder() {
        this(NORTH);
    }

    /**
     * Create an EdgeBorder with a selected edge
     * 
     * @param edge
     *            The edge to display
     */
    public EdgeBorder(int edge) {
        this.edge = edge;
    }

    /**
     * Get the insets for a given component
     */
    public Insets getBorderInsets(Component component) {
        switch (edge) {
        case SOUTH:
            return new Insets(0, 0, 2, 0);
        case EAST:
            return new Insets(0, 2, 0, 0);
        case WEST:
            return new Insets(0, 0, 0, 2);
        default:
            return new Insets(2, 0, 0, 0);
        }
    }

    /**
     * Is this border opaque
     * 
     * @return true/false if the border if opaque
     */
    public boolean isBorderOpaque() {
        return true;
    }

    /**
     * Actually go and paint the border
     */
    public void paintBorder(Component component, Graphics g, int x, int y, int w, int h) {
        if (lift == RAISED) {
            g.setColor(component.getBackground().brighter());
        } else {
            g.setColor(component.getBackground().darker());
        }

        switch (edge) {
        case SOUTH:
            g.drawLine(x, y + h - 2, w, y + h - 2);
            break;
        case EAST:
            g.drawLine(x + w - 2, y, x + w - 2, y + h);
            break;
        case WEST:
            g.drawLine(x + 1, y, x + 1, y + h);
            break;
        default:
            g.drawLine(x, y, x + w, y);
        }

        if (lift == RAISED) {
            g.setColor(component.getBackground().darker());
        } else {
            g.setColor(component.getBackground().brighter());
        }

        switch (edge) {
        case SOUTH:
            g.drawLine(x, y + h - 1, w, y + h - 1);
            break;
        case EAST:
            g.drawLine(x + w - 1, y, x + w - 1, y + h);
            break;
        case WEST:
            g.drawLine(x + 1, y, x + 1, y + h);
            break;
        default:
            g.drawLine(x, y + 1, x + w, y + 1);
        }
    }

    /** A raised border */
    public static final int RAISED = 1;

    /** A lowered border */
    public static final int LOWERED = 2;

    /** The edge to draw */
    protected int edge = NORTH;

    /** The raised/lowered state */
    protected int lift = LOWERED;
}
