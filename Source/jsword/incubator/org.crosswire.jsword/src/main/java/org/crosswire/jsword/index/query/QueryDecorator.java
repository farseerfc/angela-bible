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
 * ID: $Id: QueryDecorator.java 1466 2007-07-02 02:48:09Z dmsmith $
 */
package org.crosswire.jsword.index.query;

/**
 * A QueryDecorator allows for the decoration of strings in a way that is
 * appropriate for a Query.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [ dmsmith555 at yahoo dot com]
 */
public interface QueryDecorator
{
    String decoratePhrase(String queryWords);

    String decorateAllWords(String queryWords);

    String decorateAnyWords(String queryWords);

    String decorateNotWords(String queryWords);

    String decorateStartWords(String queryWords);

    String decorateSpellWords(String queryWords);

    String decorateRange(String queryWords);
}
