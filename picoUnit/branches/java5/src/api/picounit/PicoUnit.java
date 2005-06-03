/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.impl.PicoUnitImpl;
import junit.framework.Test;

public class PicoUnit implements JUnitTestGenerator, Registry {
	private final PicoUnitImpl implementation;

	public PicoUnit() {
		implementation = new PicoUnitImpl(this);
	}

	public PicoUnit(String name) {
		implementation = new PicoUnitImpl(name, this);
	}
	
	public Test generateJUnitTest() {
		return implementation.generateJUnitTest();
	}

	public Test generateJUnitTest(JUnitTestGenerator generator) {
		return implementation.generateJUnitTest(generator);
	}
	
	public Test generateJUnitTest(Class startingClass) {
		return implementation.generateJUnitTest(startingClass);
	}

	public Test generateSingleJUnitTest(Class testClass) {
		return implementation.generateSingleJUnitTest(testClass);
	}

	public PicoUnit setType(Class type) {
		return implementation.setType(type);
	}

	public void register(Class type, Object instance) {
		this.implementation.register(type, instance);
	}

	public void register(Class type, Class implementation) {
		this.implementation.register(type, implementation);
	}

	public void register(Class implementation) {
		this.implementation.register(implementation);
	}
}
