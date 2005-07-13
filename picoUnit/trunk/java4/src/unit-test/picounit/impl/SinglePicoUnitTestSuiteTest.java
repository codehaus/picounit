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
import picounit.finder.TestListener;
import picounit.registry.RegistryEntry;
import previous.picounit.Constraints;
import previous.picounit.Verify;

public class SinglePicoUnitTestSuiteTest implements previous.picounit.Test {
	private final TestFilter testFilter = null;
	private final MethodParameterRegistry methodParameterRegistry = null;
	private RegistryEntry registryEntry;
	private ClassLoader classLoader = null;
	private TestListener testListener;

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

		verify.that(singlePicoUnitTestSuite.countTestCases()).isEqualTo(1);
		verify.that(singlePicoUnitTestSuite.tests().nextElement())
			.isEqualTo(new NoTestsFoundTestCase(ExampleTest.class));
	}

	public void testTwoInstancesWithSameStartingClassAreEqual() {
		class StartingClass implements Test {}

		verify.that(singlePicoUnitTestSuite(StartingClass.class))
			.isEqualTo(singlePicoUnitTestSuite(StartingClass.class));
	}

	public void testTwoInstancesWithSameStartingClassAndNameAreEqual() {
		class StartingClass implements Test {}

		verify.that(singlePicoUnitTestSuite("name", StartingClass.class))
			.isEqualTo(singlePicoUnitTestSuite("name", StartingClass.class));
	}

	public void testTwoInstancesWithSameStartingClassButDifferentNameAreNotEqual() {
		class StartingClass implements Test {}

		verify.that(singlePicoUnitTestSuite("different name", StartingClass.class))
			.isDifferentTo(singlePicoUnitTestSuite("name", StartingClass.class));
	}

	public void testHasSameNameAsTestClass() {
		class StartingClass implements Test {}

		verify.that(singlePicoUnitTestSuite(StartingClass.class).getName())
			.isEqualTo(StartingClass.class.getName());
	}

	private <T> void expectIllegalArgumentException(Class<T> testClass, String expectedMessage) {
		try {
			singlePicoUnitTestSuite(testClass);

			verify.fail("IllegalArgumentException expected");
		}
		catch (IllegalArgumentException illegalArgumentException) {
			verify.that(illegalArgumentException.getMessage())
				.isEqualTo(testClass.getName() + expectedMessage);
		}
	}
	
	public <T> SinglePicoUnitTestSuite<T> singlePicoUnitTestSuite(Class<T> startingClass) {
		return singlePicoUnitTestSuite(startingClass.getName(), startingClass);
	}

	public <T> SinglePicoUnitTestSuite<T> singlePicoUnitTestSuite(String name, Class<T> startingClass) {
		return new SinglePicoUnitTestSuite<T>(name, startingClass, testFilter, registryEntry, methodParameterRegistry,
			classLoader, testListener);
	}
}
