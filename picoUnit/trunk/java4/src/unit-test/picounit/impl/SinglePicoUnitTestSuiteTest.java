/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Test;
import picounit.classloader.MethodParameterRegistry;
import picounit.finder.NoTestsFoundTestCase;
import picounit.finder.SinglePicoUnitTestSuite;
import picounit.finder.TestFilter;
import picounit.registry.RegistryEntry;
import previous.picounit.Constraints;
import previous.picounit.Verify;

public class SinglePicoUnitTestSuiteTest implements previous.picounit.Test {
	private final TestFilter testFilter = null;
	private final MethodParameterRegistry methodParameterRegistry = null;
	private RegistryEntry registryEntry;
	private ClassLoader classLoader = null;

	private final Verify verify;

	interface NonTestInterface extends Test {}
	
	public SinglePicoUnitTestSuiteTest(Constraints is, Verify verify) {
		this.verify = verify;
		
		this.registryEntry = (RegistryEntry) is.instanceOf(RegistryEntry.class);
	}

	public void testThrowsExceptionIfConstructedWithInterface() {
		expectIllegalArgumentException(NonTestInterface.class, " is an interface");
	}

	public void testThrowsExceptionIfConstructedWithAnAbstractClass() {
		abstract class NonTestClass implements Test {}

		expectIllegalArgumentException(NonTestClass.class, " is abstract");
	}

	public void testAddsTestErrorIfNoTestClassesAreFound() {
		class ExampleTest implements Test {}

		SinglePicoUnitTestSuite singlePicoUnitTestSuite = singlePicoUnitTestSuite(ExampleTest.class);

		verify.equal(1, singlePicoUnitTestSuite.countTestCases());
		verify.equal(new NoTestsFoundTestCase(ExampleTest.class), singlePicoUnitTestSuite.tests().nextElement());
	}

	public void testTwoInstancesWithSameStartingClassAreEqual() {
		class StartingClass implements Test {}

		verify.equal(singlePicoUnitTestSuite(StartingClass.class), singlePicoUnitTestSuite(StartingClass.class));
	}

	public void testTwoInstancesWithSameStartingClassAndNameAreEqual() {
		class StartingClass implements Test {}

		verify.equal(singlePicoUnitTestSuite("name", StartingClass.class), singlePicoUnitTestSuite("name", StartingClass.class));
	}

	public void testTwoInstancesWithSameStartingClassButDifferentNameAreNotEqual() {
		class StartingClass implements Test {}

		verify.notEqual(singlePicoUnitTestSuite("name", StartingClass.class),
			singlePicoUnitTestSuite("different name", StartingClass.class));
	}

	public void testTwoInstancesWithDifferentStartingClassAreNotEqual() {
		class OneStartingClasss implements Test {}
		class AnotherStartingClass implements Test {}

		verify.notEqual(singlePicoUnitTestSuite(OneStartingClasss.class),
			singlePicoUnitTestSuite(AnotherStartingClass.class));
	}

	public void testHasSameNameAsTestClass() {
		class StartingClass implements Test {}

		verify.equal(StartingClass.class.getName(), singlePicoUnitTestSuite(StartingClass.class).getName());
	}

	private void expectIllegalArgumentException(Class testClass, String expectedMessage) {
		try {
			singlePicoUnitTestSuite(testClass);

			verify.fail("IllegalArgumentException expected");
		}
		catch (IllegalArgumentException illegalArgumentException) {
			verify.equal(testClass.getName() + expectedMessage, illegalArgumentException.getMessage());
		}
	}
	
	public SinglePicoUnitTestSuite singlePicoUnitTestSuite(Class startingClass) {
		return singlePicoUnitTestSuite(startingClass.getName(), startingClass);
	}

	public SinglePicoUnitTestSuite singlePicoUnitTestSuite(String name, Class startingClass) {
		return new SinglePicoUnitTestSuite(name, startingClass, testFilter, registryEntry, methodParameterRegistry,
			classLoader);
	}
}
