/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.observer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

public class DefaultClassObserver implements ClassObserver {
	private final ClassVisitor[] classVisitors;
	private final boolean skipDebug;
	
	public DefaultClassObserver(ClassVisitor classVisitor) {
		this(classVisitor, false);
	}

	public DefaultClassObserver(ClassVisitor classVisitor, boolean skipDebug) {
		this(new ClassVisitor[] {classVisitor}, skipDebug);
	}
	
	public DefaultClassObserver(ClassVisitor[] classVisitors, boolean skipDebug) {
		this.classVisitors = classVisitors;
		this.skipDebug = skipDebug;
	}

	public void observe(String className, byte[] byteCode) {
		ClassReader classReader = new ClassReader(byteCode);

		for (int index = 0; index < classVisitors.length; index++ ) {
			classReader.accept(classVisitors[index], skipDebug);
		}
	}
}
