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
 * ID: $Id: AbstractConfigEditor.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.common.config.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.crosswire.common.config.Choice;
import org.crosswire.common.config.Config;
import org.crosswire.common.config.ConfigEvent;
import org.crosswire.common.config.ConfigListener;
import org.crosswire.common.swing.FormPane;
import org.crosswire.common.swing.GuiUtil;
import org.crosswire.common.swing.desktop.LayoutPersistence;
import org.crosswire.common.util.Logger;

/**
 * An abstract base of a Configuration Editor.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public abstract class AbstractConfigEditor extends JPanel implements ConfigEditor, ButtonPaneListener {
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.config.swing.ConfigEditor#init(org.crosswire.common
     * .config.Config)
     */
    public void construct(Config aConfig) {
        this.config = aConfig;

        initializeGUI();

        config.addConfigListener(new ConfigListener() {
            public void choiceAdded(ConfigEvent ev) {
                addChoice(ev.getChoice());
                updateTree();
            }

            public void choiceRemoved(ConfigEvent ev) {
                removeChoice(ev.getChoice());
                updateTree();
            }
        });

        // For each of the Fields put it in a FieldPanel
        for (Choice model : config) {
            addChoice(model);
        }

        updateTree();

        GuiUtil.applyDefaultOrientation(this);
        SwingUtilities.updateComponentTreeUI(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.config.swing.ConfigEditor#showDialog(java.awt.Component
     * , java.awt.event.ActionListener)
     */
    public void showDialog(Component parent, ActionListener newal) {
        this.al = newal;

        if (dialog == null) {
            Component root = SwingUtilities.getRoot(parent);
            dialog = new JDialog((JFrame) root);
            dialog.getContentPane().add(this);

            // set the name for Layout Persistence
            dialog.setName("Config");
            dialog.addWindowListener(new WindowAdapter() {
                /*
                 * (non-Javadoc)
                 * 
                 * @see
                 * java.awt.event.WindowListener#windowClosed(java.awt.event
                 * .WindowEvent)
                 */
                @Override
                public void windowClosed(WindowEvent ev) {
                    hideDialog();
                }
            });
        }

        // Update from config
        localToScreen();
        dialog.setTitle(config.getTitle());

        // Restore window size, position, and layout if previously opened,
        // otherwise use defaults.
        LayoutPersistence layoutPersistence = LayoutPersistence.instance();
        if (layoutPersistence.isLayoutPersisted(dialog)) {
            layoutPersistence.restoreLayout(dialog);
        } else {
            dialog.setSize(1000, 500);
            GuiUtil.centerOnScreen(dialog);
        }

        dialog.setModal(true);

        GuiUtil.applyDefaultOrientation(dialog);

        // show
        dialog.setVisible(true);
    }

    /**
     * Create the GUI
     */
    protected abstract void initializeGUI();

    /**
     * Update the tree structure
     */
    protected abstract void updateTree();

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.config.swing.ButtonPaneListener#ok(java.awt.event
     * .ActionEvent)
     */
    public void okPressed(ActionEvent ev) {
        screenToLocal();
        al.actionPerformed(ev);
        hideDialog();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.config.swing.ButtonPaneListener#cancel(java.awt.
     * event.ActionEvent)
     */
    public void cancelPressed(ActionEvent ev) {
        hideDialog();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.common.config.swing.ButtonPaneListener#apply(java.awt.event
     * .ActionEvent)
     */
    public void applyPressed(ActionEvent ev) {
        screenToLocal();
        al.actionPerformed(ev);
    }

    /**
     * Add a Choice to our set of panels
     */
    protected void addChoice(Choice model) {
        if (model.isHidden()) {
            return;
        }

        String key = model.getKey();
        String path = Config.getPath(model.getFullPath());

        // Check if we want to display this option
        Field field = FieldMap.getField(model);
        fields.put(key, field);

        // Get or create a FieldPanel
        FormPane card = decks.get(path);

        if (card == null) {
            card = new FormPane();
            decks.put(path, card);
            cards++;
        }

        // Add the Field to the FieldPanel
        JComponent comp = field.getComponent();

        String name = Config.getLeaf(model.getFullPath()) + ':';
        card.addEntry(name, model.getHelpText(), comp);

        // Fill in the current value
        String value = config.getLocal(key);
        field.setValue(value);
    }

    /**
     * Add a Choice to our set of panels
     */
    protected void removeChoice(Choice model) {
        String key = model.getKey();
        String path = Config.getPath(model.getFullPath());

        Field field = fields.get(key);
        if (field != null) {
            fields.remove(field);
            FormPane card = decks.get(path);

            // Remove field from card.
            String name = Config.getLeaf(model.getFullPath()) + ':';
            card.removeEntry(name);

            if (card.isEmpty()) {
                decks.remove(card);
            }
        }
    }

    /**
     * Close any open dialogs
     */
    protected void hideDialog() {
        if (dialog != null) {
            LayoutPersistence.instance().saveLayout(dialog);
            dialog.setVisible(false);
        }
    }

    /**
     * Take the data displayed on screen an copy it to the local storage area.
     */
    protected void screenToLocal() {
        for (Choice choice : config) {
            if (choice.isHidden()) {
                continue;
            }

            String key = choice.getKey();
            Field field = fields.get(key);
            String value = field.getValue();

            if (value == null) {
                log.error("null value from key=" + key);
            }

            config.setLocal(key, value);
        }
    }

    /**
     * Take the data in the local storage area and copy it on screen.
     */
    protected void localToScreen() {
        for (Choice choice : config) {
            if (choice.isHidden()) {
                continue;
            }

            String key = choice.getKey();

            Field field = fields.get(key);
            String value = config.getLocal(key);

            if (field == null) {
                log.error("Null field from key=" + key + ", skipping setting value=" + value);
            } else {
                field.setValue(value);
            }
        }
    }

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(AbstractConfigEditor.class);

    /**
     * How many cards have we created - we only need a tree if there are 2 or
     * more cards
     */
    protected int cards;

    /**
     * Action when the user clicks on accept
     */
    protected ActionListener al;

    /**
     * The class that represents the Fields that we display
     */
    protected Config config;

    /**
     * The dialog that we are displayed in
     */
    protected JDialog dialog;

    /**
     * A fast way to get at the configuration panels
     */
    protected Map<String,FormPane> decks = new HashMap<String,FormPane>();

    /**
     * The set of fields that we are displaying
     */
    protected Map<String,Field> fields = new HashMap<String,Field>();

    /**
     * The large task icon
     */
    protected static final ImageIcon TASK_ICON_LARGE = GuiUtil.getIcon("toolbarButtonGraphics/general/Preferences24.gif");

    /**
     * The small task icon
     */
    protected static final ImageIcon TASK_ICON_SMALL = GuiUtil.getIcon("toolbarButtonGraphics/general/Preferences16.gif");

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 6004078142151397638L;
}
