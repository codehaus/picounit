/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.ComparesTo;
import picounit.verify.constraint.Constraint;
import picounit.verify.constraint.DifferentTo;
import picounit.verify.constraint.EqualTo;
import picounit.verify.constraint.Modifier;
import picounit.verify.constraint.PrimativeArrayContains;
import picounit.verify.constraint.PrimativeArrayDoesNotContain;
import picounit.verify.constraint.Stringer;

public class ConstraintFactory {
	public Constraint primativeArrayContains(Object contains, Modifier modifier) {
		return new PrimativeArrayContains(contains, modifier);
	}
	
	// TODO: Add modifier
	protected Constraint primativeArrayDoesNotContain(Object doesNotContain) {
		return new PrimativeArrayDoesNotContain(doesNotContain);
	}

	public Constraint equalTo(Object equalTo, Modifier modifier, Stringer stringer) {
		return new EqualTo(equalTo, modifier, stringer);
	}

	public Constraint differentTo(Object equalTo, Modifier modifier, Stringer stringer) {
		return new DifferentTo(equalTo, modifier, stringer);
	}
	
	public Constraint greaterThan(Object greaterThan, Modifier modifier) {
		return ComparesTo.greaterThan(greaterThan, modifier);
	}

	public Constraint greaterThanOrEqualTo(Object greaterThanOrEqualTo, Modifier modifier) {
		return ComparesTo.greaterThanOrEqualTo(greaterThanOrEqualTo, modifier);
	}

	public Constraint lessThan(Object lessThan, Modifier modifier) {
		return ComparesTo.lessThan(lessThan, modifier);
	}

	public Constraint lessThanOrEqualTo(Object lessThanOrEqualTo, Modifier modifier) {
		return ComparesTo.lessThanOrEqualTo(lessThanOrEqualTo, modifier);
	}
}
