/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.ArrayModifierImpl;
import picounit.verify.constraint.CharacterCaseModifier;
import picounit.verify.constraint.Evaluator;

public class CharacterArrayConstraintsImpl extends PrimativeArrayConstraints<char[], char[]>
	implements CharacterArrayConstraints {

	public CharacterArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}

	public CharacterArrayConstraintsCaseKnown ignoringCase() {
		setModifier(new ArrayModifierImpl<char[], char[]>(CharacterCaseModifier.INSTANCE, char.class));

		return this;
	}

	public void contains(char contains) {
		passes(constraintFactory.primativeArrayContains(contains, modifier()));
	}

	public void doesNotContain(char doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(doesNotContain));
	}
}
