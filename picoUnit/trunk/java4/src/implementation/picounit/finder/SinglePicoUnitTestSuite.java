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
		ContextFinder contextFinder, MethodParameterRegistry methodParameterRegistry) {

		super(testName);

		validate(testClass);

		this.testClass = testClass;

		Method[] methods = testClass.getMethods();

		RegistryImpl registry = new RegistryFactory().create(); 
		registryEntry.registerWith(registry);

		Mocker mocker = (Mocker) registry.get(Mocker.class);

		Invoker invoker = new Invoker(registry);
		Resolver mockResolver = new MockResolver(mocker, methodParameterRegistry);
		Invoker mockInvoker = new Invoker(mockResolver, invoker);

		Instantiator instantiator = new OrdinaryInstantiator(registry);

		Thrower thrower = (Thrower) registry.get(Thrower.class);

		for (int index = 0; index < methods.length; index++) {
			Method method = methods[index];

			if (method.getName().startsWith("test") && testFilter.matches(method)) {
				addTest(new PicoUnitTestCase(method, instantiator, invoker, mockInvoker, mocker,
					contextFinder, thrower));
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
