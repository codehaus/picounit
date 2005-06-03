/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import picounit.PicoUnitException;
import picounit.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Jar implements Code {
	private final JarFile jarFile;
	private final String fileName;

	public Jar(String fileName) {
		this.fileName = fileName;
		this.jarFile = createJarFile(fileName);
	}

	public byte[] findClass(String className) {
		byte[] byteCode = null;

		try {
			JarEntry jarEntry = jarEntry(className);

			if (jarEntry != null) {
				InputStream inputStream = jarFile.getInputStream(jarEntry);
	
				if (inputStream != null) {
					byteCode = new byte[(int) jarEntry.getSize()];

					inputStream.read(byteCode, 0, byteCode.length);
				}
			}

			return byteCode;
		}
		catch (IOException ioException) {
			throw new PicoUnitException(ioException);
		}
	}

	public void visit(CodeVisitor codeVisitor) {
		codeVisitor.visit(this);
	}

	public boolean containsClass(String className) {
		return jarEntry(className) != null;
	}

	public InputStream openFile(String fileName) throws IOException {
		JarEntry jarEntry = jarFile.getJarEntry(Plugin.FILE_NAME);
		
		if (jarEntry != null) {
			return jarFile.getInputStream(jarEntry);
		}
		
		return null;
	}
	
	public boolean equals(Code code) {
		if (code == null || !code.getClass().equals(getClass())) {
			return false;
		}
		
		Jar other = (Jar) code;
		
		return fileName.equals(other.fileName);
	}

	private JarEntry jarEntry(String className) {
		return jarFile.getJarEntry(className.replace('.', '/') + ".class");
	}

	private static JarFile createJarFile(String fileName) {
		try {
			return new JarFile(fileName);
		}
		catch (IOException ioException) {
			throw new PicoUnitException(ioException);
		}
	}

	public String toString() {
		return "Jar: " + fileName;
	}
}
