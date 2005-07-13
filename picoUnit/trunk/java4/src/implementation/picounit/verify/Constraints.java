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

public class Constraints<T, M> {
	private final Evaluator evaluator;
	private Modifier<T, M> modifier;
	protected final ConstraintFactory<T> constraintFactory;

	@SuppressWarnings("unchecked")
	public Constraints(Evaluator evaluator) {
		this.evaluator = evaluator;

		setModifier(nullModifier());
		
		this.constraintFactory = new ConstraintFactory();
	}

	@SuppressWarnings("unchecked")
	public final void passes(Constraint<T> constraint) {
		setModifier(nullModifier());
		
		evaluator.evaluate(constraint, stringer());
	}

	public final void passes(Constraint<T> constraint, Stringer<T> stringer) {
		evaluator.evaluate(constraint, stringer);
	}

	protected final Constraint<T> createDelegate(Class<T> type, Object constraint) {
		return new DelegatingConstraint<T>(type, constraint);
	}

	protected final void setModifier(Modifier<T, M> modifier) {
		this.modifier = modifier;
	}

	protected Modifier<T, M> modifier() {
		return modifier;
	}
	
	@SuppressWarnings("unchecked")
	protected Modifier<T, M> nullModifier() {
		return Modifier.NULL;
	}
	
	@SuppressWarnings("unchecked")
	protected Stringer<T> stringer() {
		return Stringer.DEFAULT;
	}
}
