/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.mutator;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.CodeVisitor;

public class InterceptClientCodeCodeAdapter extends CodeAdapter {
	private final ClassAdapter classAdapter;

	public InterceptClientCodeCodeAdapter(ClassAdapter classAdapter) {
		this.classAdapter = classAdapter;
	}

	public CodeVisitor visit(String className, int access, String methodName, String description,
		String[] exceptions, Attribute attrs) {
		
		setCodeWriter(classAdapter.getCodeWriter(access, methodName, description, exceptions, attrs));

		return addMethodInvocation(className, methodName);
	}

	private CodeVisitor addMethodInvocation(String className, String methodName) {
		if (!methodName.equals("<init>") && !methodName.equals("<clinit>")) {
//			System.out.println("intercepting: " + className + "." + methodName);

//			super.visitLdcInsn(className);
//			super.visitLdcInsn(methodName);
//			super.visitMethodInsn(Constants.INVOKESTATIC, getClass().getName().replace('.', '/'),
//				"intercept", "(Ljava/lang/String;Ljava/lang/String;)V");
		}

		return this;
	}

	public static void intercept(String className, String methodName) {
		System.out.println("Intercepted: " + className + "." + methodName);
	}
}
