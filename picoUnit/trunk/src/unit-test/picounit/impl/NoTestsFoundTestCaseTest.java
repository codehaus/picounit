/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.DelegateTestResult;
import picounit.DelegatingTestResult;
import picounit.finder.NoTestsFoundException;
import picounit.finder.NoTestsFoundTestCase;
import previous.picounit.Mocker;
import previous.picounit.Test;
import previous.picounit.Verify;

public class NoTestsFoundTestCaseTest implements Test {
	public static class StartingClass {}
	
	private DelegateTestResult delegateTestResult;
	private DelegatingTestResult delegatingTestResult;

	public void mock(DelegateTestResult delegateTestResult) {
		this.delegatingTestResult = new DelegatingTestResult(delegateTestResult);

		this.delegateTestResult = delegateTestResult;
	}
	
	private NoTestsFoundTestCase noTestsFoundTestCase(Class startingClass) {
		return new NoTestsFoundTestCase(startingClass);
	}

	public void testNameSameAsStartingClass(Verify verify) {
		verify.equal(StartingClass.class.getName(), noTestsFoundTestCase(StartingClass.class).getName());
	}
	
	public void testHasOneTest(Verify verify) {
		verify.equal(1, noTestsFoundTestCase(StartingClass.class).countTestCases());
	}
	
	public void testProducesErrorWhenRun(Mocker mocker) {
		NoTestsFoundTestCase noTestsFoundTestCase = noTestsFoundTestCase(StartingClass.class);
		
		delegateTestResult.startTest(noTestsFoundTestCase);
		
		delegateTestResult.addError(noTestsFoundTestCase, new NoTestsFoundException(StartingClass.class));

		delegateTestResult.endTest(noTestsFoundTestCase);

		mocker.replay();
		
		noTestsFoundTestCase.run(delegatingTestResult);
	}

	public static class AnotherStartingClass {}

	public void testEquals(Verify verify) {
		verify.equal("Instances constructed with same starting class should be equal",
			noTestsFoundTestCase(StartingClass.class), noTestsFoundTestCase(StartingClass.class));	

		verify.notEqual("Instances constructed with different starting classes  should not be equal",
			noTestsFoundTestCase(StartingClass.class), noTestsFoundTestCase(AnotherStartingClass.class));
	}
}
