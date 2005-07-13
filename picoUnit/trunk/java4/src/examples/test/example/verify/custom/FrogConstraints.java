/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.constraint.Constraint;
import picounit.verify.constraint.Evaluator;

public class FrogConstraints {
	private final Evaluator evaluator;

	public FrogConstraints(Evaluator evaluator) {
		this.evaluator = evaluator;
	}

	public void isGreen() {
		passes(new IsGreenFrogConstraint());
	}

	public void isNotGreen() {
		passes(new IsNotGreenFrogConstraint());
	}

	public final void passes(Constraint<Frog> frogConstraint) {
		evaluator.evaluate(frogConstraint);
	}
}