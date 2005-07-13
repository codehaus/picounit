/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker_tests;

import picounit.FunctionalTest;
import picounit.TestRunner;
import previous.picounit.Ignore;
import previous.picounit.IgnoreReason;
import previous.picounit.Verify;

import junit.framework.TestFailure;
import junit.framework.TestResult;

public class MockerTestsTestCase implements FunctionalTest, Ignore {
	private final TestRunner testRunner = new TestRunner();

	public void ignoredWhen(IgnoreReason ignoreReason) {
		ignoreReason.setWhy("Haven't implemented rethrowing of suppressed exceptions");
	}

	public void testOverzealousCatchTest(Verify verify) {
		TestResult testResult = testRunner.runSingle(OverzealousCatchTest.class);

		verify.because("Exceptions from mocks should be rethrown if suppressed by overzealous catch")
			.that(testResult.failureCount()).isEqualTo(1);

		TestFailure error = (TestFailure) testResult.failures().nextElement();
		System.out.println(error);
	}

	public void testMockerErrorsThrownBeforeVerifyErrors(Verify verify) {
		TestResult testResult = testRunner.runSingle(MockerErrorsThrownBeforeVerifyErrors.class);

		verify.that(testResult.runCount()).isEqualTo(1);
		verify.that(testResult.failureCount()).isEqualTo(1);
		TestFailure testFailure = (TestFailure) testResult.failures().nextElement();
		verify.that(testFailure.exceptionMessage()).doesNotContain("verify error");
	}

	public void testMockArray(Verify verify) {
		TestResult testResult = testRunner.runSingle(MockArrayTest.class);

		verify.that(testResult.runCount()).isEqualTo(2);
		verify.that(testResult.failureCount()).isEqualTo(0);
		verify.that(testResult.errorCount()).isEqualTo(0);
	}
	
	public void testCannotMockClassWithFinalEquals(Verify verify) {
		TestResult testResult = testRunner.runSingle(MockClassWithFinalEqualsTest.class);

		verify.that(testResult.runCount()).isEqualTo(1);
		verify.that(testResult.failureCount()).isEqualTo(1);
	}
}
