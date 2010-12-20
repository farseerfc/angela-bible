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
 * ID: $Id: Msg.java 1649 2007-08-07 01:46:23Z dmsmith $
 */
package org.crosswire.jsword.book;

import org.crosswire.common.util.MsgBase;

/**
 * Compile safe Msg resource settings.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
final class Msg extends MsgBase
{
    
    static final Msg BIBLE_NOTFOUND = new Msg("Defaults.BibleNotFound"); //$NON-NLS-1$
    static final Msg DICTIONARY_NOTFOUND = new Msg("Defaults.DictionaryNotFound"); //$NON-NLS-1$
    static final Msg COMMENTARY_NOTFOUND = new Msg("Defaults.CommentaryNotFound"); //$NON-NLS-1$
    static final Msg BIBLE = new Msg("BookCategory.Bible"); //$NON-NLS-1$
    static final Msg DICTIONARY = new Msg("BookCategory.Dictionary"); //$NON-NLS-1$
    static final Msg COMMENTARY = new Msg("BookCategory.Commentary"); //$NON-NLS-1$
    static final Msg READINGS = new Msg("BookCategory.Readings"); //$NON-NLS-1$
    static final Msg GLOSSARIES = new Msg("BookCategory.Glossaries"); //$NON-NLS-1$
    static final Msg UNORTHODOX = new Msg("BookCategory.Unorthodox"); //$NON-NLS-1$
    static final Msg GENERAL = new Msg("BookCategory.General"); //$NON-NLS-1$
    static final Msg OTHER = new Msg("BookCategory.Other"); //$NON-NLS-1$

    static final Msg MISSING_VERSE = new Msg("OSISUtil.MissingVerse"); //$NON-NLS-1$
    static final Msg OSIS_BADID = new Msg("OSISUtil.OSISBadID"); //$NON-NLS-1$

    static final Msg BOOK_NOREMOVE = new Msg("Books.BookNoRemove"); //$NON-NLS-1$
    static final Msg DRIVER_NOREMOVE = new Msg("Books.DriverNoRemove"); //$NON-NLS-1$

    /**
     * Passthrough ctor
     */
    private Msg(String name)
    {
        super(name);
    }
}
