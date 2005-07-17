/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class LongArrayConstraintsImpl extends PrimativeArrayConstraints
	implements LongArrayConstraints {

	public LongArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}
	
	public LongArrayConstraintsDeltaKnown withDelta(long delta) {
		setDelta(new Long(delta));

		return this;
	}
	
	public void isEqualTo(long[] equalTo) {
		super.isEqualTo(equalTo);
	}
	
	public void isDifferentTo(long[] differentTo) {
		super.isDifferentTo(differentTo);
	}
	
	public void contains(long contains) {
		passes(constraintFactory.primativeArrayContains(new Long(contains), modifier()));
	}
	
	public void doesNotContain(long doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(new Long(doesNotContain), modifier()));
	}
}
