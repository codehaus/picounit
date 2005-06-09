/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.reflection;

import picounit.Context;
import picounit.Invokable;
import picounit.registry.Resolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoker {
	private final Resolver resolver;
	private final Invoker parameterInvoker;

	public Invoker(Resolver resolver) {
		this.resolver = resolver;
		this.parameterInvoker = this;
	}

	public Invoker(Resolver resolver, Invoker parameterInvoker) {
		this.resolver = resolver;
		this.parameterInvoker = parameterInvoker;
	}

	public void invoke(Method method, Object target) throws IllegalArgumentException,
		IllegalAccessException, InvocationTargetException {

		Object[] parameters = resolver.get(method);
		
		boolean accessible = method.isAccessible();
		method.setAccessible(true);

		try {
			Object result = method.invoke(target, parameters);

			if (result != null) {
				postInvocation(parameters, result);
			}
		}
		finally {
			method.setAccessible(accessible);
		}
	}

	private void postInvocation(Object[] parameters, Object result) throws IllegalAccessException,
		InvocationTargetException {
		
		if (result instanceof Context) {
			parameterInvoker.invoke("setUp", result);
		}

		if (result instanceof Invokable) {
			Invokable invokable = (Invokable) result;

			invokable.invoke();
		}

		if (result instanceof Context) {
			parameterInvoker.invoke("tearDown", result);
		}
	}

	public void invoke(String pattern, Object target) throws IllegalArgumentException,
		IllegalAccessException, InvocationTargetException {

		invoke(pattern, target, target.getClass());
	}

	private void invoke(String pattern, Object target, Class targetClass) throws IllegalAccessException, InvocationTargetException {
		Class superClass = targetClass.getSuperclass();
		if (!superClass.equals(Object.class)) {
			invoke(pattern, target, superClass);
		}
		
		Method[] methods = targetClass.getDeclaredMethods();

		for (int index = 0; index < methods.length; index++) {
			Method method = methods[index];

			if (method.getName().equals(pattern)) {
				invoke(method, target);
			}									   
		}
	}
}
