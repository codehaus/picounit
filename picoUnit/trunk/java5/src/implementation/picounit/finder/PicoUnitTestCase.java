/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Context;
import picounit.Mocker;
import picounit.classloader.MethodParameterRegistry;
import picounit.reflection.Instantiator;
import picounit.reflection.Invoker;
import picounit.registry.Resolver;
import picounit.verify.Thrower;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class PicoUnitTestCase extends TestCase {
	private final Method testMethod;
	private final Class testClass;

	private final Instantiator instantiator;
	private final Invoker invoker;
	private final Invoker mockInvoker;
	private final Mocker mocker;
	private final ContextFinder contextFinder;
	private final Resolver resolver;
	private final MethodParameterRegistry methodParameterRegistry;
	private final Thrower thrower;

	public PicoUnitTestCase(Method testMethod, Instantiator instantiator, Invoker invoker,
		Invoker mockInvoker, Mocker mocker, ContextFinder contextFinder, Resolver resolver,
		MethodParameterRegistry methodParameterRegistry, Thrower thrower) {

		this.testMethod = testMethod;
		this.testClass = testMethod.getDeclaringClass();
		this.instantiator = instantiator;
		this.invoker = invoker;
		this.mockInvoker = mockInvoker;
		this.mocker = mocker;
		this.contextFinder = contextFinder;
		this.resolver = resolver;
		this.methodParameterRegistry = methodParameterRegistry;
		this.thrower = thrower;
	}

	@Override
	public int countTestCases() {
		return 1;
	}

	@Override
	public String getName() {
		return testMethod.getName() + "(" + testMethod.getDeclaringClass().getName() + ")";
	}

	@Override
	public void run(TestResult result) {
		result.startTest(this);

		try {
			Context[] contexts = getContexts();

			setUp(contexts);

			Object testInstance = instantiator.instantiate(testClass);

			mock(testInstance);

			setUp(testInstance);
			
			thrower.clearError();

			test(testInstance);

			tearDown(testInstance);

			tearDown(contexts);

			mocker.verify();

			thrower.dispatchError();
		}
		catch (InstantiationException instantiationException) {
			result.addError(this, instantiationException);
		}
		catch (IllegalAccessException illegalAccessException) {
			result.addError(this, illegalAccessException);
		}
		catch (IllegalArgumentException illegalArgumentException) {
			result.addError(this, illegalArgumentException);
		}
		catch (InvocationTargetException invocationTargetException) {
			result.addError(this, invocationTargetException.getTargetException());
		}
		catch (ClassNotFoundException classNotFoundException) {
			result.addError(this, classNotFoundException);
		}
		catch (AssertionFailedError assertionFailedError) {
			// I need a test for this!
			result.addFailure(this, assertionFailedError);
		}
		catch (Throwable throwable) {
			result.addError(this, throwable);
		}

		result.endTest(this);
	}

	private void mock(Object testInstance) throws IllegalArgumentException, IllegalAccessException,
		InvocationTargetException {

		mocker.reset();
		mockInvoker.invoke("mock", testInstance);
	}

	private Context[] getContexts() throws IllegalArgumentException, InstantiationException,
		IllegalAccessException, ClassNotFoundException, InvocationTargetException {

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

	private void test(Object testInstance) throws IllegalAccessException, InvocationTargetException {
		invoker.invoke(testMethod, testInstance);
	}

	private void tearDown(Object target) throws IllegalAccessException, InvocationTargetException {
		invoker.invoke("tearDown", target);
	}

	private void tearDown(Context[] contexts) throws IllegalAccessException, InvocationTargetException {
		for (int index = 0; index < contexts.length; index++) {
			tearDown(contexts[index]);
		}
	}
}
