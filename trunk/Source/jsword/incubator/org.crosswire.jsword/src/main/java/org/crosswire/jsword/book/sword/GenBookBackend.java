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
 * ID: $Id: GenBookBackend.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.book.sword;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.crosswire.common.activate.Activator;
import org.crosswire.common.activate.Lock;
import org.crosswire.common.util.FileUtil;
import org.crosswire.common.util.Logger;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.passage.DefaultKeyList;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.TreeKey;

/**
 * Backend for General Books.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class GenBookBackend extends AbstractBackend
{
    /**
     * Simple ctor
     */
    public GenBookBackend(SwordBookMetaData sbmd) throws BookException
    {
        super(sbmd);

        index = new TreeKeyIndex(sbmd);

        String path = getExpandedDataPath();
        bdtFile = new File(path + EXTENSION_BDT);

        if (!bdtFile.canRead())
        {
            throw new BookException(Msg.READ_FAIL, new Object[] { bdtFile.getAbsolutePath() });
        }

    }

    /* (non-Javadoc)
     * @see org.crosswire.common.activate.Activatable#activate(org.crosswire.common.activate.Lock)
     */
    public final void activate(Lock lock)
    {
        try
        {
            bdtRaf = new RandomAccessFile(bdtFile, FileUtil.MODE_READ);
        }
        catch (IOException ex)
        {
            log.error("failed to open files", ex); //$NON-NLS-1$
            bdtRaf = null;
        }
        active = true;
    }

    /* (non-Javadoc)
     * @see org.crosswire.common.activate.Activatable#deactivate(org.crosswire.common.activate.Lock)
     */
    public final void deactivate(Lock lock)
    {
        try
        {
            if (bdtRaf != null)
            {
                bdtRaf.close();
            }
        }
        catch (IOException ex)
        {
            log.error("failed to close gen book files", ex); //$NON-NLS-1$
        }
        finally
        {
            bdtRaf = null;
        }
        active = false;

        // Also deactivate the index
        Activator.deactivate(index);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.sword.AbstractBackend#getRawText(org.crosswire.jsword.passage.Key, java.lang.String)
     */
    /* @Override */
    public String getRawText(Key key) throws BookException
    {
        checkActive();

        try
        {
            TreeNode node = find(key);
            byte [] userData = node.getUserData();

            // Some entries may be empty.
            if (userData.length == 8)
            {
                int start = SwordUtil.decodeLittleEndian32(userData, 0);
                int size = SwordUtil.decodeLittleEndian32(userData, 4);
                byte[] data = SwordUtil.readRAF(bdtRaf, start, size);
                decipher(data);
                return SwordUtil.decode(key, data, getBookMetaData().getBookCharset());
            }

            return ""; //$NON-NLS-1$
        }
        catch (IOException e)
        {
            throw new BookException(Msg.READ_FAIL);
        }
    }

    /**
     * Given a Key, find the TreeNode for it.
     * @param key The key to use for searching
     * @return the found node, null otherwise
     * @throws IOException
     */
    private TreeNode find(Key key) throws IOException
    {
        // We need to search from the root, so navigate to the root, saving as we go.
        List path = new ArrayList();
        for (Key parentKey = key; parentKey != null && parentKey.getName().length() > 0; parentKey = parentKey.getParent())
        {
            path.add(parentKey.getName());
        }

        TreeNode node = index.getRoot();

        node = index.getFirstChild(node);

        for (int i = path.size() - 1; i >= 0; i--)
        {
            String name = (String) path.get(i);

            // Search among the siblings for the current level.
            while (node != null && !name.equals(node.getName()))
            {
                if (node.hasNextSibling())
                {
                    node = index.getNextSibling(node);
                }
                else
                {
                    log.error("Could not find " + name); //$NON-NLS-1$
                    node = null;
                }
            }

            // If we have found it but have not exhausted the path
            // we need to get more
            if (node != null && name.equals(node.getName()) && i > 0)
            {
                node = index.getFirstChild(node);
            }
        }

        // At this point we have either found it, returning it or have not, returning null
        if (node != null && node.getName().equals(key.getName()))
        {
            return node;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.sword.AbstractBackend#readIndex()
     */
    /* @Override */
    public Key readIndex()
    {
        SwordBookMetaData bmd = getBookMetaData();
        Key reply = new DefaultKeyList(null, bmd.getName());

        try
        {
            TreeNode node = index.getRoot();
            reply = new TreeKey(node.getName(), null);
            doReadIndex(node, reply);
        }
        catch (IOException e)
        {
            log.error("Could not get read GenBook index", e); //$NON-NLS-1$
        }

        return reply;
    }

    /**
     * A helper function to recursively read the entire tree.
     *
     * @param parentNode the current node whose children are being sought
     * @param parentKey
     * @throws IOException
     */
    private void doReadIndex(TreeNode parentNode, Key parentKey) throws IOException
    {
        TreeNode currentNode = parentNode;
        if (currentNode.hasChildren())
        {
            TreeNode childNode = index.getFirstChild(currentNode);
            do
            {
                TreeKey childKey = new TreeKey(childNode.getName(), parentKey);
                parentKey.addAll(childKey);

                // Build the tree as deep as possible
                doReadIndex(childNode, childKey);

                if (!childNode.hasNextSibling())
                {
                    break;
                }

                childNode = index.getNextSibling(childNode);
            }
            while (true);
        }
    }

    /**
     * Helper method so we can quickly activate ourselves on access
     */
    protected final void checkActive()
    {
        if (!active)
        {
            Activator.activate(this);
        }
    }

    /**
     * Raw GenBook file extensions
     */
    private static final String EXTENSION_BDT = ".bdt"; //$NON-NLS-1$

    /**
     * The raw data file
     */
    private File bdtFile;

    /**
     * The random access file for the raw data
     */
    private RandomAccessFile bdtRaf;

    /**
     * The raw index file
     */
    private TreeKeyIndex index;
    /**
     * Are we active
     */
    private boolean active;

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(GenBookBackend.class);
}
