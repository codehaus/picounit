/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;


import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ContextFinder {
	private final ClassFinder classFinder;
	private final FileSystem fileSystem;
	private final ClassLoader classLoader;
	
	public ContextFinder(ClassLoader classLoader) {
		this(new ClassFinder(), new FileSystem(), classLoader);
	}

	public ContextFinder(ClassFinder classFinder, FileSystem fileSystem, ClassLoader classLoader) {
		this.classFinder = classFinder;
		this.fileSystem = fileSystem;
		this.classLoader = classLoader;
	}

	public ContextClass[] findContexts(Class testClass, final Condition condition) {
		File sourceRoot = fileSystem.getSourceRoot(testClass);

		final List listContextClasses = new LinkedList();

		classFinder.findClasses(sourceRoot, sourceRoot,
			new AboveClassDirectoryCondition(fileSystem.getClassFile(testClass), sourceRoot),
			new AddContextClassFindAction(listContextClasses, condition, classLoader)); 

		return (ContextClass[]) listContextClasses.toArray(new ContextClass[0]);
	}

	public static class AddContextClassFindAction implements FindAction {
		private final List listContextClasses;
		private final Condition condition;
		private final ClassLoader classLoader;

		private AddContextClassFindAction(List listContextClasses, Condition condition,
			ClassLoader classLoader) {

			this.listContextClasses = listContextClasses;
			this.condition = condition;
			this.classLoader = classLoader;
		}

		public void perform(String className) {
			try {
				Class aClass = classLoader.loadClass(className);

				if (condition.matches(aClass)) {
					listContextClasses.add(new PassingContextClass(aClass));
				}
			}
			catch (ClassNotFoundException classNotFoundException) {
				listContextClasses.add(new FailingContextClass(classNotFoundException));
			}
		}
	}
}