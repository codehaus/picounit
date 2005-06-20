/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Lifecycle;
import picounit.reflection.Instantiator;
import picounit.reflection.Invoker;
import picounit.reflection.OrdinaryInstantiator;
import picounit.registry.Resolver;

import java.lang.reflect.InvocationTargetException;

public class TestInstantiator {
	private final Instantiator instantiator;
	private final Invoker invoker;
	private final LifecycleInstantiator lifecycleInstantiator;

	public static TestInstantiator create(Resolver resolver, ClassLoader classLoader) {
		Instantiator instantiator = new OrdinaryInstantiator(resolver);

		return new TestInstantiator(instantiator, new Invoker(resolver),
			new LifecycleInstantiatorImpl(classLoader, instantiator));
	}

	public TestInstantiator(Instantiator instantiator, Invoker invoker,
		LifecycleInstantiator lifecycleInstantiator) {

		this.instantiator = instantiator;
		this.invoker = invoker;
		this.lifecycleInstantiator = lifecycleInstantiator;
	}

	public Object instantiate(Class testClass) throws IllegalArgumentException, InstantiationException,
		IllegalAccessException, ClassNotFoundException, InvocationTargetException {

		setUp(getLifecycles(testClass));

		return instantiator.instantiate(testClass);
	}

	private Lifecycle[] getLifecycles(Class testClass) throws IllegalArgumentException,
		InstantiationException, IllegalAccessException, ClassNotFoundException,
		InvocationTargetException {
		
		return lifecycleInstantiator.instantiate(testClass);
	}
	
	private void setUp(Lifecycle[] lifecycles) throws IllegalAccessException, InvocationTargetException {
		for (int index = 0; index < lifecycles.length; index++) {
			setUp(lifecycles[index]);
		}
	}

	private void setUp(Object target) throws IllegalAccessException, InvocationTargetException {
		invoker.invoke("setUp", target);
	}
}
