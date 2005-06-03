/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

import picounit.IntegerConstraints;

public class JMockIntegerConstraints implements IntegerConstraints {
	private final BasicConstraints basicConstraints;
	private final ConstraintStore constraintStore;

	public JMockIntegerConstraints(ConstraintStore constraintStore) {
		this(constraintStore, new BasicConstraints());
	}

	public JMockIntegerConstraints(ConstraintStore constraintStore, BasicConstraints basicConstraints) {
		this.constraintStore = constraintStore;
		this.basicConstraints = basicConstraints;
	}

	public int anInteger() {
		return constraintStore.putInteger(basicConstraints.isAnything());
	}

	public int oneOf(int[] oneOf) {
		return constraintStore.putInteger(isEqual(oneOf));
	}

	public int notEqualTo(int notEqual) {
		return constraintStore.putInteger(basicConstraints.isNotEqualTo(new Integer(notEqual)));
	}
	
	public int neitherOf(int[] neitherOf) {
		return constraintStore.putInteger(basicConstraints.not(isEqual(neitherOf)));
	}

	public int lessThan(int upperLimit) {
		return constraintStore.putInteger(basicConstraints.isLessThan(new Integer(upperLimit)));
	}

	public int lessThanOrEqualTo(int upperLimit) {
		return constraintStore.putInteger(basicConstraints.isLessThanOrEqualTo(new Integer(upperLimit)));
	}

	public int greaterThan(int lowerLimit) {
		return constraintStore.putInteger(basicConstraints.isGreaterThan(new Integer(lowerLimit)));
	}

	public int greaterThanOrEqualTo(int lowerLimit) {
		return constraintStore.putInteger(basicConstraints.isGreaterThanOrEqualTo(new Integer(lowerLimit)));
	}

	public int between(int lowerLimit, int upperLimit) {
		return constraintStore.putInteger(basicConstraints.isBetween(new Integer(lowerLimit), new Integer(upperLimit)));
	}

	public int notBetween(int lowerLimit, int upperLimit) {
		return constraintStore.putInteger(basicConstraints.isNotBetween(new Integer(lowerLimit), new Integer(upperLimit)));
	}

	private Constraint isEqual(int[] oneOf) {
		Constraint[] equals = new Constraint[oneOf.length];

		for (int index = 0; index < oneOf.length; index++ ) {
			equals[index] = basicConstraints.isEqualTo(new Integer(oneOf[index]));
		}

		return new ExtendedOr(equals);
	}
}
