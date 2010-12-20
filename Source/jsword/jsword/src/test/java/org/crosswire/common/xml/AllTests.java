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
 * ID: $Id: AllTests.java 2012 2010-11-11 23:24:41Z dmsmith $
 */
package org.crosswire.common.xml;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.crosswire.common.xml");
        // $JUnit-BEGIN$
        suite.addTestSuite(XMLUtilTest.class);
        // $JUnit-END$
        return suite;
    }
}
