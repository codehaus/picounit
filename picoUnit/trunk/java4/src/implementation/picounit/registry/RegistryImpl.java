/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.registry;

import org.picocontainer.MutablePicoContainer;
import org.picocontainer.defaults.DefaultPicoContainer;
import org.picocontainer.defaults.DuplicateComponentKeyRegistrationException;

import picounit.Registry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RegistryImpl implements Registry, Resolver {
	private final MutablePicoContainer picoContainer;

	public RegistryImpl() {
		this(new DefaultPicoContainer());
	}

	public RegistryImpl(MutablePicoContainer picoContainer) {
		this.picoContainer = picoContainer;

		register(Registry.class, this);
		register(Resolver.class, this);
	}

	public void register(Class type, Object instance) {
		try {
			picoContainer.registerComponentInstance(type, instance);
		}
		catch (DuplicateComponentKeyRegistrationException duplicateComponentKeyRegistrationException) {
			picoContainer.unregisterComponent(type);
			picoContainer.registerComponentInstance(type, instance);
		}
	}

	public void register(Class type, Class implementation) {
		try {
			picoContainer.registerComponentImplementation(type, implementation);
		}
		catch (DuplicateComponentKeyRegistrationException duplicateComponentKeyRegistrationException) {
			picoContainer.unregisterComponent(type);
			picoContainer.registerComponentImplementation(type, implementation);
		}
	}

	public void register(Class implementation) {
		try {
			picoContainer.registerComponentImplementation(implementation);
		}
		catch (DuplicateComponentKeyRegistrationException duplicateComponentKeyRegistrationException) {
			picoContainer.unregisterComponent(implementation);
			picoContainer.registerComponentImplementation(implementation);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> type) {
		return (T) picoContainer.getComponentInstanceOfType(type); 
	}

	public Object[] get(Method method) {
		return get(method.getParameterTypes());
	}
	
	public Object[] get(Constructor constructor) {
		return get(constructor.getParameterTypes());
	}

	@SuppressWarnings("unchecked")
	private Object[] get(Class[] types) {
		Object[] fixtures = new Object[types.length];

		for (int index = 0; index < types.length; index++) {
			fixtures[index] = get(types[index]);
		}

		return fixtures;
	}
}
