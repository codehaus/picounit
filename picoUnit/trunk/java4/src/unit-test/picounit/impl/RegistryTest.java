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

public class RegistryTest implements Test {
	private MutablePicoContainer picoContainer;

	public static class SomeClass{}
	public static class AnotherClass{}

	private Future<RegistryImpl> futureRegistry;

	public RegistryTest(Constraints is) {
		this.futureRegistry = is.future(RegistryImpl.class);
	}

	public void mock(MutablePicoContainer picoContainer) {
		this.picoContainer = picoContainer;

		picoContainer.registerComponentInstance(Registry.class, futureRegistry);
		picoContainer.registerComponentInstance(Resolver.class, futureRegistry);
	}
	
	public void testDelegatesInstanceRegistrationToPico(Mocker should) {
		Class key = SomeClass.class;
		Object value = new SomeClass();

		picoContainer.registerComponentInstance(key, value); 

		should.expectAboveWhenTheFollowingOccurs();

		registry().register(key, value);
	}
	
	public void testDelegatesInterfaceToImplementationRegistrationToPico(Mocker should) {
		picoContainer.registerComponentImplementation(Interface.class, Implementation.class);

		should.expectAboveWhenTheFollowingOccurs();

		registry().register(Interface.class, Implementation.class);
	}
	
	public void testDelegatesImplementationRegistrationToPico(Mocker should) {
		picoContainer.registerComponentImplementation(Implementation.class);

		should.expectAboveWhenTheFollowingOccurs();

		registry().register(Implementation.class);
	}
	
	private RegistryImpl registry() {
		RegistryImpl registry = new RegistryImpl(picoContainer);

		futureRegistry.setValue(registry);

		return registry;
	}
}
