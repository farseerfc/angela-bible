/**
 * Distribution License:
 * BibleDesktop is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2 as published by
 * the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/gpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2005
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: SiteEditorFactory.java 2053 2010-12-10 19:34:53Z dmsmith $
 */
package org.crosswire.bibledesktop.book.install;

import java.io.IOException;
import java.util.MissingResourceException;

import org.crosswire.common.util.PropertyMap;
import org.crosswire.common.util.ReflectionUtil;
import org.crosswire.common.util.ResourceUtil;
import org.crosswire.jsword.book.install.Installer;

/**
 * Factory for SiteEditors.
 * 
 * @see gnu.gpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public final class SiteEditorFactory {
    /**
     * Prevent Use
     */
    private SiteEditorFactory() {
    }

    /**
     * Create a new SiteEditor
     */
    public static SiteEditor createSiteEditor(Installer installer) {
        try {
            PropertyMap properties = ResourceUtil.getProperties(SiteEditorFactory.class);
            String className = properties.get(installer.getType());
            SiteEditor editor = (SiteEditor) ReflectionUtil.construct(className);
            editor.setInstaller(installer);
            return editor;
        } catch (MissingResourceException e) {
            assert false : e;
        } catch (IOException e) {
            assert false : e;
        } catch (ClassNotFoundException e) {
            assert false : e;
        } catch (InstantiationException e) {
            assert false : e;
        } catch (IllegalAccessException e) {
            assert false : e;
        }
        return null;
    }
}
