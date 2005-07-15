/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.Verify;
import picounit.impl.Verifiable;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.Explanation;

import java.io.File;
import java.util.Arrays;

import junit.framework.AssertionFailedError;

public class DefaultVerify extends Explanation implements Verify {
	// TODO Implement the object equals using array util
	// TODO Replace toString(array) with stringUtil.toString(array)
	// TODO Examine missing types for simple equal(type, type)
	private final BooleanConstraints booleanConstraints;
	private final ByteConstraints byteConstraints;
	private final CharacterConstraints charConstraints;
	private final DoubleConstraints doubleConstraints;
	private final FloatConstraints floatConstraints;
	private final IntegerConstraints intConstraints;
	private final LongConstraints longConstraints;
	private final ShortConstraints shortConstraints;
	
	private final FileConstraints fileConstraints;
	private final StringConstraints stringConstraints;
	private final TypedConstraints typedConstraints;
	
	private final ArrayUtil arrayUtil;
	private final StringUtil stringUtil;
	private final Thrower thrower;
	private final NumericUtil numericUtil;
	private final Verifiable verifiable;
	private final EqualUtil equalUtil;
	
	private final BooleanArrayConstraints booleanArrayConstraints;
	private final ByteArrayConstraints byteArrayConstraints;
	private final CharacterArrayConstraints characterArrayConstraints;
	private final DoubleArrayConstraints doubleArrayConstraints;
	private final FloatArrayConstraints floatArrayConstraints;
	private final IntegerArrayConstraints integerArrayConstraints;
	private final LongArrayConstraints longArrayConstraints;
	private final ShortArrayConstraints shortArrayConstraints;
	private final StringArrayConstraints stringArrayConstraints;
	private final TypedArrayConstraints typedArrayConstraints;

	public static Verify create() {
		return create(new NumericUtil(), new ArrayUtil(), new StringUtil(), new ImmediateThrower(),
			new NullVerifiable());
	}

	public static Verify create(NumericUtil numericUtil, ArrayUtil arrayUtil, StringUtil stringUtil,
		Thrower thrower, Verifiable verifiable) {

		Evaluator evaluator = new Evaluator();

		return new DefaultVerify(evaluator,
			new DefaultBooleanConstraints(evaluator),
			new DefaultByteConstraints(evaluator),
			new DefaultCharacterConstraints(evaluator),
			new DefaultDoubleConstraints(evaluator),
			new DefaultFloatConstraints(evaluator),
			new DefaultIntegerConstraints(evaluator),
			new DefaultLongConstraints(evaluator),
			new DefaultShortConstraints(evaluator),
			new DefaultFileConstraints(evaluator),
			new DefaultStringConstraints(evaluator),
			new TypedConstraintsImpl(evaluator),
			new TypedArrayConstraintsImpl(evaluator), numericUtil, arrayUtil, stringUtil, thrower, verifiable);
	}	

