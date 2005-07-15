/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class NotInstanceOf extends Constraint {
	private final Class notInstanceOf;

	public NotInstanceOf(Class notInstanceOf) {
		this.notInstanceOf = notInstanceOf;
	}

	public boolean evaluate(Object value) {
		return !notInstanceOf.isInstance(value);
	}

	public String describeFailure() {
		return "is an instance of <" + notInstanceOf.getName() + ">";
	}
}
