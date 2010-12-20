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
 * ID: $Id: XMLProcess.java 1462 2007-07-02 02:32:23Z dmsmith $
 */
package org.crosswire.common.xml;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Runs an xml parser on an xml file using an xml handler.
 * The default behavior is to check that the xml file is well-formed.
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class XMLProcess
{

    public XMLProcess()
    {
        features = new XMLFeatureSet();
    }

    /**
     * @return Returns the features.
     */
    public XMLFeatureSet getFeatures()
    {
        return features;
    }

    /**
     * Process an xml file according to the arguments.
     * @param argv
     */
    public static void main(String[] argv)
    {
        XMLProcess checker = new XMLProcess();

        // is there anything to do?
        if (argv.length == 0)
        {
            checker.usage();
            System.exit(1);
        }

        // variables
        String arg = null;

        // process arguments
        for (int i = 0; i < argv.length; i++)
        {
            arg = argv[i];
            if (arg.charAt(0) == '-')
            {
                String option = arg.substring(1);
                if ("h".equals(option)) //$NON-NLS-1$
                {
                    checker.usage();
                    System.exit(0);
                }
            }
        }

        checker.initialize(argv);
        checker.parse(arg);

    }

    private void initialize(String[] argv)
    {
        // process arguments
        int i = 0;
        for (i = 0; i < argv.length; i++)
        {
            String arg = argv[i];
            if (arg.charAt(0) == '-')
            {
                String option = arg.substring(1);
                if ("p".equals(option)) //$NON-NLS-1$
                {
                    // get parser name
                    if (++i == argv.length)
                    {
                        System.err.println("error: Missing argument to -p option."); //$NON-NLS-1$
                    }
                    parserName = argv[i];

                    createParser();
                    continue;
                }
                if ("a".equals(option)) //$NON-NLS-1$
                {
                    // get parser name
                    if (++i == argv.length)
                    {
                        System.err.println("error: Missing argument to -a option."); //$NON-NLS-1$
                    }
                    adapterName = argv[i];

                    createAdapter();
                    continue;
                }
            }
        }

        features.setFeatureStates(argv);
    }

    private void bind()
    {
        createParser();
        createAdapter();

        // Now that we have a parser and a handler
        // make the parser use them.
        setHandlers();
        features.setFeatures(parser);

    }

    private void createParser()
    {
        if (parser != null)
        {
            return;
        }

        try
        {
            parser = XMLReaderFactory.createXMLReader(parserName);
        }
        catch (SAXException e)
        {
            System.err.println("error: Unable to instantiate parser (" + parserName + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        }

    }

    private void createAdapter()
    {
        if (adapter != null)
        {
            return;
        }

        try
        {
            adapter = (XMLHandlerAdapter) Class.forName(adapterName).newInstance();
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("error: Unable to instantiate XMLHandlerAdpater (" + adapterName + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        catch (InstantiationException e)
        {
            System.err.println("error: Unable to instantiate XMLHandlerAdpater (" + adapterName + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        catch (IllegalAccessException e)
        {
            System.err.println("error: Unable to instantiate XMLHandlerAdpater (" + adapterName + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        }

    }

    private void setHandlers()
    {
        parser.setDTDHandler(adapter);
        parser.setErrorHandler(adapter);
        parser.setContentHandler(adapter);

        try
        {
            parser.setProperty(DECLARATION_HANDLER_PROPERTY_ID, adapter);
        }
        catch (SAXException e)
        {
            e.printStackTrace(System.err);
        }

        try
        {
            parser.setProperty(LEXICAL_HANDLER_PROPERTY_ID, adapter);
        }
        catch (SAXException e)
        {
            e.printStackTrace(System.err);
        }
    }

    public void parse(String xmlFile)
    {
        bind();
        // parse file
        try
        {
            System.out.println("Parsing with the following:"); //$NON-NLS-1$
            printActual();
            parser.parse(xmlFile);
            System.out.println("Done: no problems found."); //$NON-NLS-1$
        }
        catch (SAXException e)
        {
            System.err.println("error: Parse error occurred - " + e.getMessage()); //$NON-NLS-1$
            Exception nested = e.getException();
            if (nested != null)
            {
                nested.printStackTrace(System.err);
            }
            else
            {
                e.printStackTrace(System.err);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
    }

    /** Prints the usage. */
    private void usage()
    {
        System.err.println("usage: java org.crosswire.common.xml.XMLProcess (options) uri"); //$NON-NLS-1$
        System.err.println();

        System.err.println("options:"); //$NON-NLS-1$
        printUsage();
        System.err.println("  -h          This help screen."); //$NON-NLS-1$
        System.err.println();

        System.err.println("defaults:"); //$NON-NLS-1$
        printDefaults();
    }

    public void printUsage()
    {
        System.err.println("  -p name     Select parser by name."); //$NON-NLS-1$
        System.err.println("  -a name     Select XMLHandlerAdapter by name."); //$NON-NLS-1$
        features.printUsage();
    }

    public void printDefaults()
    {
        System.err.println("Parser:     " + DEFAULT_PARSER_NAME); //$NON-NLS-1$
        System.err.println("Handler:    " + DEFAULT_HANDLER_NAME); //$NON-NLS-1$
        System.err.println(new XMLFeatureSet().toString());
    }

    public void printActual()
    {
        System.err.println("Parser:     " + parserName); //$NON-NLS-1$
        System.err.println("Handler:    " + adapterName); //$NON-NLS-1$
        System.err.println(new XMLFeatureSet().toString());
    }

    // property ids

    /**
     * Lexical handler property id
     */
    private static final String LEXICAL_HANDLER_PROPERTY_ID = "http://xml.org/sax/properties/lexical-handler"; //$NON-NLS-1$

    /**
     * Declaration handler property id
     */
    private static final String DECLARATION_HANDLER_PROPERTY_ID = "http://xml.org/sax/properties/declaration-handler"; //$NON-NLS-1$

    // default settings

    /** Default parser name. */
    private static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser"; //$NON-NLS-1$
    private static final String DEFAULT_HANDLER_NAME = "org.crosswire.common.xml.XMLHandlerAdapter"; //$NON-NLS-1$

    private String parserName = DEFAULT_PARSER_NAME;
    private XMLReader parser;
    private String adapterName = DEFAULT_HANDLER_NAME;
    private XMLHandlerAdapter adapter;
    private XMLFeatureSet features;
}
