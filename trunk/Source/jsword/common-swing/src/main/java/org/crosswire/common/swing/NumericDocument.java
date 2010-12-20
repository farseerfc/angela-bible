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
 * ID: $Id: NumericDocument.java 2050 2010-12-09 15:31:45Z dmsmith $
 */
package org.crosswire.common.swing;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A numeric document simply extends document to refuse all non-numeric data
 * entered according to Character.isDigit.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @see java.lang.Character#isDigit(char)
 */
public class NumericDocument extends PlainDocument {
    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.Document#insertString(int, java.lang.String,
     * javax.swing.text.AttributeSet)
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }

        char[] upper = str.toCharArray();
        StringBuilder clear = new StringBuilder();

        for (int i = 0; i < upper.length; i++) {
            if (Character.isDigit(upper[i])) {
                clear.append(upper[i]);
            }
        }

        super.insertString(offs, clear.toString(), a);
    }

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 3761973765420102192L;
}
