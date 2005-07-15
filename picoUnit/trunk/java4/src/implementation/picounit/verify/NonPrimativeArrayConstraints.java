/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.ArrayModifier;
import picounit.verify.constraint.ArrayStringer;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.Modifier;
import picounit.verify.constraint.Stringer;

public class NonPrimativeArrayConstraints extends Constraints {
	private final ArrayStringer arrayStringer = new ArrayStringer();

	public NonPrimativeArrayConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(Object[] equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(Object[] differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}

	protected Stringer stringer() {
		return arrayStringer;
	}

	protected Modifier nullModifier() {
		return ArrayModifier.NULL;
	}

	protected ArrayModifier arrayModifier() {
		return (ArrayModifier) super.modifier();
	}
}
