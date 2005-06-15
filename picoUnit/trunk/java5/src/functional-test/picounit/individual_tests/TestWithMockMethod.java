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

public class TestWithMockMethod implements Test {
	public static final Validator validator = new Validator(TestWithMockMethod.class) {
		@Override
		public void validate(TestResult testResult) {
			matches("mock test");
		}
	};

	public void mock() {
		validator.record("mock");
	}

	public void test() {
		validator.record("test");
	}
}
