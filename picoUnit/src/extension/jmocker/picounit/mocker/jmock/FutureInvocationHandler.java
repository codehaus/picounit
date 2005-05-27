/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import picounit.Future;
import picounit.util.MethodUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FutureInvocationHandler implements InvocationHandler {
	private final MethodUtil methodUtil = new MethodUtil();
	private final Future futureEquals;

	public FutureInvocationHandler(Class futureType) {
		futureEquals = new FutureImpl(futureType);
	}

	public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
		if (method.getDeclaringClass().equals(Future.class) ||
			methodUtil.isHashCode(method) ||
			methodUtil.isEquals(method)) {
			return method.invoke(futureEquals, arguments);
		}
		else {
			return method.invoke(futureEquals.getValue(), arguments);
		}
	}
}
