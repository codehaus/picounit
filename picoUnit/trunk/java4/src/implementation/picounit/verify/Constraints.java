/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Constraint;
import picounit.verify.constraint.DelegatingConstraint;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.Modifier;
import picounit.verify.constraint.Stringer;

public class Constraints {
	private final Evaluator evaluator;
	private Modifier modifier;
	protected final ConstraintFactory constraintFactory;

	public Constraints(Evaluator evaluator) {
		this.evaluator = evaluator;

		setModifier(nullModifier());
		
		this.constraintFactory = new ConstraintFactory();
	}

	public final void passes(Constraint constraint) {
		setModifier(nullModifier());
		
		evaluator.evaluate(constraint, stringer());
	}

	public final void passes(Constraint constraint, Stringer stringer) {
		evaluator.evaluate(constraint, stringer);
	}

	protected final Constraint createDelegate(Class type, Object constraint) {
		return new DelegatingConstraint(type, constraint);
	}

	protected final void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	protected Modifier modifier() {
		return modifier;
	}
	
	protected Modifier nullModifier() {
		return Modifier.NULL;
	}
	
	protected Stringer stringer() {
		return Stringer.DEFAULT;
	}
}
