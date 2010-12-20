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
 * ID: $Id: IndexDownloader.java 2021 2010-11-25 03:31:42Z dmsmith $
 */
package org.crosswire.jsword.util;

import java.io.IOException;
import java.net.URI;

import org.crosswire.common.util.NetUtil;
import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.install.InstallException;
import org.crosswire.jsword.book.install.Installer;
import org.crosswire.jsword.index.IndexManager;
import org.crosswire.jsword.index.IndexManagerFactory;
import org.crosswire.jsword.index.IndexStatus;

/**
 * .
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public final class IndexDownloader {
    /**
     * Prevent instantiation
     */
    private IndexDownloader() {
    }

    /**
     * Download and install a search index
     * 
     * @param book
     *            The book to get an index for
     */
    public static void downloadIndex(Book book, Installer installer) throws IOException, InstallException, BookException {
        // Get a temp home
        URI tempDownload = NetUtil.getTemporaryURI(TEMP_PREFIX, TEMP_SUFFIX);

        IndexStatus finalStatus = IndexStatus.UNDONE;
        try {
            // Now we know what installer to use, download to the temp file
            installer.downloadSearchIndex(book, tempDownload);

            // And install from that file.
            IndexManager idxman = IndexManagerFactory.getIndexManager();
            book.setIndexStatus(IndexStatus.CREATING);
            idxman.installDownloadedIndex(book, tempDownload);
            finalStatus = IndexStatus.DONE;
        } finally {
            book.setIndexStatus(finalStatus);
            // tidy up after ourselves
            if (tempDownload != null) {
                NetUtil.delete(tempDownload);
            }
        }
    }

    /**
     * Temp file prefix
     */
    private static final String TEMP_PREFIX = "jsword-index";

    /**
     * Temp file suffix
     */
    private static final String TEMP_SUFFIX = "dat";
}
