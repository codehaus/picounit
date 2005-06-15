/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.LifeCycle;
import picounit.reflection.Instantiator;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class LifeCycleInstantiatorImpl implements LifeCycleInstantiator {
	private final Instantiator instantiator;
	private final ClassLoader classLoader;
	private final FileSystem fileSystem;
	private final ClassFinder classFinder;

	public LifeCycleInstantiatorImpl(ClassLoader classLoader, Instantiator instantiator) {
		this.instantiator = instantiator;
		this.classLoader = classLoader;
		this.fileSystem = new FileSystem();
		this.classFinder = new ClassFinder();
	}

	public LifeCycle[] instantiate(Class testClass) {		
		File sourceRoot = fileSystem.getSourceRoot(testClass);

		List lifeCycleList = new LinkedList();

		classFinder.findClasses(sourceRoot, sourceRoot,
			new AboveClassDirectoryCondition(fileSystem.getClassFile(testClass), sourceRoot),
			new AddLifeCycle(lifeCycleList, classLoader, instantiator)); 

		return (LifeCycle[]) lifeCycleList.toArray(new LifeCycle[0]);
	}
	
	public static class AddLifeCycle implements FindAction {
		private final List lifeCycleList;
		private final ClassLoader classLoader;
		private final Instantiator instantiator;
		private final Condition isLifeCycle;

		private AddLifeCycle(List lifeCycleList, ClassLoader classLoader, Instantiator instantiator) {
			this(lifeCycleList, classLoader, instantiator, new ImplementsCondition(LifeCycle.class));
		}
		
		private AddLifeCycle(List lifeCycleList, ClassLoader classLoader, Instantiator instantiator,
			Condition isLifeCycle) {

			this.lifeCycleList = lifeCycleList;
			this.classLoader = classLoader;
			this.instantiator = instantiator;
			this.isLifeCycle = isLifeCycle;
		}

		public void perform(String className) {
			try {
				Class aClass = classLoader.loadClass(className);

				if (isLifeCycle.matches(aClass)) {
					lifeCycleList.add( instantiator.instantiate(aClass));
				}
			}
			catch (Exception exception) {
				throw new LifeCycleInstantiationException(exception);
			}
		}
	}

	public static class LifeCycleInstantiationException extends RuntimeException {
		public LifeCycleInstantiationException(Exception exception) {
			super(exception);
		}
	}
}
