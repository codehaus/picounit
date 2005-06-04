/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CachingInstantiator implements Instantiator {
	private final Instantiator delegateInstantiator;
	private final Map<Class, Object> instancesMap = new HashMap<Class, Object>();

	public CachingInstantiator(Instantiator delegateInstantiator) {
		this.delegateInstantiator = delegateInstantiator;
	}

	public Object instantiate(Class toInstantiate) throws IllegalArgumentException,
		InstantiationException, IllegalAccessException, InvocationTargetException {

		Object instance = instancesMap.get(toInstantiate);

		if (instance == null) {
			instance = delegateInstantiator.instantiate(toInstantiate);

			instancesMap.put(toInstantiate, instance);
		}

		return instance;
	}
}
