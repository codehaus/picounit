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
import previous.picounit.Mocker;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;

public class DelegatingTestResultTest implements previous.picounit.Test {
	private final Mocker should;
	private DelegateTestResult delegateTestResult;
	
	private DelegatingTestResult delegatingTestResult;
	
	private final Test test = new TestCase() {};
	private final Throwable throwable = new Throwable();
	private final AssertionFailedError assertionFailedError = new AssertionFailedError();
	
	public DelegatingTestResultTest(Mocker should) {
		this.should = should;	
	}
	
	public void mock(DelegateTestResult delegateTestResult) {
		this.delegateTestResult = delegateTestResult;
		
		this.delegatingTestResult = new DelegatingTestResult(delegateTestResult);
	}
	
	public void testDelegatesStartTest() {
		delegateTestResult.startTest(test);
		
		should.expectAboveWhenTheFollowingOccurs();
		
		delegatingTestResult.startTest(test);
	}
	
	public void testDelegatesEndTest() {
		delegateTestResult.endTest(test);
		
		should.expectAboveWhenTheFollowingOccurs();
		
		delegatingTestResult.endTest(test);
	}
	
	public void testDelegatesAddError() {
		delegateTestResult.addError(test, throwable);
		
		should.expectAboveWhenTheFollowingOccurs();
		
		delegatingTestResult.addError(test, throwable);
	}
	
	public void testDelegatesAddFailure() {
		delegateTestResult.addFailure(test, assertionFailedError);

		should.expectAboveWhenTheFollowingOccurs();

		delegatingTestResult.addFailure(test, assertionFailedError);
	}
}
