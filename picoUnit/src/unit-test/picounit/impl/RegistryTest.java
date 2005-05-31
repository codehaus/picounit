/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import org.picocontainer.MutablePicoContainer;

import picounit.Implementation;
import picounit.Interface;
import picounit.Registry;
import picounit.registry.RegistryImpl;
import picounit.registry.Resolver;
import previous.picounit.Constraints;
import previous.picounit.Future;
import previous.picounit.Mocker;
import previous.picounit.Test;
import previous.picounit.Verify;

import java.util.Arrays;

public class RegistryTest implements Test {
	private MutablePicoContainer picoContainer;

	public static class SomeClass{}
	public static class AnotherClass{}
	
	private Future futureRegistry;

	public RegistryTest(Constraints is) {
		futureRegistry = is.future(RegistryImpl.class);
	}

	public void mock(MutablePicoContainer picoContainer) {
		this.picoContainer = picoContainer;

		picoContainer.registerComponentInstance(Registry.class, futureRegistry);
		picoContainer.registerComponentInstance(Resolver.class, futureRegistry);
	}
	
	public void testDelegatesInstanceRegistrationToPico(Mocker mocker) {
		Class key = SomeClass.class;
		Object value = new SomeClass();

		picoContainer.registerComponentInstance(key, value); 

		mocker.replay();

		registry().register(key, value);
	}
	
	public void testDelegatesInterfaceToImplementationRegistrationToPico(Mocker mocker) {
		picoContainer.registerComponentImplementation(Interface.class, Implementation.class);

		mocker.replay();

		registry().register(Interface.class, Implementation.class);
	}
	
	public void testDelegatesImplementationRegistrationToPico(Mocker mocker) {
		picoContainer.registerComponentImplementation(Implementation.class);

		mocker.replay();

		registry().register(Implementation.class);
	}
	
	public void testDelegatesGetToPico(Mocker mocker, Verify verify) {
		Class[] keys = new Class[] {SomeClass.class, AnotherClass.class};
		Object[] values = new Object[] {new SomeClass(), new AnotherClass()};
		
		mocker.expect(picoContainer.getComponentInstanceOfType(keys[0])).andReturn(values[0]);
		mocker.expect(picoContainer.getComponentInstanceOfType(keys[1])).andReturn(values[1]);
		
		mocker.replay();
		
		verify.that(Arrays.equals(values, registry().get(keys)));
	}

	private RegistryImpl registry() {
		RegistryImpl registry = new RegistryImpl(picoContainer);

		futureRegistry.setValue(registry);

		return registry;
	}
}
