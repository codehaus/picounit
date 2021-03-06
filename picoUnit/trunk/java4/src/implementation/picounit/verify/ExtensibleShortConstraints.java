/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.ShortConstraint;

public class ExtensibleShortConstraints
	extends NumberConstraints {

	public ExtensibleShortConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public void isEqualTo(short equalTo) {
		passes(constraintFactory.equalTo(new Short(equalTo), modifier(), stringer()));
	}

	public void isDifferentTo(short differentTo) {
		passes(constraintFactory.differentTo(new Short(differentTo), modifier(), stringer()));
	}

	public void isGreaterThan(short greaterThan) {
		passes(constraintFactory.greaterThan(new Short(greaterThan), modifier()));
	}

	public void isGreaterThanOrEqualTo(short greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(new Short(greaterThanOrEqualTo), modifier()));
	}

	public void isLessThan(short lessThan) {
		passes(constraintFactory.lessThan(new Short(lessThan), modifier()));
	}

	public void isLessThanOrEqualTo(short lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(new Short(lessThanOrEqualTo), modifier()));
	}

	public void passes(ShortConstraint shortConstraint) {
		passes(createDelegate(short.class, shortConstraint));
	}
}
