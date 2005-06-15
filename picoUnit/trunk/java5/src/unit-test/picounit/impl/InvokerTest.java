/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Invokable;
import picounit.reflection.Invoker;
import picounit.registry.Resolver;
import picounit.util.MethodUtil;
import previous.picounit.Mocker;
import previous.picounit.Test;
import previous.picounit.Verify;

import java.lang.reflect.Method;

public class InvokerTest implements Test {
	private Invoker invoker;
	
	private Resolver resolver;
	private Invokable invokable;
	
	public static class Fixture {}

	public interface ToInvoke {
		void methodWithZeroArguments();
		void methodWithSomeArguments(Fixture fixture);
		
		Invokable methodReturningInvokable();

		public static final Method methodWithZeroArguments =
			new MethodUtil().getMethod(ToInvoke.class, "methodWithZeroArguments");

		public static final Method methodWithSomeArguments =
			new MethodUtil().getMethod(ToInvoke.class, "methodWithSomeArguments", Fixture.class);
		
		public static final Method methodReturningInvokable =
			new MethodUtil().getMethod(ToInvoke.class, "methodReturningInvokable");
	}

	private ToInvoke toInvoke;
	
	public void mock(Invokable invokable, Resolver resolver, ToInvoke toInvoke) {
		this.invoker = new Invoker(resolver);
		
		this.invokable = invokable;
		this.resolver = resolver;
		this.toInvoke = toInvoke;
	}
	
	public void testInvokeMethodWithZeroArguments(Mocker mocker) throws Exception {
		mocker.expect(resolver.get(ToInvoke.methodWithZeroArguments)).andReturn((new Object[0]));

		toInvoke.methodWithZeroArguments();

		mocker.replay();

		invoker.invoke(ToInvoke.methodWithZeroArguments, toInvoke);
	}
	
	public void testInvokeMethodWithSomeArguments(Mocker mocker) throws Exception {
		Fixture fixture = new Fixture();

		toInvoke.methodWithSomeArguments(fixture);
		mocker.expect(resolver.get(ToInvoke.methodWithSomeArguments)).andReturn((new Object[] {fixture}));
		mocker.replay();

		invoker.invoke(ToInvoke.methodWithSomeArguments, toInvoke);
	}
	
	public void testInvokingMethodThatReturnsInvokableInvokesTheInvokable(Mocker mocker) throws Exception {
		mocker.expect(resolver.get(ToInvoke.methodReturningInvokable)).andReturn((new Object[0]));
		
		mocker.expect(toInvoke.methodReturningInvokable()).andReturn(invokable);
		
		invokable.invoke();

		mocker.replay();

		invoker.invoke(ToInvoke.methodReturningInvokable, toInvoke);
	}

	public static class SomeClass {
		public boolean picoUnitSetUpCalled = false;
		public boolean jUnitStyleSetUpCalled = false;
		
		public static final Method picoSetUp = new MethodUtil().getMethod(SomeClass.class, "setUp", Fixture.class);
		public static final Method junitSetUp = new MethodUtil().getMethod(SomeClass.class, "setUp");

		// PicoUnit style setUp
		public void setUp(Fixture fixture) {
			picoUnitSetUpCalled = true;	
		}
		
		// JUnit style setUp
		protected void setUp() throws Exception {
			jUnitStyleSetUpCalled = true;
		}
	}

	public void testInvokesProtectedMethods(Mocker mocker, Verify verify) throws Exception {
		Fixture fixture = new Fixture();
		mocker.expect(resolver.get(SomeClass.picoSetUp)).andReturn((new Object[] {fixture}));
		mocker.expect(resolver.get(SomeClass.junitSetUp)).andReturn((new Object[0]));

		mocker.replay();

		SomeClass someClass = new SomeClass();

		invoker.invoke("setUp", someClass);

		verify.that("Shoule have called PicoUnit style setUp", someClass.picoUnitSetUpCalled);
		verify.that("Should have called JUnit style setUp", someClass.jUnitStyleSetUpCalled);
	}
}
