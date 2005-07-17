/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class ShortArrayConstraintsImpl extends PrimativeArrayConstraints
	implements ShortArrayConstraints {

	public ShortArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}
	
	public ShortArrayConstraintsDeltaKnown withDelta(short delta) {
		setDelta(new Short(delta));

		return this;
	}
	
	public void isEqualTo(short[] equalTo) {
		super.isEqualTo(equalTo);
	}
	
	public void isDifferentTo(short[] differentTo) {
		super.isDifferentTo(differentTo);
	}
	
	public void contains(short contains) {
		passes(constraintFactory.primativeArrayContains(new Short(contains), modifier()));
	}
	
	public void doesNotContain(short doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(new Short(doesNotContain), modifier()));
	}
}
