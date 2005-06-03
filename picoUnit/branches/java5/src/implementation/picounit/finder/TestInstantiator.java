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
import picounit.reflection.Invoker;
import picounit.reflection.OrdinaryInstantiator;
import picounit.registry.Resolver;

import java.lang.reflect.InvocationTargetException;

public class TestInstantiator {
	private final Instantiator instantiator;
	private final ContextFinder contextFinder;
	private final Invoker invoker;
	
	public TestInstantiator(Resolver resolver, ClassLoader classLoader) {
		this(new OrdinaryInstantiator(resolver), new ContextFinder(classLoader), new Invoker(resolver));
	}

	public TestInstantiator(Instantiator instantiator, ContextFinder contextFinder, Invoker invoker) {
		this.instantiator = instantiator;
		this.contextFinder = contextFinder;
		this.invoker = invoker;
	}

	public Object instantiate(Class testClass) throws IllegalArgumentException, InstantiationException,
		IllegalAccessException, ClassNotFoundException, InvocationTargetException {

		setUp(getContexts(testClass));

		return instantiator.instantiate(testClass);
	}

	private Context[] getContexts(Class testClass) throws IllegalArgumentException,
		InstantiationException, IllegalAccessException, ClassNotFoundException,
		InvocationTargetException {

		ContextClass[] contextClasses =
			contextFinder.findContexts(testClass, new ImplementsCondition(Context.class));

		Context[] contexts = new Context[contextClasses.length];

		for (int index = 0; index < contextClasses.length; index++) {
			contexts[index] = contextClasses[index].getContext(instantiator);
		}

		return contexts;
	}
	
	private void setUp(Context[] contexts) throws IllegalAccessException, InvocationTargetException {
		for (int index = 0; index < contexts.length; index++) {
			setUp(contexts[index]);
		}
	}

	private void setUp(Object target) throws IllegalAccessException, InvocationTargetException {
		invoker.invoke("setUp", target);
	}
}
