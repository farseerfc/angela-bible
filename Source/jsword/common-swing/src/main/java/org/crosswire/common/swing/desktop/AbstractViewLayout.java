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
 * ID: $Id: AbstractViewLayout.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.common.swing.desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import org.crosswire.common.swing.GuiUtil;
import org.crosswire.common.swing.desktop.event.ViewEvent;
import org.crosswire.common.swing.desktop.event.ViewEventListener;

/**
 * Abstract manager of how we layout views.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public abstract class AbstractViewLayout implements Viewable {
    /**
     * This constructor is protected because it only needs to be seen by the sub
     * classes
     */
    protected AbstractViewLayout() {
        panel = new JPanel(new BorderLayout());
        GuiUtil.applyDefaultOrientation(panel);
        views = new ArrayList<Component>();
        listenerList = new EventListenerList();
    }

    /**
     * Add a view to the set.
     */
    public void addView(Component component) {
        views.add(component);
    }

    /**
     * Remove a view from the set.
     */
    public void removeView(Component component) {
        views.remove(component);
        fireViewRemoved(new ViewEvent(component));
    }

    /**
     * Unconditionally remove a view from the set.
     */
    protected void forceRemoveView(Component component) {
        views.remove(component);
    }

    /**
     * Get a snapshot of the views as a collection.
     * 
     * @return the views
     */
    public Collection<Component> getViews() {
        return new ArrayList<Component>(views);
    }

    /**
     * Get an iterator of a snapshot of views.
     * 
     * @return an iterator over the views.
     */
    public Iterator<Component> iterator() {
        return getViews().iterator();
    }

    /**
     * Copies all the views from the one layout to the other
     * 
     * @param other
     *            the other layout
     */
    public void moveTo(AbstractViewLayout other) {
        // Make sure we are copying to something else
        if (getClass() == other.getClass()) {
            return;
        }
        // Go through the views removing them from the layout
        // and adding them to the other
        for (Component view : this) {
            forceRemoveView(view);
            other.addView(view);
        }
    }

    /**
     * Close all the views. Note the policy is enforced that one view is kept.
     * This will keep the last one added.
     */
    public void closeAll() {
        for (Component view : this) {
            removeView(view);
        }
    }

    /**
     * Close all the views but the one provided.
     * 
     * @param component
     *            the view that is to remain open.
     */
    public void closeOthers(Component component) {
        for (Component view : this) {
            if (view != component) {
                removeView(view);
            }
        }
    }

    /**
     * Visit every view in the order that they were added.
     * 
     * @param visitor
     *            The visitor for the view
     */
    public void visit(ViewVisitor visitor) {
        for (Component view : this) {
            visitor.visitView(view);
        }
    }

    /**
     * Update the title of the view. If the component does not implement
     * Titleable, then a generated title will be used.
     * 
     * @param component
     *            the component whose title is to be used
     */
    public abstract void updateTitle(Component component);

    /**
     * Returns the top view. If no view is the top, it returns the first one
     * added.
     */
    public abstract Component getSelected();

    /**
     * Find the view and select it.
     * 
     * @param component
     */
    public abstract void select(Component component);

    /**
     * The number of views held by this layout.
     * 
     * @return the number of views held by this layout
     */
    public int getViewCount() {
        return views.size();
    }

    /**
     * Get the view by position. Note that adding and removing views changes the
     * indexes of the views. Do not use this for iteration as it is not thread
     * safe.
     * 
     * @param i
     *            the index of the view
     * @return the requested view.
     */
    public Component getView(int i) {
        return views.get(i);
    }

    /**
     * Get the title from the component, truncating it if necessary. If the
     * component does not implement Titleable of if the title is empty, then
     * titles are generated.
     * 
     * @param component
     *            from whom the title is gotten
     * @return the title, possibly truncated or generated
     */
    protected String getTitle(Component component) {
        if (component instanceof Titleable) {
            Titleable view = (Titleable) component;
            String title = view.getTitle();
            if (title != null && title.length() > 0) {
                if (title.length() <= MAX_TITLE_LEN) {
                    return title;
                }
                return title.substring(0, MAX_TITLE_LEN - 3) + "...";
            }

            // should set the title also
            return generateTitle();
        }
        return generateTitle();
    }

    /**
     * Generates a generic title
     * 
     * @return the generated title
     */
    private String generateTitle() {
        // TRANSLATOR: This is the label on a Bible View tab when it is cleared.
        // {0} is a number to make the label unique.
        return UserMsg.gettext("Untitled {0}", Integer.valueOf(base++));
    }

    /**
     * All parts are put into a panel. This prevents the programmer from having
     * to change containers.
     * 
     * @return Returns the panel.
     */
    protected JPanel getPanel() {
        return panel;
    }

    /**
     * A constraint that allows the panel to be filled up, stretching
     * horizontally and vertically.
     * 
     * @return the constraint
     */
    protected Object getConstraint() {
        return null;
    }

    /**
     * Adds a view event listener for notification of any changes to the view.
     * 
     * @param listener
     *            the listener
     */
    public synchronized void addViewEventListener(ViewEventListener listener) {
        listenerList.add(ViewEventListener.class, listener);
    }

    /**
     * Removes a view event listener.
     * 
     * @param listener
     *            the listener
     */
    public synchronized void removeViewEventListener(ViewEventListener listener) {
        listenerList.remove(ViewEventListener.class, listener);
    }

    /**
     * Notify the listeners that the view has been removed.
     * 
     * @param e
     *            the event
     * @see EventListenerList
     */
    public void fireViewRemoved(ViewEvent e) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ViewEventListener.class) {
                ((ViewEventListener) listeners[i + 1]).viewRemoved(e);
            }
        }
    }

    /**
     * The list of views.
     */
    private List<Component> views;

    /**
     * The listeners for handling ViewEvent Listeners
     */
    private EventListenerList listenerList = new EventListenerList();

    /**
     * The maximum length of a title before it is abbreviated
     */
    private static final int MAX_TITLE_LEN = 30;

    /**
     * A shared counter for creating unknown titles.
     */
    private static int base = 1;

    /**
     * The panel into which all components are placed.
     */
    private JPanel panel;

}
