/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.ignoredTests;

import picounit.Ignore;
import picounit.IgnoreReason;
import picounit.Test;
import picounit.Validator;
import junit.framework.TestResult;

public class NotIgnoredTest implements Test, Ignore {
	public static final Validator validator = new Validator(NotIgnoredTest.class) {
		@Override
		public void validate(TestResult testResult) {
			matches("ignoredWhen testSomething");
		}
	};

	public void testSomething() {
		validator.record("testSomething");
	}

	public void ignoredWhen(IgnoreReason ignoreReason) {
		validator.record("ignoredWhen");

		ignoreReason.setWhy("Don't want to run");
		ignoreReason.setCondition(false);
	}
}
