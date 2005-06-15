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

import java.util.Arrays;

import junit.framework.AssertionFailedError;

public class DefaultVerify implements Verify {
	// TODO Implement the object equals using array util
	// TODO Replace toString(array) with stringUtil.toString(array)
	// TODO Examine missing types for simple equal(type, type)
	private final ArrayUtil arrayUtil;
	private final StringUtil stringUtil;
	private final Thrower thrower;
	private final NumericUtil numericUtil;
	private final Verifiable verifiable;

	public static Verify create() {
		return new DefaultVerify(new NumericUtil(), new ArrayUtil(), new StringUtil(),
			new ImmediateThrower(), new NullVerifiable());
	}

	public DefaultVerify(NumericUtil numericUtil, ArrayUtil arrayUtil, StringUtil stringUtil,
		Thrower thrower, Verifiable verifiable) {

		this.numericUtil = numericUtil;
		this.arrayUtil = arrayUtil;
		this.stringUtil = stringUtil;
		this.thrower = thrower;
		this.verifiable = verifiable;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// picounit.Verify
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void fail() {
		verifiable.verify();

		raise();
	}


	public void fail(String message) {
		verifiable.verify();

		raise(message);
	}

	public void that(boolean expression) {
		verifiable.verify();

		if (!expression) {
			raise(message(true, false));
		}
	}

	public void that(String message, boolean expression) {
		verifiable.verify();

		if (!expression) {
			raise(message(message, true, false));
		}
	}

	public void not(boolean expression) {
		verifiable.verify();

		if (expression) {
			raise(message(false, true));
		}
	}

	public void not(String message, boolean expression) {
		verifiable.verify();

		if (expression) {
			raise(message(message, false, true));
		}
	}

	public void equal(boolean expected, boolean actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}

	public void equal(String message, boolean expected, boolean actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(byte expected, byte actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, byte expected, byte actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(char expected, char actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, char expected, char actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(double expected, double actual) {
		verifiable.verify();

		if (!numericUtil.isEqual(expected, actual)) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, double expected, double actual) {
		verifiable.verify();

		if (!numericUtil.isEqual(expected, actual)) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(double expected, double actual, double delta) {
		verifiable.verify();

		if (!numericUtil.isEqual(expected, actual, delta)) {
			raise(message(expected, actual));
		}
	}

	public void equal(String message, double expected, double actual, double delta) {
		verifiable.verify();

		if (!numericUtil.isEqual(expected, actual, delta)) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(float expected, float actual) {
		verifiable.verify();

		if (!numericUtil.isEqual(expected, actual)) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, float expected, float actual) {
		verifiable.verify();

		if (!numericUtil.isEqual(expected, actual)) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(float expected, float actual, float delta) {
		verifiable.verify();

		if (!numericUtil.isEqual(expected, actual, delta)) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, float expected, float actual, float delta) {
		verifiable.verify();

		if (!numericUtil.isEqual(expected, actual, delta)) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(int expected, int actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, int expected, int actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}
	
	public void equal(short expected, short actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, short expected, short actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}

	public void equal(long expected, long actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(expected, actual));
		}
	}
	
	public void equal(String message, long expected, long actual) {
		verifiable.verify();

		if (expected != actual) {
			raise(message(message, expected, actual));
		}
	}

	public void equal(boolean[] expected, boolean[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(byte[] expected, byte[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(char[] expected, char[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(double[] expected, double[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(double[] expected, double[] actual, double delta) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual, delta)) {
			String message = message(stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")";

			raise(message);
		}
	}

	public void equal(float[] expected, float[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(float[] expected, float[] actual, float delta) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual, delta)) {
			String message = message(stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")";

			raise(message);
		}
	}

	public void equal(int[] expected, int[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(long[] expected, long[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(short[] expected, short[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}
	
	public void equal(String message, boolean[] expected, boolean[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, byte[] expected, byte[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, char[] expected, char[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, double[] expected, double[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, double[] expected, double[] actual, double delta) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual, delta)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")");
		}
	}

	public void equal(String message, float[] expected, float[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, float[] expected, float[] actual, float delta) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual, delta)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")");
		}
	}

	public void equal(String message, int[] expected, int[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, long[] expected, long[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, short[] expected, short[] actual) {
		verifiable.verify();

		if (!arrayUtil.equal(expected, actual)) {
			raise(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}
	
	public void equal(Object expected, Object actual) {
		verifiable.verify();

		if (!equalImpl(expected, actual)) {
			raise(message(expected, actual));
		}
	}

	public void equal(String message, Object expected, Object actual) {
		verifiable.verify();

		if (!equalImpl(expected, actual)) {
			raise(message(message, expected, actual));
		}
	}

	public void notEqual(boolean notExpected, boolean actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, boolean notExpected, boolean actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(byte notExpected, byte actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, byte notExpected, byte actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(char notExpected, char actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, char notExpected, char actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(double notExpected, double actual) {
		verifiable.verify();

		if (numericUtil.isEqual(notExpected, actual)) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, double notExpected, double actual) {
		verifiable.verify();

		if (numericUtil.isEqual(notExpected, actual)) {
			raise(notEqualMessage(message, notExpected));
		}
	}

	public void notEqual(double notExpected, double actual, double delta) {
		verifiable.verify();

		if (numericUtil.isEqual(notExpected, actual, delta)) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, double notExpected, double actual, double delta) {
		verifiable.verify();

		if (numericUtil.isEqual(notExpected, actual, delta)) {
			raise(message);
		}
	}
	
	public void notEqual(float notExpected, float actual) {
		verifiable.verify();

		if (numericUtil.isEqual(notExpected, actual)) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, float notExpected, float actual) {
		verifiable.verify();

		if (numericUtil.isEqual(notExpected, actual)) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(float notExpected, float actual, float delta) {
		verifiable.verify();

		if (numericUtil.isEqual(notExpected, actual, delta)) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, float notExpected, float actual, float delta) {
		verifiable.verify();

		if (numericUtil.isEqual(notExpected, actual, delta)) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(int notExpected, int actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, int notExpected, int actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}

	public void notEqual(long notExpected, long actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, long notExpected, long actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(short notExpected, short actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(notExpected));
		}
	}
	
	public void notEqual(String message, short notExpected, short actual) {
		verifiable.verify();

		if (notExpected == actual) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(boolean[] notExpected, boolean[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(byte[] notExpected, byte[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(char[] notExpected, char[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(double[] notExpected, double[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(double[] notExpected, double[] actual, double delta) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			raise("expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(float[] notExpected, float[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(float[] notExpected, float[] actual, float delta) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			raise("expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(int[] notExpected, int[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(long[] notExpected, long[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(short[] notExpected, short[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}
	
	public void notEqual(String message, boolean[] notExpected, boolean[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, byte[] notExpected, byte[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, char[] notExpected, char[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, double[] notExpected, double[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, double[] notExpected, double[] actual, double delta) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			raise(message + ", expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(String message, float[] notExpected, float[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, float[] notExpected, float[] actual, float delta) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			raise(message + ", expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(String message, int[] notExpected, int[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, long[] notExpected, long[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, short[] notExpected, short[] actual) {
		verifiable.verify();

		if (!arrayUtil.notEqual(notExpected, actual)) {
			raise(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(Object notExpected, Object actual) {
		verifiable.verify();

		if (equalImpl(notExpected, actual)) {
			raise(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, Object notExpected, Object actual) {
		verifiable.verify();

		if(equalImpl(notExpected, actual)) {
			raise(notEqualMessage(message, notExpected));
		}
	}
	
	public void same(Object expected, Object actual) {
		verifiable.verify();

		if (expected != actual) {
			raise("expected same: <" + expected + ">, but was: <" + actual + ">");
		}
	}

	public void same(String message, Object expected, Object actual) {
		verifiable.verify();

		if (!sameReference(expected, actual)) {
			raise(message + ", expected same: <" + expected + ">, but was: <" + actual + ">");
		}
	}
	
	public void notSame(Object notExpected, Object actual) {
		verifiable.verify();

		if (sameReference(notExpected, actual)) {
			raise("expected not same: <" + notExpected + ">");
		}
	}

	public void notSame(String message, Object notExpected, Object actual) {
		verifiable.verify();

		if (sameReference(notExpected, actual)) {
			raise(message + ", expected not same: <" + notExpected + ">");
		}
	}

	public void isNull(Object object) {
		verifiable.verify();

		if (object != null) {
			raise("expected null, but was: <" + object + ">");
		}
	}
	
	public void isNull(String message, Object object) {
		verifiable.verify();
		
		if (object != null) {
			raise(message + ", expected null, but was: <" + object + ">");
		}
	}

	public void notNull(Object object) {
		verifiable.verify();
		
		if (object == null) {
			raise("expected non-null");
		}
	}
	
	public void notNull(String message, Object object) {
		verifiable.verify();

		if (object == null) {
			raise(message + ", expected non-null");
		}
	}

	public void instanceOf(Class instanceOf, Object object) {
		verifiable.verify();

		if (!isInstanceOf(instanceOf, object)) {
			raise("expected instance of: <" + instanceOf.getName() +
				">, but was: <" + object + ">");
		}
	}

	public void instanceOf(String message, Class instanceOf, Object object) {
		verifiable.verify();

		if (!isInstanceOf(instanceOf, object)) {
			raise(message + ", expected instance of: <" + instanceOf.getName() +
				">, but was: <" + object + ">");
		}
	}
	
	public void notInstanceOf(Class notInstanceOf, Object object) {
		verifiable.verify();

		if (isInstanceOf(notInstanceOf, object)) {
			raise("expected not instance of: <" + notInstanceOf.getName() +
				">, but was: <" + object + ">");
		}
	}

	public void notInstanceOf(String message, Class notInstanceOf, Object object) {
		verifiable.verify();

		if (isInstanceOf(notInstanceOf, object)) {
			raise(message + ", expected not instance of: <" + notInstanceOf.getName() +
				">, but was: <" + object + ">");
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Utility Methods
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean equalImpl(Object expected, Object actual) {
		return bothNull(expected, actual) ||
			neitherNull(expected, actual) && (sameReference(expected, actual) ||
				arrayEquals(expected, actual) ||
				ordinaryEquals(expected, actual));
	}

	private boolean sameReference(Object expected, Object actual) {
		return expected == actual;
	}

	private boolean bothNull(Object expected, Object actual) {
		return expected == null && actual == null;
	}

	private boolean neitherNull(Object expected, Object actual) {
		return expected != null && actual != null;
	}

	private boolean arrayEquals(Object expected, Object actual) {
		return expected.getClass().isArray() && actual.getClass().isArray() &&
			Arrays.equals((Object[]) expected, (Object[]) actual);
	}

	private boolean ordinaryEquals(Object expected, Object actual) {
		return expected.equals(actual);
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
}
