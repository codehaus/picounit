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
import picounit.impl.PicoUnitImpl;
import picounit.reflection.Instantiator;
import picounit.reflection.Invoker;
import picounit.reflection.OrdinaryInstantiator;
import picounit.registry.RegistryEntry;
import picounit.registry.RegistryFactory;
import picounit.registry.RegistryImpl;
import picounit.registry.Resolver;
import picounit.verify.Thrower;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.TestSuite;

public class SinglePicoUnitTestSuite extends TestSuite {
	private final Class testClass;

	public SinglePicoUnitTestSuite(String testName, Class testClass, TestFilter testFilter, RegistryEntry registryEntry,
		MethodParameterRegistry methodParameterRegistry, ClassLoader classLoader,
		TestListener testListener) {

		super(testName);

		validate(testClass);

		this.testClass = testClass;

		Method[] methods = testClass.getMethods();

		// TODO: This static code must not remain, the plugins should not be loaded in this manner anyhow, a registryEntries
		// should be constructed one time only.
		RegistryImpl registry = new RegistryFactory(PicoUnitImpl.classPath).create(); 
		registryEntry.registerWith(registry);

		Mocker mocker = (Mocker) registry.get(Mocker.class);

		Invoker invoker = new Invoker(registry);
		Resolver mockResolver = new MockResolver(mocker, methodParameterRegistry);
		Invoker mockInvoker = new Invoker(mockResolver, invoker);

		Instantiator instantiator = new OrdinaryInstantiator(registry);
		LifecycleInstantiator lifecycleInstantiator =
			new LifecycleInstantiatorImpl(classLoader, instantiator);

		Thrower thrower = (Thrower) registry.get(Thrower.class);

		for (int index = 0; index < methods.length; index++) {
			Method method = methods[index];

			if (method.getName().startsWith("test") && testFilter.matches(method)) {
				addTest(new PicoUnitTestCase(testClass, method, instantiator, invoker, mockInvoker,
					mocker, thrower, lifecycleInstantiator, testListener));

				testListener.testPrepared();
			}
		}

		if (testCount() == 0) {
			addTest(new NoTestsFoundTestCase(testClass));
		}
	}
	
	private void validate(Class testClass) {
		if (testClass.isInterface()) {
			throw new IllegalArgumentException(testClass.getName() + " is an interface");
		}

		if (Modifier.isAbstract(testClass.getModifiers())) {
			throw new IllegalArgumentException(testClass.getName() + " is abstract");
		}
	}

	public boolean equals(Object object) {
		if (object == null || !getClass().equals(object.getClass())) {
			return false;
		}

		SinglePicoUnitTestSuite other = (SinglePicoUnitTestSuite) object;

		return testClass.equals(other.testClass) &&
			getName().equals(other.getName());
	}
}
