/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Implementation;
import picounit.Registry;
import picounit.registry.ImplementationRegistryEntry;
import previous.picounit.Mocker;
import previous.picounit.Test;

public class ImplementationRegistryEntryTest implements Test {
	private final ImplementationRegistryEntry implementationRegistryEntry =
		new ImplementationRegistryEntry(Implementation.class);
	
	private Registry registry;
	
	public void mock(Registry registry) {
		this.registry = registry;
	}
	
	public void testRegisterImplementationClassWithRegistry(Mocker mocker) {
		registry.register(Implementation.class);
		
		mocker.replay();
		
		implementationRegistryEntry.registerWith(registry);
	}
}
