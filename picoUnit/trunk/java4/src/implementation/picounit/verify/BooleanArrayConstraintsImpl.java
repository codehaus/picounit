/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class BooleanArrayConstraintsImpl extends PrimativeArrayConstraints
	implements BooleanArrayConstraints {

	public BooleanArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}
	
	public void isEqualTo(boolean[] equalTo) {
		super.isEqualTo(equalTo);
	}
	
	public void isDifferentTo(boolean[] differentTo) {
		super.isDifferentTo(differentTo);
	}

	public void contains(boolean contains) {
		passes(constraintFactory.primativeArrayContains(new Boolean(contains), modifier()));
	}

	public void doesNotContain(boolean doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(new Boolean(doesNotContain)));
	}
}
