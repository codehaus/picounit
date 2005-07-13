/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import picounit.Mocker;
import picounit.Test;
import picounit.verify.Constraints;

public abstract class ConstraintsTest<T, M> implements Test {
	private Evaluator evaluator;
	private Constraint<T> constraint;

	public void mock(Evaluator evaluator, Constraint<T> constraint) {
		this.evaluator = evaluator;
		this.constraint = constraint;
	}

	public void testDelegatesConstraintToEvaluator(Mocker should) {
		shouldCall:
			evaluator.evaluate(constraint);

		should.expectAboveWhenTheFollowingOccurs();

		constraints(evaluator).passes(constraint);
	}
	
	protected final void evaluate(Constraint<T> constraint) {
		evaluator.evaluate(constraint);
	}

	protected final Evaluator evaluator() {
		return evaluator;
	}

	abstract protected Constraints<T, M> constraints(Evaluator evaluator);
}
