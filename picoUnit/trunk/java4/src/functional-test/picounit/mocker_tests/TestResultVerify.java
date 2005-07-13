/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker_tests;

import previous.picounit.Verify;

import java.util.Enumeration;

import junit.framework.TestFailure;
import junit.framework.TestResult;

public class TestResultVerify {
	private final Verify verify;

	private TestResult testResult;
	
	public TestResultVerify(Verify verify) {
		this.verify = verify;
	}

	public TestResultVerify that(TestResult testResult) {
		this.testResult = testResult;
		
		return this;
	}

	public TestResultVerify ran(int expectedRunCount) {
		verify.because("unexpected number of tests")
			.that(testResult.runCount()).isEqualTo(expectedRunCount);

		return this;
	}
	
	public TestResultVerify failed(int expectedFailureCount) {
		if (testResult.failureCount() != expectedFailureCount) {
			dumpStackTraces(testResult.failures());

			verify.because("unexpected number of failures")
				.that(testResult.failureCount()).isEqualTo(expectedFailureCount);
		}

		return this;
	}

	public TestResultVerify errored(int expectedErrorCount) {
		if (testResult.errorCount() != expectedErrorCount) {
			dumpStackTraces(testResult.errors());

			verify.because("unexpected number of errors")
				.that(testResult.errorCount()).isEqualTo(expectedErrorCount);
		}

		return this;
	}

	private void dumpStackTraces(Enumeration problems) {
		Enumeration failures = problems;
		
		while (failures.hasMoreElements()) {
			TestFailure problem = (TestFailure) failures.nextElement();
			
			problem.thrownException().printStackTrace();
		}
	}
}