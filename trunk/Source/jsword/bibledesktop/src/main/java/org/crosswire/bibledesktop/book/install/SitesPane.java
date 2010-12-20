/**
 * Distribution License:
 * BibleDesktop is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2 as published by
 * the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/gpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2005
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: SitesPane.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.bibledesktop.book.install;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.crosswire.common.progress.swing.JobsProgressBar;
import org.crosswire.common.swing.ActionFactory;
import org.crosswire.common.swing.GuiUtil;
import org.crosswire.common.swing.desktop.LayoutPersistence;
import org.crosswire.jsword.book.install.InstallManager;
import org.crosswire.jsword.book.install.Installer;
import org.crosswire.jsword.book.install.InstallerEvent;
import org.crosswire.jsword.book.install.InstallerListener;

/**
 * A SitesPane manages library sites.
 * <p>
 * so start one of these call:
 * 
 * <pre>
 * sitesPane = new SitesPane();
 * sitesPane.showInDialog(parent);
 * </pre>
 * 
 * @see gnu.gpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class SitesPane extends JPanel {
    /**
     * Simple ctor
     */
    public SitesPane() {
        init();

        imanager = new InstallManager();
        installers = imanager.getInstallers();

        addAllInstallers();

        imanager.addInstallerListener(new SiteInstallerListener());
        GuiUtil.applyDefaultOrientation(this);
    }

    /**
     * Build the GUI components
     */
    private void init() {
        actions = new ActionFactory(Msg.class, this);

        tabMain = new JTabbedPane();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(tabMain, BorderLayout.CENTER);
        this.add(new JobsProgressBar(true), BorderLayout.SOUTH);
    }

    /**
     * Re-create the list of installers
     */
    protected final void addAllInstallers() {
        // Now add panels for book installation sites
        for (String name : installers.keySet()) {
            Installer installer = installers.get(name);

            SitePane site = new SitePane(installer);
            tabMain.add(name, site);
        }

        // TRANSLATOR: Label for the tab showing the installed books.
        tabMain.add(Msg.gettext("Installed Books"), new SitePane());
    }

    /**
     * Remove all the non-local installers
     */
    protected void removeAllInstallers() {
        tabMain.removeAll();
    }

    /**
     * Add a site to the list of install sources.
     */
    public void doManageSites() {
        EditSitePane edit = new EditSitePane(imanager);
        edit.showInDialog(this);
    }

    /**
     * We are done, close the window
     */
    public void doSitesClose() {
        if (dlgMain != null) {
            LayoutPersistence.instance().saveLayout(dlgMain);
            dlgMain.setVisible(false);
        }
    }

    /**
     * Open this Panel in it's own dialog box.
     */
    public void showInDialog(Component parent) {
        Frame root = JOptionPane.getFrameForComponent(parent);
        dlgMain = new JDialog(root);
        dlgMain.getContentPane().setLayout(new BorderLayout());
        dlgMain.getContentPane().add(this, BorderLayout.CENTER);
        dlgMain.getContentPane().add(createButtons(), BorderLayout.SOUTH);
        // TRANSLATOR: Title to the window that allows the management of books. 
        dlgMain.setTitle(Msg.gettext("Available Books"));
        dlgMain.setResizable(true);
        // dlgMain.setModal(true);
        // Set the name for Persistent Layout
        dlgMain.setName("Sites");
        dlgMain.addWindowListener(new WindowAdapter() {
            /* (non-Javadoc)
             * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
             */
            @Override
            public void windowClosed(WindowEvent ev) {
                doSitesClose();
            }
        });
        dlgMain.setLocationRelativeTo(parent);

        // Restore window size, position, and layout if previously opened,
        // otherwise use defaults.
        LayoutPersistence layoutPersistence = LayoutPersistence.instance();
        if (layoutPersistence.isLayoutPersisted(dlgMain)) {
            layoutPersistence.restoreLayout(dlgMain);
        } else {
            dlgMain.setSize(750, 500);
            GuiUtil.centerOnScreen(dlgMain);
        }

        dlgMain.setVisible(true);
        dlgMain.toFront();
        GuiUtil.applyDefaultOrientation(dlgMain);
    }

    /**
     *
     */
    private Component createButtons() {
        if (pnlButtons == null) {
            JButton btnOK = new JButton(actions.getAction(CLOSE));

            JButton btnAdd = new JButton(actions.getAction(EDIT_SITE));

            pnlButtons = new JPanel();
            pnlButtons.setLayout(new FlowLayout(FlowLayout.TRAILING));
            pnlButtons.add(btnAdd, null);
            pnlButtons.add(btnOK);
        }
        return pnlButtons;

    }

    /**
     * Serialization support.
     * 
     * @param is
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        actions = new ActionFactory(SitesPane.class, this);
        imanager = new InstallManager();
        installers = imanager.getInstallers();

        is.defaultReadObject();

        addAllInstallers();

        imanager.addInstallerListener(new SiteInstallerListener());
    }

    /**
     * Local listener for install events.
     */
    class SiteInstallerListener implements InstallerListener {

        /* (non-Javadoc)
         * @see org.crosswire.jsword.book.install.InstallerListener#installerAdded(org.crosswire.jsword.book.install.InstallerEvent)
         */
        public void installerAdded(InstallerEvent ev) {
            Installer installer = ev.getInstaller();
            String name = imanager.getInstallerNameForInstaller(installer);

            SitePane site = new SitePane(installer);
            tabMain.add(name, site);
        }

        /* (non-Javadoc)
         * @see org.crosswire.jsword.book.install.InstallerListener#installerRemoved(org.crosswire.jsword.book.install.InstallerEvent)
         */
        public void installerRemoved(InstallerEvent ev) {
            // This gets tricky because if you add a site with a new name
            // but the same details as an old one, then the old name goes
            // so we can't get the old name to remove it's tab (and anyway
            // we would have to do a search through all the tabs to find it
            // by name)
            // So we just nuke all the tabs and re-create them
            removeAllInstallers();
            addAllInstallers();
        }
    }

    private static final String CLOSE = "SitesClose";
    private static final String EDIT_SITE = "ManageSites";

    /**
     * The known installers fetched from InstallManager
     */
    private transient Map<String, Installer> installers;

    /**
     * The current installer
     */
    protected transient InstallManager imanager;

    private transient ActionFactory actions;

    /*
     * GUI Components
     */
    private JDialog dlgMain;
    private JPanel pnlButtons;
    protected JTabbedPane tabMain;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3258126947069605936L;
}
