/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import java.io.File;

public class ClassFinder {
	private final FileSystem fileSystem;

	public ClassFinder() {
		this(new FileSystem());
	}

	public ClassFinder(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}
	
	public void findClasses(Class startingClass, FindAction findAction) {
		findClasses(startingClass, new AlwaysPassDirectoryCondition(), findAction);
	}

	public void findClasses(Class startingClass, DirectoryCondition directoryCondition,
		FindAction findAction) {

		findClasses(fileSystem.getDirectoryContaining(startingClass),
			fileSystem.getSourceRoot(startingClass), directoryCondition, findAction);
	}

	public void findClasses(File startFrom, File sourceRoot, DirectoryCondition directoryCondition,
		FindAction findAction) {

		findClasses(startFrom.listFiles(), sourceRoot, directoryCondition, findAction);
	}

	private void findClasses(File[] files, File sourceRoot, DirectoryCondition directoryCondition,
		FindAction findAction) {

		for (int index = 0; index < files.length; index++) {
			File siblingFile = files[index];

			if (fileSystem.isClass(siblingFile)) {
				findAction.perform(fileSystem.getClassName(siblingFile, sourceRoot));
			}
		}

		for (int index = 0; index < files.length; index++) {
			if (files[index].isDirectory() && directoryCondition.matches(files[index])) {
				File[] childFiles = fileSystem.listFiles(files[index]);

				if (childFiles.length != 0) {
					findClasses(childFiles, sourceRoot, directoryCondition, findAction);
				}
			}
		}
	}
}
