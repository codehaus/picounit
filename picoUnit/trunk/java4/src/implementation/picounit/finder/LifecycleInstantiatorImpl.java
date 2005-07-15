/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Lifecycle;
import picounit.reflection.Instantiator;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class LifecycleInstantiatorImpl implements LifecycleInstantiator {
	private final Instantiator instantiator;
	private final ClassLoader classLoader;
	private final FileSystem fileSystem;
	private final ClassFinder classFinder;

	public LifecycleInstantiatorImpl(ClassLoader classLoader, Instantiator instantiator) {
		this.instantiator = instantiator;
		this.classLoader = classLoader;
		this.fileSystem = new FileSystem();
		this.classFinder = new ClassFinder();
	}

	public Lifecycle[] instantiate(Class testClass) {		
		File sourceRoot = fileSystem.getSourceRoot(testClass);

		List lifecycleList = new LinkedList();

		classFinder.findClasses(sourceRoot, sourceRoot,
			new AboveClassDirectoryCondition(fileSystem.getClassFile(testClass), sourceRoot),
			new AddLifecycle(lifecycleList, classLoader, instantiator)); 

		return (Lifecycle[]) lifecycleList.toArray(new Lifecycle[0]);
	}
	
	public static class AddLifecycle implements FindAction {
		private final List lifecycleList;
		private final ClassLoader classLoader;
		private final Instantiator instantiator;
		private final Condition isLifecycle;

		private AddLifecycle(List lifecycleList, ClassLoader classLoader, Instantiator instantiator) {
			this(lifecycleList, classLoader, instantiator, new ImplementsCondition(Lifecycle.class));
		}
		
		private AddLifecycle(List lifecycleList, ClassLoader classLoader, Instantiator instantiator,
			Condition isLifecycle) {

			this.lifecycleList = lifecycleList;
			this.classLoader = classLoader;
			this.instantiator = instantiator;
			this.isLifecycle = isLifecycle;
		}

		public void perform(String className) {
			try {
				Class aClass = classLoader.loadClass(className);

				if (isLifecycle.matches(aClass)) {
					lifecycleList.add((Lifecycle) instantiator.instantiate(aClass));
				}
			}
			catch (Exception exception) {
				throw new LifecycleInstantiationException(exception);
			}
		}
	}

	public static class LifecycleInstantiationException extends RuntimeException {
		public LifecycleInstantiationException(Exception exception) {
			super(exception);
		}
	}
}
