/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Implementation;
import picounit.Interface;
import picounit.Registry;
import picounit.registry.InterfaceToImplementationRegistryEntry;
import previous.picounit.Mocker;
import previous.picounit.Test;

public class InterfaceToImplementationRegistryEntryTest implements Test {
	private final InterfaceToImplementationRegistryEntry interfaceToImplementationRegistryEntry =
		new InterfaceToImplementationRegistryEntry(Interface.class, Implementation.class);
	
	private Registry registry;
	
	public void mock(Registry registry) {
		this.registry = registry;
	}
	
	public void testRegistersInterfaceToImplementationWithRegistry(Mocker mocker) {
		registry.register(Interface.class, Implementation.class);
		
		mocker.replay();
		
		interfaceToImplementationRegistryEntry.registerWith(registry);
	}
}
