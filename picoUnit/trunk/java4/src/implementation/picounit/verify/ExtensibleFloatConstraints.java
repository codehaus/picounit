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

public class ExtensibleFloatConstraints<ConstraintsStage>
	extends NumberConstraints<Float, ConstraintsStage> {

	public ExtensibleFloatConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public ConstraintsStage withDelta(float delta) {
		return setDelta(delta);
	}

	public void isEqualTo(float equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(float differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}

	public void isGreaterThan(float greaterThan) {
		passes(constraintFactory.greaterThan(greaterThan, modifier()));
	}

	public void isGreaterThanOrEqualTo(float greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(greaterThanOrEqualTo, modifier()));
	}

	public void isLessThan(float lessThan) {
		passes(constraintFactory.lessThan(lessThan, modifier()));
	}

	public void isLessThanOrEqualTo(float lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(lessThanOrEqualTo, modifier()));
	}

	public void passes(FloatConstraint floatConstraint) {
		passes(createDelegate(float.class, floatConstraint));
	}
}
