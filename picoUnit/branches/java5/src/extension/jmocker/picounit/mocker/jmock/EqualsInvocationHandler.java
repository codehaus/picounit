/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import picounit.util.MethodUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class EqualsInvocationHandler implements InvocationHandler {
	private final MethodUtil methodUtil = new MethodUtil();

	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (args != null && args.length == 1 && methodUtil.isEquals(method)) {
			return new Boolean(args[0] != null && equals(args[0]));
		}
		else if (args == null || args.length == 0 && methodUtil.isHashCode(method)) {
			return new Integer(hashCode());
		}
		else if (args == null || args.length == 0 && methodUtil.isToString(method)) {
			return toString();
		}

		return null;
	}

	public abstract boolean equals(Object object);
	public abstract String toString();
	
	@Override
	public final int hashCode() {
		return getClass().hashCode();
	}
}