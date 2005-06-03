/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;
import org.jmock.core.constraint.StringContains;

import picounit.StringConstraints;

public class JMockStringConstraints implements StringConstraints {
	private final ConstraintStore constraintStore;
	private final BasicConstraints basicConstraints;

	public JMockStringConstraints(ConstraintStore constraintStore) {
		this(constraintStore, new BasicConstraints());
	}
	
	public JMockStringConstraints(ConstraintStore constraintStore, BasicConstraints basicConstraints) {
		this.constraintStore = constraintStore;
		this.basicConstraints = basicConstraints;
	}

	public String aString() {
		return constraintStore.putString(basicConstraints.isAnything());
	}
	
	public String oneOf(String[] oneOf) {
		return constraintStore.putString(isEqual(oneOf));
	}

	public String neitherOf(String[] neitherOf) {
		return constraintStore.putString(basicConstraints.not(isEqual(neitherOf)));
	}

	public String notEqualTo(String notEqual) {
		return constraintStore.putString(basicConstraints.isNotEqualTo(notEqual));
	}

	public String aStringContaining(String toContain) {
		return constraintStore.putString(new StringContains(toContain));
	}

	public String equaTolIgnoringCase(String toEqual) {
		return constraintStore.putString(new StringEqualIgnoreCase(toEqual));
	}
	
	public String aStringMatching(String pattern) {
		return constraintStore.putString(new StringMatching(pattern));
	}
	
	public String aNullString() {
		return constraintStore.putString(basicConstraints.isNull());
	}
	
	public String notANullString() {
		return constraintStore.putString(basicConstraints.isNotNull(String.class));
	}
	
	private Constraint isEqual(String[] oneOf) {
		Constraint[] equals = new Constraint[oneOf.length];

		for (int index = 0; index < oneOf.length; index++ ) {
			equals[index] = basicConstraints.isEqualTo(oneOf[index]);
		}

		return new ExtendedOr(equals);
	}
}
