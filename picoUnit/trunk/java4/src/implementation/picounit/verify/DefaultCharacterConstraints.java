/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.CharacterCaseModifier;
import picounit.verify.constraint.CharacterConstraint;
import picounit.verify.constraint.Evaluator;

public class DefaultCharacterConstraints extends Constraints<Character, Character>
	implements CharacterConstraints {

	public DefaultCharacterConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public CharacterConstraintsCaseKnown ignoringCase() {
		setModifier(CharacterCaseModifier.INSTANCE);
		
		return this;
	}

	public void isEqualTo(char equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(char differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}
	
	public void isGreaterThan(char greaterThan) {
		passes(constraintFactory.greaterThan(greaterThan, modifier()));
	}

	public void isGreaterThanOrEqualTo(char greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(greaterThanOrEqualTo, modifier()));
	}

	public void isLessThan(char lessThan) {
		passes(constraintFactory.lessThan(lessThan, modifier()));
	}

	public void isLessThanOrEqualTo(char lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(lessThanOrEqualTo, modifier()));
	}

	public void passes(CharacterConstraint characterConstraint) {
		passes(createDelegate(char.class, characterConstraint));
	}
}
