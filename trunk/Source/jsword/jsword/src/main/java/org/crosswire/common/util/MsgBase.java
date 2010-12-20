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
 * ID: $Id: MsgBase.java 2050 2010-12-09 15:31:45Z dmsmith $
 */
package org.crosswire.common.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.crosswire.common.icu.NumberShaper;

/**
 * A base class for implementing type safe internationalization (i18n) that is
 * easy for most cases. See {@link org.crosswire.common.util.Msg} for an example
 * of how to inherit from here.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class MsgBase {
    /**
     * Create a MsgBase object
     */
    protected MsgBase(String name) {
        this();
        this.name = name;
    }

    protected MsgBase() {
        this.shaper = new NumberShaper();
        loadResources();
    }

    /**
     * Get the internationalized text, but return key if key is unknown.
     * 
     * @param key
     * @return the internationalized text
     */
    public String lookup(String key) {
        return shaper.shape(obtainString(key));
    }

    /**
     * Formats the message with the given parameter.
     */
    public String toString(String key, Object param) {
        return shaper.shape(MessageFormat.format(obtainString(key), new Object[] {
            param
        }));
    }

    /**
     * Formats the message with the given parameters.
     */
    public String toString(String key, Object[] params) {
        return shaper.shape(MessageFormat.format(obtainString(key), params));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return shaper.shape(obtainString(name));
    }

    /**
     * Formats the message with the given parameter.
     */
    public String toString(Object param) {
        return shaper.shape(MessageFormat.format(obtainString(name), new Object[] {
            param
        }));
    }

    /**
     * Formats the message with the given parameters.
     */
    public String toString(Object[] params) {
        return shaper.shape(MessageFormat.format(obtainString(name), params));
    }

    /**
     * Initialize any resource bundles
     */
    protected final void loadResources() {
        Class<? extends MsgBase> implementingClass = getClass();
        String className = implementingClass.getName();

        // Class lock is needed around static resourceMap
        synchronized (MsgBase.class) {
            // see if it is in the cache
            resources = resourceMap.get(className);

            // if not then create it and put it into the cache
            if (resources == null) {
                Locale defaultLocale = Locale.getDefault();
                try {
                    resources = ResourceBundle.getBundle(className, defaultLocale, CWClassLoader.instance(implementingClass));
                    resourceMap.put(className, resources);
                } catch (MissingResourceException ex) {
                    log.warn("Assuming key is the default message " + className);
                }
            }
        }
    }

    private String obtainString(String key) {
        try {
            if (resources != null) {
                return resources.getString(key);
            }
        } catch (MissingResourceException ex) {
            log.error("Missing resource: Locale=" + Locale.getDefault().toString() + " name=" + key + " package=" + getClass().getName());
        }

        return key;
    }

    /**
     * resource map maintains a mapping of class names to resources found by
     * that name.
     */
    private static Map<String,ResourceBundle> resourceMap = new HashMap<String,ResourceBundle>();

    private String name;

    /**
     * If there is any internationalization to be done, it is thru this
     */
    private ResourceBundle resources;

    /** Internationalize numbers */
    private NumberShaper shaper;

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(MsgBase.class);
}
