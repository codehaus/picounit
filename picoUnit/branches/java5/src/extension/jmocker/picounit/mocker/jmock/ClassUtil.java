/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;


import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassUtil {
	public Constructor getBestConstructor(Class aClass) {
		List<Constructor> constructors = Arrays.asList(aClass.getDeclaredConstructors());
		final ConstructorComparator constructorComparator = new ConstructorComparator();

		Collections.sort(constructors, new Comparator<Constructor>() {
			public int compare(Constructor o1, Constructor o2) {
				return constructorComparator.compare(o1, o2);
			}
		});
		
		for (Constructor constructor : constructors) {
			System.out.println(constructor);
		}

		return (Constructor) constructors.get(0);
	}

	public Constructor getConstructorToUse(Class clazz) {
		// First try to use the default constructor
		try {
			Constructor declaredConstructor = clazz.getDeclaredConstructor(new Class[0]);

			if (!isPrivate(declaredConstructor)) {
				return declaredConstructor;
			}
		}
		catch (NoSuchMethodException e) {
		}

		// If it fails just use the first non-private one
		Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
		Constructor nonPrimativeConstructor = null;

		for (int index = 0; index < declaredConstructors.length; index++ ) {
			Constructor declaredConstructor = declaredConstructors[index];

			if (isPrivate(declaredConstructor) || isCircular(declaredConstructor)) {
				continue;
			}

			if (isPrimative(declaredConstructor)) {
				return declaredConstructor;
			}
			else if (nonPrimativeConstructor == null) {
				nonPrimativeConstructor = declaredConstructor;
			}
		}

		if (nonPrimativeConstructor != null) {
			return nonPrimativeConstructor;
		}

		throw new IllegalArgumentException("No visible constructors in class " + clazz.getName());
	}

	public Object[] getArgsForTypes(Class[] methodTypes, ProxyFactory proxyFactory) {
		Object[] methodArgs = new Object[methodTypes.length];

		for (int i = 0; i < methodTypes.length; i++ ) {
			if (methodTypes[i].isInterface()) {
				methodArgs[i] = stubClass(proxyFactory, methodTypes[i]); 
			}
			else if (methodTypes[i].isPrimitive()) {
				// Return a nice wrapped primitive type
				methodArgs[i] = handlePrimitiveType(methodTypes[i]);
			}
			else if (Modifier.isFinal(methodTypes[i].getModifiers())) {
				// Instantiate the class using the best constructor we can find (because it's not
				// possible to mock a final class)
				Constructor argConstructor = getConstructorToUse(methodTypes[i]);

				try {
					Object[] argsForTypes = getArgsForTypes(argConstructor.getParameterTypes(), proxyFactory);

					methodArgs[i] = argConstructor.newInstance(argsForTypes);
				}
				catch (Exception ex) {
					throw new Error(ex);
				}
			}
			else {
				// Just create a mock for the class...
				methodArgs[i] = stubClass(proxyFactory, methodTypes[i]);
			}
		}
		return methodArgs;
	}

	private Object stubClass(ProxyFactory proxyFactory, Class aClass) {
		return proxyFactory.create(aClass, stubInvocationHandler);
	}

	private boolean isPrimative(Constructor declaredConstructor) {
		return primativeTypes.containsAll(Arrays.asList(declaredConstructor.getParameterTypes()));
	}

	private boolean isCircular(Constructor declaredConstructor) {
		return Arrays.asList(declaredConstructor.getParameterTypes()).contains(
			declaredConstructor.getDeclaringClass());
	}

	private boolean isPrivate(Constructor declaredConstructor) {
		return Modifier.isPrivate(declaredConstructor.getModifiers());
	}

	private final Set<Class> primativeTypes = primativeTypes();

	private final StubInvocationHandler stubInvocationHandler = new StubInvocationHandler();

	private Set<Class> primativeTypes() {
		Set<Class> primativeTypes = new HashSet<Class>();

		primativeTypes.add(boolean.class);
		primativeTypes.add(byte.class);
		primativeTypes.add(char.class);
		primativeTypes.add(double.class);
		primativeTypes.add(float.class);
		primativeTypes.add(int.class);
		primativeTypes.add(long.class);
		primativeTypes.add(short.class);
		primativeTypes.add(Boolean.class);
		primativeTypes.add(Byte.class);
		primativeTypes.add(Character.class);
		primativeTypes.add(Double.class);
		primativeTypes.add(Float.class);
		primativeTypes.add(Integer.class);
		primativeTypes.add(Long.class);
		primativeTypes.add(Short.class);
		primativeTypes.add(String.class);
		primativeTypes.add(Object.class);

		return primativeTypes;
	}

	private Object handlePrimitiveType(Class type) {
		if (!type.isPrimitive())
			return null;
		if (type == Void.TYPE)
			return null;
		if (type == Boolean.TYPE)
			return new Boolean(false);
		if (type == Byte.TYPE)
			return new Byte((byte) 0);
		if (type == Short.TYPE)
			return new Short((short) 0);
		if (type == Character.TYPE)
			return new Character((char) 0);
		if (type == Integer.TYPE)
			return new Integer(0);
		if (type == Long.TYPE)
			return new Long(0);
		if (type == Float.TYPE)
			return new Float(0);
		if (type == Double.TYPE)
			return new Double(0);
		throw new RuntimeException("EasyMock bug: no return value generated!");
	}
}
