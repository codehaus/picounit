/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Interface;
import picounit.Registry;
import picounit.registry.InstanceRegistryEntry;
import previous.picounit.Mocker;
import previous.picounit.Test;

public class InstanceRegistryEntryTest implements Test {
	private final Object instance = new Object();
	
	private final InstanceRegistryEntry instanceRegistryEntry =
		new InstanceRegistryEntry(Interface.class, instance);

	private Registry registry;
	
	public void mock(Registry registry) {
		this.registry = registry;
	}
	
	public void testRegistersAgainstRegistry(Mocker mocker) {
		registry.register(Interface.class, instance);
		
		mocker.replay();
		
		instanceRegistryEntry.registerWith(registry);
	}
}
