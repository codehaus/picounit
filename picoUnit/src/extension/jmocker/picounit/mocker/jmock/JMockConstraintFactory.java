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
import org.jmock.core.constraint.IsLessThan;
import org.jmock.core.constraint.IsNot;
import org.jmock.core.constraint.Or;
import org.jmock.core.constraint.StringContains;

import picounit.ConstraintFactory;
import picounit.Future;

import java.util.HashMap;
import java.util.Map;

public class JMockConstraintFactory implements ConstraintFactory {
	private final Map sentinelToConstraintMap = new HashMap();
	private final ProxyFactory proxyFactory = new ProxyFactory();
	private final ConstraintStore constraintStore;

	public JMockConstraintFactory(ConstraintStore constraintStore) {
		this.constraintStore = constraintStore;
	}

	public Object instanceOf(Class instanceOf) {
		return proxyFactory.create(instanceOf, new InstanceOfInvocationHandler(instanceOf));
	}

	public Future future(Class futureType) {
		return (Future) proxyFactory.create(Future.class, new FutureInvocationHandler(futureType));
	}

	public int oneOf(int[] oneOf) {
		return constraintStore.putInteger(isEqual(oneOf));
	}

	public int notEqual(int notEqual) {
		return constraintStore.putInteger(not(isEqual(notEqual)));
	}
	
	public int noneOf(int[] neitherOf) {
		return constraintStore.putInteger(not(isEqual(neitherOf)));
	}

	public int lessThan(int upperLimit) {
		return constraintStore.putInteger(isLessThan(upperLimit));
	}

	public int lessThanOrEqualTo(int upperLimit) {
		return constraintStore.putInteger(or(isEqual(upperLimit), isLessThan(upperLimit)));
	}

	public int greaterThan(int lowerLimit) {
		return constraintStore.putInteger(isGreaterThan(lowerLimit));
	}

	public int greaterThanOrEqualTo(int lowerLimit) {
		return constraintStore.putInteger(or(isGreaterThan(lowerLimit), isEqual(lowerLimit)));
	}

	public int between(int lowerLimit, int upperLimit) {
		return constraintStore.putInteger(and(isGreaterThan(lowerLimit), isLessThan(upperLimit)));
	}

	public int anInteger() {
		return constraintStore.putInteger(new IsAnything());
	}

	public String notEqual(String notEqual) {
		return notEqualImpl(notEqual);
	}

	public String containing(String toContain) {
		return constraintStore.putString(new StringContains(toContain));
	}

	public String equalIgnoringCase(String toEqual) {
		return constraintStore.putString(new StringEqualIgnoreCase(toEqual));
	}

	public Object notEqual(Object notEqual) {
		return notEqualImpl(notEqual);
	}

	private Constraint isEqual(int notEqual) {
		return new IsEqual(new Integer(notEqual));
	}

	private Constraint isEqual(int[] oneOf) {
		Constraint[] equals = new Constraint[oneOf.length];

		for (int index = 0; index < oneOf.length; index++ ) {
			equals[index] = isEqual(oneOf[index]);
		}

		return new ExtendedOr(equals);
	}
	
	private Constraint isEqual(Object toEqual) {
		return new IsEqual(toEqual);
	}

	private Constraint isLessThan(int upperLimit) {
		return new IsLessThan(new Integer(upperLimit));
	}

	private Constraint isGreaterThan(int lowerLimit) {
		return new IsGreaterThan(new Integer(lowerLimit));
	}

	private Constraint or(Constraint left, Constraint right) {
		return new Or(left, right);
	}

	private Constraint and(Constraint left, Constraint right) {
		return new And(left, right);
	}

	private Constraint not(Constraint constraint) {
		return new IsNot(constraint);
	}

	private String notEqualImpl(Object notEqual) {
		return constraintStore.putString(not(isEqual(notEqual)));
	}	
}
