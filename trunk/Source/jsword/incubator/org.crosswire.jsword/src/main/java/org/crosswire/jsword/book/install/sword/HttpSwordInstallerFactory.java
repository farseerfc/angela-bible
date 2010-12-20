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
 * ID: $Id: HttpSwordInstallerFactory.java 1320 2007-05-07 01:59:06Z dmsmith $
 */
package org.crosswire.jsword.book.install.sword;

import java.util.regex.Pattern;

import org.crosswire.jsword.book.install.Installer;
import org.crosswire.jsword.book.install.InstallerFactory;

/**
 * A Factory for instances of HttpSwordInstaller.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Mark Goodwin [goodwinster at gmail dot com]
 * @author Joe Walker [joe at eireneh dot com]
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class HttpSwordInstallerFactory implements InstallerFactory
{
    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.install.InstallerFactory#createInstaller()
     */
    public Installer createInstaller()
    {
        return new HttpSwordInstaller();
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.install.InstallerFactory#createInstaller(java.lang.String)
     */
    public Installer createInstaller(String installerDefinition)
    {
        String[] parts = commaPattern.split(installerDefinition, 6);
        switch (parts.length)
        {
            case 4:
                return createOldInstaller(parts);
            case 6:
                return createInstaller(parts);
            default:
                throw new IllegalArgumentException(Msg.INVALID_DEFINITION.toString(installerDefinition));
        }

    }

    private Installer createInstaller(String[] parts)
    {
        AbstractSwordInstaller reply = new HttpSwordInstaller();

        reply.setHost(parts[0]);
        reply.setPackageDirectory(parts[1]);
        reply.setCatalogDirectory(parts[2]);
        if (parts[3].length() > 0)
        {
            reply.setProxyHost(parts[3]);
            if (parts[4].length() > 0)
            {
                reply.setProxyPort(Integer.valueOf(parts[4]));
            }
        }

        return reply;
    }

    private Installer createOldInstaller(String[] parts)
    {
        AbstractSwordInstaller reply = new HttpSwordInstaller();

        reply.setHost(parts[0]);
        reply.setPackageDirectory(parts[1] + '/' + PACKAGE_DIR);
        reply.setCatalogDirectory(parts[1] + '/' + LIST_DIR);
        if (parts[2].length() > 0)
        {
            reply.setProxyHost(parts[2]);
            if (parts[3].length() > 0)
            {
                reply.setProxyPort(Integer.valueOf(parts[3]));
            }
        }

        return reply;
    }

    /**
     * The relative path of the dir holding the zip files
     */
    protected static final String PACKAGE_DIR = "packages/rawzip"; //$NON-NLS-1$

    /**
     * The relative path of the dir holding the index file
     */
    private static final String LIST_DIR = "raw"; //$NON-NLS-1$


    private Pattern commaPattern = Pattern.compile(","); //$NON-NLS-1$
}
