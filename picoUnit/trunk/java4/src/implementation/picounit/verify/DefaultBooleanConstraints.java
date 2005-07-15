/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class DefaultBooleanConstraints extends Constraints
	implements BooleanConstraints {

	public DefaultBooleanConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isTrue() {
		passes(new IsTrue());
	}

	public void isFalse() {
		passes(new IsFalse());
	}

	public void isEqualTo(boolean equalTo) {
		passes(constraintFactory.equalTo(new Boolean(equalTo), modifier(), stringer()));
	}

	public void isDifferentTo(boolean differentTo) {
		passes(constraintFactory.differentTo(new Boolean(differentTo), modifier(), stringer()));
	}
}
