/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import java.lang.reflect.Constructor;

import picounit.PicoUnitAPI;
import picounit.PicoUnitException;
import junit.framework.Test;

public class PicoUnit implements JUnitTestGenerator, Registry {
	private final PicoUnitAPI implementation;

	public PicoUnit() {
		implementation = createImplementation(this);
	}

	public PicoUnit(String name) {
		implementation = createImplementation(name, this);
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
	
	private static PicoUnitAPI createImplementation(PicoUnit picoUnit) {
		try {
			Constructor constructor = getImplementationClass()
				.getConstructor(new Class[] {PicoUnit.class});

			return (PicoUnitAPI) constructor.newInstance(new Object[] {picoUnit});
		}
		catch (Exception e) {
			throw new PicoUnitException(e);
		}
	}

	private static PicoUnitAPI createImplementation(String name, PicoUnit picoUnit) {
		try {
			Constructor constructor = getImplementationClass()
				.getConstructor(new Class[] {String.class, PicoUnit.class});

			return (PicoUnitAPI) constructor.newInstance(new Object[] {name, picoUnit});
		}
		catch (Exception e) {
			throw new PicoUnitException(e);
		}
	}

	private static Class getImplementationClass() throws ClassNotFoundException {
		return Class.forName(System.getProperty("picounit.implementation", 
			"picounit.impl.PicoUnitImpl"));
	}
}
