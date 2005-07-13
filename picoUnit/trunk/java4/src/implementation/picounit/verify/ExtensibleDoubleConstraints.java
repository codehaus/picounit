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

public class ExtensibleDoubleConstraints<ConstraintsStage>
	extends NumberConstraints<Double, ConstraintsStage> {

	public ExtensibleDoubleConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public ConstraintsStage withDelta(double delta) {
		return setDelta(delta);
	}

	public void isEqualTo(double equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(double differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}

	public void isGreaterThan(double greaterThan) {
		passes(constraintFactory.greaterThan(greaterThan, modifier()));
	}

	public void isGreaterThanOrEqualTo(double greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(greaterThanOrEqualTo, modifier()));
	}

	public void isLessThan(double lessThan) {
		passes(constraintFactory.lessThan(lessThan, modifier()));
	}

	public void isLessThanOrEqualTo(double lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(lessThanOrEqualTo, modifier()));
	}

	public void passes(DoubleConstraint doubleConstraint) {
		passes(createDelegate(double.class, doubleConstraint));
	}
}
