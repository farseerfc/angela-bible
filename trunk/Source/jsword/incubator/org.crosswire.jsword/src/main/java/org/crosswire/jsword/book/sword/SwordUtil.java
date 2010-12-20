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
 * ID: $Id: SwordUtil.java 1505 2007-07-21 19:40:19Z dmsmith $
 */
package org.crosswire.jsword.book.sword;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.crosswire.common.util.Logger;
import org.crosswire.jsword.book.DataPolice;
import org.crosswire.jsword.passage.Key;

/**
 * Various utilities used by different Sword classes.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public final class SwordUtil
{
    /**
     * Prevent instantiation
     */
    private SwordUtil()
    {
    }

    /**
     * Read a RandomAccessFile
     * @param raf The file to read
     * @param offset The start of the record to read
     * @param theSize The number of bytes to read
     * @return the read data
     */
    protected static byte[] readRAF(RandomAccessFile raf, long offset, int theSize) throws IOException
    {
        raf.seek(offset);
        return readNextRAF(raf, theSize);
    }

    /**
     * Read a RandomAccessFile from the current location in the file.
     *
     * @param raf The file to read
     * @param theSize The number of bytes to read
     * @return the read data
     */
    protected static byte[] readNextRAF(RandomAccessFile raf, int theSize) throws IOException
    {
        long offset = raf.getFilePointer();
        int size = theSize;

        if (offset + size > raf.length())
        {
            DataPolice.report("Need to reduce size to avoid EOFException. offset=" + offset + " size=" + size + " but raf.length=" + raf.length()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            size = (int) (raf.length() - offset);
        }

        if (size < 1)
        {
            DataPolice.report("Nothing to read at offset = " + offset + " returning empty because size=" + size); //$NON-NLS-1$ //$NON-NLS-2$
            return new byte[0];
        }

        byte[] read = new byte[size];
        raf.readFully(read);

        return read;
    }

    /**
     * Read a RandomAccessFile until a particular byte is seen
     * @param raf The file to read
     * @param offset The start of the record to read
     * @param stopByte The point at which to stop reading
     * @return the read data
     */
    protected static byte[] readUntilRAF(RandomAccessFile raf, int offset, byte stopByte) throws IOException
    {
        raf.seek(offset);
        return readUntilRAF(raf, stopByte);
    }

    /**
     * Read a RandomAccessFile until a particular byte is seen
     * @param raf The file to read
     * @param stopByte The point at which to stop reading
     * @return the read data
     */
    protected static byte[] readUntilRAF(RandomAccessFile raf, byte stopByte) throws IOException
    {
        // The strategy used here is to read the file twice.
        // Once to determine how much to read and then getting the actual data.
        // It may be more efficient to incrementally build up a byte buffer.
        // Note: that growing a static array by 1 byte at a time is O(n**2)
        // This is negligible when the n is small, but prohibitive otherwise.
        long offset = raf.getFilePointer();
        int size = 0;

        int nextByte = -1;
        do
        {
            nextByte = raf.read();

            size++;
        }
        while (nextByte != -1 && nextByte != stopByte);

        // Note: we allow for nextByte == -1 to be included in size
        // so that readRAF will report EOF errors
        return readRAF(raf, offset, size);
    }

    /**
     * Decode little endian data from a byte array.
     * This assumes that the high order bit is not set as this is used solely
     * for an offset in a file in bytes. For a practical limit, 2**31 is way
     * bigger than any document that we can have.
     * @param data the byte[] from which to read 4 bytes
     * @param offset the offset into the array
     * @return The decoded data
     */
    protected static int decodeLittleEndian32(byte[] data, int offset)
    {
        // Convert from a byte to an int, but prevent sign extension.
        // So -16 becomes 240
        int byte1 = data[0 + offset] & 0xFF;
        int byte2 = (data[1 + offset] & 0xFF) << 8;
        int byte3 = (data[2 + offset] & 0xFF) << 16;
        int byte4 = (data[3 + offset] & 0xFF) << 24;

        return byte4 | byte3 | byte2 | byte1;
    }

    /**
     * Decode little endian data from a byte array
     * @param data the byte[] from which to read 4 bytes
     * @param offset the offset into the array
     * @return The decoded data
     */
    protected static int decodeLittleEndian16(byte[] data, int offset)
    {
        // Convert from a byte to an int, but prevent sign extension.
        // So -16 becomes 240
        int byte1 = data[0 + offset] & 0xFF;
        int byte2 = (data[1 + offset] & 0xFF) << 8;

        return byte2 | byte1;
    }

    /**
     * Find a byte of data in an array
     * @param data The array to search
     * @param sought The data to search for
     * @return The index of the found position or -1 if not found
     */
    protected static int findByte(byte[] data, byte sought)
    {
        for (int i = 0; i < data.length; i++)
        {
            if (data[i] == sought)
            {
                return i;
            }
        }

        return -1;
    }

    /**
     * Transform a byte array into a string given the encoding.
     * If the encoding is bad then it just does it as a string.
     * @param data The byte array to be converted
     * @param charset The encoding of the byte array
     * @return a string that is UTF-8 internally
     */
    public static String decode(Key key, byte[] data, String charset)
    {
        return decode(key, data, data.length, charset);
    }

    /**
     * Transform a byte array into a string given the encoding.
     * If the encoding is bad then it just does it as a string.
     * @param data The byte array to be converted
     * @param charset The encoding of the byte array
     * @return a string that is UTF-8 internally
     */
    public static String decode(Key key, byte[] data, int length, String charset)
    {
        if ("WINDOWS-1252".equals(charset)) //$NON-NLS-1$
        {
            clean1252(key, data, length);
        }
        String txt = ""; //$NON-NLS-1$
        try
        {
            txt = new String(data, 0, length, charset);
        }
        catch (UnsupportedEncodingException ex)
        {
            // It is impossible! In case, use system default...
            log.error(key + ": Encoding: " + charset + " not supported", ex); //$NON-NLS-1$ //$NON-NLS-2$
            txt = new String(data, 0, length);
        }

        return txt;
    }

    /**
     * Remove rogue characters in the source.
     * These are characters that are not valid in cp1252 aka WINDOWS-1252
     * and in UTF-8 or are non-printing control characters in the range
     * of 0-32.
     */
    public static void clean1252(Key key, byte[] data)
    {
        clean1252(key, data, data.length);
    }

    /**
     * Remove rogue characters in the source.
     * These are characters that are not valid in cp1252 aka WINDOWS-1252
     * and in UTF-8 or are non-printing control characters in the range
     * of 0-32.
     */
    public static void clean1252(Key key, byte[] data, int length)
    {
        for (int i = 0; i < length; i++)
        {
            // between 0-32 only allow whitespace
            // characters 0x81, 0x8D, 0x8F, 0x90 and 0x9D are undefined in cp1252
            int c = data[i] & 0xFF;
            if ((c >= 0x00 && c < 0x20 && c != 0x09 && c != 0x0A && c != 0x0D)
                || (c == 0x81 || c == 0x8D || c == 0x8F || c == 0x90 || c == 0x9D))
            {
                data[i] = 0x20;
                DataPolice.report(key.getName() + " has bad character 0x" + Integer.toString(c, 16) + " at position " + i + " in input."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
        }
    }

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(SwordUtil.class);
}
