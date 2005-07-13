/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.constraint.Constraint;

import java.awt.Color;

public class IsNotGreenFrogConstraint extends Constraint<Frog> {
	public IsNotGreenFrogConstraint() {
		super();
	}

	public boolean evaluate(Frog frog) {
		return !frog.isColor(Color.GREEN);
	}

	public String describeFailure() {
		return "is green";
	}
}
