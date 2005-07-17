/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Invocation;

import picounit.mocker.MockInvocationInspector;

public class MockInvocationObserver implements MockInvocationInspector, MockInvocationListener {
	private Invocation lastInvocation;

	///////////////////////////////////////////////////////////////////////////////////////////////
	// MockInvocationInspector
	///////////////////////////////////////////////////////////////////////////////////////////////

	public Class getLastInvocationReturnType() {
		return lastInvocation.invokedMethod.getReturnType();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// MockInvocationListener
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void fire(Invocation invocation) {
		this.lastInvocation = invocation;
	}
}
