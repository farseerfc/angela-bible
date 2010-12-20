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
 * ID: $Id: GuiUtil.java 2045 2010-12-06 21:19:49Z dmsmith $
 */
package org.crosswire.common.swing;

import java.awt.Button;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextComponent;
import java.awt.Toolkit;
import java.awt.Window;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
import javax.swing.text.JTextComponent;

import org.crosswire.common.util.Language;
import org.crosswire.common.util.Logger;
import org.crosswire.common.util.ResourceUtil;

/**
 * Various GUI Utilities.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public final class GuiUtil {
    /**
     * Prevent instantiation
     */
    private GuiUtil() {
    }

    /**
     * Returns the Icon associated with the name from the resources. The
     * resource should be in the path.
     * 
     * @param name
     *            Name of the icon file i.e., help16.gif
     * @return the image or null if the icon is not found.
     */
    public static ImageIcon getIcon(String name) {
        try {
            URL url = ResourceUtil.getResource(name);
            return new ImageIcon(url);
        } catch (MissingResourceException ex) {
            log.error("Failed to find icon name='" + name + "'", ex);
            return null;
        }
    }

    /**
     * Find the parent window.
     * 
     * @param com
     *            a component to find the frame of.
     * @return The parent Window
     */
    public static Window getWindow(Component com) {
        Component temp = com;

        if (temp == null) {
            return getRootFrame();
        }

        while (!(temp instanceof Frame || temp instanceof Dialog)) {
            temp = temp.getParent();
            if (temp == null) {
                return getRootFrame();
            }
        }

        return (Window) temp;
    }

    /**
     * Find the best frame to which to root a dialog, generally the largest
     * visible frame of the application.
     * 
     * @return the best frame.
     */
    public static Frame getRootFrame() {
        return getFrame(null);
    }

    /**
     * Find the parent Frame. This method can do more than simply walking up the
     * tree to find a parent frame by looking for default frames from
     * JOptionPane and by looking for all visible Frames. We can be sure to
     * return a Frame from this method even if null is passed in.
     * 
     * @param parent
     *            a component to find the frame of.
     * @return The parent Frame
     */
    public static Frame getFrame(Component parent) {
        if (parent == null) {
            // So we can't get one by walking up the tree so we will have to
            // get one from somewhere else.
            // Firstly someone might have called JOptionPane.setRootFrame()
            // to give us a reasonable default so try there
            Frame option = JOptionPane.getRootFrame();

            // If a default has not been set, JOptionPane.getRootFrame() calls
            // SwingUtilities.getSharedOwnerFrame() to get a new invisible frame
            // and we may be able to do better than that. Unfortunately the
            // getSharedOwnerFrame() method is not public so we have to trick
            // our way to finding if we got a duff default
            if (!option.getClass().getName().startsWith("javax.swing.SwingUtilities$"))
            {
                // So we think the JOptionPane root frame is our creation
                return option;
            }

            // We might be able to get a better default by looking through all
            // the frames and picking the biggest visible one.
            Frame best = null;
            int bestSize = 0;

            Frame[] frames = Frame.getFrames();
            for (int i = 0; i < frames.length; i++) {
                Frame frame = frames[i];
                if (frame.isVisible()) {
                    // So this frame is a candidate
                    int thisSize = frame.getWidth() * frame.getHeight();
                    if (best == null || thisSize > bestSize) {
                        best = frame;
                        bestSize = thisSize;
                    }
                }
            }

            // So if we found a frame from searching then use that
            if (best != null) {
                return best;
            }

            // if all else fails we will have to use the invisible frame
            // provided by JOptionPane
            return option;
        }

        if (parent instanceof Frame) {
            return (Frame) parent;
        }

        // So we walk up the tree looking for a frame
        return getFrame(parent.getParent());
    }

    /**
     * Find the parent Frame
     * 
     * @param com
     *            a component to find the frame of.
     * @return The parent Frame
     */
    public static Dialog getDialog(Component com) {
        Component temp = com;

        while (!(temp instanceof Dialog)) {
            temp = temp.getParent();
            if (temp == null) {
                return null;
            }
        }

        return (Dialog) temp;
    }

    /**
     * Move the specified window to the center of the screen. It is the
     * programmer's responsibility to ensure that the window fits on the screen.
     * 
     * @param win
     *            The window to be moved
     */
    public static void centerOnScreen(Window win) {
        win.setLocationRelativeTo(null);
    }

    /**
     * Set the size of the window, but no bigger than the screen. If the
     * platform supports it, this may maximize the window.
     * <p>
     * On platforms that allow docking of other windows, this routine does not
     * take that into account for sizes that are near that of the screen. For
     * example, on Windows XP, the user may have a task bar showing permanently
     * on one side of the screen and another application's toolbar on another
     * side of the screen. If the requested size of the window is less than the
     * screen size in a particular dimension, it will not be able to adjust for
     * it.
     * <p>
     * For that reason, either have the application significantly smaller than
     * the screen size or maximize the window.
     * 
     * @param win
     *            the window to resize
     * @param requestedDim
     *            how wide and tall to make the window, if possible
     * @return whether the window has been maximized horizontally
     *         (Frame.MAXIMIZED_HORIZ), vertically (Frame.MAXIMIZED_VERT
     */
    public static int setSize(Window win, Dimension requestedDim) {
        // If possible we try to honor the request
        Dimension honoredDim = (Dimension) requestedDim.clone();
        Toolkit tk = win.getToolkit();
        Dimension originalDim = win.getSize();
        Dimension screenDim = tk.getScreenSize();
        boolean isFrame = win instanceof Frame;

        // Frames may allow for maximizing in one direction the other or both
        // store any state changes in requestedState
        int honoredState = 0;

        // If the window is wider than the screen, limit it to the width of the
        // screen
        if (honoredDim.width >= screenDim.width) {
            honoredDim.width = screenDim.width;
        }

        if (honoredDim.height >= screenDim.height) {
            honoredDim.height = screenDim.height;
        }

        if (isFrame && honoredDim.equals(screenDim) && tk.isFrameStateSupported(Frame.MAXIMIZED_BOTH)) {
            honoredState |= Frame.MAXIMIZED_BOTH;
        }

        // If either the height or the width changed then use it.
        if (!honoredDim.equals(originalDim)) {
            win.setSize(honoredDim);
        }

        // One of the dimensions may have changed via setSize,
        // and the other may be waiting to change here
        if (honoredState != 0 && win instanceof Frame) {
            Frame frame = (Frame) win;
            // Make sure to preserve existing states
            frame.setExtendedState(honoredState | frame.getExtendedState());
        }

        // setExtendedState can change it.
        Dimension finalDim = win.getSize();
        honoredDim.width = finalDim.width;
        honoredDim.height = finalDim.height;

        log.warn("Window size was: " + requestedDim + " is: " + honoredDim);

        return honoredState;
    }

    /**
     * Set the window size relative to the current screen size.
     * 
     * @param win
     *            The window to resize
     * @param percentOfScreen
     *            The amount of space that the window should take up
     */
    public static void setSize(Window win, float percentOfScreen) {
        Toolkit tk = win.getToolkit();
        Dimension screenSize = tk.getScreenSize();

        int width = Float.valueOf(screenSize.width * percentOfScreen).intValue();
        int height = Float.valueOf(screenSize.height * percentOfScreen).intValue();
        Dimension winSize = new Dimension(width, height);

        win.setSize(winSize);
    }

    /**
     * Set the size of the main window to a default size.
     * 
     * @param win
     *            The window to resize
     */
    public static void defaultDesktopSize(Window win) {
        float defaultPercent = 0.75F;
        setSize(win, defaultPercent);
    }

    /**
     * Maximize the specified window. It would be good if we could detect where
     * the taskbar was and not obscure it ...
     * 
     * @param win
     *            The window to be moved
     */
    public static void maximizeWindow(Window win) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        // Check to see if the window supports maximizing
        if (win instanceof Frame && tk.isFrameStateSupported(Frame.MAXIMIZED_BOTH)) {
            Frame frame = (Frame) win;
            // Make sure to preserve existing states
            frame.setExtendedState(Frame.MAXIMIZED_BOTH | frame.getExtendedState());
        } else {
            // No, then just simulate it.
            Dimension screenDim = tk.getScreenSize();
            win.setLocation(0, 0);
            win.setSize(screenDim);
        }
    }

    /**
     * Cause a component to refresh its contents when it is changed by the
     * program and needs to be redrawn.
     * 
     * @param c
     *            the component to refresh
     */
    public static void refresh(Component c) {
        c.invalidate();
        c.validate();
        c.repaint();
        if (c instanceof JComponent) {
            ((JComponent) c).revalidate();
        }
    }

    /**
     * A more restricted version of pack() for component responding to live
     * component tweaks. Assuming that the window already has a sensible on
     * screen size, do a pack, but don't let the window grow or shrink by more
     * than 10%.
     * 
     * @param win
     *            The window to be packed
     */
    public static void restrainedRePack(Window win) {
        Dimension orig = win.getSize();
        Dimension max = new Dimension((int) (orig.width * 1.1), (int) (orig.height * 1.1));
        Dimension min = new Dimension((int) (orig.width / 1.1), (int) (orig.height / 1.1));

        win.pack();

        // If the window is wider than 110% of its original size, clip it
        if (win.getSize().width > max.width) {
            win.setSize(max.width, win.getSize().height);
        }

        // If the window is taller than 110% of its original size, clip it
        if (win.getSize().height > max.height) {
            win.setSize(win.getSize().width, max.height);
        }

        // If the window is narrower than 90% of its original size, grow it
        if (win.getSize().width < min.width) {
            win.setSize(min.width, win.getSize().height);
        }

        // If the window is shorter than 90% of its original size, grow it
        if (win.getSize().height < min.height) {
            win.setSize(win.getSize().width, min.height);
        }

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();

        // If the window is wider than the screen, clip it
        if (screenDim.width < win.getSize().width) {
            win.setSize(screenDim.width, win.getSize().height);
        }

        // If the window is taller than the screen, clip it
        if (screenDim.height < win.getSize().height) {
            win.setSize(win.getSize().width, screenDim.height);
        }

        refresh(win);

        // log.log(Level.INFO, "Failure", ex);
        // log.fine("Size was "+orig);
        // log.fine("Size is "+win.getSize());
    }

    /**
     * A more restricted version of pack() when the component is being pack()ed
     * for the first time. Since this is a 'first time only' pack we are only
     * concerned with screen size, and not any growths/shrinkages like
     * restrainedRePack(Window).
     * 
     * @param win
     *            The window to be packed
     * @param maxx
     *            The maximum fraction (0.0 to 1.0) of the screen to be taken up
     *            horizontally
     * @param maxy
     *            The maximum fraction (0.0 to 1.0) of the screen to be taken up
     *            vertically
     * @see GuiUtil#restrainedRePack(Window)
     */
    public static void restrainedPack(Window win, float maxx, float maxy) {
        win.pack();

        Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();

        // If the window is wider than the screen, clip it
        if (win.getSize().width > (screen_dim.width * maxx)) {
            win.setSize((int) (screen_dim.width * maxx), win.getSize().height);
        }

        // If the window is taller than the screen, clip it
        if (win.getSize().height > (screen_dim.height * maxy)) {
            win.setSize(win.getSize().width, (int) (screen_dim.height * maxy));
        }

        refresh(win);

        // log.log(Level.INFO, "Failure", ex);
        // log.fine("Size was "+orig);
        // log.fine("Size is "+win.getSize());
    }

    /**
     * Set the size of a component
     */
    public static void enforceMinimumSize(Component comp, int min_width, int min_height) {
        if (comp.getSize().width < min_width) {
            comp.setSize(min_width, comp.getSize().height);
        }

        if (comp.getSize().height < min_height) {
            comp.setSize(comp.getSize().width, min_height);
        }
    }

    /**
     * Attempts to get the text from a generic Component. The Components that we
     * can get some text from include: <li>
     * JTextComponent <li>JLabel <li>AbstractButton <li>JComboBox <li>JToolTip
     * <li>TextComponent <li>Button <li>Label <li>JScrollPane (recurse using the
     * View) The others are done using toString()
     * 
     * @param comp
     *            The object containing the needed text.
     */
    public static String getText(Component comp) {
        if (comp instanceof JTextComponent) {
            return ((JTextComponent) comp).getText();
        }

        if (comp instanceof JLabel) {
            return ((JLabel) comp).getText();
        }

        if (comp instanceof AbstractButton) {
            return ((AbstractButton) comp).getText();
        }

        if (comp instanceof JComboBox) {
            return ((JComboBox) comp).getSelectedItem().toString();
        }

        if (comp instanceof JToolTip) {
            return ((JToolTip) comp).getTipText();
        }

        if (comp instanceof TextComponent) {
            return ((TextComponent) comp).getText();
        }

        if (comp instanceof Button) {
            return ((Button) comp).getLabel();
        }

        if (comp instanceof Label) {
            return ((Label) comp).getText();
        }

        if (comp instanceof JScrollPane) {
            JScrollPane scr = (JScrollPane) comp;
            Component sub = scr.getViewport().getView();
            if (sub != null) {
                return getText(sub);
            }
        }

        return comp.toString();
    }

    /**
     * Recursively apply default component orientation to the component and all
     * it contains.
     * 
     * @param comp
     *            the root of the tree to which orientation needs to be applied
     */
    public static void applyDefaultOrientation(Component comp) {
        applyOrientation(comp, new Language(Locale.getDefault().getLanguage()).isLeftToRight());
    }

    /**
     * Recursively apply component orientation to the component and all it
     * contains.
     * 
     * @param comp
     *            the root of the tree to which orientation needs to be applied
     * @param ltr
     *            whether the orientation is left to right or not.
     */
    public static void applyOrientation(Component comp, boolean ltr) {
        applyOrientation(comp, ltr ? ComponentOrientation.LEFT_TO_RIGHT : ComponentOrientation.RIGHT_TO_LEFT);
    }

    /**
     * Recursively apply component orientation to the component and all it
     * contains.
     * 
     * @param comp
     *            the root of the tree to which orientation needs to be applied
     * @param orientation
     *            the orientation to be applied
     */
    public static void applyOrientation(Component comp, ComponentOrientation orientation) {
        comp.setComponentOrientation(orientation);

        if (comp instanceof JMenu) {
            JMenu menu = (JMenu) comp;
            int ncomponents = menu.getMenuComponentCount();
            for (int i = 0; i < ncomponents; ++i) {
                applyOrientation(menu.getMenuComponent(i), orientation);
            }
        } else if (comp instanceof Container) {
            Container container = (Container) comp;
            int ncomponents = container.getComponentCount();
            for (int i = 0; i < ncomponents; ++i) {
                applyOrientation(container.getComponent(i), orientation);
            }
        }
    }

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(GuiUtil.class);

}
