/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.util;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodUtil {
	public static final Method hashCode = new MethodUtil().getMethod("hashCode");
	public static final Method equals = new MethodUtil().getMethod("equals", Object.class);
	public static final Method toString = new MethodUtil().getMethod("toString");
	public static final Method finalize = new MethodUtil().getMethod("finalize");

	public Method getMethod(String name) {
		return getMethod(Object.class, name);
	}

	public Method getMethod(String name, Class parameterType) {
		return getMethod(Object.class, name, parameterType);
	}

	public Method getMethod(Class clazz, String name) {
		return getMethod(clazz, name, new Class[0]);
	}

	public Method getMethod(Class clazz, String name, Class parameterType) {
		return getMethod(clazz, name, new Class[] {parameterType});
	}

	public Method getMethod(Class clazz, String name, Class firstParameterType,
		Class secondParameterType) {

		return getMethod(clazz, name, new Class[] {firstParameterType, secondParameterType});
	}

	private Method getMethod(Class clazz, String name, Class[] parameterTypes) {
		try {
			return clazz.getDeclaredMethod(name, parameterTypes);
		}
		catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new RuntimeException(noSuchMethodException);
		}
	}
	
	public boolean matchesObjectMethods(Method toCheck) {
		return matches(hashCode, toCheck) ||
			matches(equals, toCheck) ||
			matches(toString, toCheck);
	}

	public boolean matches(Method toMatch, Method toCheck) {
		boolean toCheckClassDerivesFromToMatchClass =
			toMatch.getDeclaringClass().isAssignableFrom(toCheck.getDeclaringClass());

		boolean sameName = toCheck.getName().equals(toMatch.getName());

		boolean sameParameterTypes = Arrays.equals(toCheck.getParameterTypes(),
			toMatch.getParameterTypes());

		return toCheckClassDerivesFromToMatchClass && sameName && sameParameterTypes;
	}

	public boolean isEquals(Method method) {
		return matches(equals, method);
	}

	public boolean isHashCode(Method method) {
		return matches(hashCode, method);
	}

	public boolean isToString(Method method) {
		return matches(toString, method);
	}

	public boolean isFinalize(Method method) {
		return matches(finalize, method);
	}
}
