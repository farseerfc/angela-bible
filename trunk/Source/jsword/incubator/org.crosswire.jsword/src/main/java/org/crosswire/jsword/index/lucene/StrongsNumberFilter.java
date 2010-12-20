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
 * ID: $Id: StrongsNumberFilter.java 1395 2007-06-11 15:22:51Z dmsmith $
 */
package org.crosswire.jsword.index.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.DataPolice;
import org.crosswire.jsword.book.study.StrongsNumber;

/**
 * A StrongsNumberFilter normalizes Strong's Numbers.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class StrongsNumberFilter extends TokenFilter
{
    /**
     * Construct filtering <i>in</i>.
     */
    public StrongsNumberFilter(TokenStream in)
    {
      super(in);
    }

    /* (non-Javadoc)
     * @see org.apache.lucene.analysis.TokenStream#next()
     */
    public final Token next() throws IOException
    {
        Token token = input.next();
        if (token == null)
        {
            return null;
        }

        try
        {
            String s = new StrongsNumber(token.termText()).getStrongsNumber();
            if (!s.equals(token.termText()))
            {
                token.setTermText(s);
            }
        }
        catch (BookException e)
        {
            DataPolice.report(e.getDetailedMessage());
        }

        return token;
    }
}
