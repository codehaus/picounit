/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Environment;
import picounit.PicoUnitException;

import java.io.File;
import java.io.IOException;

public class EnvironmentImpl implements Environment {
	private File tempDirectory;
	
	public File getRootTempDirectory() {
		if (tempDirectory == null) {
			tempDirectory = createTempDirectory("picoUnit", null);	
		}

		return tempDirectory;
	}

	public File createTempFile(String fileName) {
		try {
			return File.createTempFile(fileName + ".", null, getRootTempDirectory());
		}
		catch (IOException ioException) {
			throw new PicoUnitException(ioException);
		}
	}

	public File createTempDirectory(String directoryName) {
		return createTempDirectory(directoryName, getRootTempDirectory());
	}

	private File createTempDirectory(String directoryName, File createIn) {
		try {
			File tempFile = File.createTempFile(directoryName, "", createIn);

			tempFile.delete();

			File tempDirectory = new File(tempFile + "/"); 

			tempDirectory.mkdir();
			
			return tempDirectory;
		}
		catch (IOException ioException) {
			throw new PicoUnitException(ioException);
		}
	}
}
