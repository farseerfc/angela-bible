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
 * ID: $Id: XalanProcess.java 1462 2007-07-02 02:32:23Z dmsmith $
 */
package org.crosswire.common.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Allows xalan's xslt process class to be invoked as a command line
 * application. Java 5 has renamed the main routine to _main. This class
 * normalizes the difference between Java 1.4 and 1.5 (aka 5).
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class XalanProcess
{
    /**
     * This is a utility class so the constructor is hidden.
     */
    private XalanProcess()
    {
    }
    /**
     * Run xalan's xslt process main.
     * @param args
     */
    // This main is commented for checkstyle
    public static void main(String[] args)
    {
        Class clazz = null;
        Method main = null;
        try
        {
            // Try for 1.4.x
            clazz = Class.forName("org.apache.xalan.xslt.Process"); //$NON-NLS-1$
            main = clazz.getMethod("main", new Class[] {String[].class}); //$NON-NLS-1$
        }
        catch (ClassNotFoundException e)
        {
            try
            {
                // Try for 1.5.x
                clazz = Class.forName("com.sun.org.apache.xalan.internal.xslt.Process"); //$NON-NLS-1$
                main = clazz.getMethod("_main", new Class[] {String[].class}); //$NON-NLS-1$
            }
            catch (ClassNotFoundException e1)
            {
                e1.printStackTrace(System.err);
            }
            catch (SecurityException e1)
            {
                e1.printStackTrace(System.err);
            }
            catch (NoSuchMethodException e1)
            {
                e1.printStackTrace(System.err);
            }
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace(System.err);
            return;
        }

        try
        {
            if (main != null)
            {
                main.invoke(null, new Object[] { args });
            }
        }
        catch (IllegalArgumentException e)
        {
             e.printStackTrace(System.err);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace(System.err);
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace(System.err);
        }
    }
}
