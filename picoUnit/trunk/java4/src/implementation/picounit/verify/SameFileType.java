/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import java.io.File;

import picounit.verify.constraint.Constraint;

public class SameFileType extends Constraint<File> {
	private final File sameTypeAs;

	public SameFileType(File sameTypeAs) {
		this.sameTypeAs = sameTypeAs;
	}

	public boolean evaluate(File value) {
		return (sameTypeAs == null && value == null) ||
			(sameTypeAs.isFile() && value.isFile()) ||
			(sameTypeAs.isDirectory() && value.isDirectory());
	}

	public String describeFailure() {
		return "is not a " + getType();
	}

	private String getType() {
		if (sameTypeAs == null) {
			return "null file";
		}
		else if (sameTypeAs.isFile()) {
			return "file";
		}
		else {
			return "directory";
		}
	}
}
