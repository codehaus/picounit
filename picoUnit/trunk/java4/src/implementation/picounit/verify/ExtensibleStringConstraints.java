/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.StringConstraint;
import picounit.verify.constraint.StringContains;
import picounit.verify.constraint.StringDoesNotContain;
import picounit.verify.constraint.StringDoesNotMatch;
import picounit.verify.constraint.StringMatches;

public class ExtensibleStringConstraints
	extends Constraints {

	public ExtensibleStringConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(String equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(String differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}

	public void isGreaterThanOrEqualTo(String greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(greaterThanOrEqualTo, modifier()));
	}

	public void isLessThan(String lessThan) {
		passes(constraintFactory.lessThan(lessThan, modifier()));
	}

	public void isLessThanOrEqualTo(String lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(lessThanOrEqualTo, modifier()));
	}
	
	public void isNull() {
		passes(new IsNull());
	}
	
	public void isNotNull() {
		passes(new IsNotNull());
	}

	public void contains(String contains) {
		super.passes(new StringContains(contains, modifier()));
	}

	public void doesNotContain(String doesNotContain) {
		super.passes(new StringDoesNotContain(doesNotContain, modifier()));
	}

	public void matches(String matches) {
		super.passes(new StringMatches(matches, modifier()));
	}

	public void doesNotMatch(String doesNotMatch) {
		super.passes(new StringDoesNotMatch(doesNotMatch, modifier()));
	}

	public void passes(StringConstraint stringConstraint) {
		passes(createDelegate(String.class, stringConstraint));
	}
}
