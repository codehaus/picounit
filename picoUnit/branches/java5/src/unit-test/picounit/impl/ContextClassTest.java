/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Context;
import picounit.finder.PassingContextClass;
import picounit.reflection.Instantiator;
import previous.picounit.Mocker;
import previous.picounit.Test;
import previous.picounit.Verify;

import java.lang.reflect.InvocationTargetException;

public class ContextClassTest implements Test {
	private Instantiator instantiator;

	public void mock(Instantiator instantiator) {
		this.instantiator = instantiator;
	}

	public static class ExampleContext implements Context {}

	public void testUsesInstantiatorToConstructContextClass(Mocker mocker, Verify verify)
		throws IllegalArgumentException, InstantiationException, IllegalAccessException,
		InvocationTargetException {

		ExampleContext exampleContext = new ExampleContext();
		mocker.expect(instantiator.instantiate(ExampleContext.class)).andReturn(exampleContext);

		mocker.replay();

		verify.same(exampleContext,
			new PassingContextClass(ExampleContext.class).getContext(instantiator));
	}
}
