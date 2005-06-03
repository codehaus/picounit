/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.individual_tests;

import picounit.Test;
import picounit.Validator;
import junit.framework.TestResult;

public class TestWithSetUpAndTearDown implements Test {
	public static final Validator validator = new Validator(TestWithSetUpAndTearDown.class) {
		public void validate(TestResult testResult) {
			matches("setUp testOne tearDown");
		}
	};

	public void setUp() {
		validator.record("setUp");
	}

	public void testOne() {
		validator.record("testOne");
	}

	public void tearDown() {
		validator.record("tearDown");
	}
}
