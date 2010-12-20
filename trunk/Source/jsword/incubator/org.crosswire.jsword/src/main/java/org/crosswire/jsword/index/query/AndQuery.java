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
 * ID: $Id:AndQuery.java 984 2006-01-23 14:18:33 -0500 (Mon, 23 Jan 2006) dmsmith $
 */
package org.crosswire.jsword.index.query;

import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.index.Index;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.PassageTally;

/**
 * An AND query specifies that a result needs to be in both the left and the right query results.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class AndQuery extends AbstractBinaryQuery
{

    /**
     * Create a query where the result is the intersection of two queries.
     *
     * @param theLeftQuery
     * @param theRightQuery
     */
    public AndQuery(Query theLeftQuery, Query theRightQuery)
    {
        super(theLeftQuery, theRightQuery);
    }
    /* (non-Javadoc)
     * @see org.crosswire.jsword.index.search.parse.Query#find(org.crosswire.jsword.index.search.Index)
     */
    public Key find(Index index) throws BookException
    {
        Key left = getLeftQuery().find(index);

        if (left.isEmpty())
        {
            return left;
        }

        Key right = getRightQuery().find(index);

        if (right.isEmpty())
        {
            return right;
        }

        // If ranking was requested then prioritize it.
        if (right instanceof PassageTally)
        {
            right.retainAll(left);
            return right;
        }

        left.retainAll(right);
        return left;
    }
}
