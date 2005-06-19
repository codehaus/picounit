/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Mocker;
import picounit.classloader.MethodParameterRegistry;
import picounit.registry.Resolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MockResolver implements Resolver {
	private final Mocker mocker;
	private final MethodParameterRegistry methodParameterRegistry;

	public MockResolver(Mocker mocker, MethodParameterRegistry methodParameterRegistry) {
		this.mocker = mocker;
		this.methodParameterRegistry = methodParameterRegistry;
	}

	public Object get(Class type) {
		return mocker.mock(type);
	}

	public Object[] get(Class[] classes) {
		Object[] objects = new Object[classes.length];

		for (int index = 0; index < objects.length; index++) {
			objects[index] = mocker.mock(classes[index]); 
		}

		return objects;
	}

	public Object[] get(Method method) {
		String[] parameterNames = methodParameterRegistry.get(method);
		
		Class[] parameterTypes = method.getParameterTypes();

		if (parameterNames != null && parameterNames.length == parameterTypes.length) {
			return get(parameterTypes, parameterNames);
		}
		else {
			return get(parameterTypes);
		}
	}
	
	public Object[] get(Constructor constructor) {
		return get(constructor.getParameterTypes());
	}

	private Object[] get(Class[] parameterTypes, String[] parameterNames) {
		Object[] objects = new Object[parameterTypes.length];

		for (int index = 0; index < objects.length; index++) {
			objects[index] = mocker.mock(parameterTypes[index], parameterNames[index]); 
		}

		return objects;
	}
}
