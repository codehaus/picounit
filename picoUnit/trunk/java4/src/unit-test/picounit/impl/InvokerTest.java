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
	
	public void testInvokeMethodWithZeroArguments(Mocker should) throws Exception {
		should.call(resolver.get(ToInvoke.methodWithZeroArguments))
			.andReturn((new Object[0]));

		toInvoke.methodWithZeroArguments();

		should.expectAboveWhenTheFollowingOccurs();

		invoker.invoke(ToInvoke.methodWithZeroArguments, toInvoke);
	}
	
	public void testInvokeMethodWithSomeArguments(Mocker should) throws Exception {
		Fixture fixture = new Fixture();

		toInvoke.methodWithSomeArguments(fixture);
		should.call(resolver.get(ToInvoke.methodWithSomeArguments)).andReturn((new Object[] {fixture}));

		should.expectAboveWhenTheFollowingOccurs();

		invoker.invoke(ToInvoke.methodWithSomeArguments, toInvoke);
	}
	
	public void testInvokingMethodThatReturnsInvokableInvokesTheInvokable(Mocker should) throws Exception {
		should.call(resolver.get(ToInvoke.methodReturningInvokable)).andReturn((new Object[0]));
		
		should.call(toInvoke.methodReturningInvokable()).andReturn(invokable);
		
		invokable.invoke();

		should.expectAboveWhenTheFollowingOccurs();

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

	public void testInvokesProtectedMethods(Mocker should, Verify verify) throws Exception {
		Fixture fixture = new Fixture();

		should.call(resolver.get(SomeClass.picoSetUp))
			.andReturn((new Object[] {fixture}));
		should.call(resolver.get(SomeClass.junitSetUp))
			.andReturn((new Object[0]));

		should.expectAboveWhenTheFollowingOccurs();

		SomeClass someClass = new SomeClass();

		invoker.invoke("setUp", someClass);

		verify.because("Should have called PicoUnit style setUp")
			.thatBoolean(someClass.picoUnitSetUpCalled).isTrue();
		verify.because("Should have called JUnit style setUp")
			.thatBoolean(someClass.jUnitStyleSetUpCalled).isTrue();
	}
	
	public static class Base {
		private final StringBuffer stringBuffer;

		public Base(StringBuffer stringBuffer) {
			this.stringBuffer = stringBuffer;
		}
		
		public void method() {
			stringBuffer.append("Base.method ");
		}
		
		public static final Method method = new MethodUtil().getMethod(Base.class, "method");
	}
	
	public static class Derived extends Base {
		private final StringBuffer stringBuffer;

		public Derived(StringBuffer stringBuffer) {
			super(stringBuffer);
			this.stringBuffer = stringBuffer;
		}
		
		public void method(String parameter) {
			stringBuffer.append("Derived.method(" + parameter + ") ");			
		}
		
		public static final Method method = new MethodUtil().getMethod(Derived.class, "method", String.class);
	}
	
	public void testInvokesBaseMethodsBeforeDerivedMethods(Mocker should, Verify verify) throws Exception {
		should.call(resolver.get(Base.method)).andReturn(new Object[0]);
		should.call(resolver.get(Derived.method)).andReturn(new Object[] {"parameter"});

		should.expectAboveWhenTheFollowingOccurs();

		StringBuffer stringBuffer = new StringBuffer();

		invoker.invoke("method", new Derived(stringBuffer));

		verify.that(stringBuffer.toString().trim())
			.isEqualTo("Base.method Derived.method(parameter)");
	}
}
