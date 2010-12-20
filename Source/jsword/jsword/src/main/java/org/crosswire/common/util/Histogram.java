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
 * ID: $Id: Histogram.java 2054 2010-12-10 22:12:09Z dmsmith $
 */
package org.crosswire.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple implementation of a histogram. It would be nice to enhance it to
 * order on frequency.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [ dmsmith555 at yahoo dot com]
 */
public class Histogram {
    /**
     * Create an empty histogram
     */
    public Histogram() {
        hist = new HashMap<String,Counter>();
    }

    /**
     * note that this key has been seen one time more than before.
     * 
     * @param key
     */
    public void increment(String key) {
        Counter counter = hist.get(key);
        if (counter == null) {
            counter = new Counter();
            hist.put(key, counter);
        }
        counter.increment();
    }

    public void clear() {
        hist.clear();
    }

    /**
     * The format of the histogram is an unordered list of string and the counts
     * of the number of times it has been seen.
     * 
     * @return the resultant histogram
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (Map.Entry<String,Counter> entry : hist.entrySet()) {
            buf.append(entry.getKey());
            buf.append('\t');
            buf.append(entry.getValue().toString());
            buf.append('\n');
        }
        return buf.toString();
    }

    /**
     * Trivial mutable counting integer class.
     */
    private static class Counter {
        public Counter() {
        }

        public void increment() {
            counter++;
        }

        @Override
        public String toString() {
            return Integer.toString(counter);
        }

        private int counter;
    }

    private Map<String,Counter> hist;

}
