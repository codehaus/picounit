/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.JUnitTestGenerator;
import picounit.PicoUnit;
import picounit.PicoUnitException;
import picounit.classloader.ClassReloader;
import picounit.classloader.MethodParameterRegistry;
import picounit.classloader.PicoClassLoader;
import picounit.classloader.observer.MethodNameClassObserver;
import picounit.finder.AlwaysPassingTestFilter;
import picounit.finder.DirectoryScanningTestFinder;
import picounit.finder.SingleTestTestFilter;
import picounit.finder.TestFilter;
import picounit.finder.TestFinder;
import picounit.registry.RegistryEntries;
import junit.framework.Test;

public class PicoUnitImpl {
	private static final int STACK_DISTANCE_TO_CALLER = 2;

	private final PicoUnit picoUnit;

	private final MethodParameterRegistry methodParameterRegistry = new MethodParameterRegistry();
	private final RegistryEntries registryEntries = new RegistryEntries();
	private final ClassLoader classLoader = new PicoClassLoader(getClass().getClassLoader())
		.setClassObserver(new MethodNameClassObserver(methodParameterRegistry));

	private String name;
	private Class type = picounit.Test.class;

	private TestFinder testFinder = new DirectoryScanningTestFinder(classLoader);
	private TestFilter testFilter = new AlwaysPassingTestFilter();
	private ClassReloader classRealoder = new ClassReloader();

	public PicoUnitImpl(PicoUnit picoUnit) {
		this.picoUnit = picoUnit;
	}
	
	public PicoUnitImpl(String name, PicoUnit picoUnit) {
		this(picoUnit);

		setName(name);
	}

	public Test generateJUnitTest() {
		return generateJUnitTest(getClass(getCallerClassName(new Throwable())));
	}

	public Test generateJUnitTest(JUnitTestGenerator generator) {
		return generator.generateJUnitTest();
	}

	public Test generateJUnitTest(Class startingClass) {
		try {
			return testFinder.findTests(testFilter, registryEntries, name, reloadClass(startingClass), type, methodParameterRegistry);
		}
		catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
			throw new PicoUnitException(runtimeException);
		}
	}

	public Test generateSingleJUnitTest(Class testClass) {
		setFilter(singleTest(testClass));
		setName(getSingleTestClassName(testClass));

		return generateJUnitTest(testClass);
	}

	private PicoUnit setFilter(TestFilter testFilter) {
		this.testFilter = testFilter;

		return picoUnit;
	}

	public void setName(String name) {
		this.name = name;
	}

	// TODO: Setting the Marker interface used is not integral but part of the DirectoryScanningTestFinder
	public PicoUnit setType(Class type) {
		this.type = reloadClass(type);

		return picoUnit;
	}
	
	public void register(Class type, Object instance) {
		registryEntries.register(reloadClass(type), instance);
	}

	public void register(Class type, Class implementation) {
		registryEntries.register(reloadClass(type), reloadClass(implementation));
	}

	public void register(Class implementation) {
		registryEntries.register(reloadClass(implementation));
	}

	private String getSingleTestClassName(Class testClass) {
		return name != null ? name : testClass.getName();
	}

	private String getCallerClassName(Throwable throwable) {
		StackTraceElement caller = throwable.getStackTrace()[STACK_DISTANCE_TO_CALLER];

		return caller.getClassName();
	}

	private Class getClass(String className) {
		try {
			return Class.forName(className);
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new PicoUnitException(classNotFoundException);
		}
	}

	private SingleTestTestFilter singleTest(Class testClass) {
		return new SingleTestTestFilter(reloadClass(testClass));
	}
	
	private Class reloadClass(Class aClass) {
		return classRealoder.reloadClass(classLoader, aClass);
	}
}
