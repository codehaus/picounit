/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.DefaultDoubleConstraints;
import picounit.verify.constraint.DoubleConstraint;
import picounit.verify.constraint.Evaluator;

public class CustomDoubleConstraints extends DefaultDoubleConstraints {
	public static class IsNotSmallDouble implements DoubleConstraint {
		public boolean evaluate(double value) {
			return Math.abs(value) >= 0.1; 
		}

		public String describeFailure() {
			return "is small";
		}
	}

	public CustomDoubleConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isNotSmall() {
		passes(new IsNotSmallDouble());
	}
}
