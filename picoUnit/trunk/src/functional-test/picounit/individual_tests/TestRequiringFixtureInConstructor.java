/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.individual_tests;

import picounit.Mocker;
import picounit.Test;
import picounit.Validator;
import junit.framework.Assert;
import junit.framework.TestResult;

public class TestRequiringFixtureInConstructor implements Test {
	public static final Validator validator = new Validator(TestRequiringFixtureInConstructor.class) {
		public void validate(TestResult testResult) {
			Assert.assertEquals(1, testResult.runCount());
			Assert.assertEquals(0, testResult.failureCount() + testResult.errorCount());

			has("TestRequiringFixtureInConstructor");
			matches("TestRequiringFixtureInConstructor testOne");
		}
	};

	public TestRequiringFixtureInConstructor(Mocker mocker) {
		validator.record("TestRequiringFixtureInConstructor", mocker);
	}

	public void testOne() {
		validator.record("testOne");
	}
}