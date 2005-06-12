/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Ignore;
import picounit.Registry;
import picounit.Test;
import picounit.classloader.MethodParameterRegistry;
import picounit.finder.ClassFinder;
import picounit.finder.ContextFinder;
import picounit.finder.DirectoryScanningTestFinder;
import picounit.finder.FindAction;
import picounit.finder.TestFilter;
import picounit.finder.TestInstantiator;
import picounit.registry.RegistryEntry;
import previous.picounit.Constraints;
import previous.picounit.Mocker;
import previous.picounit.Verify;
import junit.framework.TestSuite;

public class DirectoryScanningTestFinderTest implements previous.picounit.Test {
	private DirectoryScanningTestFinder testFinder;
	
	private ClassFinder classFinder;
	private ClassLoader classLoader;
	private TestFilter testFilter;
	private RegistryEntry registryEntry;
	private MethodParameterRegistry methodParameterRegistry;
	
	private final FindAction findAction;

	private final Mocker mocker;
	private final Verify verify;
	private final Constraints is;

	public DirectoryScanningTestFinderTest(Mocker mocker, Constraints is, Verify verify) {
		this.mocker = mocker;
		this.verify = verify;
		this.is = is;

		this.findAction = (FindAction) is.instanceOf(FindAction.class);
	}
	
	public void mock(ClassFinder classFinder, ContextFinder contextFinder,
		TestInstantiator testInstantiator, ClassLoader classLoader, TestFilter testFilter,
		RegistryEntry registryEntry, MethodParameterRegistry methodParameterRegistry) {
		
		this.testFinder =
			new DirectoryScanningTestFinder(classFinder, contextFinder, testInstantiator, classLoader);
		
		this.classFinder = classFinder;
		this.classLoader = classLoader;
		this.testFilter = testFilter;
		this.registryEntry = registryEntry;
		this.methodParameterRegistry = methodParameterRegistry;
	}

	public void testFindClassesBelowAClass() throws ClassNotFoundException {
		class StartingClass {}
		class TestExample implements Test {}
		class NonTestClass {}

		classFinder.findClasses(StartingClass.class, findAction);
		registryEntry.registerWith((Registry) is.instanceOf(Registry.class));
		mocker.expect(classLoader.loadClass(Ignore.class.getName())).andReturn(Ignore.class);

		mocker.replay();

		TestSuite testSuite = testFinder.findTests(testFilter, registryEntry, "name", StartingClass.class,
			Test.class, methodParameterRegistry);
		verify.equal("name", testSuite.getName());
	}
}
