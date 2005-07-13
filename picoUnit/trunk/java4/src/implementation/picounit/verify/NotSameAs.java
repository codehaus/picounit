/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Constraint;

public class NotSameAs extends Constraint {
	private final Object notTheSameAs;

	public NotSameAs(Object notTheSameAs) {
		this.notTheSameAs = notTheSameAs;
	}

	public boolean evaluate(Object value) {
		return value != notTheSameAs;
	}

	public String describeFailure() {
		return "is the same as <" + notTheSameAs + ">";
	}
}
