/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.reflection;

import picounit.registry.Resolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class OrdinaryInstantiator implements Instantiator {
	private final Resolver resolver;

	public OrdinaryInstantiator(Resolver resolver) {
		this.resolver = resolver;
	}

	@SuppressWarnings("unchecked")
	public <T> T instantiate(Class<T> toInstantiate) throws IllegalArgumentException,
		InstantiationException, IllegalAccessException, InvocationTargetException {

		Constructor<T>[] constructors = toInstantiate.getConstructors();

		if (constructors.length == 0) {
			return toInstantiate.newInstance();
		}

		if (constructors.length != 1) {
			throw new IllegalArgumentException(toInstantiate.getName() +
				" has more than one public constructor");
		}

		Constructor<T> constructor = constructors[0];

		return constructor.newInstance(resolver.get(constructor));
	}
}
