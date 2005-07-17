/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class DefaultDoubleArrayConstraints extends PrimativeArrayConstraints
	implements DoubleArrayConstraints {

	public DefaultDoubleArrayConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public DoubleArrayConstraintsDeltaKnown withDelta(double delta) {
		setDelta(new Double(delta));

		return this;
	}
	
	public void isEqualTo(double[] equalTo) {
		super.isEqualTo(equalTo);
	}
	
	public void isDifferentTo(double[] differentTo) {
		super.isDifferentTo(differentTo);
	}

	public void contains(double contains) {
		passes(constraintFactory.primativeArrayContains(new Double(contains), modifier()));
	}

	public void doesNotContain(double doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(new Double(doesNotContain), modifier()));
	}
}
