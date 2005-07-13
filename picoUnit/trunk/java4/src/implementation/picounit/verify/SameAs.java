/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Constraint;

public class SameAs extends Constraint {
	private final Object sameAs;

	public SameAs(Object sameAs) {
		this.sameAs = sameAs;
	}

	public boolean evaluate(Object value) {
		return value == sameAs;
	}

	public String describeFailure() {
		return "is not the same as <" + sameAs + ">";
	}
}
