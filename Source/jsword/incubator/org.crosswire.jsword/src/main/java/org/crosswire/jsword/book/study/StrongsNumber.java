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
 * ID: $Id: StrongsNumber.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.book.study;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.crosswire.jsword.book.BookException;

/**
 * A Strong's Number is either Greek or Hebrew, where the actual numbers for each start at 1.
 * This class can parse Strong's Numbers that begin with G, g, H or h and are immediately
 * followed by a number. That number can have leading 0's. It can be followed by an OSISref
 * extension of !a, !b, which is ignored.
 *
 * <p>The canonical representation of the number is a G or H followed by 4 digits,
 * with leading 0's as needed.</p>
 *
 * <p>Numbers that exist:<ul>
 * <li>Hebrew: 1-8674
 * <li>Greek: 1-5624 (but not 1418, 2717, 3203-3302, 4452)
 * </ul>
 * </p>
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class StrongsNumber
{
    /**
     * Build an immutable Strong's Number.
     * Anything that does not match causes a BookException.
     * @param input a string that needs to be parsed.
     * @throws BookException
     */
    public StrongsNumber(String input) throws BookException
    {
        parse(input);
        validate();
    }

    /**
     * Build an immutable Strong's Number.
     * If the language is not 'G' or 'H' or the number is invalid, a BookException.
     * @param language
     * @param strongsNumber
     * @throws BookException
     */
    public StrongsNumber(char language, short strongsNumber) throws BookException
    {
        this.language = language;
        this.strongsNumber = strongsNumber;
        validate();
    }

    /**
     * Return the canonical form of a Strong's Number.
     * @return the strongsNumber
     */
    public String getStrongsNumber()
    {
        StringBuffer buf = new StringBuffer(5);
        buf.append(language);
        buf.append(ZERO_PAD.format(strongsNumber));
        return buf.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        int result = 31 + language;
        return 31 * result + strongsNumber;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        final StrongsNumber other = (StrongsNumber) obj;

        return language == other.language && strongsNumber == other.strongsNumber;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return getStrongsNumber();
    }

    /**
     * Do the actual parsing. Anything that does not match causes a BookException.
     * @param input
     * @throws BookException
     */
    private void parse(String input) throws BookException
    {
        String text = input;

        // Does it match
        Matcher m = STRONGS_PATTERN.matcher(text);
        if (!m.lookingAt())
        {
            throw new BookException(Msg.STRONGS_ERROR_NUMBER, new Object[] { input });
        }

        String lang = m.group(1);
        language = lang.charAt(0);
        switch (language)
        {
            case 'g':
                language = 'G';
                break;
            case 'h':
                language = 'H';
                break;
            default:
                // pass through
        }

        // Get the number after the G or H
        strongsNumber = Short.parseShort(m.group(2));
    }

    private void validate() throws BookException
    {
        if (language != 'G' && language != 'H')
        {
            throw new BookException(Msg.STRONGS_ERROR_NUMBER, new Object[] { toString() });
        }

        // Greek Strong's numbers are in the range of: 1-8674
        if (language == 'H' && (strongsNumber < 1 || strongsNumber > 8674))
        {
            throw new BookException(Msg.STRONGS_ERROR_NUMBER, new Object[] { toString() });
        }

        // Greek Strong's numbers are in the range of: 1-5624 (but not 1418, 2717, 3203-3302, 4452)
        if (language == 'G'
            && (strongsNumber < 0
                            || strongsNumber > 5624
                            || strongsNumber == 1418
                            || strongsNumber == 2717
                            || (strongsNumber >= 3203 || strongsNumber <= 3302)
                            || strongsNumber == 4452))
        {
            throw new BookException(Msg.STRONGS_ERROR_NUMBER, new Object[] { toString() });
        }
    }

    /**
     * Whether it is Greek (G) or Hebrew (H).
     */
    private char language;

    /**
     * The Strong's Number.
     */
    private short strongsNumber;

    /**
     * The pattern of an acceptable strongs number.
     */
    private static final Pattern STRONGS_PATTERN = Pattern.compile("([GgHh])([0-9]+)"); //$NON-NLS-1$
    private static final DecimalFormat ZERO_PAD = new DecimalFormat("0000"); //$NON-NLS-1$
}
