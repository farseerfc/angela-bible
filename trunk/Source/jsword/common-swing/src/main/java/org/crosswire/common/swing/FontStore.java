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
 * ID: $Id: org.eclipse.jdt.ui.prefs 1178 2006-11-06 12:48:02Z dmsmith $
 */
package org.crosswire.common.swing;

import java.awt.Font;
import java.io.IOException;
import java.net.URI;

import org.crosswire.common.util.FileUtil;
import org.crosswire.common.util.Language;
import org.crosswire.common.util.Logger;
import org.crosswire.common.util.NetUtil;
import org.crosswire.common.util.PropertyMap;
import org.crosswire.common.util.ResourceUtil;

/**
 * Font Store maintains a persistent, hierarchical store of user font
 * preferences. A font preference consists of the name of a resource and a font
 * specification for that resource. The name of the resource may be any unique
 * value that follows the rules for a property key. The font specification is
 * the font itself or a string representation of the font that can be turned
 * into a font with <code>Font.decode(String)</code>.
 * <p>
 * Many languages share the same script. Rather than setting a font spec for
 * many resources with the same language, this class makes it possible to set a
 * font spec for each language.
 * </p>
 * <p>
 * Thus, the look up hierarchy begins with an exact match for the requested
 * resource. If it does not work the lookup continues in the following order:
 * the specified language's font, the fallback font, and the default font. Of
 * course, if that does not work, use any font that Java thinks is appropriate,
 * but use the size and style of the default font. Since scripts are shared by
 * many languages, this FontStore supports the setting of Language defaults. If
 * the requested language font does not exist a more general one will be
 * provided.
 * </p>
 * <p>
 * Note: Some languages are represented as transliterations and others have more
 * than one script, which may or may not be supported by a single font.
 * </p>
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class FontStore {

    /**
     * Create an new FontStore with the given persistent store.
     * 
     * @param storeName
     *            The name of the store, used as a file name and as a label
     *            inside the fontStore.
     * @param fontDir
     *            The location where the fontStore can be stored.
     */
    public FontStore(String storeName, URI fontDir) {
        if (fontDir == null) {
            throw new IllegalArgumentException("fontStore cannot be null");
        }
        this.storeName = storeName;
        this.fontStore = NetUtil.lengthenURI(fontDir, this.storeName + FileUtil.EXTENSION_PROPERTIES);
        this.fontMap = new PropertyMap();
    }

    /**
     * @return the defaultFont
     */
    public String getDefaultFont() {
        load();
        defaultFont = fontMap.get(DEFAULT_KEY, DEFAULT_FONT);
        return defaultFont;
    }

    /**
     * @param defaultFont
     *            the defaultFont to set
     */
    public void setDefaultFont(String defaultFont) {
        load();
        this.defaultFont = defaultFont;
        fontMap.put(DEFAULT_KEY, defaultFont);
        store();
    }

    /**
     * Store a font specification for the resource.
     * 
     * @param resource
     *            the resource
     * @param font
     *            the font
     */
    public void setFont(String resource, Font font) {
        if (resource == null || font == null) {
            return;
        }
        load();
        fontMap.put(resource, GuiConvert.font2String(font));
        store();
    }

    /**
     * Store a font specification for the language.
     * 
     * @param lang
     *            the language
     * @param font
     *            the font
     */
    public void setFont(Language lang, Font font) {
        if (lang == null || font == null) {
            return;
        }
        load();
        fontMap.put(new StringBuilder(LANG_KEY_PREFIX).append(lang.getCode()).toString(), GuiConvert.font2String(font));
        store();
    }

    /**
     * Remove the font settings for a given key
     * 
     * @param key
     *            the book initials or language code
     */
    public void resetFont(String key) {
        load();
        fontMap.remove(key);
        store();
    }

    /**
     * Get a font for the specified resource. If it does not work try the
     * following in order: the specified language's font, the fallback font, and
     * the default font. Of course, if that does not work, use any font that
     * Java thinks is appropriate, but use the size and style of the default
     * font.
     * 
     * @param resource
     *            the name of the resource for whom the font is stored.
     * @param lang
     *            the language of the resource
     * @param fallback
     *            the fontspec for the fallback font
     * @return the requested font if possible. A fallback font otherwise.
     */
    public Font getFont(String resource, Language lang, String fallback) {
        load();

        String fontSpec = null;
        if (resource != null) {
            fontSpec = fontMap.get(resource);
        }

        if (fontSpec != null) {
            Font obtainedFont = obtainFont(fontSpec);
            if (obtainedFont != null) {
                return obtainedFont;
            }
            fontSpec = null;
        }

        if (lang != null) {
            fontSpec = fontMap.get(new StringBuilder(LANG_KEY_PREFIX).append(lang.getCode()).toString());
        }

        if (fontSpec != null) {
            Font obtainedFont = obtainFont(fontSpec);
            if (obtainedFont != null) {
                return obtainedFont;
            }
        }

        fontSpec = fallback;
        if (fontSpec != null) {
            Font obtainedFont = obtainFont(fontSpec);
            if (obtainedFont != null) {
                return obtainedFont;
            }
        }

        return GuiConvert.string2Font(defaultFont);
    }

    /**
     * @return the storeName
     */
    protected String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName
     *            the storeName to set
     */
    protected void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the fontStore
     */
    protected URI getFontStore() {
        return fontStore;
    }

    /**
     * @param fontStore
     *            the fontStore to set
     */
    protected void setFontStore(URI fontStore) {
        this.fontStore = fontStore;
    }

    /**
     * @return the loaded
     */
    protected boolean isLoaded() {
        return loaded;
    }

    /**
     * @param loaded
     *            the loaded to set
     */
    protected void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    /**
     * @return the fontMap
     */
    protected PropertyMap getFontMap() {
        return fontMap;
    }

    /**
     * @param fontMap
     *            the fontMap to set
     */
    protected void setFontMap(PropertyMap fontMap) {
        this.fontMap = fontMap;
    }

    /**
     * Load the store, if it has not been loaded.
     */
    protected void load() {
        if (loaded) {
            return;
        }

        try {
            fontMap = ResourceUtil.getProperties(storeName);
            loaded = true;
        } catch (IOException e) {
            log.error("Unable to load the font store: " + fontStore);
            fontMap = new PropertyMap();
        }
    }

    /**
     * Store the store, if it exists.
     */
    protected void store() {
        load();

        try {
            NetUtil.storeProperties(fontMap, fontStore, storeName);
        } catch (IOException ex) {
            log.error("Failed to save BibleDesktop UI Translation", ex);
        }
    }

    protected Font obtainFont(String fontSpec) {
        if (fontSpec != null) {
            // Creating a font never fails. Java just silently does
            // substitution.
            // Ensure that substitution does not happen.
            Font obtainedFont = GuiConvert.string2Font(fontSpec);
            String obtainedFontSpec = GuiConvert.font2String(obtainedFont);
            if (obtainedFontSpec != null && obtainedFontSpec.equalsIgnoreCase(fontSpec)) {
                return obtainedFont;
            }
        }
        return null;
    }

    protected static final String DEFAULT_FONT = "Dialog-PLAIN-12";
    protected static final String LANG_KEY_PREFIX = "lang.";
    protected static final String DEFAULT_KEY = "default";

    private String storeName;
    private String defaultFont;
    private URI fontStore;
    private boolean loaded;
    private PropertyMap fontMap;

    private static final Logger log = Logger.getLogger(FontStore.class);
}
