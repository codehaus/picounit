/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder.ignore;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class IgnoredTestSuite extends TestSuite {
	public IgnoredTestSuite(Class classToIgnore, String whyIgnore) {
		super(classToIgnore.getName());

		addTest(new IgnoredTest(whyIgnore));
	}

	public static class IgnoredTest extends TestCase {
		public IgnoredTest(String name) {
			super(name);
		}

		public int countTestCases() {
			return 1;
		}

		public void run(TestResult testResult) {
			testResult.startTest(this);
			testResult.endTest(this);
		}
	}
}