/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class Explanation {
	private final Evaluator evaluator;

	public Explanation(Evaluator evaluator) {
		this.evaluator = evaluator;
	}

	public void setReason(String reason) {
		evaluator.setReason(reason);
	}

	protected final void setValue(Object value) {
		evaluator.setValue(value);
	}
}
