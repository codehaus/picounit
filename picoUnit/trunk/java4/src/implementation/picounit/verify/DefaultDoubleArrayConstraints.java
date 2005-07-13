/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class DefaultDoubleArrayConstraints extends PrimativeArrayConstraints<double[], double[]>
	implements DoubleArrayConstraints {

	public DefaultDoubleArrayConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public DoubleArrayConstraintsDeltaKnown withDelta(double delta) {
		setDelta(delta);

		return this;
	}

	public void contains(double contains) {
		passes(constraintFactory.primativeArrayContains(contains, modifier()));
	}

	public void doesNotContain(double doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(doesNotContain));
	}
}
