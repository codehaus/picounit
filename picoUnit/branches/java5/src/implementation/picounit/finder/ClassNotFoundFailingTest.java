/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import junit.framework.TestCase;
import junit.framework.TestResult;

public class ClassNotFoundFailingTest extends TestCase {
	final ClassNotFoundException classNotFoundException;

	public ClassNotFoundFailingTest(String className, ClassNotFoundException classNotFoundException) {
		super(className);

		this.classNotFoundException = classNotFoundException;
	}

	@Override
	public int countTestCases() {
		return 1;
	}

	@Override
	public void run(TestResult testResult) {
		testResult.startTest(this);

		testResult.addError(this, classNotFoundException);

		testResult.endTest(this);
	}
}