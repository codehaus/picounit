/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.individual_tests;

import picounit.FunctionalTest;
import picounit.Marker;
import picounit.PicoUnit;
import picounit.individual_test_with_different_marker.TestWithDifferentMarker;
import previous.picounit.Verify;
import junit.framework.TestFailure;
import junit.framework.TestResult;

public class IndividualTestsTestCase implements FunctionalTest {
	private final TestResult testResult = new TestResult();

	private void runSingle(Class testClass) {
		new PicoUnit().generateSingleJUnitTest(testClass).run(testResult);
	}
	
	private void runSingle(Class testClass, Class markerClass) {
		new PicoUnit().setType(markerClass).generateSingleJUnitTest(testClass).run(testResult); 
	}

	public void testEmptyTest(Verify verify) {
		runSingle(EmptyTest.class);

		verify.equal(1, testResult.runCount());
		verify.equal(1, testResult.errorCount());
		TestFailure error = (TestFailure) testResult.errors().nextElement();
		verify.equal("No tests found", error.exceptionMessage());
	}

	public void testTestRequiringNoFixtures(Verify verify) {
		TestRequiringNoFixtures.operations.clear();

		runSingle(TestRequiringNoFixtures.class);

		verify.equal(1, testResult.runCount());
		verify.equal(0, testResult.failureCount() + testResult.errorCount());
		TestRequiringNoFixtures.operations.matches("testOne");
	}
	
	public void testTestRequiringFixtureInConstructor() {
		TestRequiringFixtureInConstructor.validator.runSingle();
	}
	
	public void testTestRequiringFixtureInTestMethod(Verify verify) {
		TestRequiringFixtureInTestMethod.operations.clear();
		runSingle(TestRequiringFixtureInTestMethod.class);
		
		verify.equal(1, testResult.runCount());
		verify.equal(0, testResult.failureCount() + testResult.errorCount());

		TestRequiringFixtureInTestMethod.operations.matches("testOne");
		verify.notNull(TestRequiringFixtureInTestMethod.operations.get("testOne"));
	}
	
	public void testTestWithSetUpAndTearDown() {
		TestWithSetUpAndTearDown.validator.runSingle();
	}
	
	public void testTestWithMockMethod() {
		TestWithMockMethod.validator.runSingle();
	}
	
	public void testTestWithDifferentMarker(Verify verify) {
		TestWithDifferentMarker.operations.clear();

		runSingle(TestWithDifferentMarker.class, Marker.class);

		verify.equal(1, testResult.runCount());
		verify.equal(0, testResult.failureCount() + testResult.errorCount());
		TestWithDifferentMarker.operations.matches("testOne");
	}
}
