/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import picounit.Constraints;
import picounit.DoubleConstraints;
import picounit.Future;
import picounit.IntegerConstraints;
import picounit.LongConstraints;
import picounit.ObjectConstraints;
import picounit.StringConstraints;

import java.util.HashMap;
import java.util.Map;

public class JMockConstraints implements Constraints {
	private final Map sentinelToConstraintMap = new HashMap();
	private final ProxyFactory proxyFactory = new ProxyFactory();
	private final IntegerConstraints integerConstraints;
	private final LongConstraints longConstraints;
	private final DoubleConstraints doubleConstraints;
	private final StringConstraints stringConstraints;
	private final ObjectConstraints objectConstraints;

	public JMockConstraints(ConstraintStore constraintStore) {
		this(new JMockIntegerConstraints(constraintStore), new JMockLongConstraints(constraintStore),
			new JMockDoubleConstraints(constraintStore), new JMockStringConstraints(constraintStore),
			new JMockObjectConstraints(constraintStore));
	}

	public JMockConstraints(IntegerConstraints integerConstraints,
		LongConstraints longConstraints, DoubleConstraints doubleConstraints,
		StringConstraints stringConstraints, ObjectConstraints objectConstraints) {

		this.integerConstraints = integerConstraints;
		this.longConstraints = longConstraints;
		this.doubleConstraints = doubleConstraints;
		this.stringConstraints = stringConstraints;
		this.objectConstraints = objectConstraints;
	}

	public Object instanceOf(Class instanceOf) {
		return proxyFactory.create(instanceOf, new InstanceOfInvocationHandler(instanceOf));
	}

