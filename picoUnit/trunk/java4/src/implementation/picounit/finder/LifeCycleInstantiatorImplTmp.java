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

public class LifeCycleInstantiatorImplTmp implements LifeCycleInstantiatorTmp {
	private final Instantiator instantiator;
	private final ClassLoader classLoader;
	private final FileSystem fileSystem;
	private final ClassFinder classFinder;

	public LifeCycleInstantiatorImplTmp(ClassLoader classLoader, Instantiator instantiator) {
		this.instantiator = instantiator;
		this.classLoader = classLoader;
		this.fileSystem = new FileSystem();
		this.classFinder = new ClassFinder();
	}

	public Lifecycle[] instantiate(Class testClass) {		
		File sourceRoot = fileSystem.getSourceRoot(testClass);

		List lifeCycleList = new LinkedList();

		classFinder.findClasses(sourceRoot, sourceRoot,
			new AboveClassDirectoryCondition(fileSystem.getClassFile(testClass), sourceRoot),
			new AddLifecycle(lifeCycleList, classLoader, instantiator)); 

		return (Lifecycle[]) lifeCycleList.toArray(new Lifecycle[0]);
	}
	
	public static class AddLifecycle implements FindAction {
		private final List lifeCycleList;
		private final ClassLoader classLoader;
		private final Instantiator instantiator;
		private final Condition isLifeCycle;

		private AddLifecycle(List lifeCycleList, ClassLoader classLoader, Instantiator instantiator) {
			this(lifeCycleList, classLoader, instantiator, new ImplementsCondition(Lifecycle.class));
		}
		
		private AddLifecycle(List lifeCycleList, ClassLoader classLoader, Instantiator instantiator,
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
