/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Ignore;
import picounit.classloader.ClassReloader;
import picounit.classloader.MethodParameterRegistry;
import picounit.finder.ignore.IgnoreCondition;
import picounit.finder.ignore.IgnoredTestSuite;
import picounit.registry.RegistryEntry;
import picounit.registry.RegistryFactory;
import picounit.registry.RegistryImpl;
import picounit.util.PackageUtil;
import junit.framework.TestSuite;

public class DirectoryScanningTestFinder implements TestFinder {
	private final ClassFinder classFinder;
	private final ContextFinder contextFinder;
	private final ClassLoader classLoader;
	private final PackageUtil packageUtil = new PackageUtil();
	private final TestInstantiator testInstantiator;

	public DirectoryScanningTestFinder(ClassLoader classLoader) {
		this(new ClassFinder(), new ContextFinder(classLoader),
			new TestInstantiator(new RegistryFactory().create(), classLoader), classLoader);
	}

	public DirectoryScanningTestFinder(ClassFinder classFinder, ContextFinder contextFinder,
		TestInstantiator testInstantiator, ClassLoader classLoader) {

		this.classFinder = classFinder;
		this.contextFinder = contextFinder;
		this.testInstantiator = testInstantiator;
		this.classLoader = classLoader;
	}

	public TestSuite findTests(TestFilter testFilter, RegistryEntry registryEntry, String name, Class startingClass,
		Class markerClass, MethodParameterRegistry methodParameterRegistry) {

		TestSuite testSuite = new TestSuite(getName(name, startingClass));
		TestSuite ignoredTestSuite = new TestSuite("Ignored Tests");

		RegistryImpl registry = new RegistryFactory().create();
		registryEntry.registerWith(registry);
		Class ignoreClass = new ClassReloader().reloadClass(classLoader, Ignore.class);

		classFinder.findClasses(startingClass, new AddTestFindAction(testFilter, classLoader, testSuite,
			ignoredTestSuite, markerClass, registryEntry, contextFinder, testInstantiator, ignoreClass, methodParameterRegistry));

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
		private final ContextFinder contextFinder;
		private final TestFilter testFilter;
		private final MethodParameterRegistry methodParameterRegistry;

		public AddTestFindAction(TestFilter testFilter, ClassLoader classLoader, TestSuite testSuite,
			TestSuite ignoredTestSuite, Class markerClass, RegistryEntry registryEntry,
			ContextFinder contextFinder, TestInstantiator testInstantiator, Class ignoreClass,
			MethodParameterRegistry methodParameterRegistry) {

			this.testFilter = testFilter;
			this.classLoader = classLoader;
			this.testSuite = testSuite;
			this.registryEntry = registryEntry;
			this.contextFinder = contextFinder;
			this.ignoredTestSuite = ignoredTestSuite;
			this.methodParameterRegistry = methodParameterRegistry;
			this.isTestCondition = new ImplementsCondition(markerClass);
			this.isIgnored =
				new IgnoreCondition(new ImplementsCondition(ignoreClass), testInstantiator);
		}

		public void perform(String className) {
			try {
				Class aClass = classLoader.loadClass(className);

				if (testFilter.matches(aClass) && isTestCondition.matches(aClass)) {
					if (isIgnored.matches(aClass)) {
						ignoredTestSuite.addTest(
							new IgnoredTestSuite(aClass, isIgnored.whyIgnored(aClass)));
					}
					else {
						testSuite.addTest(new SinglePicoUnitTestSuite(aClass.getName(), aClass, testFilter,
							registryEntry, contextFinder, methodParameterRegistry));
					}
				}
			}
			catch (ClassNotFoundException classNotFoundException) {
				testSuite.addTest(new ClassNotFoundFailingTest(className, classNotFoundException));
			}
		}
	}
}