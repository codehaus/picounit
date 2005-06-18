/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import picounit.DoubleConstraints;
import picounit.IntegerConstraints;
import picounit.LongConstraints;
import picounit.ObjectConstraints;
import picounit.StringConstraints;
import previous.picounit.DelegateVerify;
import previous.picounit.Test;

public class JMockConstraintsTest implements Test {
	private JMockConstraints jmockConstraintFactory;

	private IntegerConstraints integerConstraints;
	private LongConstraints longConstraints;
	private DoubleConstraints doubleConstraints;
	private StringConstraints stringConstraints;
	private ObjectConstraints objectConstraints;

	public void mock(IntegerConstraints integerConstraints, LongConstraints longConstraints,
		DoubleConstraints doubleConstraints, StringConstraints stringConstraints,
		ObjectConstraints objectConstraints) {

		this.jmockConstraintFactory = new JMockConstraints(integerConstraints, longConstraints,
			doubleConstraints, stringConstraints, objectConstraints);
		
		this.integerConstraints = integerConstraints;
		this.longConstraints = longConstraints;
		this.doubleConstraints = doubleConstraints;
		this.stringConstraints = stringConstraints;
		this.objectConstraints = objectConstraints;
	}

	public void testDelegatesToIntegerConstraints(DelegateVerify should) {
		should.delegateTo(integerConstraints.anInteger())
			.whenCalling(jmockConstraintFactory.anInteger());

		should.delegateTo(integerConstraints.oneOf(severalIntegers()))
			.whenCalling(jmockConstraintFactory.oneOf(severalIntegers()));

		should.delegateTo(integerConstraints.neitherOf(severalIntegers()))
			.whenCalling(jmockConstraintFactory.neitherOf(severalIntegers()));
		
		should.delegateTo(integerConstraints.notEqualTo(400))
			.whenCalling(jmockConstraintFactory.notEqualTo(400));

		should.delegateTo(integerConstraints.lessThan(400))
			.whenCalling(jmockConstraintFactory.lessThan(400));
		
		should.delegateTo(integerConstraints.lessThanOrEqualTo(400))
			.whenCalling(jmockConstraintFactory.lessThanOrEqualTo(400));

		should.delegateTo(integerConstraints.greaterThan(400))
			.whenCalling(jmockConstraintFactory.greaterThan(400));

		should.delegateTo(integerConstraints.greaterThanOrEqualTo(400))
			.whenCalling(jmockConstraintFactory.greaterThanOrEqualTo(400));
		
		should.delegateTo(integerConstraints.between(100, 200))
			.whenCalling(jmockConstraintFactory.between(100, 200));
	
		should.delegateTo(integerConstraints.notBetween(100, 200))
			.whenCalling(jmockConstraintFactory.notBetween(100, 200));
	
		should.delegateTo(integerConstraints.almostEqualTo(100, 200))
			.whenCalling(jmockConstraintFactory.almostEqualTo(100, 200));
	}
	
	public void testDelegatesToLongConstraints(DelegateVerify should) {
		should.delegateTo(longConstraints.aLong())
			.whenCalling(jmockConstraintFactory.aLong());

		should.delegateTo(longConstraints.oneOf(severalLongs()))
			.whenCalling(jmockConstraintFactory.oneOf(severalLongs()));

		should.delegateTo(longConstraints.neitherOf(severalLongs()))
			.whenCalling(jmockConstraintFactory.neitherOf(severalLongs()));

		should.delegateTo(longConstraints.notEqualTo(100L))
			.whenCalling(jmockConstraintFactory.notEqualTo(100L));

		should.delegateTo(longConstraints.lessThan(100L))
			.whenCalling(jmockConstraintFactory.lessThan(100L));

		should.delegateTo(longConstraints.lessThanOrEqualTo(100L))
			.whenCalling(jmockConstraintFactory.lessThanOrEqualTo(100L));

		should.delegateTo(longConstraints.greaterThan(100L))
			.whenCalling(jmockConstraintFactory.greaterThan(100L));

		should.delegateTo(longConstraints.greaterThanOrEqualTo(100L))
			.whenCalling(jmockConstraintFactory.greaterThanOrEqualTo(100L));

		should.delegateTo(longConstraints.between(100L, 200L))
			.whenCalling(jmockConstraintFactory.between(100L, 200L));

		should.delegateTo(longConstraints.notBetween(100L, 200L))
			.whenCalling(jmockConstraintFactory.notBetween(100L, 200L));

		should.delegateTo(longConstraints.almostEqualTo(100L, 20L))
			.whenCalling(jmockConstraintFactory.almostEqualTo(100L, 20L));
	}
	
