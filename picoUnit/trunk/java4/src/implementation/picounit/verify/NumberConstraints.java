/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Delta;
import picounit.verify.constraint.DeltaModifier;
import picounit.verify.constraint.Evaluator;

public class NumberConstraints<N extends Number & Comparable<N>, ConstraintsStage>
	extends Constraints<N, Delta> {

	protected ConstraintsStage constraintsStage;

	public NumberConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public ConstraintsStage setDelta(N delta) {
		setModifier(new DeltaModifier<N>(delta));

		return constraintsStage;
	}

	protected void setConstraintsStage(ConstraintsStage constraintsStage) {
		this.constraintsStage = constraintsStage;
	}
}
