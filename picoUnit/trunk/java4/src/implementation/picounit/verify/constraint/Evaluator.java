/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import junit.framework.AssertionFailedError;

public class Evaluator {
	private String reason;

	private Object value;
	
	public void setValue(Object value) {
		this.value = value;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@SuppressWarnings("unchecked")
	public <T> void evaluate(Constraint<T> constraint) {
		evaluate(constraint, Stringer.DEFAULT);
	}

	@SuppressWarnings("unchecked")
	public <T> void evaluate(Constraint<T> constraint, Stringer<T> stringer) {
		T value = (T) this.value;

		if (!constraint.evaluate(value)) {
			throw new AssertionFailedError((prefix() + "<" + stringer.toString(value) + "> " +
				constraint.describeFailure()));
		}
	}

	private String prefix() {
		try { 
			return reason == null ? "" : reason + ", ";
		}
		finally {
			reason = null;
		}
	}
}
