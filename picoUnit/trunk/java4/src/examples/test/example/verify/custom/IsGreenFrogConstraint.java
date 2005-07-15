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

public class IsGreenFrogConstraint extends Constraint {
	public boolean evaluate(Object object) {
		Frog frog = (Frog) object;

		return frog.isColor(Color.GREEN);
	}

	public String describeFailure() {
		return "is not green";
	}
}