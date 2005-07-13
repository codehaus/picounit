/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.jmock.core.AbstractDynamicMock;
import org.jmock.core.DynamicMockError;
import org.jmock.core.Invocation;
import org.jmock.core.InvocationDispatcher;
import org.jmock.core.LIFOInvocationDispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BetterCGLIBCoreMock<T> extends AbstractDynamicMock
	implements InvocationHandler, MethodInterceptor {

    private final T proxy;

    public BetterCGLIBCoreMock(Class<T> mockedType, String name) {
        this(mockedType, name, new LIFOInvocationDispatcher(), new ProxyFactory());
    }

    public BetterCGLIBCoreMock(Class<T> mockedType, String name,
    	InvocationDispatcher invocationDispatcher, ProxyFactory proxyFactory) {

    	super(mockedType, name, invocationDispatcher);

    	if (mockedType.isInterface()) {
    		this.proxy = proxyFactory.create(mockedType, (InvocationHandler) this);
    	}
    	else {
    		this.proxy = proxyFactory.create(mockedType, (MethodInterceptor) this);
    	}
	}
    
    public T proxy() {
        return proxy;
    }

    public Object intercept( Object thisProxy, Method method, Object[] args, MethodProxy superProxy )
		throws Throwable {

    	try {
    		return mockInvocation(new Invocation(proxy, method, args));
    	}
    	catch (DynamicMockError dynamicMockError) {
    		throw dynamicMockError;
    	}
    }

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return mockInvocation(new Invocation(proxy, method, args));
	}
}
