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
import previous.picounit.Mocker;
import previous.picounit.Test;
import previous.picounit.Verify;

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
	
	public void testDelegatesToIntegerConstraints(Mocker should, Verify verify) {
		should.call(integerConstraints.anInteger()).andReturn(111);
		should.call(integerConstraints.oneOf(severalIntegers())).andReturn(222);
		should.call(integerConstraints.neitherOf(severalIntegers())).andReturn(333);
		should.call(integerConstraints.notEqualTo(400)).andReturn(444);
		should.call(integerConstraints.lessThan(500)).andReturn(555);
		should.call(integerConstraints.lessThanOrEqualTo(600)).andReturn(666);
		should.call(integerConstraints.greaterThan(700)).andReturn(777);
		should.call(integerConstraints.greaterThanOrEqualTo(800)).andReturn(888);
		should.call(integerConstraints.between(911, 922)).andReturn(999);
		should.call(integerConstraints.notBetween(110, 220)).andReturn(010);

		should.doAboveWhen();

		verify.equal(111, jmockConstraintFactory.anInteger());
		verify.equal(222, jmockConstraintFactory.oneOf(severalIntegers()));
		verify.equal(333, jmockConstraintFactory.neitherOf(severalIntegers()));
		verify.equal(444, jmockConstraintFactory.notEqualTo(400));
		verify.equal(555, jmockConstraintFactory.lessThan(500));
		verify.equal(666, jmockConstraintFactory.lessThanOrEqualTo(600));
		verify.equal(777, jmockConstraintFactory.greaterThan(700));
		verify.equal(888, jmockConstraintFactory.greaterThanOrEqualTo(800));
		verify.equal(999, jmockConstraintFactory.between(911, 922));
		verify.equal(010, jmockConstraintFactory.notBetween(110, 220));
	}
	
	public void testDelegatesToLongConstraints(Mocker should, Verify verify) {
		should.call(longConstraints.aLong()).andReturn(111);
		should.call(longConstraints.oneOf(severalLongs())).andReturn(222);
		should.call(longConstraints.neitherOf(severalLongs())).andReturn(333);
		should.call(longConstraints.notEqualTo(400)).andReturn(444);
		should.call(longConstraints.lessThan(500)).andReturn(555);
		should.call(longConstraints.lessThanOrEqualTo(600)).andReturn(666);
		should.call(longConstraints.greaterThan(700)).andReturn(777);
		should.call(longConstraints.greaterThanOrEqualTo(800)).andReturn(888);
		should.call(longConstraints.between(911, 922)).andReturn(999);
		should.call(longConstraints.notBetween(110, 220)).andReturn(010);

		should.doAboveWhen();

		verify.equal(111, jmockConstraintFactory.aLong());
		verify.equal(222, jmockConstraintFactory.oneOf(severalLongs()));
		verify.equal(333, jmockConstraintFactory.neitherOf(severalLongs()));
		verify.equal(444, jmockConstraintFactory.notEqualTo(400L));
		verify.equal(555, jmockConstraintFactory.lessThan(500L));
		verify.equal(666, jmockConstraintFactory.lessThanOrEqualTo(600L));
		verify.equal(777, jmockConstraintFactory.greaterThan(700L));
		verify.equal(888, jmockConstraintFactory.greaterThanOrEqualTo(800L));
		verify.equal(999, jmockConstraintFactory.between(911L, 922L));
		verify.equal(010, jmockConstraintFactory.notBetween(110L, 220L));
	}
	
	public void testDelegatesToDoubleConstraints(Mocker should, Verify verify) {
		should.call(doubleConstraints.aDouble()).andReturn(111.1);
		should.call(doubleConstraints.oneOf(severalDoubles())).andReturn(222.2);
		should.call(doubleConstraints.neitherOf(severalDoubles())).andReturn(333.3);
		should.call(doubleConstraints.notEqualTo(400.4)).andReturn(444.4);
		should.call(doubleConstraints.lessThan(500.5)).andReturn(555.5);
		should.call(doubleConstraints.lessThanOrEqualTo(600.6)).andReturn(666.6);
		should.call(doubleConstraints.greaterThan(700.7)).andReturn(777.7);
		should.call(doubleConstraints.greaterThanOrEqualTo(800.8)).andReturn(888.8);
		should.call(doubleConstraints.between(911.9, 922.9)).andReturn(999.9);
		should.call(doubleConstraints.notBetween(110.1, 220.2)).andReturn(010.1);

		should.doAboveWhen();

		verify.equal(111.1, jmockConstraintFactory.aDouble());
		verify.equal(222.2, jmockConstraintFactory.oneOf(severalDoubles()));
		verify.equal(333.3, jmockConstraintFactory.neitherOf(severalDoubles()));
		verify.equal(444.4, jmockConstraintFactory.notEqualTo(400.4));
		verify.equal(555.5, jmockConstraintFactory.lessThan(500.5));
		verify.equal(666.6, jmockConstraintFactory.lessThanOrEqualTo(600.6));
		verify.equal(777.7, jmockConstraintFactory.greaterThan(700.7));
		verify.equal(888.8, jmockConstraintFactory.greaterThanOrEqualTo(800.8));
		verify.equal(999.9, jmockConstraintFactory.between(911.9, 922.9));
		verify.equal(010.1, jmockConstraintFactory.notBetween(110.1, 220.2));
	}

	public void testDelegatesToStringConstraints(Mocker should, Verify verify) {
		should.call(stringConstraints.aString()).andReturn("aString");
		should.call(stringConstraints.aNullString()).andReturn("null");
		should.call(stringConstraints.notANullString()).andReturn("notNull");
		should.call(stringConstraints.oneOf(severalStrings())).andReturn("oneOf");
		should.call(stringConstraints.neitherOf(severalStrings())).andReturn("neitherOf");
		should.call(stringConstraints.notEqualTo("notEqual")).andReturn("notEqualTo");
		should.call(stringConstraints.equaTolIgnoringCase("equalIgnoringCase")).andReturn("equalToIgnoringCase");
		should.call(stringConstraints.aStringContaining("containing")).andReturn("aStringContaining");
		should.call(stringConstraints.aStringMatching("pattern")).andReturn("aStringMatching");
		
		should.doAboveWhen();
		
		verify.equal("aString", jmockConstraintFactory.aString());
		verify.equal("null", jmockConstraintFactory.aNullString());
		verify.equal("notNull", jmockConstraintFactory.notANullString());
		verify.equal("oneOf", jmockConstraintFactory.oneOf(severalStrings()));
		verify.equal("neitherOf", jmockConstraintFactory.neitherOf(severalStrings()));
		verify.equal("notEqualTo", jmockConstraintFactory.notEqualTo("notEqual"));
		verify.equal("equalToIgnoringCase", jmockConstraintFactory.equaTolIgnoringCase("equalIgnoringCase"));
		verify.equal("aStringContaining", jmockConstraintFactory.aStringContaining("containing"));
		verify.equal("aStringMatching", jmockConstraintFactory.aStringMatching("pattern"));
	}
	
	public void testDelegatesToObjectConstaints(Mocker should, Verify verify) {
		Object anObject = new Object();
		Object[] someObjects = new Object[] {"String", new Integer(1), new Double(3.3)};
		
		should.call(objectConstraints.anObject()).andReturn("anObject");
		should.call(objectConstraints.aNullObject()).andReturn("aNullObject");
		should.call(objectConstraints.notANullObject()).andReturn("notANullObject");
		should.call(objectConstraints.oneOf(someObjects)).andReturn("oneOf");
		should.call(objectConstraints.neitherOf(someObjects)).andReturn("neitherOf");
		should.call(objectConstraints.notEqual(anObject)).andReturn("notEqual");

		should.doAboveWhen();

		verify.equal("anObject", jmockConstraintFactory.anObject());
		verify.equal("aNullObject", jmockConstraintFactory.aNullObject());
		verify.equal("notANullObject", jmockConstraintFactory.notANullObject());
		verify.equal("oneOf", jmockConstraintFactory.oneOf(someObjects));
		verify.equal("neitherOf", jmockConstraintFactory.neitherOf(someObjects));
		verify.equal("notEqual", jmockConstraintFactory.notEqual(anObject));
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
		return null;
	}
}
