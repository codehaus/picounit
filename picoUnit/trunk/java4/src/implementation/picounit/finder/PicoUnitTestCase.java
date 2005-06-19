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
	private final LifeCycleInstantiator lifeCycleInstantiator;

	public PicoUnitTestCase(Method testMethod, Instantiator instantiator, Invoker invoker,
		Invoker mockInvoker, Mocker mocker, Thrower thrower, LifeCycleInstantiator lifeCycleInstantiator) {

		this.testMethod = testMethod;
		this.testClass = testMethod.getDeclaringClass();
		this.instantiator = instantiator;
		this.invoker = invoker;
		this.mockInvoker = mockInvoker;
		this.mocker = mocker;
		this.thrower = thrower;
		this.lifeCycleInstantiator = lifeCycleInstantiator;
	}

	public int countTestCases() {
		return 1;
	}

	public String getName() {
		return testMethod.getName() + "(" + testMethod.getDeclaringClass().getName() + ")";
	}

	public void run(TestResult result) {
		result.startTest(this);

		try {
			Lifecycle[] lifeCycles = getLifeCycles();

			setUp(lifeCycles);

			Object testInstance = instantiator.instantiate(testClass);

			mock(testInstance);

			setUp(testInstance);
			
			thrower.clearError();

			test(testInstance);

			tearDown(testInstance);

			tearDown(lifeCycles);

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

	private Lifecycle[] getLifeCycles() throws IllegalArgumentException, InstantiationException,
		IllegalAccessException, ClassNotFoundException, InvocationTargetException {

		return lifeCycleInstantiator.instantiate(testClass);
	}

	private void setUp(Lifecycle[] lifeCycles) throws IllegalAccessException, InvocationTargetException {
		for (int index = 0; index < lifeCycles.length; index++) {
			setUp(lifeCycles[index]);
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

	private void tearDown(Lifecycle[] contexts) throws IllegalAccessException, InvocationTargetException {
		for (int index = 0; index < contexts.length; index++) {
			tearDown(contexts[index]);
		}
	}
}
