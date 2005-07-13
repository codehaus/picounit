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

public class NonPrimativeArrayConstraints<T> extends Constraints<T[], T[]> {
	private final ArrayStringer<T> arrayStringer = new ArrayStringer<T>();

	public NonPrimativeArrayConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(T ... equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(T ... differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}

	protected Stringer<T[]> stringer() {
		return arrayStringer;
	}

	@SuppressWarnings("unchecked")
	protected Modifier<T[], T[]> nullModifier() {
		return ArrayModifier.NULL;
	}

	protected ArrayModifier<T[], T[]> modifier() {
		return (ArrayModifier<T[], T[]>) super.modifier();
	}
}
