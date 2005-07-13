/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.util.FileReader;
import picounit.verify.constraint.Evaluator;

import java.io.File;

public class DefaultFileConstraints extends Constraints<File, File>
	implements FileConstraints {

	private ArrayUtil arrayUtil = new ArrayUtil();
	private FileReader fileReader = new FileReader();

	public DefaultFileConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(File equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(File differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}

	public void isGreaterThan(File greaterThan) {
		passes(constraintFactory.greaterThan(greaterThan, modifier()));
	}

	public void isGreaterThanOrEqualTo(File greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(greaterThanOrEqualTo, modifier()));
	}

	public void isLessThan(File lessThan) {
		passes(constraintFactory.lessThan(lessThan, modifier()));
	}

	public void isLessThanOrEqualTo(File lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(lessThanOrEqualTo, modifier()));
	}

	public void isTheSameTypeAs(File sameTypeAs) {
		passes(new SameFileType(sameTypeAs));
	}

	public void hasTheSameContentsAs(File sameContentsAs) {
		passes(new SameFileContents(sameContentsAs, fileReader, arrayUtil));
	}
}
