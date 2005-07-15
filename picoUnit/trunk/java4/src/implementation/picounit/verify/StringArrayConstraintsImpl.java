/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.ArrayModifierImpl;
import picounit.verify.constraint.ContainsElement;
import picounit.verify.constraint.DoesNotContainElement;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.Modifier;
import picounit.verify.constraint.StringCaseModifier;

public class StringArrayConstraintsImpl extends NonPrimativeArrayConstraints
	implements StringArrayConstraints {

	public StringArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}

	public StringArrayConstraintsCaseKnown ignoringCase() {
		setModifier(ARRAY_CASE_MODIFIER);
		
		return this;
	}
	
	public void isEqualTo(String[] equalTo) {
		super.isEqualTo(equalTo);
	}
	
	public void isDifferentTo(String[] differentTo) {
		super.isDifferentTo(differentTo);
	}

	public void contains(String contains) {
		passes(new ContainsElement(contains, arrayModifier()));
	}

	public void doesNotContain(String doesNotContain) {
		passes(new DoesNotContainElement(doesNotContain, arrayModifier()));
	}

	private static final Modifier ARRAY_CASE_MODIFIER =
		new ArrayModifierImpl(StringCaseModifier.INSTANCE, String.class);
}

