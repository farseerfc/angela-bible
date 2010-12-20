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
 * ID: $Id: VerseRangeTest.java 2051 2010-12-09 19:09:05Z dmsmith $
 */
package org.crosswire.jsword.passage;

import java.util.Iterator;

import junit.framework.TestCase;

/**
 * JUnit Test.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class VerseRangeTest extends TestCase {
    public VerseRangeTest(String s) {
        super(s);
    }

    VerseRange gen11_1 = null;
    VerseRange gen11_2 = null;
    VerseRange gen11_9 = null;
    VerseRange gen11_a = null;
    VerseRange gen12_1 = null;
    VerseRange gen_all = null;
    VerseRange gen_ex1 = null;
    VerseRange gen_exo = null;
    VerseRange gen_rev = null;
    VerseRange rev99_9 = null;
    VerseRange rev11_9 = null;
    VerseRange rev99_1 = null;

    Verse gen11 = null;
    Verse gen12 = null;
    Verse gen19 = null;
    Verse gen21 = null;
    Verse gen22 = null;
    Verse gen99 = null;
    Verse exo11 = null;
    Verse rev11 = null;
    Verse rev12 = null;
    Verse rev21 = null;
    Verse rev22 = null;
    Verse rev99 = null;
    Verse rev91 = null;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        gen11_1 = RestrictionType.NONE.toRange(new Verse(1, 1, 1), 1);
        gen11_2 = RestrictionType.NONE.toRange(new Verse(1, 1, 1), 2);
        gen11_9 = RestrictionType.NONE.toRange(new Verse(1, 1, 1), 31);
        gen11_a = RestrictionType.NONE.toRange(new Verse(1, 1, 1), 32);
        gen12_1 = RestrictionType.NONE.toRange(new Verse(1, 1, 2), 1);
        gen_all = RestrictionType.NONE.toRange(new Verse(1, 1, 1), 1533);
        gen_ex1 = RestrictionType.NONE.toRange(new Verse(1, 1, 1), 1534);
        gen_exo = RestrictionType.NONE.toRange(new Verse(1, 1, 1), 2746);
        gen_rev = RestrictionType.NONE.toRange(new Verse(1, 1, 1), 31102);
        rev99_9 = RestrictionType.NONE.toRange(new Verse(66, 22, 1), 21);
        rev11_9 = RestrictionType.NONE.toRange(new Verse(66, 1, 1), 404);
        rev99_1 = RestrictionType.NONE.toRange(new Verse(66, 22, 21), 1);

        gen11 = new Verse(1, 1, 1);
        gen12 = new Verse(1, 1, 2);
        gen19 = new Verse(1, 1, 31);
        gen21 = new Verse(1, 2, 1);
        gen22 = new Verse(1, 2, 2);
        gen99 = new Verse(1, 50, 26);
        exo11 = new Verse(2, 1, 1);
        rev11 = new Verse(66, 1, 1);
        rev12 = new Verse(66, 1, 2);
        rev21 = new Verse(66, 2, 1);
        rev22 = new Verse(66, 2, 2);
        rev99 = new Verse(66, 22, 21);
        rev91 = new Verse(66, 22, 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() {
    }

    public void testNewViaString() throws Exception {
        assertEquals(gen11_1, VerseRangeFactory.fromString("Gen 1:1-1"));
        assertEquals(gen11_2, VerseRangeFactory.fromString("Gen 1:1-2"));
        assertEquals(gen11_2, VerseRangeFactory.fromString("Gen 1:1-1:2"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-50:26"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-50:$"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-50:ff"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-$:26"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-ff:26"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-$:$"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-$:ff"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-ff:$"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-ff:ff"));
        assertEquals(gen_ex1, VerseRangeFactory.fromString("Gen 1:1-Exo 1:1"));
        assertEquals(gen_exo, VerseRangeFactory.fromString("Gen-Exo"));
        assertEquals(gen_rev, VerseRangeFactory.fromString("Gen 1:1-Rev 22:21"));
        assertEquals(gen_rev, VerseRangeFactory.fromString("Gen 1:1-Rev 22:$"));
        assertEquals(gen_rev, VerseRangeFactory.fromString("Gen 1:1-Rev 22:ff"));
        assertEquals(gen11_1, VerseRangeFactory.fromString("Gen 1:1"));
        assertEquals(rev99_1, VerseRangeFactory.fromString("Rev 22:21"));
        assertEquals(rev99_1, VerseRangeFactory.fromString("Rev 22:21-21"));
        assertEquals(gen11_1, VerseRangeFactory.fromString("Gen 1:1-Gen 1:1"));
        assertEquals(gen11_2, VerseRangeFactory.fromString("Gen 1:1-Gen 1:2"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-50:26"));
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen 1:1-Gen 50:26"));
        assertEquals(rev99_1, VerseRangeFactory.fromString("Rev 22:21-Rev 22:21"));
        assertEquals(rev99_1, VerseRangeFactory.fromString("Rev 22:21-Rev 22:ff"));
        assertEquals(rev99_1, VerseRangeFactory.fromString("Rev 22:21-Rev 22:$"));
        assertEquals(rev99_1, VerseRangeFactory.fromString("Rev 22:21-$"));
        assertEquals(rev99_1, VerseRangeFactory.fromString("Rev 22:21-21"));
        assertEquals(gen11_1, VerseRangeFactory.fromString("Gen 1:1-1:1"));
        assertEquals(gen_ex1, VerseRangeFactory.fromString("g 1 1-e 1 1"));
        assertEquals(gen_ex1, VerseRangeFactory.fromString("g 1-e 1 1"));
        assertEquals(gen_ex1, VerseRangeFactory.fromString("Genesis 1:1-e 1 1"));
        assertEquals(gen_ex1, VerseRangeFactory.fromString("g-e:1:1"));
        try {
            VerseRangeFactory.fromString("Gen 1:1-Gen 1:2-Gen 1:3");fail();}
        catch (NoSuchVerseException ex) {
        }
        try {
            VerseRangeFactory.fromString("Gen 1:1-2-3");fail();}
        catch (NoSuchVerseException ex) {
        }
        try {
            VerseRangeFactory.fromString("b 1:1-2");fail();}
        catch (NoSuchVerseException ex) {
        }
        try {
            VerseRangeFactory.fromString("g-f 1 2");fail();}
        catch (NoSuchVerseException ex) {
        }
        try {
            VerseRangeFactory.fromString((String) null);
            fail();
        } catch (NoSuchVerseException ex) {
        }
        assertEquals(gen_all, VerseRangeFactory.fromString("Gen"));
        assertEquals(rev99_9, VerseRangeFactory.fromString("Rev 22"));
        assertEquals(rev11_9, VerseRangeFactory.fromString("Rev"));
    }

    public void testToString() {
        assertEquals(gen11_1.toString(), "Gen 1:1");
        assertEquals(gen11_2.toString(), "Gen 1:1-2");
        assertEquals(gen11_9.toString(), "Gen 1");
        assertEquals(gen11_a.toString(), "Gen 1:1-2:1");
        assertEquals(gen12_1.toString(), "Gen 1:2");
        assertEquals(gen_all.toString(), "Gen");
        assertEquals(gen_ex1.toString(), "Gen 1:1-Exo 1:1");
        assertEquals(gen_rev.toString(), "Gen-Rev");
        assertEquals(rev99_9.toString(), "Rev 22");
        assertEquals(rev11_9.toString(), "Rev");
        assertEquals(rev99_1.toString(), "Rev 22:21");
    }

    public void testPersistentNaming() throws Exception {
        PassageUtil.setPersistentNaming(false);
        assertEquals(VerseRangeFactory.fromString("1corinth 8-9").toString(), "1Cor 8-9");
        assertEquals(VerseRangeFactory.fromString("Genesis 1 1").toString(), "Gen 1:1");
        assertEquals(VerseRangeFactory.fromString("g 1 1-e 1 1").toString(), "Gen 1:1-Exo 1:1");
        assertEquals(VerseRangeFactory.fromString("g-e:1:10").toString(), "Gen 1:1-Exo 1:10");
        assertEquals(VerseRangeFactory.fromString("g 1-e 2").toString(), "Gen 1-Exo 2");
        PassageUtil.setPersistentNaming(true);
        assertEquals(VerseRangeFactory.fromString("Genesis 1 1").toString(), "Genesis 1 1");
        assertEquals(VerseRangeFactory.fromString("g 1 1-e 1 1").toString(), "g 1 1-e 1 1");
        assertEquals(VerseRangeFactory.fromString("g-e:1:1").toString(), "g-e:1:1");
        assertEquals(VerseRangeFactory.fromString("g 1-e 2").toString(), "g 1-e 2");
        PassageUtil.setPersistentNaming(false);
    }

    public void testNewViaVerseInt() throws Exception {
        assertEquals(gen11_1, RestrictionType.NONE.toRange(VerseFactory.fromString("Gen 1:1"), 1));
    }

    public void testNewViaVerseIntBoolean() {
        assertEquals(gen_rev, RestrictionType.NONE.toRange(gen11, 999999));
        assertEquals(gen11_1, RestrictionType.NONE.toRange(gen11, 0));
        assertEquals(gen11_1, RestrictionType.NONE.toRange(gen11, -1));
    }

    public void testNewViaVerse() {
        assertEquals(gen11_1, new VerseRange(gen11));
        assertEquals(rev99_1, new VerseRange(rev99));
    }

    public void testNewViaVerseVerse() throws Exception {
        assertEquals(gen11_1, new VerseRange(gen11, gen11));
        assertEquals(gen11_2, new VerseRange(gen11, gen12));
        assertEquals(gen11_2, new VerseRange(gen12, gen11));
        assertEquals(gen_rev, new VerseRange(gen11, rev99));
        assertEquals(gen_rev, new VerseRange(rev99, gen11));
        assertEquals(gen_all, new VerseRange(gen11, gen99));
        assertEquals(gen_all, new VerseRange(gen99, gen11));
        assertEquals(gen_ex1, new VerseRange(gen11, exo11));
        assertEquals(gen_ex1, new VerseRange(exo11, gen11));
        assertEquals(gen11_1, new VerseRange(gen11, new Verse(1, 1, 1)));
    }

    public void testNewViaVerseIntIntBoolean() {
        assertEquals(gen11_1, RestrictionType.CHAPTER.blur(gen11, 0, 0));
        assertEquals(gen11_1, RestrictionType.NONE.blur(gen11, 0, 0));
        assertEquals(gen11_2, RestrictionType.CHAPTER.blur(gen11, 0, 1));
        assertEquals(gen11_2, RestrictionType.NONE.blur(gen11, 0, 1));
        assertEquals(gen11_1, RestrictionType.CHAPTER.blur(gen11, 1, 0));
        assertEquals(gen11_1, RestrictionType.NONE.blur(gen11, 1, 0));
        assertEquals(gen11_1, RestrictionType.CHAPTER.blur(gen11, 9, 0));
        assertEquals(gen11_1, RestrictionType.NONE.blur(gen11, 9, 0));
        assertEquals(rev99_1, RestrictionType.CHAPTER.blur(rev99, 0, 1));
        assertEquals(rev99_1, RestrictionType.NONE.blur(rev99, 0, 1));
        assertEquals(rev99_1, RestrictionType.CHAPTER.blur(rev99, 0, 9));
        assertEquals(rev99_1, RestrictionType.NONE.blur(rev99, 0, 9));
        assertEquals(gen11_9, RestrictionType.NONE.blur(gen11, 0, 30));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 0, 30));
        assertEquals(gen11_9, RestrictionType.NONE.blur(gen11, 1, 30));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 1, 30));
        assertEquals(gen11_9, RestrictionType.NONE.blur(gen11, 9, 30));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 9, 30));
        assertEquals(gen11_a, RestrictionType.NONE.blur(gen11, 0, 31));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 0, 31));
        assertEquals(gen11_a, RestrictionType.NONE.blur(gen11, 1, 31));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 1, 31));
        assertEquals(gen11_a, RestrictionType.NONE.blur(gen11, 9, 31));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 9, 31));
        assertEquals(gen_all, RestrictionType.NONE.blur(gen11, 0, 1532));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 0, 1532));
        assertEquals(gen_all, RestrictionType.NONE.blur(gen11, 1, 1532));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 1, 1532));
        assertEquals(gen_all, RestrictionType.NONE.blur(gen11, 9, 1532));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 9, 1532));
        assertEquals(gen_ex1, RestrictionType.NONE.blur(gen11, 0, 1533));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 0, 1533));
        assertEquals(gen_ex1, RestrictionType.NONE.blur(gen11, 1, 1533));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 1, 1533));
        assertEquals(gen_ex1, RestrictionType.NONE.blur(gen11, 9, 1533));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 9, 1533));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 0, 31101));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 0, 31101));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 1, 31101));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 1, 31101));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 9, 31101));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 9, 31101));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 0, 31102));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 0, 31102));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 1, 31102));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 1, 31102));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 9, 31102));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 9, 31102));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 0, 99999));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 0, 99999));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 1, 99999));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 1, 99999));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11, 9, 99999));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11, 9, 99999));
        assertEquals(gen11_2, RestrictionType.CHAPTER.blur(gen12, 1, 0));
        assertEquals(gen11_2, RestrictionType.NONE.blur(gen12, 1, 0));
        assertEquals(gen11_2, RestrictionType.CHAPTER.blur(gen12, 9, 0));
        assertEquals(gen11_2, RestrictionType.NONE.blur(gen12, 9, 0));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99, 20, 0));
        assertEquals(rev99_9, RestrictionType.NONE.blur(rev99, 20, 0));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99, 20, 1));
        assertEquals(rev99_9, RestrictionType.NONE.blur(rev99, 20, 1));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99, 20, 9));
        assertEquals(rev99_9, RestrictionType.NONE.blur(rev99, 20, 9));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99, 403, 0));
        assertEquals(rev11_9, RestrictionType.NONE.blur(rev99, 403, 0));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99, 403, 1));
        assertEquals(rev11_9, RestrictionType.NONE.blur(rev99, 403, 1));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99, 403, 9));
        assertEquals(rev11_9, RestrictionType.NONE.blur(rev99, 403, 9));
    }

    public void testNewViaVerseRangeIntIntBoolean() {
        assertEquals(gen11_1, RestrictionType.CHAPTER.blur(gen11_1, 0, 0));
        assertEquals(gen11_1, RestrictionType.NONE.blur(gen11_1, 0, 0));
        assertEquals(gen11_2, RestrictionType.CHAPTER.blur(gen11_1, 0, 1));
        assertEquals(gen11_2, RestrictionType.NONE.blur(gen11_1, 0, 1));
        assertEquals(gen11_1, RestrictionType.CHAPTER.blur(gen11_1, 1, 0));
        assertEquals(gen11_1, RestrictionType.NONE.blur(gen11_1, 1, 0));
        assertEquals(gen11_1, RestrictionType.CHAPTER.blur(gen11_1, 9, 0));
        assertEquals(gen11_1, RestrictionType.NONE.blur(gen11_1, 9, 0));
        assertEquals(rev99_1, RestrictionType.CHAPTER.blur(rev99_1, 0, 1));
        assertEquals(rev99_1, RestrictionType.NONE.blur(rev99_1, 0, 1));
        assertEquals(rev99_1, RestrictionType.CHAPTER.blur(rev99_1, 0, 9));
        assertEquals(rev99_1, RestrictionType.NONE.blur(rev99_1, 0, 9));
        assertEquals(gen11_9, RestrictionType.NONE.blur(gen11_1, 0, 30));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 0, 30));
        assertEquals(gen11_9, RestrictionType.NONE.blur(gen11_1, 1, 30));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 1, 30));
        assertEquals(gen11_9, RestrictionType.NONE.blur(gen11_1, 9, 30));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 9, 30));
        assertEquals(gen11_a, RestrictionType.NONE.blur(gen11_1, 0, 31));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 0, 31));
        assertEquals(gen11_a, RestrictionType.NONE.blur(gen11_1, 1, 31));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 1, 31));
        assertEquals(gen11_a, RestrictionType.NONE.blur(gen11_1, 9, 31));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 9, 31));
        assertEquals(gen_all, RestrictionType.NONE.blur(gen11_1, 0, 1532));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 0, 1532));
        assertEquals(gen_all, RestrictionType.NONE.blur(gen11_1, 1, 1532));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 1, 1532));
        assertEquals(gen_all, RestrictionType.NONE.blur(gen11_1, 9, 1532));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 9, 1532));
        assertEquals(gen_ex1, RestrictionType.NONE.blur(gen11_1, 0, 1533));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 0, 1533));
        assertEquals(gen_ex1, RestrictionType.NONE.blur(gen11_1, 1, 1533));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 1, 1533));
        assertEquals(gen_ex1, RestrictionType.NONE.blur(gen11_1, 9, 1533));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 9, 1533));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 0, 31101));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 0, 31101));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 1, 31101));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 1, 31101));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 9, 31101));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 9, 31101));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 0, 31102));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 0, 31102));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 1, 31102));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 1, 31102));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 9, 31102));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 9, 31102));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 0, 99999));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 0, 99999));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 1, 99999));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 1, 99999));
        assertEquals(gen_rev, RestrictionType.NONE.blur(gen11_1, 9, 99999));
        assertEquals(gen11_9, RestrictionType.CHAPTER.blur(gen11_1, 9, 99999));
        assertEquals(gen11_2, RestrictionType.CHAPTER.blur(gen12_1, 1, 0));
        assertEquals(gen11_2, RestrictionType.NONE.blur(gen12_1, 1, 0));
        assertEquals(gen11_2, RestrictionType.CHAPTER.blur(gen12_1, 9, 0));
        assertEquals(gen11_2, RestrictionType.NONE.blur(gen12_1, 9, 0));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99_1, 20, 0));
        assertEquals(rev99_9, RestrictionType.NONE.blur(rev99_1, 20, 0));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99_1, 20, 1));
        assertEquals(rev99_9, RestrictionType.NONE.blur(rev99_1, 20, 1));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99_1, 20, 9));
        assertEquals(rev99_9, RestrictionType.NONE.blur(rev99_1, 20, 9));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99_1, 403, 0));
        assertEquals(rev11_9, RestrictionType.NONE.blur(rev99_1, 403, 0));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99_1, 403, 1));
        assertEquals(rev11_9, RestrictionType.NONE.blur(rev99_1, 403, 1));
        assertEquals(rev99_9, RestrictionType.CHAPTER.blur(rev99_1, 403, 9));
        assertEquals(rev11_9, RestrictionType.NONE.blur(rev99_1, 403, 9));
    }

    public void testNewViaVerseRangeVerseRange() {
        assertEquals(gen_rev, new VerseRange(gen11_1, rev99_9));
        assertEquals(gen_rev, new VerseRange(gen_rev, rev99_9));
        assertEquals(gen_rev, new VerseRange(rev11_9, gen_rev));
        assertEquals(gen_rev, new VerseRange(gen_rev, gen_rev));
        try {
            new VerseRange(gen_rev, null);
            fail();
        } catch (NullPointerException ex) {
        }
        try {
            new VerseRange((VerseRange) null, gen_rev);
            fail();
        } catch (NullPointerException ex) {
        }
        try {
            new VerseRange((VerseRange) null, (VerseRange) null);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    public void testGetName() {
        assertEquals(gen11_1.getName(), "Gen 1:1");
        assertEquals(gen11_2.getName(), "Gen 1:1-2");
        assertEquals(gen11_9.getName(), "Gen 1");
        assertEquals(gen11_a.getName(), "Gen 1:1-2:1");
        assertEquals(gen12_1.getName(), "Gen 1:2");
        assertEquals(gen_all.getName(), "Gen");
        assertEquals(gen_ex1.getName(), "Gen 1:1-Exo 1:1");
        assertEquals(gen_rev.getName(), "Gen-Rev");
        assertEquals(rev99_9.getName(), "Rev 22");
        assertEquals(rev11_9.getName(), "Rev");
        assertEquals(rev99_1.getName(), "Rev 22:21");
    }

    public void testGetNameVerse() {
        assertEquals(gen11_2.getName(gen11), "1-2");
        assertEquals(gen12_1.getName(gen11), "2");
        assertEquals(rev99_9.getName(gen11), "Rev 22");
        assertEquals(rev99_9.getName(null), "Rev 22");
    }

    public void testGetStart() {
        assertEquals(gen11_1.getStart(), gen11);
        assertEquals(gen11_2.getStart(), gen11);
        assertEquals(gen11_9.getStart(), gen11);
        assertEquals(gen11_a.getStart(), gen11);
        assertEquals(gen12_1.getStart(), gen12);
        assertEquals(gen_all.getStart(), gen11);
        assertEquals(gen_ex1.getStart(), gen11);
        assertEquals(gen_rev.getStart(), gen11);
        assertEquals(rev99_9.getStart(), rev91);
        assertEquals(rev11_9.getStart(), rev11);
        assertEquals(rev99_1.getStart(), rev99);
    }

    public void testGetEnd() {
        assertEquals(gen11_1.getEnd(), gen11);
        assertEquals(gen11_2.getEnd(), gen12);
        assertEquals(gen11_9.getEnd(), gen19);
        assertEquals(gen11_a.getEnd(), gen21);
        assertEquals(gen12_1.getEnd(), gen12);
        assertEquals(gen_all.getEnd(), gen99);
        assertEquals(gen_ex1.getEnd(), exo11);
        assertEquals(gen_rev.getEnd(), rev99);
        assertEquals(rev99_9.getEnd(), rev99);
        assertEquals(rev11_9.getEnd(), rev99);
        assertEquals(rev99_1.getEnd(), rev99);
    }

    public void testGetVerseCount() {
        assertEquals(gen11_1.getCardinality(), 1);
        assertEquals(gen11_2.getCardinality(), 2);
        assertEquals(gen11_9.getCardinality(), 31);
        assertEquals(gen11_a.getCardinality(), 32);
        assertEquals(gen12_1.getCardinality(), 1);
        assertEquals(gen_all.getCardinality(), 1533);
        assertEquals(gen_ex1.getCardinality(), 1534);
        assertEquals(gen_rev.getCardinality(), 31102);
        assertEquals(rev99_9.getCardinality(), 21);
        assertEquals(rev11_9.getCardinality(), 404);
        assertEquals(rev99_1.getCardinality(), 1);
    }

    public void testClone() {
        assertTrue(gen11_1 != gen11_1.clone());
        assertTrue(gen11_1.equals(gen11_1.clone()));
        assertTrue(rev99_1 != rev99_1.clone());
        assertTrue(rev99_1.equals(rev99_1.clone()));
    }

    public void testCompareTo() {
        assertEquals(rev99_1.compareTo(gen11_1), 1);
        assertEquals(gen11_1.compareTo(rev99_1), -1);
        assertEquals(gen11_1.compareTo(gen11_1), 0);
        assertEquals(gen11_1.compareTo(gen11_2), -1);
        assertEquals(gen11_1.compareTo(gen11_9), -1);
        assertEquals(gen11_1.compareTo(gen11_a), -1);
        assertEquals(gen11_1.compareTo(gen_all), -1);
        assertEquals(gen12_1.compareTo(gen11_1), 1);
        assertEquals(gen12_1.compareTo(gen11_2), 1);
        assertEquals(gen12_1.compareTo(gen_rev), 1);
        assertEquals(gen12_1.compareTo(gen12_1), 0);
        assertEquals(gen12_1.compareTo(rev99_1), -1);
        assertEquals(gen12_1.compareTo(rev99_9), -1);
        try {
            gen12_1.compareTo(null);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    public void testAdjacentTo() throws Exception {
        assertTrue(!gen11_1.adjacentTo(rev99_9));
        assertTrue(!gen11_1.adjacentTo(rev11_9));
        assertTrue(!gen12_1.adjacentTo(rev99_9));
        assertTrue(!rev99_1.adjacentTo(gen11_1));
        assertTrue(gen11_1.adjacentTo(gen12_1));
        assertTrue(gen_all.adjacentTo(gen_ex1));
        assertTrue(gen_all.adjacentTo(gen_rev));
        assertTrue(VerseRangeFactory.fromString("Gen 1:10-11").adjacentTo(VerseRangeFactory.fromString("Gen 1:12-13")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:10-12").adjacentTo(VerseRangeFactory.fromString("Gen 1:11-13")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:10-13").adjacentTo(VerseRangeFactory.fromString("Gen 1:11-12")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:10-13").adjacentTo(VerseRangeFactory.fromString("Gen 1:10-13")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:11-12").adjacentTo(VerseRangeFactory.fromString("Gen 1:10-13")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:11-13").adjacentTo(VerseRangeFactory.fromString("Gen 1:10-12")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:12-13").adjacentTo(VerseRangeFactory.fromString("Gen 1:10-11")));
        assertTrue(!VerseRangeFactory.fromString("Gen 1:10-11").adjacentTo(VerseRangeFactory.fromString("Gen 1:13-14")));
        assertTrue(!VerseRangeFactory.fromString("Gen 1:13-14").adjacentTo(VerseRangeFactory.fromString("Gen 1:10-11")));
        try {
            gen_all.adjacentTo(null);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    public void testOverlaps() throws Exception {
        assertTrue(VerseRangeFactory.fromString("Gen 1:10-11").overlaps(VerseRangeFactory.fromString("Gen 1:11-12")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:10-12").overlaps(VerseRangeFactory.fromString("Gen 1:11-13")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:10-13").overlaps(VerseRangeFactory.fromString("Gen 1:11-12")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:10-13").overlaps(VerseRangeFactory.fromString("Gen 1:10-13")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:11-12").overlaps(VerseRangeFactory.fromString("Gen 1:10-13")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:11-13").overlaps(VerseRangeFactory.fromString("Gen 1:10-12")));
        assertTrue(VerseRangeFactory.fromString("Gen 1:11-12").overlaps(VerseRangeFactory.fromString("Gen 1:10-11")));
        assertTrue(!VerseRangeFactory.fromString("Gen 1:10-11").overlaps(VerseRangeFactory.fromString("Gen 1:12-13")));
        assertTrue(!VerseRangeFactory.fromString("Gen 1:12-13").overlaps(VerseRangeFactory.fromString("Gen 1:10-11")));
        try {
            gen_all.overlaps(null);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    public void testContainsVerse() {
        assertTrue(gen_all.contains(gen11));
        assertTrue(gen_all.contains(gen12));
        assertTrue(gen_all.contains(gen99));
        assertTrue(gen11_1.contains(gen11));
        assertTrue(gen11_2.contains(gen11));
        assertTrue(gen11_2.contains(gen12));
        assertTrue(gen12_1.contains(gen12));
        assertTrue(rev99_9.contains(rev91));
        assertTrue(rev99_9.contains(rev99));
        assertTrue(!rev99_9.contains(gen11));
        assertTrue(!gen11_1.contains(gen12));
        assertTrue(!gen_all.contains(exo11));
        assertTrue(!rev99_9.contains(rev11));
        try {
            gen_all.contains((Verse) null);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    public void testContainsVerseRange() {
        assertTrue(gen_all.contains(gen11_1));
        assertTrue(gen_all.contains(gen11_2));
        assertTrue(gen_all.contains(gen12_1));
        assertTrue(gen11_1.contains(gen11_1));
        assertTrue(gen11_2.contains(gen11_1));
        assertTrue(!gen11_2.contains(gen_all));
        assertTrue(!gen12_1.contains(gen11_2));
        assertTrue(rev99_9.contains(rev99_9));
        assertTrue(!rev99_9.contains(gen_all));
        assertTrue(rev99_9.contains(rev99_1));
        assertTrue(!gen11_1.contains(rev99_9));
        assertTrue(gen_all.contains(gen12_1));
        assertTrue(!rev99_9.contains(rev11_9));
        try {
            gen_all.contains((VerseRange) null);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    public void testIsChapter() throws Exception {
        assertTrue(VerseRangeFactory.fromString("Gen 1").isWholeChapter());
        assertTrue(VerseRangeFactory.fromString("Gen 1:1-ff").isWholeChapter());
        assertTrue(VerseRangeFactory.fromString("Gen 1:1-$").isWholeChapter());
        assertTrue(VerseRangeFactory.fromString("Exo 2").isWholeChapter());
        assertTrue(VerseRangeFactory.fromString("Exo 2:1-ff").isWholeChapter());
        assertTrue(VerseRangeFactory.fromString("Exo 2:1-$").isWholeChapter());
        assertTrue(!VerseRangeFactory.fromString("Num 3:1").isWholeChapter());
        assertTrue(!VerseRangeFactory.fromString("Num 4:1-5:1").isWholeChapter());
        assertTrue(!VerseRangeFactory.fromString("Num 5:1-6:ff").isWholeChapter());
        assertTrue(!VerseRangeFactory.fromString("Lev").isWholeChapter());
    }

    public void testIsBook() throws Exception {
        assertTrue(VerseRangeFactory.fromString("Gen").isWholeBook());
        assertTrue(VerseRangeFactory.fromString("Gen 1:1-Gen 50:ff").isWholeBook());
        assertTrue(VerseRangeFactory.fromString("Gen 1:1-Gen 50:$").isWholeBook());
        assertTrue(VerseRangeFactory.fromString("Gen 1-50:ff").isWholeBook());
        assertTrue(!VerseRangeFactory.fromString("Num 1:2-Num $:$").isWholeBook());
        assertTrue(!VerseRangeFactory.fromString("Num 4:1-5:1").isWholeBook());
        assertTrue(!VerseRangeFactory.fromString("Num 5:1-6:ff").isWholeBook());
        assertTrue(!VerseRangeFactory.fromString("Lev-Deu 1:1").isWholeBook());
    }

    public void testToVerseArray() {
        assertEquals(gen11_1.toVerseArray()[0], gen11);
        assertEquals(gen11_2.toVerseArray()[0], gen11);
        assertEquals(gen11_2.toVerseArray()[1], gen12);
        assertEquals(gen11_9.toVerseArray()[0], gen11);
        assertEquals(gen11_9.toVerseArray()[30], gen19);
        assertEquals(gen12_1.toVerseArray()[0], gen12);
        assertEquals(gen_all.toVerseArray()[0], gen11);
        assertEquals(gen_ex1.toVerseArray()[0], gen11);
        assertEquals(gen_ex1.toVerseArray()[1533], exo11);
        assertEquals(rev11_9.toVerseArray()[0], rev11);
        assertEquals(rev11_9.toVerseArray()[1], rev12);
        assertEquals(rev11_9.toVerseArray()[403], rev99);
        assertEquals(gen11_1.toVerseArray().length, 1);
        assertEquals(gen11_2.toVerseArray().length, 2);
        assertEquals(gen11_9.toVerseArray().length, 31);
        assertEquals(gen11_a.toVerseArray().length, 32);
        assertEquals(gen12_1.toVerseArray().length, 1);
        assertEquals(gen_all.toVerseArray().length, 1533);
        assertEquals(gen_ex1.toVerseArray().length, 1534);
        assertEquals(rev11_9.toVerseArray().length, 404);
    }

    public void testVerseElements() {
        Iterator<Key> it = gen11_1.iterator();
        while (it.hasNext()) {
            assertTrue(it.hasNext());
            Verse v = (Verse) it.next();
            assertEquals(v, gen11);
            assertTrue(!it.hasNext());
        }
        it = gen11_2.iterator();
        while (it.hasNext()) {
            assertTrue(it.hasNext());
            Verse v = (Verse) it.next();
            assertEquals(v, gen11);
            assertTrue(it.hasNext());
            v = (Verse) it.next();
            assertEquals(v, gen12);
            assertTrue(!it.hasNext());
        }
    }
}
