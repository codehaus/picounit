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

public class PrimativeArrayConstraints extends Constraints {
	private final PrimativeArrayStringer primativeArrayStringer = new PrimativeArrayStringer();

	public PrimativeArrayConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(Object equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(Object differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}
	
	protected Stringer stringer() {
		return primativeArrayStringer;
	}

	protected void setDelta(Number delta) {
		setModifier(new ArrayModifierImpl(new DeltaModifier(delta), Delta.class));
	}
}
