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
 * ID: $Id: TDIViewLayout.java 2050 2010-12-09 15:31:45Z dmsmith $
 */
package org.crosswire.common.swing.desktop;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.TabbedPaneUI;

import org.crosswire.common.swing.GuiUtil;

/**
 * TDI (Tabbed Document Interface) manager of how we layout views as tabs.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class TDIViewLayout extends AbstractViewLayout {
    /**
     * Build a TDI layout
     */
    public TDIViewLayout() {
        super();
        tabs = new JTabbedPane();
        if (UIManager.getDefaults().containsKey("BibleViewPane.TabbedPaneUI")) {
            tabs.setUI((TabbedPaneUI) UIManager.get("BibleViewPane.TabbedPaneUI"));
        }
        tabs.setMinimumSize(new Dimension(0, 0));
        GuiUtil.applyDefaultOrientation(tabs);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.swing.desktop.AbstractViewLayout#addView(java.awt
     * .Component)
     */
    @Override
    public void addView(Component component) {
        int viewCount = getViewCount();

        if (viewCount > 0) {
            if (viewCount == 1) {
                Component first = getView(0);
                getPanel().remove(first);
                tabs.add(first, getTitle(first));
                getPanel().add(tabs, getConstraint());
            }

            tabs.add(component, getTitle(component));
            tabs.setSelectedComponent(component);
        } else {
            getPanel().add(component, getConstraint());
        }

        super.addView(component);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.swing.desktop.AbstractViewLayout#removeView(java
     * .awt.Component)
     */
    @Override
    public void removeView(Component component) {
        int viewCount = getViewCount();

        if (viewCount == 1) {
            if (component instanceof Clearable) {
                ((Clearable) component).clear();
            }
            return;
        }

        tabs.remove(component);

        // There were two tabs and now there is one
        // We migrate from tabs to just the component
        if (viewCount == 2) {
            Component remaining = tabs.getComponentAt(0);
            // remove both tabs, because 0 will be reparented
            tabs.removeTabAt(0);
            getPanel().remove(tabs);
            getPanel().add(remaining, getConstraint());
        }

        super.removeView(component);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.swing.desktop.AbstractViewLayout#forceRemoveView
     * (java.awt.Component)
     */
    @Override
    protected void forceRemoveView(Component component) {
        int viewCount = getViewCount();

        if (viewCount == 1) {
            getPanel().remove(component);
        } else {
            tabs.remove(component);

            // There were two tabs and now there is one
            // We migrate from tabs to just the component
            if (viewCount == 2) {
                Component remaining = tabs.getComponentAt(0);
                // remove both tabs, because 0 will be reparented
                tabs.removeTabAt(0);
                getPanel().remove(tabs);
                getPanel().add(remaining, getConstraint());
            }
        }
        super.forceRemoveView(component);
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.crosswire.bibledesktop.desktop.ViewLayout#update(org.crosswire.
     * bibledesktop.book.BibleViewPane)
     */
    @Override
    public void updateTitle(Component component) {
        if (getViewCount() > 1) {
            int index = tabs.indexOfComponent(component);
            tabs.setTitleAt(index, getTitle(component));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.crosswire.bibledesktop.desktop.ViewLayout#getSelected()
     */
    @Override
    public Component getSelected() {
        if (getViewCount() == 1) {
            return getView(0);
        }
        return tabs.getSelectedComponent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.swing.desktop.AbstractViewLayout#select(java.awt
     * .Component)
     */
    @Override
    public void select(Component component) {
        // If we don't have tabs then it is selected.
        if (getViewCount() > 1) {
            tabs.setSelectedComponent(component);
        }
    }

    /**
     * Bind a popup to the tabbed page
     * 
     * @param popup
     */
    public void addPopup(JPopupMenu popup) {
        MouseListener ml = new TabPopupListener(tabs, popup);
        tabs.addMouseListener(ml);
    }

    /**
     * The tabbed view pane
     */
    private JTabbedPane tabs;
}
