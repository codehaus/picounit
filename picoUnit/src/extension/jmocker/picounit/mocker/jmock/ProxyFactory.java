/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import picounit.PicoUnitException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFactory {
	private final ClassUtil classUtil = new ClassUtil();

	public static boolean isProxy(Object object) {
		return object != null && isProxy(object.getClass());
	}

	public static boolean isProxy(Class clazz) {
		return Enhancer.isEnhanced(clazz);
	}

	public Object create(Class clazz, Callback callback) {
		if (isProxy(clazz)) {
			return create(clazz.getSuperclass(), callback);
		}

		if (clazz.isInterface()) {
			return createInterfaceProxy(clazz, callback);
		}
		else {
			return createClassProxy(clazz, callback);
		}
	}
	
	public Object create(Class clazz, InvocationHandler invocationHandler) {
		return create(clazz, convertInvocationHandler(invocationHandler));
	}

	public Object create(Class[] classes, InvocationHandler invocationHandler) {
		return create(classes, convertInvocationHandler(invocationHandler));
	}
	
	public Object create(Class[] classes, Callback callback) {
		if (onlyInterfaces(classes)) {
			return createInterfaceProxy(classes, callback);
		}
		else if (onlyOneClass(classes)) {
			return createMixedProxy(getClass(classes), getInterfaces(classes), callback);
		}
		else {
			throw new PicoUnitException("Cannot create a proxy with more than one class");
		}
	}

	private Object createClassProxy(Class clazz, Callback callback) {
		Enhancer enhancer = createEnhancer(callback);
	
		enhancer.setSuperclass(clazz);

		return enhancer.create(getConstructorTypes(clazz), getConstructorValues(clazz));
	}

	private Object createInterfaceProxy(Class clazz, Callback callback) {
		return createInterfaceProxy(new Class[] {clazz}, callback);
	}

	private Object createInterfaceProxy(Class[] classes, Callback callback) {
		Enhancer enhancer = createEnhancer(callback);

		enhancer.setInterfaces(classes);

		return enhancer.create();
	}

	private Object createMixedProxy(Class clazz, Class[] interfaceClasses, Callback callback) {
		Enhancer enhancer = createEnhancer(callback);

		enhancer.setInterfaces(interfaceClasses);
		enhancer.setSuperclass(clazz);

		return enhancer.create(getConstructorTypes(clazz), getConstructorValues(clazz));
	}

	private Enhancer createEnhancer(Callback callback) {
		Enhancer enhancer = new Enhancer();

		enhancer.setCallback(callback);

		return enhancer;
	}

	private Callback convertInvocationHandler(final InvocationHandler invocationHandler) {
		return new net.sf.cglib.proxy.InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return invocationHandler.invoke(proxy, method, args);
			}
		};
	}

	private Class[] getConstructorTypes(Class clazz) {
		return classUtil.getConstructorToUse(clazz).getParameterTypes();
	}

	private Object[] getConstructorValues(Class clazz) {
		return classUtil.getArgsForTypes(getConstructorTypes(clazz), this);
	}
	

	private boolean onlyInterfaces(Class[] classes) {
		return classCount(classes) == classes.length;
	}

	private boolean onlyOneClass(Class[] classes) {
		return classCount(classes) == 1;
	}

	private int classCount(Class[] classes) {
		int count = 0;
		
		for (int index = 0; index < classes.length; index++ ) {
			if (!classes[index].isInterface()) {
				count++;
			}
		}
		
		return count;
	}

	private Class[] getInterfaces(Class[] classes) {
		Class[] interfaceClasses = new Class[classes.length - 1];

		int passedClassOffset = 0;
		for (int index = 0; index < classes.length; index++ ) {
			if (classes[index].isInterface()) {
				interfaceClasses[index + passedClassOffset] = classes[index];
			}
			else {
				passedClassOffset = 1;
			}
		}

		return interfaceClasses;
	}

	private Class getClass(Class[] classes) {
		for (int index = 0; index < classes.length; index++ ) {
			if (!classes[index].isInterface()) {
				return classes[index];
			}
		}
		
		throw new PicoUnitException("No class found");
	}	
}
