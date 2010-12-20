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
 * ID: $Id: FormPane.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.common.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A Panel customized to hold fields.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class FormPane extends JPanel {
    /**
     * Create a FormPane
     */
    public FormPane() {
        setLayout(new BorderLayout());
        inner = new JPanel(new GridBagLayout());
        add(inner, BorderLayout.NORTH);
        setBorder(BorderFactory.createEmptyBorder());
        inner.setBorder(BorderFactory.createEmptyBorder());
        GuiUtil.applyDefaultOrientation(this);
    }

    /**
     * Add a field to this panel
     * 
     * @param prompt
     *            The name for the field
     * @param comp
     *            The component to add alongside the label
     */
    public void addEntry(String prompt, String tooltip, Component comp) {
        JLabel label = new JLabel(prompt);
        label.setToolTipText(tooltip);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridwidth = GridBagConstraints.RELATIVE; // next-to-last
        c.fill = GridBagConstraints.NONE; // reset to default
        c.weightx = 0.0; // reset to default
        inner.add(label, c);

        c.gridwidth = GridBagConstraints.REMAINDER; // end row
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        inner.add(comp, c);

        comps.put(prompt + SUFFIX_LABEL, label);
        comps.put(prompt + SUFFIX_COMP, comp);
    }

    /**
     * Add a field to this panel
     * 
     * @param prompt
     *            The name for the field
     */
    public void removeEntry(String prompt) {
        inner.remove(comps.get(prompt + SUFFIX_LABEL));
        inner.remove(comps.get(prompt + SUFFIX_COMP));

        comps.remove(prompt + SUFFIX_LABEL);
        comps.remove(prompt + SUFFIX_COMP);
    }

    /**
     * Is this panel empty
     */
    public boolean isEmpty() {
        return comps.size() == 0;
    }

    /**
     * Get a list of the labels
     */
    public String[] getFieldNames() {
        int count = getComponentCount() / 2;
        String[] list = new String[count];

        for (int i = 0; i < count; i++) {
            JLabel label = (JLabel) getComponent(i * 2);
            list[i] = label.getText();
        }

        return list;
    }

    /**
     * Get at list of the values in the fields
     */
    public String[] getFieldValues() {
        int count = getComponentCount() / 2;
        String[] list = new String[count];

        for (int i = 0; i < count; i++) {
            Component comp = getComponent(i * 2 + 1);
            list[i] = GuiUtil.getText(comp);
        }

        return list;
    }

    private static final String SUFFIX_COMP = "_comp";
    private static final String SUFFIX_LABEL = "_label";

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3258135738867790641L;

    private JPanel inner;

    /**
     * A store of the available components
     */
    protected Hashtable<String,Component> comps = new Hashtable<String,Component>();
}
