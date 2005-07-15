/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Lifecycle;
import picounit.Mocker;
import picounit.reflection.Instantiator;
import picounit.reflection.Invoker;
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
	private final Thrower thrower;
	private final LifecycleInstantiator lifecycleInstantiator;
	private final TestListener testListener;

	public PicoUnitTestCase(Class testClass, Method testMethod, Instantiator instantiator,
		Invoker invoker, Invoker mockInvoker, Mocker mocker, Thrower thrower,
		LifecycleInstantiator lifecycleInstantiator, TestListener testListener) {
		
		this.testMethod = testMethod;
		this.testClass = testClass;
		this.instantiator = instantiator;
		this.invoker = invoker;
		this.mockInvoker = mockInvoker;
		this.mocker = mocker;
		this.thrower = thrower;
		this.lifecycleInstantiator = lifecycleInstantiator;
		this.testListener = testListener;
	}

	public int countTestCases() {
		return 1;
	}

	public String getName() {
		return testMethod.getName() + "(" + testMethod.getDeclaringClass().getName() + ")"; 
	}

	public void run(TestResult result) {
		result.startTest(this);

		testListener.testStarted();

		try {
			Lifecycle[] lifecycles = getLifecycles();

			setUp(lifecycles);

			Object testInstance = instantiator.instantiate(testClass);

			mock(testInstance);

			setUp(testInstance);
			
			thrower.clearError();

			test(testInstance);

			tearDown(testInstance);
			
			mocker.verify();

			tearDown(lifecycles);

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
			Throwable targetException = invocationTargetException.getTargetException();

			if (targetException instanceof AssertionFailedError) {
				result.addFailure(this, (AssertionFailedError) targetException);
			}
			else {
				result.addError(this, targetException);
			}
		}
		catch (ClassNotFoundException classNotFoundException) {
			result.addError(this, classNotFoundException);
		}
		catch (AssertionFailedError assertionFailedError) {
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

	private Lifecycle[] getLifecycles() throws IllegalArgumentException, InstantiationException,
		IllegalAccessException, ClassNotFoundException, InvocationTargetException {

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

	private void test(Object testInstance) throws IllegalAccessException, InvocationTargetException {
		invoker.invoke(testMethod, testInstance);
	}

	private void tearDown(Object target) throws IllegalAccessException, InvocationTargetException {
		invoker.invoke("tearDown", target);
	}

	private void tearDown(Lifecycle[] lifecycles) throws IllegalAccessException, InvocationTargetException {
		for (int index = 0; index < lifecycles.length; index++) {
			tearDown(lifecycles[index]);
		}
	}
}
