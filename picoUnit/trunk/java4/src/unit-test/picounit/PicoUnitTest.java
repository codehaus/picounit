/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.classloader.MethodParameterRegistry;
import picounit.finder.ContextFinder;
import picounit.finder.SinglePicoUnitTestSuite;
import picounit.finder.TestFilter;
import picounit.registry.RegistryEntry;
import previous.picounit.Verify;
import junit.framework.TestSuite;

public class PicoUnitTest implements previous.picounit.Test {
	private static class StartingClass implements Test {}

	private final TestFilter testFilter = null;
	private final ContextFinder contextFinder = null;
	private final MethodParameterRegistry methodParameterRegistry = null;
	private final PicoUnit picoUnit = new PicoUnit();

	private RegistryEntry registryEntry;

	private final Verify verify;
	
	public PicoUnitTest(Verify verify) {
		this.verify = verify;
	}
	
	public void mock(RegistryEntry registryEntry) {
		this.registryEntry = registryEntry;
	}
	
	public void xtestGenerateSingleCreatesSinglePicoUnitTestSuite() {
		verify.equal(singleTest(StartingClass.class.getName(), 
		new SinglePicoUnitTestSuite(StartingClass.class.getName(), StartingClass.class, testFilter,
			registryEntry, contextFinder, methodParameterRegistry)), picoUnit.generateSingleJUnitTest(StartingClass.class));
	}

	private TestSuite singleTest(String name, SinglePicoUnitTestSuite test) {
		TestSuite testSuite = new TestSuite(name);
		testSuite.addTest(test);
		return testSuite;
	}

	private class TestClass implements Test {}

	public void xtestGenerateSingle() {
		verify.equal(singlePicoUnitTestSuite(TestClass.class.getName(), TestClass.class), picoUnit.generateSingleJUnitTest(TestClass.class));
	}

	public void xtestGenerateSingleWithName() {
		verify.equal(singlePicoUnitTestSuite("Some Name", TestClass.class), picoUnit("Some Name").generateSingleJUnitTest(TestClass.class));
	}
	
	private PicoUnit picoUnit(String name) {
		return new PicoUnit(name);
	}

	public void testDetectingClass() {
		new PicoUnit().generateJUnitTest();
	}
	
	private SinglePicoUnitTestSuite singlePicoUnitTestSuite(String name, Class startingClass) {
		return new SinglePicoUnitTestSuite(name, startingClass, testFilter, registryEntry, contextFinder,
			methodParameterRegistry);
	}
}
