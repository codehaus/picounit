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

public class DefaultCharacterConstraints extends Constraints
	implements CharacterConstraints {

	public DefaultCharacterConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public CharacterConstraintsCaseKnown ignoringCase() {
		setModifier(CharacterCaseModifier.INSTANCE);
		
		return this;
	}

	public void isEqualTo(char equalTo) {
		passes(constraintFactory.equalTo(new Character(equalTo), modifier(), stringer()));
	}

	public void isDifferentTo(char differentTo) {
		passes(constraintFactory.differentTo(new Character(differentTo), modifier(), stringer()));
	}
	
	public void isGreaterThan(char greaterThan) {
		passes(constraintFactory.greaterThan(new Character(greaterThan), modifier()));
	}

	public void isGreaterThanOrEqualTo(char greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(new Character(greaterThanOrEqualTo), modifier()));
	}

	public void isLessThan(char lessThan) {
		passes(constraintFactory.lessThan(new Character(lessThan), modifier()));
	}

	public void isLessThanOrEqualTo(char lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(new Character(lessThanOrEqualTo), modifier()));
	}

	public void passes(CharacterConstraint characterConstraint) {
		passes(createDelegate(char.class, characterConstraint));
	}
}
