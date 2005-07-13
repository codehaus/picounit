/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.finder.FileSystem;
import picounit.impl.fileSystem.StartingClass;
import previous.picounit.Test;
import previous.picounit.Verify;

import java.io.File;

public class FileSystemTest implements Test {	
	private final FileSystem fileSystem = new FileSystem();
	
	public void testGetSiblingFiles(Verify verify) {
		File[] siblingFiles = fileSystem.getSiblingFiles(StartingClass.class);

		verify.that(siblingFiles).contains(file("SiblingClass.class"));
		verify.that(siblingFiles).contains(file("SiblingNonClass.txt"));
		verify.that(siblingFiles).contains(file("StartingClass.class"));
	}

	public void testGetSourceRoot(Verify verify) {
		verify.that(fileSystem.getSourceRoot(getClass()))
			.isEqualTo(sourceRoot());
	}

	public void testRelativeFile(Verify verify) {
		verify.that(fileSystem.getRelativeFile(thisPackageFile(), thisRelativeFileName()))
			.isEqualTo(thisClassFile());
		verify.that(fileSystem.getRelativeFile(sourceRoot(), getClass().getName().replace('.', File.separatorChar) + ".class"))
			.isEqualTo(thisClassFile());
	}
	
	private String thisRelativeFileName() {
		String className = getClass().getName();

		return className.substring(className.lastIndexOf('.')  + 1) + ".class"; 
	}

	private File file(String fileName) {
		return new File(StartingClass.class.getResource(fileName).getFile());
	}

	private File sourceRoot() {
		return thisPackageFile().getParentFile().getParentFile();
	}

	private File thisPackageFile() {
		return thisClassFile().getParentFile();
	}

	private File thisClassFile() {
		return new File(getClass().getResource(thisRelativeFileName()).getFile());
	}
}
