/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.mutator;


public class InterceptClientCodeClassAdapter extends ClassAdapter {
	private final InterceptClientCodeCodeAdapter interceptClientCodeCodeVisitor;

	private String className;

	public InterceptClientCodeClassAdapter() {
		this.interceptClientCodeCodeVisitor = new InterceptClientCodeCodeAdapter(this);
	}

//	public void visit(int access, String className, String superName, String[] interfaces, String sourceFile) {
//		this.className = className.replace('/', '.');
//
//		super.visit(access, className, superName, interfaces, sourceFile);
//	}
//
//	public CodeVisitor visitMethod(int access, String methodName, String desc, String[] exceptions,
//		Attribute attrs) {
//
//		return super.visitMethod(access, methodName, desc, exceptions, attrs);
//		
////		return interceptClientCodeCodeVisitor
////			.visit(className, access, methodName, desc, exceptions, attrs);
//	}
}
