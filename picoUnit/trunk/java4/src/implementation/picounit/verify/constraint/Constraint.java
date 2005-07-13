/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public abstract class Constraint<T> {
	abstract public boolean evaluate(T value);
	abstract public String describeFailure();

	public boolean equals(Object object) {
		if (object == null || !getClass().equals(object.getClass())) {
			return false;
		}
		
		Constraint other = (Constraint) object;

		return equalsImpl(other);
	}

	protected boolean equalsImpl(Object value) {
		return this == value;
	}
}