	public DefaultVerify(Evaluator evaluator, BooleanConstraints booleanConstraints,
		ByteConstraints byteConstraints, CharacterConstraints charConstraints,
		DoubleConstraints doubleConstraints, FloatConstraints floatConstraints, IntegerConstraints intConstraints,
		LongConstraints longConstraints, ShortConstraints shortConstraints, FileConstraints fileConstraints,
		StringConstraints stringConstraints, TypedConstraints typedConstraints,
		TypedArrayConstraints typedArrayConstraints, NumericUtil numericUtil,
		ArrayUtil arrayUtil, StringUtil stringUtil, Thrower thrower, Verifiable verifiable) {

		super(evaluator);

		this.booleanConstraints = booleanConstraints;
		this.byteConstraints = byteConstraints;
		this.charConstraints = charConstraints;
		this.doubleConstraints = doubleConstraints;
		this.floatConstraints = floatConstraints;
		this.intConstraints = intConstraints;
		this.longConstraints = longConstraints;
		this.shortConstraints = shortConstraints;

		this.fileConstraints = fileConstraints;
		this.stringConstraints = stringConstraints;
		this.typedConstraints = typedConstraints;

		this.booleanArrayConstraints = new BooleanArrayConstraintsImpl(evaluator);
		this.byteArrayConstraints = new DefaultByteArrayConstraints(evaluator);
		this.characterArrayConstraints = new CharacterArrayConstraintsImpl(evaluator);
		this.doubleArrayConstraints = new DefaultDoubleArrayConstraints(evaluator);
		this.floatArrayConstraints = new FloatArrayConstraintsImpl(evaluator);
		this.integerArrayConstraints = new IntegerArrayConstraintsImpl(evaluator);
		this.longArrayConstraints = new LongArrayConstraintsImpl(evaluator);
		this.shortArrayConstraints = new ShortArrayConstraintsImpl(evaluator);
		this.stringArrayConstraints = new StringArrayConstraintsImpl(evaluator);
		this.typedArrayConstraints = typedArrayConstraints;

		this.numericUtil = numericUtil;
		this.arrayUtil = arrayUtil;
		this.stringUtil = stringUtil;
		this.thrower = thrower;
		this.verifiable = verifiable;
		this.equalUtil = new EqualUtil();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// VerifyStage
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public VerifyStage because(String reason) {
		setReason(reason);

		return this;
	}
	
	public BooleanConstraints thatBoolean(boolean value) {
		verify();
		
		setValue(new Boolean(value));

		return booleanConstraints;
	}
	
	public ByteConstraints that(byte value) {
		verify();
		
		setValue(new Byte(value));
	
		return byteConstraints;
	}
	
	public CharacterConstraints that(char value) {
		verify();
		
		setValue(new Character(value));
		
		return charConstraints;
	}
	
	public DoubleConstraints that(double value) {
		verify();
		
		setValue(new Double(value));
		
		return doubleConstraints;
	}
	
	public FloatConstraints that(float value) {
		verify();
		
		setValue(new Float(value));
		
		return floatConstraints;
	}
	
	public IntegerConstraints that(int value) {
		verify();
		
		setValue(new Integer(value));
		
		return intConstraints;
	}
	
	public LongConstraints that(long value) {
		verify();
		
		setValue(new Long(value));
		
		return longConstraints;
	}
	
	public ShortConstraints that(short value) {
		verify();
		
		setValue(new Short(value));
		
		return shortConstraints;
	}
	
	public FileConstraints that(File value) {
		verify();
		
		setValue(value);

		return fileConstraints;
	}
	
	public StringConstraints that(String value) {
		verify();
		
		setValue(value);
		
		return stringConstraints;
	}

	public TypedConstraints that(Object value) {
		verify();
		
		setValue(value);

		return typedConstraints;
	}
	
	public BooleanArrayConstraints that(boolean[] value) {
		verify();
		
		setValue(value);

		return booleanArrayConstraints;
	}
	
	public ByteArrayConstraints that(byte[] value) {
		verify();
		
		setValue(value);

		return byteArrayConstraints;
	}
	
	public CharacterArrayConstraints that(char[] value) {
		verify();
		
		setValue(value);

		return characterArrayConstraints;
	}
	
	public DoubleArrayConstraints that(double[] value) {
		verify();
		
		setValue(value);

		return doubleArrayConstraints;
	}
	
	public FloatArrayConstraints that(float[] value) {
		verify();
		
		setValue(value);

		return floatArrayConstraints;
	}
	
	public IntegerArrayConstraints that(int[] value) {
		verify();
		
		setValue(value);

		return integerArrayConstraints;
	}
	
	public LongArrayConstraints that(long[] value) {
		verify();
		
		setValue(value);

		return longArrayConstraints;
	}
	
	public ShortArrayConstraints that(short[] value) {
		verify();
		
		setValue(value);

		return shortArrayConstraints;
	}
	
	public StringArrayConstraints that(String[] value) {
		verify();
		
		setValue(value);

		return stringArrayConstraints;
	}
	
	public TypedArrayConstraints that(Object[] value) {
		verify();
		
		setValue(value);

		return typedArrayConstraints;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// picounit.Verify
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void fail() {
		verify();

		raise();
	}


	public void fail(String message) {
		verify();

		raise(message);
	}

	public void that(boolean expression) {
		verify();

		if (!expression) {
			raise(message(true, false));
		}
	}

	public void that(String message, boolean expression) {
		verify();

		if (!expression) {
			raise(message(message, true, false));
		}
	}

	public void not(boolean expression) {
		verify();

		if (expression) {
			raise(message(false, true));
		}
	}

	public void not(String message, boolean expression) {
		verify();

		if (expression) {
			raise(message(message, false, true));
		}
	}

	public void equal(boolean expected, boolean actual) {
		verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}

	public void equal(String message, boolean expected, boolean actual) {
		verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(byte expected, byte actual) {
		verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, byte expected, byte actual) {
		verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(char expected, char actual) {
		verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, char expected, char actual) {
		verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(double expected, double actual) {
		verify();

		if (!numericUtil.isEqual(expected, actual)) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, double expected, double actual) {
		verify();

		if (!numericUtil.isEqual(expected, actual)) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(double expected, double actual, double delta) {
		verify();

		if (!numericUtil.isEqual(expected, actual, delta)) {
			raise(message(expected, actual));
		}
	}

	public void equal(String message, double expected, double actual, double delta) {
		verify();

		if (!numericUtil.isEqual(expected, actual, delta)) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(float expected, float actual) {
		verify();

		if (!numericUtil.isEqual(expected, actual)) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, float expected, float actual) {
		verify();

		if (!numericUtil.isEqual(expected, actual)) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(float expected, float actual, float delta) {
		verify();

		if (!numericUtil.isEqual(expected, actual, delta)) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, float expected, float actual, float delta) {
		verify();

		if (!numericUtil.isEqual(expected, actual, delta)) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(int expected, int actual) {
		verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, int expected, int actual) {
		verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(short expected, short actual) {
		verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, short expected, short actual) {
		verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}

	public void equal(long expected, long actual) {
		verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, long expected, long actual) {
		verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}

	public void equal(boolean[] expected, boolean[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(byte[] expected, byte[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(char[] expected, char[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(double[] expected, double[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(double[] expected, double[] actual, double delta) {
		verify();

		if (!arrayUtil.equal(expected, actual, delta)) {
			String message = message(stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")";

			raise(message);
		}
	}

	public void equal(float[] expected, float[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(float[] expected, float[] actual, float delta) {
		verify();

		if (!arrayUtil.equal(expected, actual, delta)) {
			String message = message(stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")";

			raise(message);
		}
	}

	public void equal(int[] expected, int[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(long[] expected, long[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(short[] expected, short[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}
	
	public void equal(String message, boolean[] expected, boolean[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, byte[] expected, byte[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, char[] expected, char[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, double[] expected, double[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, double[] expected, double[] actual, double delta) {
		verify();

		if (!arrayUtil.equal(expected, actual, delta)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")");
		}
	}

	public void equal(String message, float[] expected, float[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, float[] expected, float[] actual, float delta) {
		verify();

		if (!arrayUtil.equal(expected, actual, delta)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")");
		}
	}

	public void equal(String message, int[] expected, int[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, long[] expected, long[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, short[] expected, short[] actual) {
		verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}
	
	public void equal(Object expected, Object actual) {
		verify();

		if (!equalUtil.equal(expected, actual)) {
			raise(message(expected, actual));
		}
	}

	public void equal(String message, Object expected, Object actual) {
		verify();

		if (!equalUtil.equal(expected, actual)) {
			raise(message(message, expected, actual));
		}
	}

	public void notEqual(boolean notExpected, boolean actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, boolean notExpected, boolean actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(byte notExpected, byte actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, byte notExpected, byte actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(char notExpected, char actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, char notExpected, char actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(double notExpected, double actual) {
		verify();

		if (numericUtil.isEqual(notExpected, actual)) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, double notExpected, double actual) {
		verify();

		if (numericUtil.isEqual(notExpected, actual)) {
			raise(notEqualMessage(message, notExpected));
		}
	}

	public void notEqual(double notExpected, double actual, double delta) {
		verify();

		if (numericUtil.isEqual(notExpected, actual, delta)) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, double notExpected, double actual, double delta) {
		verify();

		if (numericUtil.isEqual(notExpected, actual, delta)) {
			raise(message);
		}
	}
	
	public void notEqual(float notExpected, float actual) {
		verify();

		if (numericUtil.isEqual(notExpected, actual)) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, float notExpected, float actual) {
		verify();

		if (numericUtil.isEqual(notExpected, actual)) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(float notExpected, float actual, float delta) {
		verify();

		if (numericUtil.isEqual(notExpected, actual, delta)) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, float notExpected, float actual, float delta) {
		verify();

		if (numericUtil.isEqual(notExpected, actual, delta)) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(int notExpected, int actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, int notExpected, int actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}

	public void notEqual(long notExpected, long actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, long notExpected, long actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(short notExpected, short actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, short notExpected, short actual) {
		verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(boolean[] notExpected, boolean[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(byte[] notExpected, byte[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(char[] notExpected, char[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(double[] notExpected, double[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(double[] notExpected, double[] actual, double delta) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			raise("expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(float[] notExpected, float[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(float[] notExpected, float[] actual, float delta) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			raise("expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(int[] notExpected, int[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(long[] notExpected, long[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(short[] notExpected, short[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}
	
	public void notEqual(String message, boolean[] notExpected, boolean[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, byte[] notExpected, byte[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, char[] notExpected, char[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, double[] notExpected, double[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, double[] notExpected, double[] actual, double delta) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			raise(message + ", expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(String message, float[] notExpected, float[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, float[] notExpected, float[] actual, float delta) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			raise(message + ", expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(String message, int[] notExpected, int[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, long[] notExpected, long[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, short[] notExpected, short[] actual) {
		verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(Object notExpected, Object actual) {
		verify();

		if (equalUtil.equal(notExpected, actual)) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, Object notExpected, Object actual) {
		verify();

		if(equalUtil.equal(notExpected, actual)) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void same(Object expected, Object actual) {
		verify();

		if (expected != actual) {
			raise("expected same: <" + expected + ">, but was: <" + actual + ">");
		}
	}

	public void same(String message, Object expected, Object actual) {
		verify();

		if (!sameReference(expected, actual)) {
			raise(message + ", expected same: <" + expected + ">, but was: <" + actual + ">");
		}
	}
	
	public void notSame(Object notExpected, Object actual) {
		verify();

		if (sameReference(notExpected, actual)) {
			raise("expected not same: <" + notExpected + ">");
		}
	}

	public void notSame(String message, Object notExpected, Object actual) {
		verify();

		if (sameReference(notExpected, actual)) {
			raise(message + ", expected not same: <" + notExpected + ">");
		}
	}

	public void isNull(Object object) {
		verify();

		if (object != null) {
			raise("expected null, but was: <" + object + ">");
		}
	}
	
	public void isNull(String message, Object object) {
		verify();
		
		if (object != null) {
			raise(message + ", expected null, but was: <" + object + ">");
		}
	}

	public void notNull(Object object) {
		verify();
		
		if (object == null) {
			raise("expected non-null");
		}
	}
	
	public void notNull(String message, Object object) {
		verify();

		if (object == null) {
			raise(message + ", expected non-null");
		}
	}

	public void instanceOf(Class instanceOf, Object object) {
		verify();

//		that(object).isAnInstanceOf(instanceOf);

		if (!isInstanceOf(instanceOf, object)) {
			raise("expected instance of: <" + instanceOf.getName() +
				">, but was: <" + object + ">");
		}
	}

	public void instanceOf(String message, Class instanceOf, Object object) {
		verify();

//		because(message).that(object).isAnInstanceOf(instanceOf);

		if (!isInstanceOf(instanceOf, object)) {
			raise(message + ", expected instance of: <" + instanceOf.getName() +
				">, but was: <" + object + ">");
		}
	}
	
	public void notInstanceOf(Class notInstanceOf, Object object) {
		verify();

		if (isInstanceOf(notInstanceOf, object)) {
			raise("expected not instance of: <" + notInstanceOf.getName() +
				">, but was: <" + object + ">");
		}
	}

	public void notInstanceOf(String message, Class notInstanceOf, Object object) {
		verify();

		if (isInstanceOf(notInstanceOf, object)) {
			raise(message + ", expected not instance of: <" + notInstanceOf.getName() +
				">, but was: <" + object + ">");
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Utility Methods
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean sameReference(Object expected, Object actual) {
		return expected == actual;
	}

	private boolean isInstanceOf(Class instanceOf, Object object) {
		return object != null && instanceOf.isAssignableFrom(object.getClass());
	}

	private String message(boolean expected, boolean actual) {
		return message(new Boolean(expected), new Boolean(actual));
	}

	private String message(String message, boolean expected, boolean actual) {
		return message(message, new Boolean(expected), new Boolean(actual));
	}

	private String message(double expected, double actual) {
		return message(null, new Double(expected), new Double(actual));
	}

	private String message(String message, double expected, double actual) {
		return message(message, new Double(expected), new Double(actual));
	}

	private String message(long expected, long actual) {
		return message(null, new Long(expected), new Long(actual));
	}

	private String message(String message, long expected, long actual) {
		return message(message, new Long(expected), new Long(actual));
	}
	
	private String message(Object expected, Object actual) {
		return "expected: <" + toString(expected) + ">, but was: <" + toString(actual) + ">";
	}

	private String message(String message, Object expected, Object actual) {
		String mainString = "expected: <" + toString(expected) + ">, but was: <" + toString(actual) + ">";

		if (message != null && message.length() != 0) {
			return message + ", " + mainString;
		}
		else {
			return mainString;
		}
	}
	
	private String notEqualMessage(boolean notExpected) {
		return notEqualMessage(new Boolean(notExpected));
	}
	
	private String notEqualMessage(double notExpected) {
		return notEqualMessage(new Double(notExpected));
	}
	
	private String notEqualMessage(long notExpected) {
		return notEqualMessage(new Long(notExpected));
	}

	private String notEqualMessage(Object notExpected) {
		return "expected not equal: <" + notExpected + ">";
	}
	
	private String notEqualMessage(String message, boolean notExpected) {
		return notEqualMessage(message, new Boolean(notExpected));
	}

	private String notEqualMessage(String message, double notExpected) {
		return notEqualMessage(message, new Double(notExpected));
	}

	private String notEqualMessage(String message, long notExpected) {
		return notEqualMessage(message, new Long(notExpected));
	}
	
	private String notEqualMessage(String message, Object notExpected) {
		return message + ", " + "expected not equal: <" + notExpected + ">";
	}

	private String toString(Object object) {
		if (object == null) {
			return "null";
		}
		else if (object.getClass().isArray()) {
			Object[] array = (Object[]) object;
			
			return Arrays.asList(array).toString();
		}
		else {
			return object.toString();
		}
	}

	private void raise() {
		thrower.errorEvent(new AssertionFailedError());
	}

	private void raise(String message) {
		thrower.errorEvent(new AssertionFailedError(message));
	}

	private void verify() throws AssertionFailedError {
		verifiable.verify();
	}
}
