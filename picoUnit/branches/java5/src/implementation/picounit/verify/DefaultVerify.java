/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.Verify;

import java.util.Arrays;

import junit.framework.AssertionFailedError;

public class DefaultVerify implements Verify {
	// TODO Implement the object equals using array util
	// TODO Replace toString(array) with stringUtil.toString(array)
	// TODO Examine missing types for simple equal(type, type)
	private final ArrayUtil arrayUtil;
	private final StringUtil stringUtil;
	private final Thrower thrower;

	public DefaultVerify() {
		this(new ArrayUtil(), new StringUtil(), new ImmediateThrower());
	}

	public DefaultVerify(ArrayUtil arrayUtil, StringUtil stringUtil, Thrower thrower) {
		this.arrayUtil = arrayUtil;
		this.stringUtil = stringUtil;
		this.thrower = thrower;
	}

	public void fail() {
		thrower.errorEvent(new AssertionFailedError());
	}

	public void fail(String message) {
		thrower.errorEvent(new AssertionFailedError(message));
	}

	public void that(boolean expression) {
		if (!expression) {
			fail(message(true, false));
		}
	}

	public void that(String message, boolean expression) {
		if (!expression) {
			fail(message(message, true, false));
		}
	}

	public void not(boolean expression) {
		if (expression) {
			fail(message(false, true));
		}
	}

	public void not(String message, boolean expression) {
		if (expression) {
			fail(message(message, false, true));
		}
	}

	public void equal(boolean expected, boolean actual) {
		if (expected != actual) {
			fail(message(expected, actual));
		}
	}

	public void equal(String message, boolean expected, boolean actual) {
		if (expected != actual) {
			fail(message(message, expected, actual));
		}
	}
	
	public void equal(double expected, double actual) {
		equal(expected, actual, arrayUtil.SMALLEST_DOUBLE_DELTA);
	}
	
	public void equal(String message, double expected, double actual) {
		equal(message, expected, actual, arrayUtil.SMALLEST_DOUBLE_DELTA);
	}
	
	public void equal(double expected, double actual, double delta) {
		if (Math.abs(expected - actual) > delta) {
			fail();
		}
	}
	
	public void equal(String message, double expected, double actual, double delta) {
		if (Math.abs(expected - actual) > delta) {
			fail(message);
		}
	}

	public void equal(long expected, long actual) {
		if (expected != actual) {
			fail(message(expected, actual));
		}
	}
	
	public void equal(String message, long expected, long actual) {
		if (expected != actual) {
			fail(message(message, expected, actual));
		}
	}

