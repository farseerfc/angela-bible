package org.crosswire.common.diff;

/* Test harness for diff_match_patch.java
 *
 * Version 2.2, May 2007
 * If debugging errors, start with the first reported error, 
 * subsequent tests often rely on earlier ones.
 */

import junit.framework.TestCase;

public class CommonalityTest extends TestCase {

    @Override
    protected void setUp() {
    }

    public void testPrefix() {
        // Detect and remove any common prefix.
        assertEquals("Commonality.prefix: Null case.", 0, Commonality.prefix("abc", "xyz"));
        assertEquals("Commonality.prefix: Non-null case.", 4, Commonality.prefix("1234abc", "1234xyz"));
    }

    public void testSuffix() {
        // Detect and remove any common suffix.
        assertEquals("Commonality.suffix: Null case.", 0, Commonality.suffix("abc", "xyz"));
        assertEquals("Commonality.suffix: Non-null case.", 4, Commonality.suffix("abc1234", "xyz1234"));
    }

    public void testHalfmatch() {
        // Detect a halfmatch.
        assertNull("Commonality.halfMatch: No match.", Commonality.halfMatch("1234567890", "abcdef"));
        assertEquals(
                "Commonality.halfMatch: Single Match #1.", new CommonMiddle("12", "90", "a", "z", "345678"), Commonality.halfMatch("1234567890", "a345678z"));
        assertEquals(
                "Commonality.halfMatch: Single Match #2.", new CommonMiddle("a", "z", "12", "90", "345678"), Commonality.halfMatch("a345678z", "1234567890"));
        assertEquals(
                "Commonality.halfMatch: Multiple Matches #1.", new CommonMiddle("12123", "123121", "a", "z", "1234123451234"), Commonality.halfMatch("121231234123451234123121", "a1234123451234z"));
        assertEquals(
                "Commonality.halfMatch: Multiple Matches #2.", new CommonMiddle("", "-=-=-=-=-=", "x", "", "x-=-=-=-=-=-=-="), Commonality.halfMatch("x-=-=-=-=-=-=-=-=-=-=-=-=", "xx-=-=-=-=-=-=-="));
        assertEquals(
                "Commonality.halfMatch: Multiple Matches #3.", new CommonMiddle("-=-=-=-=-=", "", "", "y", "-=-=-=-=-=-=-=y"), Commonality.halfMatch("-=-=-=-=-=-=-=-=-=-=-=-=y", "-=-=-=-=-=-=-=yy"));
    }
}
