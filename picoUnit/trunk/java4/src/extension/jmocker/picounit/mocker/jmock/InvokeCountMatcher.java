/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Invocation;

public class InvokeCountMatcher extends ExtendedInvokedRecorder {
	public InvokeCountMatcher(int expectedCount) {
		this.expectedCount = expectedCount;
	}

	public boolean matches(Invocation invocation) {
		return getInvocationCount() < expectedCount;
	}

	public void verify() {
		verifyHasBeenInvokedExactly(expectedCount);
	}

	public boolean hasDescription() {
		return true;
	}

	public StringBuffer describeTo(StringBuffer stringbuffer) {
		return stringbuffer.append("expected ").append(expectedCount).append(" times, invoked ")
			.append(getInvocationCount()).append(" times");
	}

	private int expectedCount;
}
