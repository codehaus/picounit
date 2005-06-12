/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.util.PackageUtil;

import java.io.File;
import java.util.StringTokenizer;

public class FileSystem {
	private static final String CLASS_EXTENSION = ".class";
	private final PackageUtil packageUtil = new PackageUtil();

	public File[] getSiblingFiles(Class aClass) {
		return getDirectoryContaining(aClass).listFiles();
	}

	public File getDirectoryContaining(Class aClass) {
		return getClassFile(aClass).getParentFile();
	}

	public File getSourceRoot(Class aClass) {
		File file = getClassFile(aClass);

		for (int sourceDepth = getSourceDepth(aClass); sourceDepth > 0; sourceDepth--) {
			file = file.getParentFile();
		}

		return file;
	}

	public String getClassName(File classFile, Class startingClass) {
		return getClassName(classFile, getSourceRoot(startingClass));
	}

	public String getClassName(File classFile, File sourceRoot) {
		String relativeClassFile = classFile.getAbsolutePath().substring(sourceRoot.getAbsolutePath().length() + 1);

		String className = relativeClassFile.substring(0, relativeClassFile.length() - CLASS_EXTENSION.length()).replace(File.separatorChar, '.');

		return className;
	}

	public boolean isClass(File file) {
		return file.isFile() && file.getAbsolutePath().endsWith(CLASS_EXTENSION);
	}

	public File[] listFiles(File file) {
		return file.isDirectory() ? file.listFiles() : new File[0];
	}

	public File getRelativeFile(File relativeTo, String fileName) {
		return new File(relativeTo.getAbsoluteFile().toString() + File.separatorChar + fileName);
	}
	
	public String getRelativeFileName(File relativeTo, File file) {
		String relativeToPath = relativeTo.getAbsolutePath();
		String filePath = file.getAbsolutePath();

		return filePath.substring(relativeToPath.length());
	}

	public File getClassFile(Class aClass) {
		return new File(aClass.getResource("/" + getRelativeClassFileName(aClass)).getFile());
	}
	
	public boolean containsClass(File sourceRoot, String className) {
		return getRelativeFile(sourceRoot, getRelativeClassFileName(className)).exists();
	}

	private int getSourceDepth(Class aClass) {
		String packageName = packageUtil.getPackageName(aClass);

		return packageName.equals(packageUtil.defaultPackage()) ? 1 : 1 + new StringTokenizer(packageName, ".").countTokens();
	}

	public String getRelativeClassFileName(Class aClass) {
		return getRelativeClassFileName(aClass.getName());
	}

	private String getRelativeClassFileName(String className) {
		return className.replace('.', '/') + ".class";
	}

	public boolean equals(Object object) {
		if (object == null || !getClass().equals(object.getClass())) {
			return false;
		}

		return true;
	}

	public int hashCode() {
		return getClass().hashCode();
	}
}