	public void testDelegatesToDoubleConstraints(DelegateVerify should) {
		should.delegateTo(doubleConstraints.aDouble())
			.whenCalling(jmockConstraintFactory.aDouble());

		should.delegateTo(doubleConstraints.oneOf(severalDoubles()))
			.whenCalling(jmockConstraintFactory.oneOf(severalDoubles()));

		should.delegateTo(doubleConstraints.neitherOf(severalDoubles()))
			.whenCalling(jmockConstraintFactory.neitherOf(severalDoubles()));

		should.delegateTo(doubleConstraints.notEqualTo(123.456))
			.whenCalling(jmockConstraintFactory.notEqualTo(123.456));

		should.delegateTo(doubleConstraints.lessThan(123.456))
			.whenCalling(jmockConstraintFactory.lessThan(123.456));

		should.delegateTo(doubleConstraints.lessThanOrEqualTo(123.456))
			.whenCalling(jmockConstraintFactory.lessThanOrEqualTo(123.456));

		should.delegateTo(doubleConstraints.greaterThan(123.456))
			.whenCalling(jmockConstraintFactory.greaterThan(123.456));

		should.delegateTo(doubleConstraints.greaterThanOrEqualTo(123.456))
			.whenCalling(jmockConstraintFactory.greaterThanOrEqualTo(123.456));

		should.delegateTo(doubleConstraints.between(123.456, 654.321))
			.whenCalling(jmockConstraintFactory.between(123.456, 654.321));

		should.delegateTo(doubleConstraints.notBetween(123.456, 654.321))
			.whenCalling(jmockConstraintFactory.notBetween(123.456, 654.321));

		should.delegateTo(doubleConstraints.almostEqualTo(123.456, 654.321))
			.whenCalling(jmockConstraintFactory.almostEqualTo(123.456, 654.321));
	}

	public void testDelegatesToStringConstraints(DelegateVerify should) {
		should.delegateTo(stringConstraints.aString())
			.whenCalling(jmockConstraintFactory.aString());

		should.delegateTo(stringConstraints.aNullString())
			.whenCalling(jmockConstraintFactory.aNullString());

		should.delegateTo(stringConstraints.notANullString())
			.whenCalling(jmockConstraintFactory.notANullString());

		should.delegateTo(stringConstraints.oneOf(severalStrings()))
			.whenCalling(jmockConstraintFactory.oneOf(severalStrings()));

		should.delegateTo(stringConstraints.neitherOf(severalStrings()))
			.whenCalling(jmockConstraintFactory.neitherOf(severalStrings()));

		should.delegateTo(stringConstraints.notEqualTo("notEqualTo"))
			.whenCalling(jmockConstraintFactory.notEqualTo("notEqualTo"));

		should.delegateTo(stringConstraints.equalToIgnoringCase("equalToIgnoringCase"))
			.whenCalling(jmockConstraintFactory.equalToIgnoringCase("equalToIgnoringCase"));

		should.delegateTo(stringConstraints.aStringContaining("aStringContaining"))
			.whenCalling(jmockConstraintFactory.aStringContaining("aStringContaining"));

		should.delegateTo(stringConstraints.aStringNotContaining("aStringNotContaining"))
			.whenCalling(jmockConstraintFactory.aStringNotContaining("aStringNotContaining"));

		should.delegateTo(stringConstraints.aStringMatching("aStringMatching"))
			.whenCalling(jmockConstraintFactory.aStringMatching("aStringMatching"));

		should.delegateTo(stringConstraints.aStringNotMatching("aStringNotMatching"))
			.whenCalling(jmockConstraintFactory.aStringNotMatching("aStringNotMatching"));
	}

	public void testDelegatesToObjectConstaints(DelegateVerify should) {
		should.delegateTo(objectConstraints.anObject())
			.whenCalling(jmockConstraintFactory.anObject());

		should.delegateTo(objectConstraints.aNullObject())
			.whenCalling(jmockConstraintFactory.aNullObject());

		should.delegateTo(objectConstraints.notANullObject())
			.whenCalling(jmockConstraintFactory.notANullObject());

		should.delegateTo(objectConstraints.oneOf(severalObjects()))
			.whenCalling(jmockConstraintFactory.oneOf(severalObjects()));

		should.delegateTo(objectConstraints.neitherOf(severalObjects()))
			.whenCalling(jmockConstraintFactory.neitherOf(severalObjects()));

		Object anObject = new Object();
		should.delegateTo(objectConstraints.notEqual(anObject))
			.whenCalling(jmockConstraintFactory.notEqual(anObject));
	}

	private Object[] severalObjects() {
		return new Object[] {"one", new Integer(2), new Double(3.3)};
	}
	
	private int[] severalIntegers() {
		return new int[] {1, 2, 3, 9, 8, 7};
	}
	
	private long[] severalLongs() {
		return new long[] {100, 200, 300, 900, 800, 700};
	}
	
	private double[] severalDoubles() {
		return new double[] {1.0, 2.0, 3.0, 9.0, 8.0, 7.0};
	}

	private String[] severalStrings() {
		return new String[] {"one", "two", "three"};
	}
}
