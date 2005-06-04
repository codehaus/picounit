/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Invocation;
import org.jmock.core.Stub;

import picounit.mocker.Action;

public class ActionStub<Type> implements Stub {
	private final Action<Type> action;

	public ActionStub(Action<Type> action) {
		this.action = action;
	}
	
	public Object invoke(Invocation invocation) throws Throwable {
		return action.perform();
	}

	public StringBuffer describeTo(StringBuffer stringBuffer) {
		return null;
	}
}
