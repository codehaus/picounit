/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestResult;

public class DelegatingTestResult extends TestResult {
	private final DelegateTestResult delegateTestResult;

	public DelegatingTestResult(DelegateTestResult delegateTestResult) {
		this.delegateTestResult = delegateTestResult;
	}

	public void startTest(Test test) {
		delegateTestResult.startTest(test);
	}

	public void endTest(Test test) {
		delegateTestResult.endTest(test);
	}

	public synchronized void addError(Test error, Throwable throwable) {
		delegateTestResult.addError(error, throwable);
	}
	
	public synchronized void addFailure(Test failure, AssertionFailedError assertionFailedError) {
		delegateTestResult.addFailure(failure, assertionFailedError);
	}
}
