/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

import picounit.ObjectConstraints;

public class JMockObjectConstraints implements ObjectConstraints {
	private final ConstraintStore constraintStore;
	private final BasicConstraints basicConstraints;

	public JMockObjectConstraints(ConstraintStore constraintStore) {
		this(constraintStore, new BasicConstraints());
	}
	
	public JMockObjectConstraints(ConstraintStore constraintStore, BasicConstraints basicConstraints) {
		this.constraintStore = constraintStore;
		this.basicConstraints = basicConstraints;
	}

	public Object anObject() {
		return constraintStore.putString(basicConstraints.isAnything());
	}	

	public Object aNullObject() {
		return constraintStore.putString(basicConstraints.isNull());
	}

	public Object notANullObject() {
		return constraintStore.putString(basicConstraints.isNotNull(Object.class));
	}

	public Object oneOf(Object ... oneOf) {
		return constraintStore.putString(isEqual(oneOf));
	}
	
	public Object neitherOf(Object ... neitherOf) {
		return constraintStore.putString(basicConstraints.not(isEqual(neitherOf)));
	}

	public Object notEqual(Object notEqual) {
		return constraintStore.putString(basicConstraints.isNotEqualTo(notEqual));
	}
	
	private Constraint isEqual(Object[] oneOf) {
		Constraint[] equals = new Constraint[oneOf.length];

		for (int index = 0; index < oneOf.length; index++ ) {
			equals[index] = basicConstraints.isEqualTo(oneOf[index]);
		}

		return new ExtendedOr(equals);
	}
}
