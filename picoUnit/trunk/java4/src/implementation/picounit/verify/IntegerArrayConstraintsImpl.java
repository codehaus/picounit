/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class IntegerArrayConstraintsImpl extends PrimativeArrayConstraints
	implements IntegerArrayConstraints {

	public IntegerArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}
	
	public IntegerArrayConstraintsDeltaKnown withDelta(int delta) {
		setDelta(new Integer(delta));
		
		return this;
	}
	
	public void isEqualTo(int[] equalTo) {
		super.isEqualTo(equalTo);
	}
	
	public void isDifferentTo(int[] differentTo) {
		super.isDifferentTo(differentTo);
	}

	public void contains(int contains) {
		passes(constraintFactory.primativeArrayContains(new Integer(contains), modifier()));
	}
	
	public void doesNotContain(int doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(new Integer(doesNotContain)));
	}
}
