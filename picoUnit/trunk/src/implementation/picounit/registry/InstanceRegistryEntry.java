/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.registry;

import picounit.Registry;

public class InstanceRegistryEntry implements RegistryEntry {
	private final Class interfaceClass;
	private final Object instance;

	public InstanceRegistryEntry(Class interfaceClass, Object instance) {
		this.interfaceClass = interfaceClass;
		this.instance = instance;
	}

	public void registerWith(Registry registry) {
		registry.register(interfaceClass, instance);
	}
}
