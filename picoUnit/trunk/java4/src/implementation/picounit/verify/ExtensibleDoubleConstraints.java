/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.DoubleConstraint;
import picounit.verify.constraint.Evaluator;

public class ExtensibleDoubleConstraints extends NumberConstraints {
	public ExtensibleDoubleConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public void isEqualTo(double equalTo) {
		passes(constraintFactory.equalTo(new Double(equalTo), modifier(), stringer()));
	}

	public void isDifferentTo(double differentTo) {
		passes(constraintFactory.differentTo(new Double(differentTo), modifier(), stringer()));
	}

	public void isGreaterThan(double greaterThan) {
		passes(constraintFactory.greaterThan(new Double(greaterThan), modifier()));
	}

	public void isGreaterThanOrEqualTo(double greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(new Double(greaterThanOrEqualTo), modifier()));
	}

	public void isLessThan(double lessThan) {
		passes(constraintFactory.lessThan(new Double(lessThan), modifier()));
	}

	public void isLessThanOrEqualTo(double lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(new Double(lessThanOrEqualTo), modifier()));
	}

	public void passes(DoubleConstraint doubleConstraint) {
		passes(createDelegate(double.class, doubleConstraint));
	}
}
