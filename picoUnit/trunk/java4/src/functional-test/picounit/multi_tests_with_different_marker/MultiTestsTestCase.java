/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.multi_tests_with_different_marker;

import picounit.FunctionalTest;
import picounit.Marker;
import picounit.TestRunner;
import picounit.multi_tests_with_different_marker.sub.ThirdTest;
import previous.picounit.Verify;
import junit.framework.TestResult;

public class MultiTestsTestCase implements FunctionalTest {
	private final TestRunner testRunner = new TestRunner();
	
	public void testRunsAllTestInDirectoryAndBelow(Verify verify) {
		FirstTest.testInvoked = false;
		SecondTest.testInvoked = false;
		ThirdTest.testInvoked = false;

		TestResult testResult = testRunner.run(FirstTest.class, Marker.class);

		verify.that(testResult.runCount()).isEqualTo(3);
		verify.that(testResult.failureCount() + testResult.errorCount()).isEqualTo(0);
		verify.thatBoolean(FirstTest.testInvoked).isTrue();
		verify.thatBoolean(SecondTest.testInvoked).isTrue();
		verify.thatBoolean(ThirdTest.testInvoked).isTrue();
	}
}
