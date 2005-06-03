/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.registry.RegistryEntries;
import junit.framework.Assert;
import junit.framework.TestResult;

public abstract class Validator implements Registry {
	private final Operations operations = new Operations();
	private final RegistryEntries registryEntries = new RegistryEntries();
	private final Class testClass;

	public Validator(Class testClass) {
		this.testClass = testClass;
	}

	public void runSingle() {
		TestResult testResult = new TestResult();

		registryEntries.register(Validator.class, this);

		PicoUnit picoUnit = new PicoUnit();
		registryEntries.registerWith(picoUnit);

		picoUnit.generateSingleJUnitTest(testClass).run(testResult);

		validate(testResult);
	}
	
	public void record(String methodName) {
		operations.record(methodName);
	}

	public void record(String methodName, Object parameter) {
		operations.record(methodName, parameter);
	}

	public void matches(String toMatch) {
		operations.matches(toMatch);
	}
	
	public void has(String parameter) {
		Assert.assertNotNull(operations.get(parameter));
	}
	
	public void register(Class implementation) {
		registryEntries.register(implementation);
	}
	
	public void register(Class type, Class implementation) {
		registryEntries.register(type, implementation);
	}
	
	public void register(Class type, Object instance) {
		registryEntries.register(type, instance);
	}

	abstract public void validate(TestResult testResult);
}