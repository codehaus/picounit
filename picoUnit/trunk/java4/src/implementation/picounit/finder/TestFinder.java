/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.classloader.MethodParameterRegistry;
import picounit.registry.RegistryEntries;
import junit.framework.TestSuite;

public interface TestFinder {
	TestSuite findTests(TestFilter testFilter, RegistryEntries registryEntries, String name, Class startingClass,
		Class markerClass, MethodParameterRegistry methodParameterRegistry);
}