	public Future future(Class futureType) {
		return (Future) proxyFactory.create(Future.class, new FutureInvocationHandler(futureType));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// IntegerConstraints
	///////////////////////////////////////////////////////////////////////////////////////////////
	public int anInteger() {
		return integerConstraints.anInteger();
	}

	public int oneOf(int[] oneOf) {
		return integerConstraints.oneOf(oneOf);
	}

	public int notEqualTo(int notEqual) {
		return integerConstraints.notEqualTo(notEqual);
	}
	
	public int neitherOf(int[] neitherOf) {
		return integerConstraints.neitherOf(neitherOf);
	}

	public int lessThan(int upperLimit) {
		return integerConstraints.lessThan(upperLimit);
	}

	public int lessThanOrEqualTo(int upperLimit) {
		return integerConstraints.lessThanOrEqualTo(upperLimit);
	}

	public int greaterThan(int lowerLimit) {
		return integerConstraints.greaterThan(lowerLimit);
	}

	public int greaterThanOrEqualTo(int lowerLimit) {
		return integerConstraints.greaterThanOrEqualTo(lowerLimit);
	}

	public int between(int lowerLimit, int upperLimit) {
		return integerConstraints.between(lowerLimit, upperLimit);
	}

	public int notBetween(int lowerLimit, int upperLimit) {
		return integerConstraints.notBetween(lowerLimit, upperLimit);
	}
	
	public int almostEqualTo(int equalTo, int errorAllowed) {
		return integerConstraints.almostEqualTo(equalTo, errorAllowed);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// LongConstraints
	///////////////////////////////////////////////////////////////////////////////////////////////
	public long aLong() {
		return longConstraints.aLong();
	}
	
	public long oneOf(long[] oneOf) {
		return longConstraints.oneOf(oneOf);
	}
	
	public long neitherOf(long[] neitherOf) {
		return longConstraints.neitherOf(neitherOf);
	}
	
	public long notEqualTo(long notEqual) {
		return longConstraints.notEqualTo(notEqual);
	}
	
	public long lessThan(long upperLimit) {
		return longConstraints.lessThan(upperLimit);
	}

	public long lessThanOrEqualTo(long upperLimit) {
		return longConstraints.lessThanOrEqualTo(upperLimit);
	}
	
	public long greaterThan(long lowerLimit) {
		return longConstraints.greaterThan(lowerLimit);
	}
	
	public long greaterThanOrEqualTo(long lowerLimit) {
		return longConstraints.greaterThanOrEqualTo(lowerLimit);
	}
	
	public long between(long lowerLimit, long upperLimit) {
		return longConstraints.between(lowerLimit, upperLimit);
	}
	
	public long notBetween(long lowerLimit, long upperLimit) {
		return longConstraints.notBetween(lowerLimit, upperLimit);
	}
	
	public long almostEqualTo(long equalTo, long errorAllowed) {
		return longConstraints.almostEqualTo(equalTo, errorAllowed);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// DoubleConstraints
	///////////////////////////////////////////////////////////////////////////////////////////////
	public double aDouble() {
		return doubleConstraints.aDouble();
	}
	
	public double oneOf(double[] oneOf) {
		return doubleConstraints.oneOf(oneOf);
	}
	
	public double neitherOf(double[] neitherOf) {
		return doubleConstraints.neitherOf(neitherOf);
	}
	
	public double notEqualTo(double notEqual) {
		return doubleConstraints.notEqualTo(notEqual);
	}
	
	public double lessThan(double upperLimit) {
		return doubleConstraints.lessThan(upperLimit);
	}
	
	public double lessThanOrEqualTo(double upperLimit) {
		return doubleConstraints.lessThanOrEqualTo(upperLimit);
	}
	
	public double greaterThan(double lowerLimit) {
		return doubleConstraints.greaterThan(lowerLimit);
	}
	
	public double greaterThanOrEqualTo(double lowerLimit) {
		return doubleConstraints.greaterThanOrEqualTo(lowerLimit);
	}
	
	public double between(double lowerLimit, double upperLimit) {
		return doubleConstraints.between(lowerLimit, upperLimit);
	}
	
	public double notBetween(double lowerLimit, double upperLimit) {
		return doubleConstraints.notBetween(lowerLimit, upperLimit);
	}
	
	public double almostEqualTo(double equalTo, double errorAllowed) {
		return doubleConstraints.almostEqualTo(equalTo, errorAllowed);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// StringConstraints
	///////////////////////////////////////////////////////////////////////////////////////////////
	public String aString() {
		return stringConstraints.aString();
	}
	
	public String oneOf(String[] oneOf) {
		return stringConstraints.oneOf(oneOf);
	}

	public String neitherOf(String[] neitherOf) {
		return stringConstraints.neitherOf(neitherOf);
	}

	public String notEqualTo(String notEqual) {
		return stringConstraints.notEqualTo(notEqual);
	}

	public String aStringContaining(String toContain) {
		return stringConstraints.aStringContaining(toContain);
	}

	public String aStringNotContaining(String notToContain) {
		return stringConstraints.aStringNotContaining(notToContain);
	}

	public String equalToIgnoringCase(String toEqual) {
		return stringConstraints.equalToIgnoringCase(toEqual);
	}
	
	public String aStringMatching(String pattern) {
		return stringConstraints.aStringMatching(pattern);
	}
	
	public String aStringNotMatching(String pattern) {
		return stringConstraints.aStringNotMatching(pattern);
	}

	public String aNullString() {
		return stringConstraints.aNullString();
	}

	public String notANullString() {
		return stringConstraints.notANullString()	;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// ObjectConstraints
	///////////////////////////////////////////////////////////////////////////////////////////////
	public Object notEqual(Object notEqual) {
		return objectConstraints.notEqual(notEqual);
	}
	
	public Object anObject() {
		return objectConstraints.anObject();
	}
	
	public Object aNullObject() {
		return objectConstraints.aNullObject();
	}
	
	public Object notANullObject() {
		return objectConstraints.notANullObject();
	}
	
	public Object oneOf(Object[] oneOf) {
		return objectConstraints.oneOf(oneOf);
	}
	
	public Object neitherOf(Object[] neitherOf) {
		return objectConstraints.neitherOf(neitherOf);
	}
}
