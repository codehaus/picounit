/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.LongConstraint;


public class ExtensibleLongConstraints<ConstraintsStage>
	extends NumberConstraints<Long, ConstraintsStage> {

	public ExtensibleLongConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public ConstraintsStage withDelta(long delta) {
		return setDelta(delta);
	}

	public void isEqualTo(long equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(long differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}

	public void isGreaterThan(long greaterThan) {
		passes(constraintFactory.greaterThan(greaterThan, modifier()));
	}

	public void isGreaterThanOrEqualTo(long greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(greaterThanOrEqualTo, modifier()));
	}

	public void isLessThan(long lessThan) {
		passes(constraintFactory.lessThan(lessThan, modifier()));
	}

	public void isLessThanOrEqualTo(long lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(lessThanOrEqualTo, modifier()));
	}

	public void passes(LongConstraint longConstraint) {
		passes(createDelegate(long.class, longConstraint));
	}
}
