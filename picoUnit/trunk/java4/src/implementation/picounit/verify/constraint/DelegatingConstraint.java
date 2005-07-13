/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import picounit.PicoUnitException;
import picounit.util.MethodUtil;

import java.lang.reflect.Method;

public class DelegatingConstraint<T> extends Constraint<T> {
	private final Object delegate;

	private final Method evaluate;
	private final Method describeFailure;

	public DelegatingConstraint(Class<T> type, Object delegate) {
		this(type, delegate, new MethodUtil());
	}

	public DelegatingConstraint(Class<T> type, Object delegate, MethodUtil methodUtil) {
		super();

		this.delegate = delegate;
		this.evaluate = methodUtil.getMethod(delegate.getClass(), "evaluate", type);
		
		if (!evaluate.getReturnType().equals(boolean.class)) {
			throw new PicoUnitException("'evaluate' should have signature: boolean evaluate(<Type>)");
		}

		this.describeFailure = methodUtil.getMethod(delegate.getClass(), "describeFailure");

		if (!describeFailure.getReturnType().equals(String.class)) {
			throw new PicoUnitException("'describeFailure' should have signature: String describeFailure()");
		}
	}

	public boolean evaluate(T value) {
		return (Boolean) invoke(evaluate, delegate, value);
	}

	public String describeFailure() {
		return (String) invoke(describeFailure, delegate, null);
	}

	private <T> Object invoke(Method toInvoke, final Object invokeOn, T value) {
		try {
			return toInvoke.invoke(invokeOn, value);
		}
		catch (Exception exception) {
			throw new PicoUnitException(exception);
		}
	}
}