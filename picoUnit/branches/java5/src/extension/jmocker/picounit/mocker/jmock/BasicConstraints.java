/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;
import org.jmock.core.constraint.And;
import org.jmock.core.constraint.IsAnything;
import org.jmock.core.constraint.IsEqual;
import org.jmock.core.constraint.IsGreaterThan;
import org.jmock.core.constraint.IsInstanceOf;
import org.jmock.core.constraint.IsLessThan;
import org.jmock.core.constraint.IsNot;
import org.jmock.core.constraint.IsNull;
import org.jmock.core.constraint.Or;

public class BasicConstraints {
	public Constraint isAnything() {
		return new IsAnything();
	}

	public Constraint isEqualTo(Object equalTo) {
		return new IsEqual(equalTo);
	}

	public Constraint isNotEqualTo(Object notEqual) {
		return not(isEqualTo(notEqual));
	}

	public Constraint isLessThan(Comparable upperLimit) {
		return new IsLessThan(upperLimit);
	}

	public Constraint isLessThanOrEqualTo(Comparable upperLimit) {
		return or(isLessThan(upperLimit), isEqualTo(upperLimit));
	}

	public Constraint isGreaterThan(Comparable lowerLimit) {
		return new IsGreaterThan(lowerLimit);
	}

	public Constraint isGreaterThanOrEqualTo(Comparable lowerLimit) {
		return or(isGreaterThan(lowerLimit), isEqualTo(lowerLimit));
	}

	public Constraint isBetween(Comparable lowerLimit, Comparable upperLimit) {
		return and(isGreaterThan(lowerLimit), isLessThan(lowerLimit));
	}

	public Constraint isNotBetween(Comparable lowerLimit, Comparable upperLimit) {
		return or(isLessThanOrEqualTo(lowerLimit), isGreaterThanOrEqualTo(upperLimit));
	}
	
	public Constraint not(Constraint toNegate) {
		return new IsNot(toNegate);
	}
	
	public Constraint or(Constraint left, Constraint right) {
		return new Or(left, right);
	}
	
	public Constraint and(Constraint left, Constraint right) {
		return new And(left, right);
	}

	public Constraint isNull() {
		return new IsNull();
	}

	public Constraint isNotNull(Class instanceOf) {
		return new IsInstanceOf(instanceOf);
	}
}