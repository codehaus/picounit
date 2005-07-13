/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.registry;

import picounit.Registry;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RegistryEntries implements Registry, RegistryEntry {
	private final List<RegistryEntry> entries = new LinkedList<RegistryEntry>();

	public void register(Class type, Class implementation) {
		addEntry(new InterfaceToImplementationRegistryEntry(type, implementation));
	}

	public void register(Class type, Object instance) {
		addEntry(new InstanceRegistryEntry(type, instance));
	}

	public void register(Class implementation) {
		addEntry(new ImplementationRegistryEntry(implementation));
	}

	public void registerWith(Registry registry) {
		for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
			RegistryEntry registryEntry = (RegistryEntry) iterator.next();

			registryEntry.registerWith(registry);
		}
	}

	private void addEntry(RegistryEntry entry) {
		entries.add(entry);
	}
}
