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
 * ID: $Id: MDIViewLayout.java 2050 2010-12-09 15:31:45Z dmsmith $
 */
package org.crosswire.common.swing.desktop;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.crosswire.common.swing.GuiUtil;

/**
 * MDI (Multiple documet interface) manager of how we layout views as
 * sub-windows.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class MDIViewLayout extends AbstractViewLayout {
    /**
     * Create a MDIViewLayout
     */
    public MDIViewLayout() {
        super();
        desk = new JDesktopPane();
        getPanel().add(desk, getConstraint());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.swing.desktop.AbstractViewLayout#addView(java.awt
     * .Component)
     */
    @Override
    public void addView(Component view) {
        super.addView(view);

        String name = getTitle(view);

        JInternalFrame iframe = new JInternalFrame(name, true, true, true, true);
        iframe.setPreferredSize(new Dimension(640, 480));
        iframe.getContentPane().add(view);

        desk.add(iframe/* , JLayeredPane.PALETTE_LAYER */);

        iframe.addInternalFrameListener(new CustomInternalFrameAdapter());
        iframe.addVetoableChangeListener(new CloseListener());

        GuiUtil.applyDefaultOrientation(iframe);
        iframe.setVisible(true);
        iframe.pack();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.swing.desktop.AbstractViewLayout#removeView(java
     * .awt.Component)
     */
    @Override
    public void removeView(Component view) {
        Component comp = SwingUtilities.getAncestorOfClass(JInternalFrame.class, view);
        if (comp instanceof JInternalFrame) {
            JInternalFrame iframe = (JInternalFrame) comp;
            if (getViewCount() > 1) {
                // We need to remove our listener
                // because calling dispose will call it otherwise.
                // We want it to be called only when the 'X' window close button
                // is pressed
                removeInternalFrameListener(iframe);
                iframe.dispose();

                super.removeView(view);
            } else {
                if (view instanceof Clearable) {
                    ((Clearable) view).clear();
                }
                // Some one may have clicked on the close button,
                // which made the view hidden
                iframe.setVisible(true);
            }
        }
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
        Component comp = SwingUtilities.getAncestorOfClass(JInternalFrame.class, component);
        if (comp instanceof JInternalFrame) {
            JInternalFrame iframe = (JInternalFrame) comp;
            // We need to remove our listener
            // because calling dispose will call it otherwise.
            // We want it to be called only when the 'X' window close button is
            // pressed
            removeInternalFrameListener(iframe);
            iframe.dispose();
        }
        super.forceRemoveView(component);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.swing.desktop.AbstractViewLayout#updateTitle(java
     * .awt.Component)
     */
    @Override
    public void updateTitle(Component view) {
        Component comp = SwingUtilities.getAncestorOfClass(JInternalFrame.class, view);
        if (comp instanceof JInternalFrame) {
            JInternalFrame iframe = (JInternalFrame) comp;
            iframe.setTitle(getTitle(view));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.crosswire.common.swing.desktop.AbstractViewLayout#getSelected()
     */
    @Override
    public Component getSelected() {
        JInternalFrame frame = desk.getSelectedFrame();

        if (frame == null) {
            // none of the frames are selected, but things like cut/copy/paste
            // rely on there being a 'current' BibleViewPane so we just use the
            // first one we find, which might be the top one?
            Component[] comps = desk.getComponents();
            for (int i = 0; i < comps.length; i++) {
                if (comps[i] instanceof JInternalFrame) {
                    frame = (JInternalFrame) comps[i];
                    break;
                }
            }
        }

        if (frame == null) {
            return null;
        }

        Container contentPane = frame.getContentPane();
        if (contentPane.getComponentCount() > 0) {
            return contentPane.getComponent(0);
        }
        return null;
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
        JInternalFrame frame = desk.getSelectedFrame();

        // is it already selected?
        if (frame != null && frame.getContentPane().getComponent(0) == component) {
            return;
        }

        Component[] comps = desk.getComponents();
        for (int i = 0; i < comps.length; i++) {
            if (comps[i] instanceof JInternalFrame) {
                frame = (JInternalFrame) comps[i];
                if (frame.getContentPane().getComponent(0) == component) {
                    desk.setSelectedFrame(frame);
                    return;
                }
            }
        }
    }

    /**
     * Find and remove the CustomInternalFrameAdapter that is attached to the
     * iframe.
     * 
     * @param iframe
     */
    private void removeInternalFrameListener(JInternalFrame iframe) {
        InternalFrameListener[] listeners = iframe.getInternalFrameListeners();
        for (int i = 0; i < listeners.length; i++) {
            InternalFrameListener listener = listeners[i];
            if (listener instanceof CustomInternalFrameAdapter) {
                iframe.removeInternalFrameListener(listener);
            }
        }
    }

    /**
     * So we can tidy things up when a window is closed
     */
    class CustomInternalFrameAdapter extends InternalFrameAdapter {
        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.event.InternalFrameListener#internalFrameClosed(javax
         * .swing.event.InternalFrameEvent)
         */
        @Override
        public void internalFrameClosed(InternalFrameEvent ev) {
            JInternalFrame iframe = ev.getInternalFrame();
            Component view = iframe.getContentPane().getComponent(0);
            MDIViewLayout.this.removeView(view);
        }
    }

    /**
     * We need to veto the closing of the last window.
     */
    class CloseListener implements VetoableChangeListener {

        /*
         * (non-Javadoc)
         * 
         * @seejava.beans.VetoableChangeListener#vetoableChange(java.beans.
         * PropertyChangeEvent)
         */
        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            // Don't allow the last one to be closed.
            String name = evt.getPropertyName();
            if (name.equals(JInternalFrame.IS_CLOSED_PROPERTY) && MDIViewLayout.this.desk.getComponentCount() == 1) {
                throw new PropertyVetoException("Cannot close the last window", evt);
            }
        }

    }

    /**
     * The desktop of sub-windows
     */
    protected JDesktopPane desk;
}
