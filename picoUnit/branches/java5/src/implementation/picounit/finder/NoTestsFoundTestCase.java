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

public class NoTestsFoundTestCase extends TestCase {
	private final Class startingClass;

	public NoTestsFoundTestCase(Class startingClass) {
		super(startingClass.getName());

		this.startingClass = startingClass;
	}

	public void run(TestResult testResult) {
		testResult.startTest(this);

		testResult.addError(this, new NoTestsFoundException(startingClass));

		testResult.endTest(this);
	}

	public boolean equals(Object object) {
		if (object == null || !getClass().equals(object.getClass())) {
			return false;
		}

		NoTestsFoundTestCase other = (NoTestsFoundTestCase) object;

		return startingClass.equals(other.startingClass);
	}

	public int hashCode() {
		return startingClass.hashCode();
	}
}