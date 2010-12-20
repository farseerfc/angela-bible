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
 * ID: $Id: StackTrace.java 1264 2007-03-30 18:00:18Z dmsmith $
 */
package org.crosswire.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Unscramble the current stack, and present the data from it to the
 * user in various forms. This code is slightly dodgy in that it
 * makes use of the way exceptions print their stack straces, however
 * it is probably a safe enough assumption for the moment.
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public final class StackTrace
{
    /**
     * Generate a stack trace an model it
     */
    public StackTrace()
    {
        init(new Throwable(), 2);
    }

    /**
     * We already have an Exception that we'd like to model
     * @param ex The Exception to model
     */
    public StackTrace(Throwable ex)
    {
        init(ex, 1);
    }

    /**
     * Create a stack trace of the code at this point
     * @param ex The Throwable containing the Stack Trace
     * @param discard The number of uppermost stack frames to ignore
     */
    private void init(Throwable ex, int discard)
    {
        StringWriter sout = new StringWriter();
        ex.printStackTrace(new PrintWriter(sout));
        String msg = new String(sout.getBuffer());
        String[] calls = StringUtil.split(msg, "\n\r"); //$NON-NLS-1$

        int total = 0;
        for (int i = 0; i < calls.length - discard; i++)
        {
            String call = calls[i + discard];

            if (!(call.startsWith("Caused") || call.indexOf("...") >= 0)) //$NON-NLS-1$ //$NON-NLS-2$
            {
                total++;
            }
        }

        classNames = new String[total];
        methodNames = new String[total];
        fileNames = new String[total];
        lineNumbers = new int[total];

        int j = 0;
        for (int i = 0; i < calls.length - discard; i++)
        {
            String call = calls[i + discard];

            try
            {
                if (!(call.startsWith("Caused") || call.indexOf("...") >= 0)) //$NON-NLS-1$ //$NON-NLS-2$
                {
                    int spcIndex = call.indexOf(' ');
                    int lhsIndex = call.indexOf('(');
                    int clnIndex = call.indexOf(':');
                    int rhsIndex = call.indexOf(')');

                    String fullFn = call.substring(spcIndex + 1, lhsIndex).trim();
                    int lastDot = fullFn.lastIndexOf('.');

                    classNames[j] = fullFn.substring(0, lastDot).replace('/', '.');
                    methodNames[j] = fullFn.substring(lastDot + 1);

                    if (clnIndex != -1 && lhsIndex < clnIndex)
                    {
                        fileNames[j] = call.substring(lhsIndex + 1, clnIndex);
                        lineNumbers[j] = Integer.parseInt(call.substring(clnIndex + 1, rhsIndex));
                    }
                    else
                    {
                        fileNames[j] = call.substring(lhsIndex + 1, rhsIndex);
                        lineNumbers[j] = 0;
                    }
                    j++;
                }
            }
            catch (NumberFormatException ex2)
            {
                classNames[j] = "ParseError: "; //$NON-NLS-1$
                methodNames[j] = call;
                fileNames[j] = "Error"; //$NON-NLS-1$
                lineNumbers[j] = 0;
                j++;
            }
        }
    }

    /**
     * How many stack elements are there?
     */
    public int countStackElements()
    {
        return methodNames.length;
    }

    /**
     * Get the name of a function
     * @param level Number of calling function
     */
    public String getFunctionName(int level)
    {
        return methodNames[level];
    }

    /**
     * Get the name of a function including class name
     * @param level Number of calling function
     */
    public String getFullFunctionName(int level)
    {
        return classNames[level] + '.' + methodNames[level] + "()"; //$NON-NLS-1$
    }

    /**
     * Get the name of a class
     * @param level Number of calling function
     */
    public String getClassName(int level)
    {
        return classNames[level];
    }

    /**
     * Get the name of a file
     * @param level Number of calling function
     */
    public String getFileName(int level)
    {
        return fileNames[level];
    }

    /**
     * Get the line number within a file
     * @param level Number of calling function
     */
    public int getLineNumber(int level)
    {
        return lineNumbers[level];
    }

    public int getClassCount()
    {
        return classNames.length;
    }
    /**
     * Get the Class that owns the function
     * @param level Number of calling function
     */
    public Class getClass(int level)
    {
        try
        {
            return Class.forName(classNames[level]);
        }
        catch (ClassNotFoundException ex)
        {
            assert false : ex;
            return null;
        }
    }

    /**
     * Base class for the real enumeration implementations below
     */
    public abstract class AbstractStackIterator implements Iterator
    {
        /* (non-Javadoc)
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext()
        {
            return level < getClassCount();
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#remove()
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        /**
         * @return Returns the level.
         */
        public int getAndIncrementLevel() throws NoSuchElementException
        {
            return level++;
        }

        /**
         * Are there more stack levels
         */
        private int level;
    }

    /**
     * To iterate over the class names
     */
    public Iterator getClassNameElements()
    {
        return new AbstractStackIterator()
        {
            public Object next() throws NoSuchElementException
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                return getClassName(getAndIncrementLevel());
            }
        };
    }

    /**
     * To iterate over the function names
     */
    public Iterator getFunctionNameElements()
    {
        return new AbstractStackIterator()
        {
            public Object next() throws NoSuchElementException
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                return getFunctionName(getAndIncrementLevel());
            }
        };
    }

    /**
     * To iterate over the full function names
     */
    public Iterator getFullFunctionNameElements()
    {
        return new AbstractStackIterator()
        {
            public Object next() throws NoSuchElementException
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                return getFullFunctionName(getAndIncrementLevel());
            }
        };
    }

    /**
     * Array containing the class names
     */
    private String[] classNames;

    /**
     * Array containing the method names
     */
    private String[] methodNames;

    /**
     * Array containing the file names
     */
    private String[] fileNames;

    /**
     * Array containing the line numbers
     */
    private int[] lineNumbers;
}
