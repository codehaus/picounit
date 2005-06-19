/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.mocker.jmock.ClassUtil;
import picounit.reflection.Instantiator;
import picounit.reflection.OrdinaryInstantiator;
import picounit.registry.Resolver;
import previous.picounit.Mocker;
import previous.picounit.Test;
import previous.picounit.Verify;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstantiatorTest implements Test {
	private Instantiator instantiator;

	private Resolver resolver;

	public static class ClassWithZeroArgConstructor {
		public static final Constructor zeroArgConstructor =
			new ClassUtil().getConstructor(ClassWithZeroArgConstructor.class);
	}
	
	public void mock(Resolver resolver) {
		this.instantiator = new OrdinaryInstantiator(resolver);
		
		this.resolver = resolver;
	}
	
	public void testInstantiateZeroArgConstructor(Mocker mocker, Verify verify) throws IllegalArgumentException,
		InstantiationException, IllegalAccessException, InvocationTargetException {
		
		mocker.expect(resolver.get(ClassWithZeroArgConstructor.zeroArgConstructor))
			.andReturn((new Object[0]));
		
		mocker.replay();

		Object instance = instantiator.instantiate(ClassWithZeroArgConstructor.class);
		
		verify.notNull(instance);
		verify.that(instance instanceof ClassWithZeroArgConstructor);
	}
	
	public static class Fixture {
	}

	public static class ClassWithNonZeroArgConstructor {
		public static final Constructor nonZeroArgConstructor =
			new ClassUtil().getConstructor(ClassWithNonZeroArgConstructor.class, Fixture.class);
		
		public final Fixture fixture;

		public ClassWithNonZeroArgConstructor(Fixture fixture) {
			this.fixture = fixture;
		}
	}

	public void testInstantiateNonZeroArgConstructor(Mocker mocker, Verify verify) throws IllegalArgumentException,
		InstantiationException, IllegalAccessException, InvocationTargetException {

		Fixture fixture = new Fixture();
		mocker.expect(resolver.get(ClassWithNonZeroArgConstructor.nonZeroArgConstructor))
			.andReturn((new Object[] {fixture}));

		mocker.replay();

		Object instance = instantiator.instantiate(ClassWithNonZeroArgConstructor.class);

		verify.notNull(instance);
		verify.that(instance instanceof ClassWithNonZeroArgConstructor);

		ClassWithNonZeroArgConstructor classWithNonZeroArgConstructor =
			(ClassWithNonZeroArgConstructor) instance;

		verify.equal(fixture, classWithNonZeroArgConstructor.fixture);
	}
}
