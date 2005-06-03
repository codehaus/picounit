/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import picounit.util.FileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ClassDirectory implements Code {
	private final FileReader fileReader = new FileReader();
	private final String directory;

	public ClassDirectory(String directory) {
		this.directory = directory;
	}

	public byte[] findClass(String className) {
		byte[] byteCode = null;

		if (containsClass(className)) {
			byteCode = fileReader.readContents(classFile(className)); 
		}

		return byteCode;
	}

	private File classFile(String className) {
		return new File(directory + File.separator + className.replace('.', File.separatorChar) + ".class");
	}

	public void visit(CodeVisitor codeVisitor) {
		codeVisitor.visit(this);
	}

	public InputStream openFile(String fileName) throws FileNotFoundException {
		return new FileInputStream(directory + File.separator + fileName);
	}

	public boolean containsClass(String className) {
		return classFile(className).exists();
	}
	
	public boolean equals(Code code) {
		if (code == null || !code.getClass().equals(getClass())) {
			return false;
		}

		ClassDirectory other = (ClassDirectory) code;

		return directory.equals(other.directory);
	}
	
	public String toString() {
		return "ClassDirectory: " + directory;
	}
}
