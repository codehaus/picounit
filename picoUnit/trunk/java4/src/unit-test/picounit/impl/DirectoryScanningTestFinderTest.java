/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Ignore;
import picounit.Instrumentation;
import picounit.Test;
import picounit.classloader.MethodParameterRegistry;
import picounit.finder.ClassFinder;
import picounit.finder.DirectoryScanningTestFinder;
import picounit.finder.FindAction;
import picounit.finder.TestFilter;
import picounit.finder.TestInstantiator;
import picounit.registry.RegistryEntries;
import previous.picounit.Constraints;
import previous.picounit.Mocker;
import previous.picounit.Verify;
import junit.framework.TestSuite;

public class DirectoryScanningTestFinderTest implements previous.picounit.Test{
	private DirectoryScanningTestFinder testFinder;
	
	private ClassFinder classFinder;
	private ClassLoader classLoader;
	private TestFilter testFilter;
	private RegistryEntries registryEntries;
	private MethodParameterRegistry methodParameterRegistry;
	
	private final FindAction findAction;

	private final Mocker should;
	private final Verify verify;
	private final Constraints is;
	
	public DirectoryScanningTestFinderTest(Mocker mocker, Constraints is, Verify verify) {
		this.should = mocker;
		this.is = is;
		this.verify = verify;
		this.findAction = (FindAction) is.instanceOf(FindAction.class);
	}
	
	public void mock(ClassFinder classFinder, TestInstantiator testInstantiator,
		ClassLoader classLoader, TestFilter testFilter, RegistryEntries registryEntries,
		MethodParameterRegistry methodParameterRegistry) {
		
		this.testFinder =
			new DirectoryScanningTestFinder(classFinder, testInstantiator, classLoader);
		
		this.classFinder = classFinder;
		this.classLoader = classLoader;
		this.testFilter = testFilter;
		this.registryEntries = registryEntries;
		this.methodParameterRegistry = methodParameterRegistry;
	}

	class StartingClass {}
	class TestExample implements Test {}
	class NonTestClass {}

	public void testFindClassesBelowAClass() throws ClassNotFoundException {
		classFinder.findClasses(StartingClass.class, findAction);
		should.call((Class) classLoader.loadClass(Ignore.class.getName())).andReturn(Ignore.class);
		registryEntries.register(Instrumentation.class, is.instanceOf(InstrumentationImpl.class));

		should.expectAboveWhenTheFollowingOccurs();

		TestSuite testSuite = testFinder.findTests(testFilter, registryEntries, "name", StartingClass.class,
			Test.class, methodParameterRegistry);

		verify.that(testSuite.getName()).isEqualTo("name");
	}
}
