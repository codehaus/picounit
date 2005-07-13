/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.registry;

import picounit.classloader.ClassPath;
import picounit.classloader.PluginCodeVisitor;
import picounit.impl.Timer;

public class RegistryFactory {
	private static final Timer timer = new Timer("RegistryFactory");

	private final ClassPath classPath;
	
	public RegistryFactory() {
		this(new ClassPath());
	}

	public RegistryFactory(ClassPath classPath) {
		this.classPath = classPath;
	}

	public RegistryImpl create() {
		timer.start();

		RegistryImpl registry = new RegistryImpl();

		classPath.visit(new PluginCodeVisitor(registry));
		
		timer.end();

		return registry;
	}
}
