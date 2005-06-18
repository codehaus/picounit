/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.PicoUnit;
import junit.framework.TestResult;

public class TestRunner {
	private final PicoUnit picoUnit;

	public TestRunner() {
		this(new PicoUnit());
	}

	public TestRunner(PicoUnit picoUnit) {
		this.picoUnit = picoUnit;
	}

	public TestResult runSingle(Class testClass) {
		return runSingle(testClass, Test.class);
	}

	public TestResult runSingle(Class testClass, Class markerClass) {
		TestResult testResult = new TestResult();

		picoUnit.setType(markerClass).generateSingleJUnitTest(testClass).run(testResult);

		return testResult;
	}
	
	public TestResult run(Class testClass) {
		return run(testClass, Test.class);
	}

	public TestResult run(Class testClass, Class markerClass) {
		TestResult testResult = new TestResult();

		picoUnit.setType(markerClass).generateJUnitTest(testClass).run(testResult);

		return testResult;
	}
}