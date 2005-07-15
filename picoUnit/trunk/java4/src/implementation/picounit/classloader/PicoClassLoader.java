/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import picounit.classloader.mutator.ClassMutator;
import picounit.classloader.mutator.NullClassMutator;
import picounit.classloader.observer.ClassObserver;
import picounit.classloader.observer.NullClassObserver;

import java.util.HashMap;
import java.util.Map;

public class PicoClassLoader extends ClassLoader {
	private final ClassPath classPath;
	private final Map loadedClasses = new HashMap();

	private ClassMutator classMutator;
	private ClassObserver classObserver;
	
	public PicoClassLoader(ClassLoader parent, ClassPath classPath) {
		super(parent);

		this.classPath = classPath;
		this.classObserver = new NullClassObserver();
		this.classMutator = new NullClassMutator();
	}

	public PicoClassLoader setClassObserver(ClassObserver classObserver) {
		this.classObserver = classObserver;
		
		return this;
	}
	
	public PicoClassLoader setClassMutator(ClassMutator classMutator) {
		this.classMutator = classMutator;
		
		return this;
	}

	public Class loadClass(String className) throws ClassNotFoundException {
		if (isJDKClass(className)) {
			return getParent().loadClass(className);
		}

		Class loadedClass = (Class) loadedClasses.get(className);
		if (loadedClass != null) {
			return loadedClass;
		}

		byte[] byteCode = classPath.findClass(className);

		boolean mutated = false;
		if (classMutator.shouldMutate(className)) {
			byteCode = classMutator.mutateByteCode(className, byteCode);
			mutated = true;
		}

		classObserver.observe(className, byteCode);

		if (mutated) {
			loadedClass = defineClass(className, byteCode, 0, byteCode.length);
		}
		else {
			loadedClass = getParent().loadClass(className);
		}

		loadedClasses.put(className, loadedClass);

		return loadedClass;
	}

	private boolean isJDKClass(String className) {
		return className.startsWith("java");
	}
}
