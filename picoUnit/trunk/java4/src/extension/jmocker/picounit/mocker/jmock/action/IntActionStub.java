/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock.action;

import org.jmock.core.Invocation;
import org.jmock.core.Stub;

import picounit.mocker.IntAction;

public class IntActionStub implements Stub {
	private final IntAction intAction;

	public IntActionStub(IntAction intAction) {
		this.intAction = intAction;
	}

	public Object invoke(Invocation invocation) throws Throwable {
		return new Integer(intAction.perform());
	}

	public StringBuffer describeTo(StringBuffer buffer) {
		return null;
	}
}