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
import org.jmock.core.Invocation;
import org.jmock.core.InvocationDispatcher;
import org.jmock.core.LIFOInvocationDispatcher;

import java.lang.reflect.Method;

public class BetterCGLIBCoreMock extends AbstractDynamicMock implements MethodInterceptor {
    private Object proxy;

    public BetterCGLIBCoreMock( Class mockedType ) {
        this(mockedType, mockNameFromClass(mockedType), new LIFOInvocationDispatcher());
    }

    public BetterCGLIBCoreMock( Class mockedType, String name ) {
        this(mockedType, name, new LIFOInvocationDispatcher());
    }

    public BetterCGLIBCoreMock(Class mockedType, String name,
		InvocationDispatcher invocationDispatcher ) {

		super(mockedType, name, invocationDispatcher);

        this.proxy = new ProxyFactory().create(mockedType, this);
    }

    public Object proxy() {
        return this.proxy;
    }

    public Object intercept( Object thisProxy, Method method, Object[] args, MethodProxy superProxy )
		throws Throwable {

        return mockInvocation(new Invocation(proxy, method, args));
    }
}
