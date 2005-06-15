/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import java.util.HashMap;
import java.util.Map;

public class MethodParameterRegistry {
	private final Map<String, String[]> methodParameters = new HashMap<String, String[]>();

	public void register(String className, String methodName, String[] parameterNames) {
		methodParameters.put(className + "." + methodName, parameterNames);
	}

	public String[] get(String className, String methodName) {
		return methodParameters.get(className + "." + methodName);
	}
}
