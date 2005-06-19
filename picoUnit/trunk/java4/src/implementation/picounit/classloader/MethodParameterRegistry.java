/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodParameterRegistry {
	private final Map methodParameters = new HashMap();

	public void register(String className, String methodName, String[] parameterNames) {
		methodParameters.put(className + "." + methodName, parameterNames);
	}

	public String[] get(Constructor constructor) {
		return get(constructor.getDeclaringClass().getName(), "<init>");
	}

	public String[] get(Method method) {
		return get(method.getDeclaringClass().getName(), method.getName());
	}

	private String[] get(String className, String methodName) {
		return (String[]) methodParameters.get(className + "." + methodName);
	}
}
