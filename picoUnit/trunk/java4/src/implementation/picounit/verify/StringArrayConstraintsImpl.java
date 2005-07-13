/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.ArrayModifier;
import picounit.verify.constraint.ArrayModifierImpl;
import picounit.verify.constraint.StringCaseModifier;
import picounit.verify.constraint.ContainsElement;
import picounit.verify.constraint.DoesNotContainElement;
import picounit.verify.constraint.Evaluator;

public class StringArrayConstraintsImpl extends NonPrimativeArrayConstraints<String>
	implements StringArrayConstraints {

	public StringArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}

	public StringArrayConstraintsCaseKnown ignoringCase() {
		setModifier(ARRAY_CASE_MODIFIER);
		
		return this;
	}

	@SuppressWarnings("unchecked")
	public void contains(String contains) {
		passes(new ContainsElement(contains, modifier()));
	}

	@SuppressWarnings("unchecked")
	public void doesNotContain(String doesNotContain) {
		passes(new DoesNotContainElement(doesNotContain, modifier()));
	}

	private static final ArrayModifier<String[], String[]> ARRAY_CASE_MODIFIER =
		new ArrayModifierImpl<String[], String[]>(StringCaseModifier.INSTANCE, String.class);
}

