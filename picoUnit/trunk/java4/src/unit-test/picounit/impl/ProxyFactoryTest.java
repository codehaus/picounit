/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Implementation;
import picounit.Interface;
import picounit.mocker.jmock.ProxyFactory;
import previous.picounit.Test;
import previous.picounit.Verify;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFactoryTest implements Test {
	private final ProxyFactory proxyFactory = new ProxyFactory();
	private final MockInvocationHandler mockInvocationHandler = new MockInvocationHandler();

	public void testProxyInterface(Verify verify) throws Throwable {
		Interface proxiedInterface = proxyFactory.create(Interface.class, mockInvocationHandler);

		proxiedInterface.method();

		verify.that(mockInvocationHandler.getInvokedMethod())
			.isEqualTo(Interface.method);
		verify.that(mockInvocationHandler.getArgs()).isNull();
	}
	
	public void testProxyClass(Verify verify) {
		Implementation proxiedInterface =
			proxyFactory.create(Implementation.class, mockInvocationHandler);

		proxiedInterface.method();

		verify.that(mockInvocationHandler.getInvokedMethod()).isEqualTo(Implementation.method);
		verify.that(mockInvocationHandler.getArgs()).isEqualTo(new Object[0]);
	}

	public static class MockInvocationHandler implements InvocationHandler {
		private Method method;
		private Object[] args;

		public Method getInvokedMethod() {
			return method;
		}

		public Object[] getArgs() {
			return args;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			this.method = method;
			this.args = args;

			return null;
		}
	}
}
