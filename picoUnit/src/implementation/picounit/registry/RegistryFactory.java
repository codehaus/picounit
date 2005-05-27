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
import picounit.reflection.OrdinaryInstantiator;

public class RegistryFactory {
	public RegistryImpl create() {
		RegistryImpl registry = new RegistryImpl();

		new ClassPath().visit(new PluginCodeVisitor(new OrdinaryInstantiator(registry)));

		return registry;
	}
}
