/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.AClass;
import picounit.Interface;
import picounit.mocker.jmock.ConstructorComparator;
import previous.picounit.Test;
import previous.picounit.Verify;

import java.lang.reflect.Constructor;

public class ConstructorComparatorTest implements Test {
	private final ConstructorComparator constructorComparator = new ConstructorComparator();
	private final Verify verify;
	
	public ConstructorComparatorTest(Verify verify) {
		this.verify = verify;
	}

	public void testSameConstructorEquivalent() {
		assertEquivalent(constructor(), constructor());
	}

	public void testPublicConstructorBetterThanPackage() {
		assertBetter(constructor(AClass.Public.class), constructor(AClass.Package.class));
	}

	public void testPackageBetterThanProtected() {
		assertBetter(constructor(AClass.Package.class), constructor(AClass.Protected.class));
	}

	public void testProtectedBetterThanPrivate() {
		assertBetter(constructor(AClass.Protected.class), constructor(AClass.Private.class));
	}

	public void testNoArgsBetterThanOneArg() {
		assertBetter(constructor(), constructor(AClass.Public.class));
	}
	
	public void testPrimativeBetterThanInterface() {
		assertBetter(constructor(int.class), constructor(Interface.class));
		assertBetter(constructor(String.class), constructor(Interface.class));
	}

	public void testInterfacesBetterThanClasses() {
		assertBetter(constructor(Interface.class), constructor(Class.class));
	}
	
	public void testNonCircularBetterThanCircular() {
		assertBetter(constructor(Class.class), constructor(AClass.class));
	}

	private void assertBetter(Constructor left, Constructor right) {
		verify.that(constructorComparator.compare(left, right)).isEqualTo(-1);
		verify.that(constructorComparator.compare(right, left)).isEqualTo(1);
	}

	public void assertEquivalent(Constructor left, Constructor right) {
		verify.that(constructorComparator.compare(left, right)).isEqualTo(0);
	}

	private Constructor constructor() {
		return constructor(new Class[0]);
	}

	private Constructor constructor(Class parameterType) {
		return constructor(new Class[] {parameterType});
	}

	private Constructor constructor(Class[] parameterTypes) {
		try {
			return AClass.class.getDeclaredConstructor(parameterTypes);
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
}
