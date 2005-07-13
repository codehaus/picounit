/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Invocation;
import org.jmock.core.matcher.InvokedRecorder;

import junit.framework.Assert;

public class ExtendedInvokedRecorder extends InvokedRecorder {
	private int invocationCount;
	
	public ExtendedInvokedRecorder() {
		invocationCount = 0;
	}

    public int getInvocationCount()
    {
        return invocationCount;
    }

    public boolean hasBeenInvoked()
    {
        return invocationCount > 0;
    }

	public void verifyHasBeenInvokedExactly(int expectedCount) {
		Assert.assertEquals(expectedCount, invocationCount);
	}

    public void invoked(Invocation invocation) {
		super.invoked(invocation);

        invocationCount++;
    }
}
