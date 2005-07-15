/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class ClassPath {
	private final Code[] code;

	public ClassPath() {
		this(System.getProperty("java.class.path"), new CodeFactory());
	}

	public ClassPath(String classPath, CodeFactory codeFactory) {
		StringTokenizer stringTokenizer = new StringTokenizer(classPath, File.pathSeparator);

		List code = new LinkedList();
		Set visitedClassPathEntries = new HashSet();
		while(stringTokenizer.hasMoreTokens()) {
			String classPathEntry = stringTokenizer.nextToken();

			if (!visitedClassPathEntries.contains(classPathEntry)) {
				code.add(codeFactory.create(classPathEntry));

				visitedClassPathEntries.add(classPathEntry);
			}
		}

		this.code = (Code[]) code.toArray(new Code[0]);
	}
	
	public Code findCodeContaining(String className) {
		FindClassCodeVisitor findClassCodeVisitor = new FindClassCodeVisitor(className);

		visit(findClassCodeVisitor);

		return findClassCodeVisitor.iterator().next();
	}

	public byte[] findClass(String className) {
		byte[] byteCode = null;

		for (int index = 0; index < code.length && byteCode == null; index++ ) {
			byteCode = code[index].findClass(className);
		}

		return byteCode;
	}

	public void visit(CodeVisitor codeVisitor) {
		for (int index = 0; index < code.length; index++ ) {
			code[index].visit(codeVisitor);
		}
	}

	public boolean contains(Code code) {
		for (int index = 0; index < this.code.length; index++ ) {
			if (this.code[index].equals(code)) {
				return true;
			}
		}

		return false;
	}
}
