/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class FloatArrayConstraintsImpl extends PrimativeArrayConstraints
	implements FloatArrayConstraints {

	public FloatArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}
	
	public FloatArrayConstraintsDeltaKnown withDelta(float delta) {
		setDelta(new Float(delta));

		return this;
	}
	
	public void isEqualTo(float[] equalTo) {
		super.isEqualTo(equalTo);
	}
	
	public void isDifferentTo(float[] differentTo) {
		super.isDifferentTo(differentTo);
	}
	
	public void contains(float contains) {
		passes(constraintFactory.primativeArrayContains(new Float(contains), modifier()));
	}
	
	public void doesNotContain(float doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(new Float(doesNotContain), modifier()));
	}
}
