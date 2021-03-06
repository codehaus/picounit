/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import picounit.Plugin;
import picounit.Registry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PluginCodeVisitor implements CodeVisitor {
	private final Registry registry;

	public PluginCodeVisitor(Registry registry) {
		this.registry = registry;
	}
	
	public void visit(ClassDirectory classDirectory) {
		insertPlugin(classDirectory);
	}

	public void visit(Jar jar) {
		insertPlugin(jar);
	}

	private void insertPlugin(Code code) {
		try {
			InputStream pluginData = code.openFile(Plugin.FILE_NAME);
			if (pluginData != null) {
				instantiateAndInsertPlugin(loadPluginProperties(pluginData));
			}
		}
		catch (FileNotFoundException fileNotFoundException) {
		}
		catch (IOException ioException) {
		}
	}

	private void instantiateAndInsertPlugin(Properties pluginProperties)  {
		String pluginClassName = pluginProperties.getProperty(Plugin.CLASS_NAME_KEY);

		try {
			Plugin plugin = (Plugin) Class.forName(pluginClassName).newInstance();

			plugin.insert(registry, pluginProperties);
		}
		catch (Exception exception) {
//			exception.printStackTrace();
		}
	}

	private Properties loadPluginProperties(InputStream inputStream) throws IOException {
		try {
			Properties pluginProperties = new Properties();

			pluginProperties.load(inputStream);

			return pluginProperties;
		}
		finally {
			inputStream.close();
		}
	}
}
