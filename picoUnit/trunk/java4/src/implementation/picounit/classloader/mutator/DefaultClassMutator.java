/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.mutator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class DefaultClassMutator implements ClassMutator {
	private final ClassAdapter[] classAdapters;
	private final boolean computeMaxs;
	private final boolean skipDebug;
	
	public DefaultClassMutator(ClassAdapter classAdapter) {
		this(classAdapter, true, false);
	}

	public DefaultClassMutator(ClassAdapter[] classAdapters) {
		this(classAdapters, true, false);
	}

	public DefaultClassMutator(ClassAdapter classAdapter, boolean computeMaxs, boolean skipDebug) {
		this(new ClassAdapter[] {classAdapter}, computeMaxs, skipDebug);
	}

	public DefaultClassMutator(ClassAdapter[] classAdapters, boolean computeMaxs, boolean skipDebug) {
		this.classAdapters = classAdapters;
		this.computeMaxs = computeMaxs;
		this.skipDebug = skipDebug;
	}

	public boolean shouldMutate(String className) {
		return false;
	}

	public byte[] mutateByteCode(String className, byte[] byteCode) {
		for (int index = 0; index < classAdapters.length; index++ ) {
			byteCode = mutate(byteCode, classAdapters[index]);
		}

		return byteCode;
	}

	private byte[] mutate(byte[] byteCode, ClassAdapter classAdapter) {
		classAdapter.setClassWriter(new ClassWriter(computeMaxs));

		new ClassReader(byteCode).accept(classAdapter, skipDebug);

		return classAdapter.toByteArray();
	}
}
