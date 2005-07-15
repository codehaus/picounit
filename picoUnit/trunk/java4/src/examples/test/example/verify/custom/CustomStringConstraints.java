/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.ExtensibleStringConstraints;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.ModifiableConstraint;
import picounit.verify.constraint.StringCaseModifier;

public class CustomStringConstraints extends ExtensibleStringConstraints implements CustomStringConstraintsStage {

	public CustomStringConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public CustomStringConstraintsStage ignoringCase() {
		setModifier(StringCaseModifier.INSTANCE);
		
		return this;
	}

	public void isOfLength(int length) {
		passes(new StringOfLengthConstraint(length));
	}

	public void isAPalindrome() {
		passes(new IsPalindrome());
	}

	public void isEqualToBOOM() {
		passes(new ModifiableConstraint(modifier()){
			public boolean evaluate(Object value) {
				return modify(value).equals(modify("BOOM"));
			}

			public String describeFailureImpl() {
				return "does not equal <BOOM>";
			}
		});
	}
}