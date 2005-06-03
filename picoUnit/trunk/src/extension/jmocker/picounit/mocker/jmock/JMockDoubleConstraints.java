/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

import picounit.DoubleConstraints;

public class JMockDoubleConstraints implements DoubleConstraints {
	private final ConstraintStore constraintStore;
	private final BasicConstraints basicConstraints;

	public JMockDoubleConstraints(ConstraintStore constraintStore) {
		this(constraintStore, new BasicConstraints());
	}
	
	public JMockDoubleConstraints(ConstraintStore constraintStore, BasicConstraints basicConstraints) {
		this.constraintStore = constraintStore;
		this.basicConstraints = basicConstraints;
	}

	public double aDouble() {
		return constraintStore.putDouble(basicConstraints.isAnything());
	}
	
	public double oneOf(double[] oneOf) {
		return constraintStore.putDouble(isEqual(oneOf));
	}
	
	public double neitherOf(double[] neitherOf) {
		return constraintStore.putDouble(basicConstraints.not(isEqual(neitherOf)));
	}
	
	public double notEqualTo(double notEqual) {
		return constraintStore.putDouble(basicConstraints.or(
			basicConstraints.isLessThan(new Double(notEqual)),
			basicConstraints.isGreaterThan(new Double(notEqual))));
	}
	
	public double lessThan(double upperLimit) {
		return constraintStore.putDouble(basicConstraints.isLessThan(new Double(upperLimit)));
	}
	
	public double lessThanOrEqualTo(double upperLimit) {
		return constraintStore.putDouble(basicConstraints.isLessThanOrEqualTo(new Double(upperLimit)));
	}
	
	public double greaterThan(double lowerLimit) {
		return constraintStore.putDouble(basicConstraints.isGreaterThan(new Double(lowerLimit)));
	}
	
	public double greaterThanOrEqualTo(double lowerLimit) {
		return constraintStore.putDouble(basicConstraints.isGreaterThanOrEqualTo(new Double(lowerLimit)));
	}
	
	public double between(double lowerLimit, double upperLimit) {
		return constraintStore.putDouble(basicConstraints.isBetween(new Double(lowerLimit), new Double(upperLimit)));
	}
	
	public double notBetween(double lowerLimit, double upperLimit) {
		return constraintStore.putDouble(basicConstraints.isNotBetween(new Double(lowerLimit), new Double(upperLimit)));
	}
	
	private Constraint isEqual(double[] oneOf) {
		Constraint[] equals = new Constraint[oneOf.length];

		for (int index = 0; index < oneOf.length; index++ ) {
			equals[index] = basicConstraints.isEqualTo(new Double(oneOf[index]));
		}

		return new ExtendedOr(equals);
	}
}
