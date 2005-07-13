/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class ShortArrayConstraintsImpl extends PrimativeArrayConstraints<short[], short[]>
	implements ShortArrayConstraints {

	public ShortArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}
	
	public ShortArrayConstraintsDeltaKnown withDelta(short delta) {
		setDelta(delta);

		return this;
	}
	
	public void contains(short contains) {
		passes(constraintFactory.primativeArrayContains(contains, modifier()));
	}
	
	public void doesNotContain(short doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(doesNotContain));
	}
}
