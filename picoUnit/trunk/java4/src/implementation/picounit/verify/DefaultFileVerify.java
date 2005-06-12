/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.FileVerify;
import picounit.Verify;
import picounit.util.FileReader;

import java.io.File;

public class DefaultFileVerify implements FileVerify {
	private final FileReader fileReader;
	private final Verify verify;
	private final ArrayUtil arrayUtil;
	private final StringUtil stringUtil;

	public DefaultFileVerify(FileReader fileReader, ArrayUtil arrayUtil, StringUtil stringUtil, Verify verify) {
		this.fileReader = fileReader;
		this.arrayUtil = arrayUtil;
		this.stringUtil = stringUtil;
		this.verify = verify;
	}
	
	public void sameType(File expected, File actual) {
		if (expected.isFile() != actual.isFile()) {
			verify.fail(expected + " is a " + getType(expected) + ", but " + actual + " is a " + getType(actual));
		}
	}

	public void sameContents(File expected, File actual) {
		sameType(expected, actual);

		if (expected.isFile()) {
			equalFileContents(expected, actual);
		}
		else {
			equalDirectoryContents(expected, actual);
		}
	}

	private void equalFileContents(File expected, File actual) {
		byte[] expectedContents = fileReader.readContents(expected);
		byte[] actualContents = fileReader.readContents(actual);
		if (!arrayUtil.equal(expectedContents, actualContents)) {
			verify.fail("Contents of files difer, expected file name: " + expected + ", actual file name: " + 
				actual);
		}
	}

	private void equalDirectoryContents(File expected, File actual) {
		File[] expectedFiles = expected.listFiles();
		File[] actualFiles = actual.listFiles();

		if (expectedFiles.length != actualFiles.length) {
			verify.fail("expected: <" + stringUtil.toString(expectedFiles) + ">, but was: <" + stringUtil.toString(actualFiles) + ">");
		}

		for (int index = 0; index < expectedFiles.length; index++ ) {
			sameContents(expectedFiles[index], actualFiles[index]);
		}
	}

	private String getType(File file) {
		return file.isFile() ? "file" : "directory";
	}
}
