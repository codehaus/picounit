/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.constraint.Constraint;

public class StringOfLengthConstraint extends Constraint<String> {
	private final int length;

	public StringOfLengthConstraint(int length) {
		super();
		
		this.length = length;
	}

	public boolean evaluate(String value) {
		return value.length() == length;
	}

	public String describeFailure() {
		return "is not of length: " + length;
	}

	protected boolean equalsImpl(Object other) {
		StringOfLengthConstraint stringOfLengthConstraint = (StringOfLengthConstraint) other;

		return length == stringOfLengthConstraint.length;
	}

	public String toString() {
		return "StringOfLength: " + length;
	}
}