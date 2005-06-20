package picounit.impl;

import picounit.DelegateTestResult;
import picounit.DelegatingTestResult;
import picounit.Lifecycle;
import picounit.Mocker;
import picounit.Test;
import picounit.finder.LifecycleInstantiator;
import picounit.finder.PicoUnitTestCase;
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

	private LifecycleInstantiator lifecycleInstantiator;

	private PicoUnitTestCase picoUnitTestCase(Method testMethod) {
		return new PicoUnitTestCase(testMethod, instantiator, invoker, mockInvoker, mockedMocker,
			thrower, lifecycleInstantiator);
	}
	
	public void mock(DelegateTestResult delegateTestResult, Instantiator instantiator, Invoker invoker,
		Invoker mockInvoker, Mocker mockedMocker, Lifecycle lifecycle, Thrower thrower,
		LifecycleInstantiator lifecycleInstantiator) {
		
		this.lifecycleInstantiator = lifecycleInstantiator;
		this.testResult = new DelegatingTestResult(delegateTestResult);

		this.delegateTestResult = delegateTestResult;
		this.instantiator = instantiator;
		this.invoker = invoker;
		this.mockInvoker = mockInvoker;
		this.mockedMocker = mockedMocker;
		this.lifecycle = lifecycle;
		this.thrower = thrower;
	}

	public void testHasSingleTestCaseCount(Verify verify) {
		verify.equal(1, picoUnitTestCase(TestExample.testExample).countTestCases());
	}

	public void testRun(previous.picounit.Mocker should) throws IllegalArgumentException, InvocationTargetException,
		InstantiationException, IllegalAccessException, ClassNotFoundException {

		TestExample testExample = new TestExample();
		PicoUnitTestCase picoUnitTestCase = picoUnitTestCase(TestExample.testExample);

		delegateTestResult.startTest(picoUnitTestCase);

		should.call(lifecycleInstantiator.instantiate(TestExample.class))
			.andReturn(new Lifecycle[] {lifecycle});

		mockedMocker.reset();

		invoker.invoke("setUp", lifecycle);

		should.call(instantiator.instantiate(TestExample.class)).andReturn(testExample);
		invoker.invoke("setUp", testExample);

		mockInvoker.invoke("mock", testExample);

		thrower.clearError();
		
		invoker.invoke(TestExample.testExample, testExample);
		
		invoker.invoke("tearDown", testExample);
		invoker.invoke("tearDown", lifecycle);

		mockedMocker.verify();

		thrower.dispatchError();

		delegateTestResult.endTest(picoUnitTestCase);

		should.doAboveWhen();

		picoUnitTestCase.run(testResult);
	}

	public void testExceptionsThrownDuringLifecycleSetupBecomeErrors(previous.picounit.Mocker should)
		throws IllegalArgumentException, InstantiationException, IllegalAccessException,
		ClassNotFoundException, InvocationTargetException {

		PicoUnitTestCase picoUnitTestCase = picoUnitTestCase(TestExample.testExample);

		delegateTestResult.startTest(picoUnitTestCase);
		
		IllegalArgumentException exception = new IllegalArgumentException();
		should.call(lifecycleInstantiator.instantiate(TestExample.class)).andRaise(exception);

		delegateTestResult.addError(picoUnitTestCase, exception); 
		delegateTestResult.endTest(picoUnitTestCase);

		should.replay();

		picoUnitTestCase.run(testResult);
	}

	public void testIfTestAndTearDownThrowIncludeBothExceptionsInTestResult() {
	}

	public void testHasSameNameAsTestMethodWithClassName(Verify verify) {
		verify.equal(TestExample.testExample.getName() + "(" + TestExample.class.getName() + ")",
			picoUnitTestCase(TestExample.testExample).getName());
	}

	public static class TestExample implements Test {
		public static final Method testExample = new MethodUtil().getMethod(TestExample.class,
			"testExample");

		public void testExample() {
		}
	}
}
