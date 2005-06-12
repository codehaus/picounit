/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import java.util.Properties;

/**
 * Interface for extending PicoUnit. Implement this interface, package your implementation in a jar
 * file and place a file in the root of the jar with the name 'picounit-plugin.properties'. Include
 * at least one key "plugin-class=<your plugin implementation class>". If you now run PicoUnit
 * then the property file will be read and your plugin will be instantiated.
 * 
 * @author Stacy Curl
 */
public interface Plugin {
	String FILE_NAME = "picounit-plugin.properties";
	String CLASS_NAME_KEY = "plugin-class";

	void insert(Properties pluginProperties);
}
