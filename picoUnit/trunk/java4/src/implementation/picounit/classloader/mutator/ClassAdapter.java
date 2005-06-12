/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.mutator;

import net.sf.cglib.transform.NullClassVisitor;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.CodeWriter;

public class ClassAdapter extends org.objectweb.asm.ClassAdapter {
	public ClassAdapter() {
		super(new NullClassVisitor());
	}

	public void setClassWriter(ClassWriter classWriter) {
		cv = classWriter;
	}

	public CodeWriter getCodeWriter(int access, String name, String desc, String[] exceptions, Attribute attrs) {
		return (CodeWriter) getClassWriter().visitMethod(access, name, desc, exceptions, attrs);
	}

	public byte[] toByteArray() {
		return getClassWriter().toByteArray();
	}

	private ClassWriter getClassWriter() {
		return (ClassWriter) cv;
	}
}
