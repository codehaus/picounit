/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.util.FileReader;
import picounit.verify.constraint.Constraint;

import java.io.File;

public class SameFileContents extends Constraint<File> {
	private final File sameContentsAs;
	private final FileReader fileReader;
	private final ArrayUtil arrayUtil;

	public SameFileContents(File sameContentsAs, FileReader fileReader, ArrayUtil arrayUtil) {
		this.sameContentsAs = sameContentsAs;
		this.fileReader = fileReader;
		this.arrayUtil = arrayUtil;
	}
	
	public boolean evaluate(File value) {
		return (sameContentsAs == null && value == null) ||
			sameContents(sameContentsAs, value);
	}

	// Interestingly by separating the evaluation and the failure description it is harder to report on
	// where the contents are different, the deep information is lost, cache or recalc ?
	public String describeFailure() {
		return "does not contain the same contents as <" + sameContentsAs + ">";
	}

	private boolean sameContents(File first, File second) {
		return (first.isFile() && second.isFile() && sameFileContents(first, second)) ||
			(first.isDirectory() && second.isDirectory() && sameDirectoryContents(first, second));
	}

	private boolean sameFileContents(File first, File second) {
		return arrayUtil.equal(fileReader.readContents(first), fileReader.readContents(second));
	}

	private boolean sameDirectoryContents(File first, File second) {
		File[] firstFiles = first.listFiles();
		File[] secondFiles = second.listFiles();

		if (firstFiles.length != secondFiles.length) {
			return false;
		}

		for (int index = 0; index < firstFiles.length; index++ ) {
			if (!sameContents(firstFiles[index], secondFiles[index])) {
				return false;
			}
		}
		
		return true;
	}
}
