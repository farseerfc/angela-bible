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
 * ID: $Id: FixedSplitPane.java 1464 2007-07-02 02:34:40Z dmsmith $
 */
package org.crosswire.common.swing;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 * This is a hack to fix the setDividerLocation problem and other layout
 * problems.
 * <p>
 * See Bug Parade 4101306, 4485465 for a description of the WIDE divider
 * problem.
 * <p>
 * Bug Reports on JSplitpane setDividerLocation<br>
 * 4101306, 4125713, 4148530
 *<p>
 * From the javadoc for setDividerLocation(double):
 * -------------------------------------------<br>
 * <p>
 * This method is implemented in terms of setDividerLocation(int). This method
 * immediately changes the size of the receiver based on its current size. If
 * the receiver is not correctly realized and on screen, this method will have
 * no effect (new divider location will become (current size *
 * proportionalLocation) which is 0).<br>
 * -------------------------------------------<br>
 * So, as you can see the JSplitPane MUST be visible invoking this method
 * otherwise it will not have the desired effect.
 * <p>
 * Another, Bug Report 4786896 notes that if the preferred sizes of the two
 * components plus the divider of the split pane adds up to more than the
 * preferred size of the JSplitPane, then JSplitPane will use the minimum size
 * of the components.
 * <p>
 * Since the preferred way of managing the sizes of containers is not with pixel
 * counts, the solution here is to set the preferred size to zero.
 * <p>
 * See Bug Parade: 4265389 for a description of the ComponentOrientation
 * problem.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class AltFixedSplitPane extends JSplitPane {
    /* BUG_PARADE(DMS): many bugs here */

    /**
     * Constructor for FixedSplitPane
     */
    public AltFixedSplitPane() {
    }

    /**
     * Constructor for FixedSplitPane that has no divider shadow and starts out
     * with an empty border.
     */
    public AltFixedSplitPane(boolean visibleDividerBorder) {
        this();
        this.visibleDividerBorder = visibleDividerBorder;
        setBorder(EMPTY_BORDER);
    }

    /**
     * Constructor for FixedSplitPane
     */
    public AltFixedSplitPane(int arg0) {
        super(arg0);
    }

    /**
     * Constructor for FixedSplitPane
     */
    public AltFixedSplitPane(int arg0, boolean arg1) {
        super(arg0, arg1);
    }

    /**
     * Constructor for FixedSplitPane
     */
    public AltFixedSplitPane(int arg0, Component arg1, Component arg2) {
        super(arg0, arg1, arg2);
    }

    /**
     * Constructor for FixedSplitPane
     */
    public AltFixedSplitPane(int arg0, boolean arg1, Component arg2, Component arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.Component#setComponentOrientation(java.awt.ComponentOrientation)
     */
    @Override
    public void setComponentOrientation(ComponentOrientation o) {
        // Turns out that this is currently ignored by JSplitPane
        // So we are handling it here.
        boolean isLtoR = getComponentOrientation().isLeftToRight();

        // if the layout is stacked/vertical then we are done
        if (getOrientation() == JSplitPane.VERTICAL_SPLIT) {
            return;
        }

        if (o.isLeftToRight() != isLtoR) {
            // Swap the left and right
            Component left = getLeftComponent();
            Component right = getRightComponent();
            setRightComponent(null);
            setLeftComponent(right);
            setRightComponent(left);

            // The resizing is now reversed
            setResizeWeight(isLtoR ? 1 - getResizeWeight() : getResizeWeight());

            // also swap the proportional divider location
            if (hasProportionalLocation) {
                setDividerLocation(isLtoR ? 1 - proportionalLocation : proportionalLocation);
            }
        }

        super.setComponentOrientation(o);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.Container#addImpl(java.awt.Component, java.lang.Object,
     * int)
     */
    @Override
    protected void addImpl(Component comp, Object constraints, int index) {
        if (comp instanceof JComponent) {
            ((JComponent) comp).setPreferredSize(DOT);
        }

        // flip the constraint if we are not left to right
        Object realConstraints = constraints;
        if (!getComponentOrientation().isLeftToRight()) {
            realConstraints = constraints != null && constraints.equals(JSplitPane.RIGHT) ? JSplitPane.LEFT : JSplitPane.RIGHT;
        }

        super.addImpl(comp, realConstraints, index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JSplitPane#setBottomComponent(java.awt.Component)
     */
    @Override
    public void setBottomComponent(Component comp) {
        if (comp instanceof JComponent) {
            ((JComponent) comp).setPreferredSize(DOT);
        }
        super.setBottomComponent(comp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JSplitPane#setLeftComponent(java.awt.Component)
     */
    @Override
    public void setLeftComponent(Component comp) {
        if (comp instanceof JComponent) {
            ((JComponent) comp).setPreferredSize(DOT);
        }

        if (getComponentOrientation().isLeftToRight()) {
            super.setLeftComponent(comp);
        } else {
            super.setRightComponent(comp);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JSplitPane#setRightComponent(java.awt.Component)
     */
    @Override
    public void setRightComponent(Component comp) {
        if (comp instanceof JComponent) {
            ((JComponent) comp).setPreferredSize(DOT);
        }

        if (getComponentOrientation().isLeftToRight()) {
            super.setRightComponent(comp);
        } else {
            super.setLeftComponent(comp);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JSplitPane#setTopComponent(java.awt.Component)
     */
    @Override
    public void setTopComponent(Component comp) {
        if (comp instanceof JComponent) {
            ((JComponent) comp).setPreferredSize(DOT);
        }
        super.setTopComponent(comp);
    }

    /**
     * Validates this container and all of its subcomponents. The first time
     * this method is called, the initial divider position is set.
     */
    @Override
    public void validate() {
        if (firstValidate) {
            firstValidate = false;
            if (hasProportionalLocation) {
                setDividerLocation(proportionalLocation);
            }
        }
        super.validate();
    }

    /**
     * Sets the divider location as a percentage of the JSplitPane's size.
     */
    @Override
    public void setDividerLocation(double newProportionalLoc) {
        double realProportionalLoc = newProportionalLoc;

        // When the orientation is reversed the proportions are reversed.
        if (getComponentOrientation().isLeftToRight()) {
            realProportionalLoc = 1 - realProportionalLoc;
        }

        if (!firstValidate) {
            hasProportionalLocation = true;
            proportionalLocation = realProportionalLoc;
        } else {
            super.setDividerLocation(realProportionalLoc);
        }
    }

    /**
     * Sets the divider location as a percentage of the JSplitPane's size.
     */
    @Override
    public void setResizeWeight(double newResizeWeight) {
        double realResizeWeight = newResizeWeight;

        // When the orientation is reversed the proportions are reversed.
        if (getComponentOrientation().isLeftToRight()) {
            realResizeWeight = 1 - realResizeWeight;
        }

        super.setResizeWeight(realResizeWeight);
    }

    /**
     * Whether visible borders are hinted
     * 
     * @return the divider border visiblity hint
     */
    public boolean isVisibleDividerBorder() {
        return visibleDividerBorder;
    }

    /**
     * Set a hint whether the border should be visible or not. Look and feels
     * may ignore this.
     * 
     * @param newVisibility
     */
    public void setVisibleDividerBorder(boolean newVisibility) {
        boolean oldVisibility = isVisibleDividerBorder();
        if (oldVisibility == newVisibility) {
            return;
        }
        visibleDividerBorder = newVisibility;
        firePropertyChange(PROPERTYNAME_VISIBLE_DIVIDER_BORDER, oldVisibility, newVisibility);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#updateUI()
     */
    @Override
    public void updateUI() {
        super.updateUI();
        if (!visibleDividerBorder) {
            SplitPaneUI splitPaneUI = getUI();
            if (splitPaneUI instanceof BasicSplitPaneUI) {
                BasicSplitPaneUI basicUI = (BasicSplitPaneUI) splitPaneUI;
                basicUI.getDivider().setBorder(EMPTY_BORDER);
            }
        }
    }

    private static final Dimension DOT = new Dimension(0, 0);
    private boolean firstValidate = true;
    private boolean hasProportionalLocation;
    private double proportionalLocation;

    /**
     * Property for border visibility
     */
    public static final String PROPERTYNAME_VISIBLE_DIVIDER_BORDER = "visibleDividerBorder";

    /**
     * An Empty Border
     */
    private static final Border EMPTY_BORDER = new EmptyBorder(0, 0, 0, 0);

    /**
     * Hint for whether Divider border should be visible.
     */
    private boolean visibleDividerBorder;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3761687909593789241L;
}
