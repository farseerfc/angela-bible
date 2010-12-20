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
 * Copyright: 2007
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: PropertyMapTest.java 2056 2010-12-12 04:34:41Z dmsmith $
 */
package org.crosswire.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.MissingResourceException;

import junit.framework.TestCase;

/**
 *
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class PropertyMapTest extends TestCase {

    /**
     * @param name
     */
    public PropertyMapTest(String name) {
        super(name);
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        m = new PropertyMap();
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBasic() {
        assertEquals("Test for an element not present", null, m.get("diddly"));
        m.put("diddly", "squat");
        assertEquals("Test for a present element", "squat", m.get("diddly"));
    }

    public void testLoad() {
        InputStream in = null;
        try {
            in = ResourceUtil.getResourceAsStream(this.getClass(), "PropertyMap.properties");
            m.load(in);
        } catch (MissingResourceException e) {
            fail("Unable to find PropertyMap.properties");
        } catch (IOException e) {
            fail("Unable to read PropertyMap.properties");
        } finally {
            IOUtil.close(in);
        }
    }

    public void testRead() {
        InputStream in = null;
        try {
            in = ResourceUtil.getResourceAsStream(this.getClass(), "PropertyMap.properties");
            m.load(in);
        } catch (MissingResourceException e) {
            fail("Unable to find PropertyMap.properties");
        } catch (IOException e) {
            fail("Unable to read PropertyMap.properties");
        } finally {
            IOUtil.close(in);
        }
        assertEquals("Only one element was in the file", 1, m.size());
        assertEquals("Test that the load worked", "I am", m.get("Here"));
    }
    public void testSave() {
        m.put("Here", "Am I");
        URI uri = CWProject.instance().getWritableURI("test", FileUtil.EXTENSION_PROPERTIES);
        OutputStream out = null;
        try {
            out = NetUtil.getOutputStream(uri);
            m.store(out, "Test data can be deleted at any time");
        } catch (IOException e) {
            fail("Unable to save test.properties");
        } finally {
            IOUtil.close(out);
        }
    }

    public void testReload() {
        assertEquals("The map is empty", 0, m.size());

        InputStream is = null;
        URI uri = CWProject.instance().getWritableURI("test", FileUtil.EXTENSION_PROPERTIES);
        try {
            is = NetUtil.getInputStream(uri);
            m.load(is);
        } catch (IOException e) {
            fail("Unable to reload test.properties");
        } finally {
            IOUtil.close(is);
        }
        assertEquals("Only one element was in the file", 1, m.size());
        assertEquals("Test that the save and reload worked", "Am I", m.get("Here"));
    }

    private PropertyMap m;
}
