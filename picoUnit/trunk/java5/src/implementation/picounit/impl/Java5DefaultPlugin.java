/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.DelegateVerify;
import picounit.Plugin;
import picounit.Registry;
import picounit.registry.DefaultPlugin;
import picounit.verify.DefaultDelegateVerify;

import java.util.Properties;

public class Java5DefaultPlugin implements Plugin {
	private final Registry registry;
	private final DefaultPlugin defaultPlugin;

	public Java5DefaultPlugin(Registry registry) {
		this.registry = registry;
		this.defaultPlugin = new DefaultPlugin(registry);
	}

	public void insert(Properties pluginProperties) {
		defaultPlugin.insert(pluginProperties);

		registry.register(DelegateVerify.class, DefaultDelegateVerify.class);
	}
}
