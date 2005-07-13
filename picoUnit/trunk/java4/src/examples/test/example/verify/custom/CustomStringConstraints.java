/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.ExtensibleStringConstraints;
import picounit.verify.StringConstraints;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.ModifiableConstraint;

public class CustomStringConstraints
	extends ExtensibleStringConstraints<CustomStringConstraintsStage> 
	implements StringConstraints, CustomStringConstraintsStage {

	public CustomStringConstraints(Evaluator evaluator) {
		super(evaluator);

		setConstraintsStage(this);
	}

	public void isOfLength(int length) {
		passes(new StringOfLengthConstraint(length));
	}

	public void isAPalindrome() {
		passes(new IsPalindrome());
	}

	public void isEqualToBOOM() {
		passes(new ModifiableConstraint<String, String>(modifier()){
			public boolean evaluate(String value) {
				return modify(value).equals(modify("BOOM"));
			}

			public String describeFailureImpl() {
				return "does not equal <BOOM>";
			}
		});
	}
}