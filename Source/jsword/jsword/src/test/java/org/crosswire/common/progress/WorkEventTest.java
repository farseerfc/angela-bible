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
 * ID: $Id: WorkEventTest.java 2039 2010-12-04 13:53:31Z dmsmith $
 */
package org.crosswire.common.progress;

import junit.framework.TestCase;

/**
 * JUnit Test.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class WorkEventTest extends TestCase {
    /**
     * Constructor for WorkEventTest.
     * 
     * @param arg0
     */
    public WorkEventTest(String arg0) {
        super(arg0);
    }

    public void testGetJob() {
        Progress job = JobManager.createJob("wibble");
        job.beginJob("wibble");
        WorkEvent ev = new WorkEvent(job);

        assertEquals(ev.getJob(), job);
        assertEquals(ev.getSource(), job);
    }
}
