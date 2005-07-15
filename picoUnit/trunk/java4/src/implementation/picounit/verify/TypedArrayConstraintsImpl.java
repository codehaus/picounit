/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.TypedArrayConstraints;
import picounit.verify.constraint.ArrayModifier;
import picounit.verify.constraint.ArrayStringer;
import picounit.verify.constraint.ContainsElement;
import picounit.verify.constraint.DoesNotContainElement;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.Modifier;
import picounit.verify.constraint.Stringer;

public class TypedArrayConstraintsImpl extends Constraints
	implements TypedArrayConstraints {

	private final Stringer arrayStringer = new ArrayStringer();

	public TypedArrayConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}
	
	public void isEqualTo(Object[] equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferenTo(Object[] differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));		
	}

	public void contains(Object element) {
		passes(new ContainsElement(element));
	}
	
	public void doesNotContain(Object doesNotContain) {
		passes(new DoesNotContainElement(doesNotContain, arrayModifier()));
	}
	
	public void isTheSameAs(Object[] sameAs) {
		passes(new SameAs(sameAs));
	}
	
	public void isNotTheSameAs(Object[] notTheSameAs) {
		passes(new NotSameAs(notTheSameAs));
	}
	
	public void isNull() {
		passes(new IsNull());
	}
	
	public void isNotNull() {
		passes(new IsNotNull());
	}
	
	protected Stringer stringer() {
		return arrayStringer;
	}
	
	protected Modifier nullModifier() {
		return ArrayModifier.NULL;
	}

	protected ArrayModifier arrayModifier() {
		return (ArrayModifier) super.modifier();
	}
}
