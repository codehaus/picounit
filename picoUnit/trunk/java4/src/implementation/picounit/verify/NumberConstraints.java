/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.DeltaModifier;
import picounit.verify.constraint.Evaluator;

public class NumberConstraints extends Constraints {
	protected Object constraintsStage;

	public NumberConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public void setDelta(Number delta) {
		setModifier(new DeltaModifier(delta));
	}
}
