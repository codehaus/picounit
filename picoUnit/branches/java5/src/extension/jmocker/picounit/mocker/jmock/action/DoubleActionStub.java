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

import picounit.mocker.DoubleAction;

public class DoubleActionStub implements Stub {
	private final DoubleAction doubleAction;

	public DoubleActionStub(DoubleAction doubleAction) {
		this.doubleAction = doubleAction;
	}

	public Object invoke(Invocation invocation) throws Throwable {
		return new Double(doubleAction.perform());
	}

	public StringBuffer describeTo(StringBuffer buffer) {
		return null;
	}
}