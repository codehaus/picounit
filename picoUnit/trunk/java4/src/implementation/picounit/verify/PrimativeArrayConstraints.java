/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.ArrayModifierImpl;
import picounit.verify.constraint.Delta;
import picounit.verify.constraint.DeltaModifier;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.PrimativeArrayStringer;
import picounit.verify.constraint.Stringer;

public class PrimativeArrayConstraints<T, M> extends Constraints<T, M> {
	private final PrimativeArrayStringer<T> primativeArrayStringer = new PrimativeArrayStringer<T>();

	public PrimativeArrayConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(T equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(T differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}
	
	protected Stringer<T> stringer() {
		return primativeArrayStringer;
	}

	protected void setDelta(Number delta) {
		setModifier(new ArrayModifierImpl<T, M>(new DeltaModifier<Number>(delta), Delta.class));
	}
}
