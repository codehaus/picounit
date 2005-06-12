/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.observer;

import picounit.classloader.MethodParameterRegistry;

public class DefaultMethodParameterListener implements MethodParameterListener {
	private final MethodParameterRegistry methodParameterRegistry;

	public DefaultMethodParameterListener(MethodParameterRegistry methodParameterRegistry) {
		this.methodParameterRegistry = methodParameterRegistry;
	}

	public void methodEvent(String className, String methodName, String[] parameterNames) {
		methodParameterRegistry.register(className, methodName, parameterNames);
	}
}
