package picounit.impl;

import picounit.Context;
import picounit.DelegateTestResult;
import picounit.DelegatingTestResult;
import picounit.Mocker;
import picounit.Test;
import picounit.finder.ContextClass;
import picounit.finder.ContextFinder;
import picounit.finder.ImplementsCondition;
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
	private ContextFinder contextFinder;
	private ContextClass contextClass;
	private Context context;
	private Thrower thrower;

	private PicoUnitTestCase picoUnitTestCase(Method testMethod) {
		return new PicoUnitTestCase(testMethod, instantiator, invoker, mockInvoker, mockedMocker,
			contextFinder, thrower);
	}
	
	public void mock(DelegateTestResult delegateTestResult, Instantiator instantiator, Invoker invoker,
		Invoker mockInvoker, Mocker mockedMocker, ContextFinder contextFinder,
		ContextClass contextClass, Context context, Thrower thrower) {
		
		this.testResult = new DelegatingTestResult(delegateTestResult);

		this.delegateTestResult = delegateTestResult;
		this.instantiator = instantiator;
		this.invoker = invoker;
		this.mockInvoker = mockInvoker;
		this.mockedMocker = mockedMocker;
		this.contextFinder = contextFinder;
		this.contextClass = contextClass;
		this.context = context;
		this.thrower = thrower;
	}

	public void testHasSingleTestCaseCount(Verify verify) {
		verify.equal(1, picoUnitTestCase(TestExample.testExample).countTestCases());
	}

	public void testRun(previous.picounit.Mocker mocker) throws IllegalArgumentException, InvocationTargetException,
		InstantiationException, IllegalAccessException, ClassNotFoundException {

		TestExample testExample = new TestExample();
		PicoUnitTestCase picoUnitTestCase = picoUnitTestCase(TestExample.testExample);

		delegateTestResult.startTest(picoUnitTestCase);

		mocker.expect(contextFinder.findContexts(TestExample.class,
			new ImplementsCondition(Context.class))).andReturn((new ContextClass[] {contextClass}));

		mockedMocker.reset();
		mocker.expect(contextClass.getContext(instantiator)).andReturn(context);

		invoker.invoke("setUp", context);

		mocker.expect(instantiator.instantiate(TestExample.class)).andReturn(testExample);
		invoker.invoke("setUp", testExample);

		mockInvoker.invoke("mock", testExample);

		thrower.clearError();
		
		invoker.invoke(TestExample.testExample, testExample);
		
		invoker.invoke("tearDown", testExample);
		invoker.invoke("tearDown", context);

		mockedMocker.verify();

		thrower.dispatchError();

		delegateTestResult.endTest(picoUnitTestCase);

		mocker.replay();

		picoUnitTestCase.run(testResult);
	}

	public void testExceptionsThrownDuringContextSetupBecomeErrors(previous.picounit.Mocker mocker)
		throws IllegalArgumentException, InstantiationException, IllegalAccessException,
		ClassNotFoundException, InvocationTargetException {

		PicoUnitTestCase picoUnitTestCase = picoUnitTestCase(TestExample.testExample);

		delegateTestResult.startTest(picoUnitTestCase);
		
		mocker.expect(contextFinder.findContexts(TestExample.class,
		new ImplementsCondition(Context.class))).andReturn((new ContextClass[] {contextClass}));

		IllegalArgumentException exception = new IllegalArgumentException();
		mocker.expect(contextClass.getContext(instantiator)).andRaise(exception);

		delegateTestResult.addError(picoUnitTestCase, exception); 
		delegateTestResult.endTest(picoUnitTestCase);

		mocker.replay();

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
