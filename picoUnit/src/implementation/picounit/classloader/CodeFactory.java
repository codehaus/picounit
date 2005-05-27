/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

public class CodeFactory {
	public Code create(String classPathEntry) {
		if (isJar(classPathEntry)) {
			return new Jar(classPathEntry);
		}
		else {
			return new ClassDirectory(classPathEntry);
		}
	}

	private boolean isJar(String classPathEntry) {
		return classPathEntry.endsWith(".jar") ||
			classPathEntry.endsWith(".zip");
	}
}
