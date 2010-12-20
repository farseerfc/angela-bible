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
 * ID: $Id: AdvancedConfigEditor.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.common.config.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.crosswire.common.config.Choice;
import org.crosswire.common.swing.CWScrollPane;

/**
 * A further refinement of a Tree Configuration Editor
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class AdvancedConfigEditor extends TreeConfigEditor {
    /**
     * WARNING: this code is not called from anywhere and is probably broken
     */
    protected void jbInit() {
        ctm = new AdvancedConfigureTreeModel();
        tree = new JTree();
        JScrollPane scroll = new CWScrollPane(tree);
        scroll.setPreferredSize(new Dimension(150, 150));

        CustomTreeCellRenderer render = new CustomTreeCellRenderer();
        comps = new HashMap<String,Component>();

        // Hack: tree depends on it being a Color not a sub of it.
        Color orig = UIManager.getColor("control");
        Color bg = new Color(orig.getRed(), orig.getGreen(), orig.getBlue());

        // This seems to be broken ...
        render.setLeafIcon(TASK_ICON_SMALL);
        render.setBackgroundNonSelectionColor(bg);

        tree.setBackground(bg);
        tree.setModel(ctm);
        tree.setCellRenderer(render);
        tree.setShowsRootHandles(true);
        tree.setRootVisible(false);
        tree.putClientProperty("JTree.lineStyle", "None");
        tree.setSelectionRow(0);
        tree.setEditable(true);

        setLayout(new BorderLayout(5, 10));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(BorderLayout.CENTER, scroll);
        add(BorderLayout.SOUTH, new ButtonPane(this));
    }

    /**
     * Updates to the tree that we need to do on any change
     */
    @Override
    protected void updateTree() {
        // expand the tree
        /*
         * int row = 0; while (row < tree.getRowCount()) {
         * tree.expandRow(row++); }
         */

        ctm.fireTreeStructureChanged(this);
    }

    /**
     * Add a Choice to our set of panels
     */
    @Override
    protected void addChoice(Choice model) {
        String key = model.getKey();
        Field field = FieldMap.getField(model);
        fields.put(key, field);

        // Add the Field to the FieldPanel
        JComponent comp = field.getComponent();
        comp.setToolTipText(model.getHelpText());
        comps.put(key, comp);

        // Fill in the current value
        String value = config.getLocal(key);
        field.setValue(value);
    }

    /**
     * Add a Choice to our set of panels
     */
    @Override
    protected void removeChoice(Choice choice) {
        String key = choice.getKey();
        Field field = fields.get(key);
        if (field != null) {
            fields.remove(field);
        }

        Component comp = comps.get(key);
        if (comp != null) {
            comps.remove(key);
        }
    }

    /**
     * Used to update the configuration panel whenever someone selects a
     * different item form the tree on the LHS of the configuration dialog.
     */
    @Override
    public void selectCard() {
        Object obj = tree.getLastSelectedPathComponent();
        if (obj == null) {
            return;
        }

        // TRANSLATOR: This is the label for the banner when one opens a type of Options/Preferences.
        // {0} is the type of preference, e.g. Bible Display
        title.setText(UserMsg.gettext("{0} Preferences", obj));

        // Get the name of the current deck
        Object[] list = tree.getSelectionPath().getPath();
        StringBuilder path = new StringBuilder();

        for (int i = 1; i < list.length; i++) {
            if (i > 1) {
                path.append('.');
            }

            path.append(list[i].toString());
        }

        String key = path.toString();
        if (decks.containsKey(key)) {
            layout.show(deck, key);
        } else {
            layout.show(deck, BLANK);
        }

        deck.repaint();
    }

    /**
     * A hash of components
     */
    protected Map<String,Component> comps;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3616451198199345203L;

    /**
     * A custom data model for the TreeConfig Tree
     * 
     * @author Joe Walker
     */
    class AdvancedConfigureTreeModel extends ConfigureTreeModel {
        /*
         * (non-Javadoc)
         * 
         * @see
         * org.crosswire.common.config.swing.TreeConfigEditor.ConfigureTreeModel
         * #getChildren(java.lang.String)
         */
        @Override
        protected List<String> getChildren(String path) {
            List<String> retcode = new ArrayList<String>();

            for (Choice choice : config) {
                if (choice.isHidden()) {
                    continue;
                }

                String temp = choice.getKey();

                if (temp.startsWith(path) && !temp.equals(path)) {
                    // Chop off the similar start
                    temp = temp.substring(path.length());
                    if (temp.charAt(0) == '.') {
                        temp = temp.substring(1);
                    }

                    // Chop off all after the first dot
                    int dotPos = temp.indexOf('.');
                    if (dotPos != -1) {
                        temp = temp.substring(0, dotPos);
                    }

                    // Add it to the list if needed
                    if (temp.length() > 0 && !retcode.contains(temp)) {
                        retcode.add(temp);
                    }
                }
            }

            return retcode;
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
         */
        @Override
        public Object getChild(Object parent, int index) {
            if (parent instanceof CompNode) {
                return null;
            }

            String path = ((Node) parent).getFullName();
            List<String> children = getChildren(path);

            if (children.isEmpty()) {
                return new CompNode(path);
            }

            String name = children.get(index);
            return new Node(path, name);
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
         */
        @Override
        public int getChildCount(Object parent) {
            if (parent instanceof CompNode) {
                return 0;
            }

            String path = ((Node) parent).getFullName();
            int children = getChildren(path).size();
            if (children == 0) {
                children = 1;
            }

            return children;
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
         */
        @Override
        public boolean isLeaf(Object node) {
            return node instanceof CompNode;
        }
    }

    /**
     * Simple Tree Node
     */
    static class CompNode {
        /**
         * Create a node with a name and path
         */
        public CompNode(String path) {
            this.path = path;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return path;
        }

        /**
         * The path to us
         */
        public String getFullName() {
            return path;
        }

        /**
         * The path to us
         */
        private String path;
    }

    /**
     * The renderer for our tree
     */
    class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax
         * .swing.JTree, java.lang.Object, boolean, boolean, boolean, int,
         * boolean)
         */
        @Override
        public Component getTreeCellRendererComponent(JTree jtree, Object value, boolean isselected, boolean expanded, boolean leaf, int row, boolean focus) {
            if (!(value instanceof CompNode)) {
                return super.getTreeCellRendererComponent(jtree, value, isselected, expanded, leaf, row, focus);
            }

            JComponent comp = (JComponent) comps.get(value.toString());

            if (comp == null) {
                return super.getTreeCellRendererComponent(jtree, value, isselected, expanded, leaf, row, focus);
            }

            if (isselected) {
                comp.setBorder(BorderFactory.createLineBorder(Color.black));
            } else {
                comp.setBorder(BorderFactory.createEmptyBorder());
            }

            return comp;
        }

        /**
         * Serialization ID
         */
        private static final long serialVersionUID = 3256720688860576049L;
    }
}
