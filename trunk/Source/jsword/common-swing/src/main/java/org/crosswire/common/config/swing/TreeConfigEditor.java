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
 * ID: $Id: TreeConfigEditor.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.common.config.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.crosswire.common.config.Choice;
import org.crosswire.common.config.Config;
import org.crosswire.common.swing.CWScrollPane;
import org.crosswire.common.swing.FixedSplitPane;
import org.crosswire.common.swing.FormPane;
import org.crosswire.common.swing.GuiUtil;

/**
 * A Configuration Editor that provides a tree for navigating to options.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class TreeConfigEditor extends AbstractConfigEditor {
    /**
     * <br />
     * Danger - this method is not called by the TreeConfigEditor constructor,
     * it is called by the AbstractConfigEditor constructor so any field
     * initializers will be called AFTER THIS METHOD EXECUTES so don't use field
     * initializers.
     */
    @Override
    protected void initializeGUI() {
        JPanel panel = new JPanel();
        JPanel blank = new JPanel();
        DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
        // prevent truncation (This is a hack!)
        dtcr.setPreferredSize(new Dimension(1200, 100));
        ctm = new ConfigureTreeModel();
        tree = new JTree();
        title = new JLabel();
        deck = new JPanel();
        layout = new CardLayout();

        // TRANSLATOR: Label indicating that the user should select a preference category.
        blank.add(new JLabel(UserMsg.gettext("Select a preference category")));

        deck.setLayout(layout);
        deck.add(blank, BLANK);

        dtcr.setLeafIcon(TASK_ICON_SMALL);

        // These settings will need to change if we have a true tree.
        tree.setModel(ctm);
        tree.setCellRenderer(dtcr);
        tree.setShowsRootHandles(false);
        tree.setRootVisible(false);
        tree.putClientProperty("JTree.lineStyle", "None");
        tree.setSelectionRow(0);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent ev) {
                selectCard();
            }
        });

        title.setIcon(TASK_ICON_LARGE);
        title.setFont(getFont().deriveFont(Font.PLAIN, 16));
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        title.setBackground(Color.gray);
        title.setForeground(Color.white);
        title.setOpaque(true);
        // TRANSLATOR: This is the label for the banner when one opens Options/Preferences.
        title.setText(UserMsg.gettext("Preferences"));
        title.setAlignmentX(SwingConstants.LEADING);

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(BorderLayout.NORTH, title);
        panel.add(BorderLayout.CENTER, deck);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JSplitPane sptMain = new FixedSplitPane();
        sptMain.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        sptMain.setDividerLocation(0.25D);
        sptMain.setResizeWeight(0.25D);
        sptMain.setLeftComponent(new CWScrollPane(tree));
        sptMain.setRightComponent(panel);

        add(BorderLayout.CENTER, sptMain);
        add(BorderLayout.SOUTH, new ButtonPane(this));
        GuiUtil.applyDefaultOrientation(this);
    }

    /**
     * Updates to the tree that we need to do on any change
     */
    @Override
    protected void updateTree() {
        // expand the tree
        int row = 0;
        while (row < tree.getRowCount()) {
            tree.expandRow(row++);
        }

        ctm.fireTreeStructureChanged(this);
    }

    /**
     * Add a Choice to our set of panels
     */
    @Override
    protected void addChoice(Choice model) {
        if (model.isHidden()) {
            return;
        }

        super.addChoice(model);

        // Sort the tree out
        String path = Config.getPath(model.getFullPath());
        FormPane card = decks.get(path);
        if (card != null && card.getParent() == null) {
            JScrollPane scroll = new CWScrollPane(card);
            scroll.setBorder(BorderFactory.createEmptyBorder());
            deck.add(scroll, path);
        }
    }

    /**
     * Add a Choice to our set of panels
     */
    @Override
    protected void removeChoice(Choice model) {
        super.removeChoice(model);

        // Sort the tree out
        String path = Config.getPath(model.getFullPath());
        FormPane card = decks.get(path);
        if (card != null && card.isEmpty()) {
            deck.remove(card.getParent());
        }
    }

    /**
     * Used to update the configuration panel whenever someone selects a
     * different item form the tree on the LHS of the configuration dialog.
     */
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
        GuiUtil.applyDefaultOrientation(deck);
        if (decks.containsKey(key)) {
            layout.show(deck, key);
        } else {
            layout.show(deck, BLANK);
        }

        deck.repaint();
    }

    /**
     * The name of the blank tab
     */
    protected static final String BLANK = "$$BLANK$$";

    /**
     * The tree containing the Field sets
     */
    protected JTree tree;

    /**
     * The custom tree model for the tree
     */
    protected transient ConfigureTreeModel ctm;

    /**
     * The title for the config panels
     */
    protected JLabel title;

    /**
     * Contains the configuration panels
     */
    protected JPanel deck;

    /**
     * Layout for the config panels
     */
    protected CardLayout layout;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3256720688860576049L;

    /**
     * A custom data model for the TreeConfig Tree
     * 
     * @author Claude Duguay
     * @author Joe Walker
     */
    protected class ConfigureTreeModel implements TreeModel {
        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.tree.TreeModel#getRoot()
         */
        public Object getRoot() {
            return root;
        }

        /**
         * Get a List of the children rooted at path
         */
        protected List<String> getChildren(String path) {
            List<String> retcode = new ArrayList<String>();

            Iterator<Choice> it = config.iterator();
            while (it.hasNext()) {
                Choice choice = it.next();
                if (choice.isHidden()) {
                    continue;
                }

                String temp = choice.getFullPath();

                if (temp.startsWith(path) && !temp.equals(path)) {
                    // Chop off the similar start
                    temp = temp.substring(path.length());
                    if (temp.charAt(0) == '.') {
                        temp = temp.substring(1);
                    }

                    // Chop off all after the first dot
                    int dot_pos = temp.indexOf('.');
                    if (dot_pos == -1) {
                        continue;
                    }
                    temp = temp.substring(0, dot_pos);

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
        public Object getChild(Object parent, int index) {
            String path = ((Node) parent).getFullName();
            String name = getChildren(path).get(index);
            return new Node(path, name);
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
         */
        public int getChildCount(Object parent) {
            String path = ((Node) parent).getFullName();
            return getChildren(path).size();
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
         */
        public boolean isLeaf(Object node) {
            String path = ((Node) node).getFullName();
            return getChildren(path).size() == 0;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath
         * , java.lang.Object)
         */
        public void valueForPathChanged(TreePath path, Object value) {
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object,
         * java.lang.Object)
         */
        public int getIndexOfChild(Object parent, Object child) {
            String path = ((Node) parent).getFullName();
            List<String> children = getChildren(path);
            return children.indexOf(child);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.tree.TreeModel#addTreeModelListener(javax.swing.event
         * .TreeModelListener)
         */
        public void addTreeModelListener(TreeModelListener li) {
            listeners.add(TreeModelListener.class, li);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event
         * .TreeModelListener)
         */
        public void removeTreeModelListener(TreeModelListener li) {
            listeners.remove(TreeModelListener.class, li);
        }

        /**
         * Notify all listeners that have registered interest for notification
         * on this event type. The event instance is lazily created using the
         * parameters passed into the fire method.
         * 
         * @see EventListenerList
         */
        protected void fireTreeStructureChanged(Object source) {
            fireTreeStructureChanged(source, new Object[] {
                root
            });
        }

        /**
         * Notify all listeners that have registered interest for notification
         * on this event type. The event instance is lazily created using the
         * parameters passed into the fire method.
         * 
         * @see EventListenerList
         */
        protected void fireTreeStructureChanged(Object source, Object[] path) {
            // Guaranteed to return a non-null array
            Object[] array = listeners.getListenerList();
            TreeModelEvent ev = null;

            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = array.length - 2; i >= 0; i -= 2) {
                if (array[i] == TreeModelListener.class) {
                    // Lazily create the event:
                    if (ev == null) {
                        ev = new TreeModelEvent(source, path);
                    }

                    ((TreeModelListener) array[i + 1]).treeStructureChanged(ev);
                }
            }
        }

        /**
         * The Listeners.
         */
        protected EventListenerList listeners = new EventListenerList();

        /**
         * The root node
         */
        private Node root = new Node("", "");
    }

    /**
     * Simple Tree Node
     */
    protected static class Node {
        /**
         * Create a node with a name and path
         */
        protected Node(String path, String name) {
            this.path = path;
            this.name = name;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return name;
        }

        /**
         * The path to us
         */
        public String getFullName() {
            if (path.length() == 0 || name.length() == 0) {
                return path + name;
            }
            return path + "." + name;
        }

        /**
         * The displayed string
         */
        private String name;

        /**
         * The path to us
         */
        private String path;
    }
}
