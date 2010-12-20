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
 * ID: $Id: IndexResolver.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.bibledesktop.book.install;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.crosswire.common.swing.CWOptionPane;
import org.crosswire.common.util.Logger;
import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.install.InstallException;
import org.crosswire.jsword.book.install.InstallManager;
import org.crosswire.jsword.book.install.Installer;
import org.crosswire.jsword.index.IndexManagerFactory;
import org.crosswire.jsword.util.IndexDownloader;

/**
 * A class to prompt the user to download or create a search index and to do
 * carry out the users wishes.
 * 
 * @see gnu.gpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public final class IndexResolver {
    /**
     * Prevent instantiation
     */
    private IndexResolver() {
    }

    // /**
    // * The options that we show to the user for how to get a search index
    // */
    // private static Object[] options = new Object[]
    // {
    // Msg.OPTION_DOWNLOAD,
    // Msg.OPTION_GENERATE,
    // Msg.OPTION_CANCEL,
    // };

    /**
     * @param parent
     * 
     */
    public static void scheduleIndex(Book book, Component parent) {
        // LATER(DMS): Enable this when we have indexes to download
        // String title = Msg.HOW_MESSAGE_TITLE.toString();
        // Msg msg = Msg.HOW_MESSAGE;
        // int choice = CWOptionPane.showOptionDialog(parent, msg, title,
        // JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
        // options, options[0]);

        int choice = 1;

        switch (choice) {
        case 0: // download
            Installer installer = selectInstaller(parent);
            if (installer != null) {
                Exception ex = null;
                try {
                    IndexDownloader.downloadIndex(book, installer);
                } catch (InstallException e) {
                    ex = e;

                } catch (BookException e) {
                    ex = e;
                } catch (IOException e) {
                    ex = e;
                }

                if (ex != null) {
                    log.error("index download failed: ", ex);
                    // TRANSLATOR: Title to a dialog that asks whether the user wants to generate an index.
                    // Currently unused.
                    String gtitle = Msg.gettext("Download or generate?");
                    // TRANSLATOR: The download failed for one reason or another. So now the user is asked whether the index should be generated.
                    // Currently unused.
                    String gmsg = Msg.gettext("Downloading failed.\nDo you wish to generate an index anyway?");
                    int yn = CWOptionPane.showConfirmDialog(parent, gmsg, gtitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (yn == JOptionPane.YES_OPTION) {
                        IndexManagerFactory.getIndexManager().scheduleIndexCreation(book);
                    }
                }
            }
            break;

        case 1: // generate
            IndexManagerFactory.getIndexManager().scheduleIndexCreation(book);
            break;

        default: // cancel
            break;
        }
    }

    /**
     * Pick an installer
     * 
     * @param parent
     *            A component to tie dialogs to
     * @return The chosen installer or null if the user cancelled.
     */
    private static Installer selectInstaller(Component parent) {
        // Pick an installer
        InstallManager insman = new InstallManager();
        Map<String,Installer> installers = insman.getInstallers();
        Installer installer = null;
        if (installers.size() == 1) {
            for (Installer anInstaller : installers.values()) {
                installer = anInstaller;
                break;
            }
            assert installer != null;
        } else {
            JComboBox choice = new JComboBox(new InstallManagerComboBoxModel(insman));
            // TRANSLATOR: Label for a list of index download sites.
            // Currently unused.
            JLabel label = new JLabel(Msg.gettext("Which download site do you wish to use?"));
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(label, BorderLayout.NORTH);
            panel.add(choice, BorderLayout.CENTER);

            // TRANSLATOR: Title for a dialog that asks whether the user should download the index.
            // Currently unused.
            String title = Msg.gettext("Download an index?");

            int yn = CWOptionPane.showConfirmDialog(parent, panel, title, JOptionPane.YES_OPTION);
            if (yn == JOptionPane.YES_OPTION) {
                installer = (Installer) choice.getSelectedItem();
            }
        }

        return installer;
    }

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(IndexResolver.class);
}
