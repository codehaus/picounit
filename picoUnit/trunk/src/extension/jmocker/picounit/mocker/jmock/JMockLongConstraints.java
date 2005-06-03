/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

import picounit.LongConstraints;

public class JMockLongConstraints implements LongConstraints {
	private final ConstraintStore constraintStore;
	private final BasicConstraints basicConstraints;

	public JMockLongConstraints(ConstraintStore constraintStore) {
		this(constraintStore, new BasicConstraints());
	}
	
	public JMockLongConstraints(ConstraintStore constraintStore, BasicConstraints basicConstraints) {
		this.constraintStore = constraintStore;
		this.basicConstraints = basicConstraints;
	}

	public long aLong() {
		return constraintStore.putLong(basicConstraints.isAnything());
	}
	
	public long oneOf(long[] oneOf) {
		return constraintStore.putLong(isEqual(oneOf));
	}
	
	public long neitherOf(long[] neitherOf) {
		return constraintStore.putLong(basicConstraints.not(isEqual(neitherOf)));
	}
	
	public long notEqualTo(long notEqual) {
		return constraintStore.putLong(basicConstraints.isNotEqualTo(new Long(notEqual)));
	}
	
	public long lessThan(long upperLimit) {
		return constraintStore.putLong(basicConstraints.isLessThan(new Long(upperLimit)));
	}

	public long lessThanOrEqualTo(long upperLimit) {
		return constraintStore.putLong(basicConstraints.isLessThanOrEqualTo(new Long(upperLimit)));
	}
	
	public long greaterThan(long lowerLimit) {
		return constraintStore.putLong(basicConstraints.isGreaterThan(new Long(lowerLimit)));
	}
	
	public long greaterThanOrEqualTo(long lowerLimit) {
		return constraintStore.putLong(basicConstraints.isGreaterThanOrEqualTo(new Long(lowerLimit)));
	}
	
	public long between(long lowerLimit, long upperLimit) {
		return constraintStore.putLong(basicConstraints.isBetween(new Long(lowerLimit), new Long(upperLimit)));
	}
	
	public long notBetween(long lowerLimit, long upperLimit) {
		return constraintStore.putLong(basicConstraints.isNotBetween(new Long(lowerLimit), new Long(upperLimit)));
	}

	private Constraint isEqual(long[] oneOf) {
		Constraint[] equals = new Constraint[oneOf.length];

		for (int index = 0; index < oneOf.length; index++ ) {
			equals[index] = basicConstraints.isEqualTo(new Long(oneOf[index]));
		}

		return new ExtendedOr(equals);
	}
}
