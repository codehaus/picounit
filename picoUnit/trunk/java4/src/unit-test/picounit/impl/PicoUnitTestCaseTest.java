package picounit.impl;

import picounit.DelegateTestResult;
import picounit.DelegatingTestResult;
import picounit.Lifecycle;
import picounit.Mocker;
import picounit.Test;
import picounit.finder.LifecycleInstantiator;
import picounit.finder.PicoUnitTestCase;
import picounit.finder.TestListener;
import picounit.reflection.Instantiator;
import picounit.reflection.Invoker;
import picounit.util.MethodUtil;
import picounit.verify.Thrower;
import previous.picounit.Verify;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.TestResult;

public class PicoUnitTestCaseTest implements previous.picounit.Test {
	private TestResult testResult;

	private DelegateTestResult delegateTestResult;
	private Instantiator instantiator;
	private Invoker invoker;
	private Invoker mockInvoker;
	private Mocker mockedMocker;
	private Lifecycle lifecycle;
	private Thrower thrower;
	private TestListener testListener;

	private LifecycleInstantiator lifecycleInstantiator;

	@SuppressWarnings("unchecked")
	private PicoUnitTestCase picoUnitTestCase(Method testMethod) {
		return new PicoUnitTestCase(testMethod.getDeclaringClass(), testMethod, instantiator, invoker, mockInvoker,
			mockedMocker, thrower, lifecycleInstantiator, testListener);
	}
	
	public void mock(DelegateTestResult delegateTestResult, Instantiator instantiator, Invoker invoker,
		Invoker mockInvoker, Mocker mockedMocker, Lifecycle lifecycle, Thrower thrower,
		LifecycleInstantiator lifecycleInstantiator, TestListener testListener) {
		
		this.lifecycleInstantiator = lifecycleInstantiator;
		this.testResult = new DelegatingTestResult(delegateTestResult);

		this.delegateTestResult = delegateTestResult;
		this.instantiator = instantiator;
		this.invoker = invoker;
		this.mockInvoker = mockInvoker;
		this.mockedMocker = mockedMocker;
		this.lifecycle = lifecycle;
		this.thrower = thrower;
		this.testListener = testListener;
	}

	@SuppressWarnings("unchecked")
	public void testHasSingleTestCaseCount(Verify verify) {
		verify.that(picoUnitTestCase(TestExample.testExample).countTestCases()).isEqualTo(1);
	}

	public void testRun(previous.picounit.Mocker should) throws IllegalArgumentException, InvocationTargetException,
		InstantiationException, IllegalAccessException, ClassNotFoundException {

		TestExample testExample = new TestExample();
		PicoUnitTestCase picoUnitTestCase = picoUnitTestCase(TestExample.testExample);

		delegateTestResult.startTest(picoUnitTestCase);
		testListener.testStarted();

		should.call(lifecycleInstantiator.instantiate(TestExample.class))
			.andReturn(new Lifecycle[] {lifecycle});

		mockedMocker.reset();

		invoker.invoke("setUp", lifecycle);

		should.call((Object) instantiator.instantiate(TestExample.class)).andReturn(testExample);
		invoker.invoke("setUp", testExample);

		mockInvoker.invoke("mock", testExample);

		thrower.clearError();
		
		invoker.invoke(TestExample.testExample, testExample);
		
		invoker.invoke("tearDown", testExample);
		invoker.invoke("tearDown", lifecycle);

		mockedMocker.verify();

		thrower.dispatchError();

		delegateTestResult.endTest(picoUnitTestCase);

		should.expectAboveWhenTheFollowingOccurs();

		picoUnitTestCase.run(testResult);
	}

	public void testExceptionsThrownDuringLifecycleSetupBecomeErrors(previous.picounit.Mocker should)
		throws IllegalArgumentException, InstantiationException, IllegalAccessException,
		ClassNotFoundException, InvocationTargetException {

		PicoUnitTestCase picoUnitTestCase = picoUnitTestCase(TestExample.testExample);

		delegateTestResult.startTest(picoUnitTestCase);
		testListener.testStarted();

		IllegalArgumentException exception = new IllegalArgumentException();
		should.call(lifecycleInstantiator.instantiate(TestExample.class))
			.andRaise(exception);

		delegateTestResult.addError(picoUnitTestCase, exception); 
		delegateTestResult.endTest(picoUnitTestCase);

		should.expectAboveWhenTheFollowingOccurs();

		picoUnitTestCase.run(testResult);
	}

	public void testIfTestAndTearDownThrowIncludeBothExceptionsInTestResult() {
	}

	@SuppressWarnings("unchecked")
	public void testHasSameNameAsTestMethodWithClassName(Verify verify) {
		verify.that(picoUnitTestCase(TestExample.testExample).getName())
			.isEqualTo(TestExample.testExample.getName() + "(" + TestExample.class.getName() + ")");
	}

	public static class TestExample implements Test {
		public static final Method testExample = new MethodUtil().getMethod(TestExample.class,
			"testExample");

		public void testExample() {
		}
	}
}
