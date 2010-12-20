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
 * ID: $Id: OptionsField.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.common.config.swing;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import org.crosswire.common.config.Choice;
import org.crosswire.common.config.MultipleChoice;
import org.crosswire.common.diff.Distance;
import org.crosswire.common.swing.GuiUtil;
import org.crosswire.common.util.Logger;

/**
 * Allow the user to choose from a combo box.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class OptionsField implements Field {

    /**
     * Create an empty OptionsField
     */
    public OptionsField() {
        combo = new JComboBox(new String[] {
            Msg.NO_OPTIONS.toString()
        });
        // Set the preferred width. Note, the actual combo box will resize to
        // the width of it's container
        combo.setPreferredSize(new Dimension(100, combo.getPreferredSize().height));
        GuiUtil.applyDefaultOrientation(combo);
    }

    /**
     * Some fields will need some extra info to display properly like the
     * options in an options field. FieldMap calls this method with options
     * provided by the choice.
     * 
     * @param param
     *            The options provided by the Choice
     */
    public void setChoice(Choice param) {
        if (param instanceof MultipleChoice) {
            MultipleChoice mc = (MultipleChoice) param;
            list = mc.getOptions();

            if (list == null) {
                throw new IllegalArgumentException("getOptions() returns null for option with help text " + mc.getHelpText());
            }
            combo.setModel(new DefaultComboBoxModel(list));
        } else {
            list = new String[] {
                Msg.ERROR.toString()
            };
        }
    }

    /**
     * Return a string for use in the properties file
     * 
     * @return The current value
     */
    public String getValue() {
        Object reply = combo.getSelectedItem();

        if (reply instanceof Map.Entry<?,?>) {
            return ((Map.Entry<?,?>) reply).getKey().toString();
        }
        return reply == null ? "" : reply.toString();
    }

    /**
     * Set the current value
     * 
     * @param value
     *            The new text
     */
    public void setValue(String value) {
        if (list != null && list.length > 0) {
            int distance = value.length();
            for (int i = 0; i < list.length; i++) {
                if (value.equals(list[i])) {
                    combo.setSelectedItem(list[i]);
                    return;
                }
                distance = Math.max(distance, list[i].length());
            }

            // We didn't find an exact match so look for the closest one.
            distance++; // A number larger than the length of any of the strings
            // in question.
            int bestMatch = 0;
            for (int i = 0; i < list.length; i++) {
                int newDistance = Distance.getLevenshteinDistance(value, list[i]);
                if (distance > newDistance) {
                    bestMatch = i;
                    distance = newDistance;
                }
            }

            combo.setSelectedItem(list[bestMatch]);
            if (bestMatch > 0) {
                log.warn("Checked for options without finding exact match: '" + value + "'. Best match is: " + combo.getSelectedItem());
                return;
            }
        }

        // Equate null and empty string
        Object selected = combo.getSelectedItem();
        if (value.length() > 0 && selected != null) {
            log.warn("Checked for options without finding: '" + value + "'. Defaulting to first option: " + selected);
        }
    }

    /**
     * Get the actual component that we can add to a Panel. (This can well be
     * this in an implementation).
     */
    public JComponent getComponent() {
        return combo;
    }

    /**
     * The component that we are wrapping in a field
     */
    private JComboBox combo;

    /**
     * The options
     */
    private String[] list;

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(OptionsField.class);
}
