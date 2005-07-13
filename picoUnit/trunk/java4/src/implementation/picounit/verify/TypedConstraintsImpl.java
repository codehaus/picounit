/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.TypedConstraints;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.InstanceOf;
import picounit.verify.constraint.NotInstanceOf;

public class TypedConstraintsImpl extends Constraints<Object, Object>
	implements TypedConstraints<Object> {

	public TypedConstraintsImpl(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(Object equalTo) {
		passes(constraintFactory.equalTo(equalTo, modifier(), stringer()));
	}

	public void isDifferentTo(Object differentTo) {
		passes(constraintFactory.differentTo(differentTo, modifier(), stringer()));
	}
	
	public void isAnInstanceOf(Class instanceOf) {
		passes(new InstanceOf(instanceOf));
	}
	
	public void isNotAnInstanceOf(Class notInstanceOf) {
		passes(new NotInstanceOf(notInstanceOf));
	}
	
	@SuppressWarnings("unchecked")
	public void isTheSameAs(Object sameAs) {
		passes(new SameAs(sameAs));
	}

	@SuppressWarnings("unchecked")
	public void isNotTheSameAs(Object notTheSameAs) {
		passes(new NotSameAs(notTheSameAs));
	}

	@SuppressWarnings("unchecked")
	public void isNull() {
		passes(new IsNull());
	}

	@SuppressWarnings("unchecked")
	public void isNotNull() {
		passes(new IsNotNull());
	}
//	
//	public void isTheSameAs(Object sameAs) {
//		
//	}
//	
//	public void isNotTheSameAs(Object notTheSameAs) {
//		
//	}
//	
//	public void isAnInstanceOf(Class anInstanceOf) {
//		
//	}
//	
//	public void isNotAndInstanceOf(Class notAnInstanceOf) {
//		
//	}
}
