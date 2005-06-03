/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.mutator;

import net.sf.cglib.transform.NullClassVisitor;

import org.objectweb.asm.CodeVisitor;
import org.objectweb.asm.CodeWriter;

public class CodeAdapter extends org.objectweb.asm.CodeAdapter {
	public CodeAdapter() {
		super(nullCodeVisitor());
	}

	public void setCodeWriter(CodeWriter codeWriter) {
		cv = codeWriter;
	}

	private static CodeVisitor nullCodeVisitor() {
		return NullClassVisitor.INSTANCE.visitMethod(1, null, null, null, null);
	}
}
