/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Invocation;

import junit.framework.Assert;

public class InvokeRangeMatcher extends ExtendedInvokedRecorder {
	private final int minimumCount;
    private final int maximumCount;

    public InvokeRangeMatcher(int minimumCount, int maximumCount) {
        this.minimumCount = minimumCount;
		this.maximumCount = maximumCount;
    }
	
    public boolean matches( Invocation invocation ) {
        return getInvocationCount() < maximumCount;
    }

	public void verify() {
		Assert.assertTrue("expected method was not invoked the expected number of times: expected between " +
			minimumCount + " and " + maximumCount + " times, was invoked " + getInvocationCount() + " times",
			getInvocationCount() >= minimumCount && getInvocationCount() <= maximumCount);
    }

    public boolean hasDescription() {
        return true;
    }

    public StringBuffer describeTo( StringBuffer buffer ) {
        return buffer.append("expected between ")
			.append(minimumCount)
			.append(" and ")
			.append(maximumCount)
			.append(" times, invoked ")
			.append(getInvocationCount())
			.append(" times");
    }
}
