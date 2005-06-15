/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Context;
import picounit.reflection.Instantiator;

import java.lang.reflect.InvocationTargetException;

public class PassingContextClass implements ContextClass {
	private final Class contextClass;

	public PassingContextClass(Class contextClass) {
		this.contextClass = contextClass;
	}

	public Context getContext(Instantiator instantiator) throws IllegalArgumentException,
		InstantiationException, IllegalAccessException, InvocationTargetException {

		return (Context) instantiator.instantiate(contextClass);
	}
}
