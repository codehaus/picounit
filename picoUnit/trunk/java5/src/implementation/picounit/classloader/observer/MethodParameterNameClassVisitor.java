/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.observer;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.CodeVisitor;

import picounit.classloader.MethodParameterRegistry;

public class MethodParameterNameClassVisitor implements ClassVisitor {
	private final MethodParameterNameCodeVisitor methodParameterNameCodeVisitor;
	private String className;
	
	public MethodParameterNameClassVisitor(MethodParameterRegistry methodParameterRegistry) {
		this(new MethodParameterNameCodeVisitor(
			new DefaultMethodParameterListener(methodParameterRegistry)));
	}

	public MethodParameterNameClassVisitor(MethodParameterNameCodeVisitor methodParameterNameCodeVisitor) {
		this.methodParameterNameCodeVisitor = methodParameterNameCodeVisitor;
	}

	public void visit(int access, String className, String superName, String[] interfaces, String sourceFile) {
		this.className = className.replace('/', '.');
	}

	public void visitInnerClass(String name, String outerName, String innerName, int access) {
	}

	public void visitField(int access, String name, String desc, Object value, Attribute attrs) {
	}

	public CodeVisitor visitMethod(int access, String name, String desc, String[] exceptions, Attribute attrs) {
		return methodParameterNameCodeVisitor.visit(className, access, name, desc, exceptions, attrs);
	}

	public void visitAttribute(Attribute attr) {
	}

	public void visitEnd() {
	}
}
