/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import java.util.LinkedList;
import java.util.List;

public class FindClassCodeVisitor implements CodeVisitor {
	private final String className;
	private final List codeContaining;

	public FindClassCodeVisitor(String className) {
		this.className = className;
		this.codeContaining = new LinkedList();
	}

	public void visit(ClassDirectory classDirectory) {
		if (classDirectory.containsClass(className)) {
			codeContaining.add(classDirectory);
		}
	}

	public void visit(Jar jar) {
		if (jar.containsClass(className)) {
			codeContaining.add(jar);
		}
	}

	public CodeIterator iterator() {
		return new CodeIterator(codeContaining.iterator());
	}
}