	public void equal(boolean[] expected, boolean[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(byte[] expected, byte[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(char[] expected, char[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(double[] expected, double[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(double[] expected, double[] actual, double delta) {
		if (!arrayUtil.equal(expected, actual, delta)) {
			String message = message(stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")";

			fail(message);
		}
	}

	public void equal(float[] expected, float[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(float[] expected, float[] actual, float delta) {
		if (!arrayUtil.equal(expected, actual, delta)) {
			String message = message(stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")";

			fail(message);
		}
	}

	public void equal(int[] expected, int[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(long[] expected, long[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(short[] expected, short[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}
	
	public void equal(String message, boolean[] expected, boolean[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, byte[] expected, byte[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, char[] expected, char[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, double[] expected, double[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, double[] expected, double[] actual, double delta) {
		if (!arrayUtil.equal(expected, actual, delta)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")");
		}
	}

	public void equal(String message, float[] expected, float[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, float[] expected, float[] actual, float delta) {
		if (!arrayUtil.equal(expected, actual, delta)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)) +
				" (with delta: " + delta + ")");
		}
	}

	public void equal(String message, int[] expected, int[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, long[] expected, long[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}

	public void equal(String message, short[] expected, short[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			fail(message(message, stringUtil.toString(expected), stringUtil.toString(actual)));
		}
	}
	
	public void equal(Object expected, Object actual) {
		equal(null, expected, actual);
	}

	public void equal(String message, Object expected, Object actual) {
		if (expected == actual) {
			return;
		}

		if (!equalImpl(expected, actual)) {
			fail(message(message, expected, actual));
		}
	}

	public void notEqual(boolean notExpected, boolean actual) {
		if (notExpected == actual) {
			fail(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, boolean notExpected, boolean actual) {
		if (notExpected == actual) {
			fail(notEqualMessage(message, notExpected));
		}
	}

	public void notEqual(long notExpected, long actual) {
		if (notExpected == actual) {
			fail(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, long notExpected, long actual) {
		if (notExpected == actual) {
			fail(notEqualMessage(message, notExpected));
		}
	}
	
	public void notEqual(boolean[] notExpected, boolean[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(byte[] notExpected, byte[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(char[] notExpected, char[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(double[] notExpected, double[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(double[] notExpected, double[] actual, double delta) {
		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			fail("expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(float[] notExpected, float[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(float[] notExpected, float[] actual, float delta) {
		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			fail("expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(int[] notExpected, int[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(long[] notExpected, long[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(short[] notExpected, short[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(stringUtil.toString(notExpected)));
		}
	}
	
	public void notEqual(String message, boolean[] notExpected, boolean[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, byte[] notExpected, byte[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, char[] notExpected, char[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, double[] notExpected, double[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, double[] notExpected, double[] actual, double delta) {
		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			fail(message + ", expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(String message, float[] notExpected, float[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, float[] notExpected, float[] actual, float delta) {
		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			fail(message + ", expected <" + stringUtil.toString(actual) + "> not equal <" +
				stringUtil.toString(notExpected) + "> (with delta: " + delta + ")");
		}
	}

	public void notEqual(String message, int[] notExpected, int[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, long[] notExpected, long[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(String message, short[] notExpected, short[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			fail(notEqualMessage(message, stringUtil.toString(notExpected)));
		}
	}

	public void notEqual(Object notExpected, Object actual) {
		if (equalImpl(notExpected, actual)) {
			fail(notEqualMessage(notExpected));
		}
	}

	public void notEqual(String message, Object notExpected, Object actual) {
		if(equalImpl(notExpected, actual)) {
			fail(notEqualMessage(message, notExpected));
		}
	}
	
	public void same(Object expected, Object actual) {
		if (expected != actual) {
			fail("expected same: <" + expected + ">, but was: <" + actual + ">");
		}
	}

	public void same(String message, Object expected, Object actual) {
		if (expected != actual) {
			fail(message + ", expected same: <" + expected + ">, but was: <" + actual + ">");
		}
	}
	
	public void notSame(Object notExpected, Object actual) {
		if (notExpected == actual) {
			fail("expected not same: <" + notExpected + ">");
		}
	}

	public void notSame(String message, Object notExpected, Object actual) {
		if (notExpected == actual) {
			fail(message + ", expected not same: <" + notExpected + ">");
		}
	}

	public void isNull(Object object) {
		equal(null, object);
	}
	
	public void isNull(String message, Object object) {
		equal(message, null, object);
	}

	public void notNull(Object object) {
		notEqual(null, object);
	}
	
	public void notNull(String message, Object object) {
		notEqual(message, null, object);
	}

	public void instanceOf(Class instanceOf, Object object) {
		if (object != null) {
			that(instanceOf.isAssignableFrom(object.getClass()));
		}
	}

	public void instanceOf(String message, Class instanceOf, Object object) {
		if (object != null) {
			that(message, instanceOf.isAssignableFrom(object.getClass()));
		}
	}

	private boolean equalImpl(Object expected, Object actual) {
		return bothNull(expected, actual) ||
			neitherNull(expected, actual) &&
				(arrayEquals(expected, actual) || ordinaryEquals(expected, actual));
	}

	private boolean ordinaryEquals(Object expected, Object actual) {
		return expected.equals(actual);
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

	private String message(boolean expected, boolean actual) {
		return message(new Boolean(expected), new Boolean(actual));
	}

	private String message(String message, boolean expected, boolean actual) {
		return message(message, new Boolean(expected), new Boolean(actual));
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
	
	private String notEqualMessage(boolean expected) {
		return notEqualMessage(new Boolean(expected));
	}
	
	private String notEqualMessage(long expected) {
		return notEqualMessage(new Long(expected));
	}

	private String notEqualMessage(Object expected) {
		return "expected not equal: <" + expected + ">";
	}
	
	private String notEqualMessage(String message, boolean expected) {
		return notEqualMessage(message, new Boolean(expected));
	}

	private String notEqualMessage(String message, long expected) {
		return notEqualMessage(message, new Long(expected));
	}
	
	private String notEqualMessage(String message, Object expected) {
		return message + ", " + "expected not equal: <" + expected + ">";
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

	private boolean oneNullOtherNotNull(Object expected, Object actual) {
		return (expected == null && actual != null) ||
			(expected != null && actual == null);
	}
}
