/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Ignore;
import picounit.Instrumentation;
import picounit.classloader.ClassReloader;
import picounit.classloader.MethodParameterRegistry;
import picounit.finder.ignore.IgnoreCondition;
import picounit.finder.ignore.IgnoredTestSuite;
import picounit.impl.InstrumentationController;
import picounit.impl.InstrumentationImpl;
import picounit.registry.RegistryEntries;
import picounit.registry.RegistryEntry;
import picounit.util.PackageUtil;
import junit.framework.TestSuite;

public class DirectoryScanningTestFinder implements TestFinder {
	private final ClassFinder classFinder;
	private final ClassLoader classLoader;
	private final PackageUtil packageUtil = new PackageUtil();
	private final TestInstantiator testInstantiator;

	public DirectoryScanningTestFinder(ClassFinder classFinder, TestInstantiator testInstantiator,
		ClassLoader classLoader) {

		this.classFinder = classFinder;
		this.testInstantiator = testInstantiator;
		this.classLoader = classLoader;
	}

	public TestSuite findTests(TestFilter testFilter, RegistryEntries registryEntries, String name,
		Class startingClass, Class markerClass, MethodParameterRegistry methodParameterRegistry) {

		TestSuite testSuite = new TestSuite(getName(name, startingClass));
		TestSuite ignoredTestSuite = new TestSuite("Ignored Tests");

		Class ignoreClass = new ClassReloader().reloadClass(classLoader, Ignore.class);

		final InstrumentationController instrumentationController = new InstrumentationImpl();
		registryEntries.register(Instrumentation.class, instrumentationController);

		TestListener testListener = new TestListener() {
			private int numTests = 0;
			private int currentTestIndex = 0;

			public void testPrepared() {
				numTests++ ;
			}

			public void testStarted() {
				if (currentTestIndex == 0) {
					instrumentationController.setIsFirst(true);
					instrumentationController.setIsLast(false);
				}
				else if (currentTestIndex == 1) {
					instrumentationController.setIsFirst(false);
				}
				if (currentTestIndex == (numTests - 1)) {
					instrumentationController.setIsLast(true);
				}

				currentTestIndex++ ;
			}
		};

		classFinder.findClasses(startingClass, new AddTestFindAction(testFilter, classLoader,
			testSuite, ignoredTestSuite, markerClass, registryEntries, testInstantiator,
			ignoreClass, methodParameterRegistry, testListener));

		if (testSuite.testCount() == 0) {
			testSuite.addTest(new NoTestsFoundTestCase(startingClass));
		}

		if (ignoredTestSuite.countTestCases() > 0) {
			testSuite.addTest(ignoredTestSuite);
		}

		return testSuite;
	}

	private String getName(String name, Class startingClass) {
		return name != null ? name : packageUtil.getPackageName(startingClass);
	}

	public static class AddTestFindAction implements FindAction {
		private final ClassLoader classLoader;
		private final TestSuite testSuite;
		private final TestSuite ignoredTestSuite;
		private final ImplementsCondition isTestCondition;
		private final IgnoreCondition isIgnored;
		private final RegistryEntry registryEntry;
		private final TestFilter testFilter;
		private final MethodParameterRegistry methodParameterRegistry;
		private final TestListener testListener;

		public AddTestFindAction(TestFilter testFilter, ClassLoader classLoader,
			TestSuite testSuite, TestSuite ignoredTestSuite, Class markerClass,
			RegistryEntry registryEntry, TestInstantiator testInstantiator,
			Class ignoreClass, MethodParameterRegistry methodParameterRegistry,
			TestListener testListener) {

			this.testFilter = testFilter;
			this.classLoader = classLoader;
			this.testSuite = testSuite;
			this.registryEntry = registryEntry;
			this.ignoredTestSuite = ignoredTestSuite;
			this.methodParameterRegistry = methodParameterRegistry;
			this.isTestCondition = new ImplementsCondition(markerClass);
			this.isIgnored = new IgnoreCondition(new ImplementsCondition(ignoreClass),
				testInstantiator);
			this.testListener = testListener;
		}

		public void perform(String className) {
			try {
				Class aClass = classLoader.loadClass(className);

				if (testFilter.matches(aClass) && isTestCondition.matches(aClass)) {
					if (isIgnored.matches(aClass)) {
						ignoredTestSuite.addTest(new IgnoredTestSuite(aClass, isIgnored.whyIgnored(aClass)));
					}
					else {
						testSuite.addTest(new SinglePicoUnitTestSuite(aClass.getName(), aClass,
							testFilter, registryEntry, methodParameterRegistry, classLoader,
							testListener));
					}
				}
			}
			catch (ClassNotFoundException classNotFoundException) {
				testSuite.addTest(new ClassNotFoundFailingTest(className, classNotFoundException));
			}
		}
	}
}
