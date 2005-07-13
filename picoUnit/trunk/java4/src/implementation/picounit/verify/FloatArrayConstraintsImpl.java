/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class FloatArrayConstraintsImpl extends PrimativeArrayConstraints<float[], float[]>
	implements FloatArrayConstraints {

	public FloatArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}
	
	public FloatArrayConstraintsDeltaKnown withDelta(float delta) {
		setDelta(delta);

		return this;
	}
	
	public void contains(float contains) {
		passes(constraintFactory.primativeArrayContains(contains, modifier()));
	}
	
	public void doesNotContain(float doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(doesNotContain));
	}
}
