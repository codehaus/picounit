/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import picounit.PicoUnitException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
	private final ClassUtil classUtil = new ClassUtil();

	public static boolean isProxy(Object object) {
		return object != null && isProxy(object.getClass());
	}

	public static boolean isProxy(Class clazz) {
		return Proxy.isProxyClass(clazz) ||  
			Enhancer.isEnhanced(clazz);
	}

	public Object create(Class clazz, MethodInterceptor methodInterceptor) {
		if (isProxy(clazz)) {
			System.err.println("proxying a proxy is deprecated; " + clazz.getName());
			return create(clazz.getSuperclass(), methodInterceptor);
		}

		if (clazz.isInterface()) {
			return createInterfaceProxy(clazz, convertMethodInterceptor(methodInterceptor));
		}
		else {
			return createClassProxy(clazz, methodInterceptor);
		}
	}

	public Object create(Class clazz, InvocationHandler invocationHandler) {
		if (isProxy(clazz)) {
			System.err.println("proxying a proxy is deprecated; " + clazz.getName());
			return create(clazz.getSuperclass(), invocationHandler);
		}

		if (clazz.isInterface()) {
			return createInterfaceProxy(clazz, invocationHandler);
		}
		else {
			return createClassProxy(clazz, convertInvocationHandler(invocationHandler));
		}
	}

	private Object createInterfaceProxy(Class clazz, InvocationHandler invocationHandler) {
		return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz}, invocationHandler);
	}

	private Object createClassProxy(Class clazz, MethodInterceptor callback) {
		Enhancer enhancer = new Enhancer();

		enhancer.setCallback(callback);
	
		enhancer.setSuperclass(clazz);

		return enhancer.create(getConstructorTypes(clazz), getConstructorValues(clazz));
	}

	private MethodInterceptor convertInvocationHandler(final InvocationHandler invocationHandler) {
		return new MethodInterceptor(){
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				return invocationHandler.invoke(proxy, method, args);
			}
		};
	}
	
	private InvocationHandler convertMethodInterceptor(final MethodInterceptor methodInterceptor) {
		return new InvocationHandler(){
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return methodInterceptor.intercept(methodInterceptor, method, args, null);
			}
		};
	}

	private Class[] getConstructorTypes(Class clazz) {
		return classUtil.getConstructorToUse(clazz).getParameterTypes();
	}

	private Object[] getConstructorValues(Class clazz) {
		try {
			return classUtil.getArgsForTypes(getConstructorTypes(clazz), this);
		}
		catch (IllegalArgumentException illegalArgumentException) {
			throw new PicoUnitException(illegalArgumentException);
		}
		catch (InstantiationException instantiationException) {
			throw new PicoUnitException(instantiationException);
		}
		catch (IllegalAccessException illegalAccessException) {
			throw new PicoUnitException(illegalAccessException);
		}
		catch (InvocationTargetException invocationTargetException) {
			throw new PicoUnitException(invocationTargetException);
		}
	}	
}
