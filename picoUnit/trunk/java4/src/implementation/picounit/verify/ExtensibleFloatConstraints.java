/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.FloatConstraint;

public class ExtensibleFloatConstraints extends NumberConstraints {
	public ExtensibleFloatConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public void isEqualTo(float equalTo) {
		passes(constraintFactory.equalTo(new Float(equalTo), modifier(), stringer()));
	}

	public void isDifferentTo(float differentTo) {
		passes(constraintFactory.differentTo(new Float(differentTo), modifier(), stringer()));
	}

	public void isGreaterThan(float greaterThan) {
		passes(constraintFactory.greaterThan(new Float(greaterThan), modifier()));
	}

	public void isGreaterThanOrEqualTo(float greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(new Float(greaterThanOrEqualTo), modifier()));
	}

	public void isLessThan(float lessThan) {
		passes(constraintFactory.lessThan(new Float(lessThan), modifier()));
	}

	public void isLessThanOrEqualTo(float lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(new Float(lessThanOrEqualTo), modifier()));
	}

	public void passes(FloatConstraint floatConstraint) {
		passes(createDelegate(float.class, floatConstraint));
	}
}
