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

public class ExtensibleLongConstraints extends NumberConstraints {

	public ExtensibleLongConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(long equalTo) {
		passes(constraintFactory.equalTo(new Long(equalTo), modifier(), stringer()));
	}

	public void isDifferentTo(long differentTo) {
		passes(constraintFactory.differentTo(new Long(differentTo), modifier(), stringer()));
	}

	public void isGreaterThan(long greaterThan) {
		passes(constraintFactory.greaterThan(new Long(greaterThan), modifier()));
	}

	public void isGreaterThanOrEqualTo(long greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(new Long(greaterThanOrEqualTo), modifier()));
	}

	public void isLessThan(long lessThan) {
		passes(constraintFactory.lessThan(new Long(lessThan), modifier()));
	}

	public void isLessThanOrEqualTo(long lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(new Long(lessThanOrEqualTo), modifier()));
	}

	public void passes(LongConstraint longConstraint) {
		passes(createDelegate(long.class, longConstraint));
	}
}
