/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.IntegerConstraint;

public class ExtensibleIntegerConstraints extends NumberConstraints {

	public ExtensibleIntegerConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(int equalTo) {
		passes(constraintFactory.equalTo(new Integer(equalTo), modifier(), stringer()));
	}

	public void isDifferentTo(int differentTo) {
		passes(constraintFactory.differentTo(new Integer(differentTo), modifier(), stringer()));
	}

	public void isGreaterThan(int greaterThan) {
		passes(constraintFactory.greaterThan(new Integer(greaterThan), modifier()));
	}

	public void isGreaterThanOrEqualTo(int greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(new Integer(greaterThanOrEqualTo), modifier()));
	}

	public void isLessThan(int lessThan) {
		passes(constraintFactory.lessThan(new Integer(lessThan), modifier()));
	}

	public void isLessThanOrEqualTo(int lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(new Integer(lessThanOrEqualTo), modifier()));
	}

	public void passes(IntegerConstraint integerConstraint) {
		passes(createDelegate(int.class, integerConstraint));
	}
}
