/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.migration;

import picounit.Constraints;
import picounit.Mocker;
import picounit.Test;
import picounit.Verify;

abstract public class TestCase implements Test {
	public Constraints is;
	public Mocker should;
	private Verify verify;
	interface PreventCollision {} 

	public final void setUp(Constraints is, Mocker should, Verify verify, PreventCollision preventCollision) {
		this.is = is;
		this.should = should;
		this.verify = verify;
	}

	/** setUp methods should be public in PicoUnit */
	public void setUp() throws Exception {
	}

	/** setUp methods should be public in PicoUnit */
	public void tearDown() throws Exception {
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// JUnit equivalent methods 
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void assertTrue(String message, boolean expression) {
		verify.because(message).thatBoolean(expression).isTrue();
	}

	public void assertTrue(boolean expression) {
		verify.thatBoolean(expression).isTrue();
	}
	
	public void assertFalse(String message, boolean expression) {
		verify.because(message).thatBoolean(expression).isFalse();
	}
	
	public void assertFalse(boolean expression) {
		verify.thatBoolean(expression).isFalse();
	}
	
	public void assertEquals(String message, Object expected, Object actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(Object expected, Object actual) {
		verify.that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String message, String expected, String actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String expected, String actual) {
		verify.that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String message, double expected, double actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(double expected, double actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertEquals(String message, float expected, float actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(float expected, float actual) {
		verify.that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String message, long expected, long actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(long expected, long actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertEquals(String message, boolean expected, boolean actual) {
		verify.because(message).thatBoolean(actual).isEqualTo(expected);
	}
	
	public void assertEquals(boolean expected, boolean actual) {
		verify.thatBoolean(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String message, byte expected, byte actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(byte expected, byte actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertEquals(String message, char expected, char actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(char expected, char actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertEquals(String message, int expected, int actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(int expected, int actual) {
		verify.that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String message, short expected, short actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(short expected, short actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertNotNull(String message, Object object) {
		verify.because(message).that(object).isNotNull();
	}
	
	public void assertNotNull(Object object) {
		verify.that(object).isNotNull();
	}
	
	public void assertNull(String message, Object object) {
		verify.because(message).that(object).isNull();
	}

	public void assertNull(Object object) {
		verify.that(object).isNull();
	}
	
	public void assertSame(String message, Object expected, Object actual) {
		verify.because(message).that(actual).isTheSameAs(expected);
	}

	public void assertSame(Object expected, Object actual) {
		verify.that(actual).isTheSameAs(expected);
	}
	
	public void assertNotSame(String message, Object notExpected, Object actual) {
		verify.because(message).that(actual).isNotTheSameAs(notExpected);
	}

	public void assertNotSame(Object notExpected, Object actual) {
		verify.that(actual).isNotTheSameAs(notExpected);
	}

	public void fail(String message) {
		verify.fail(message);
	}
	
	public void fail() {
		verify.fail();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Additional Methods
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void assertEquals(String message, double[] expected, double[] actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(double[] expected, double[] actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertEquals(String message, float[] expected, float[] actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(float[] expected, float[] actual) {
		verify.that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String message, long[] expected, long[] actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(long[] expected, long[] actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertEquals(String message, boolean[] expected, boolean[] actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(boolean[] expected, boolean[] actual) {
		verify.that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String message, byte[] expected, byte[] actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(byte[] expected, byte[] actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertEquals(String message, char[] expected, char[] actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(char[] expected, char[] actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertEquals(String message, int[] expected, int[] actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(int[] expected, int[] actual) {
		verify.that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(String message, short[] expected, short[] actual) {
		verify.because(message).that(actual).isEqualTo(expected);
	}
	
	public void assertEquals(short[] expected, short[] actual) {
		verify.that(actual).isEqualTo(expected);
	}

	public void assertNotEquals(String message, Object expected, Object actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(Object expected, Object actual) {
		verify.that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String message, String expected, String actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String expected, String actual) {
		verify.that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String message, double expected, double actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(double expected, double actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, float expected, float actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(float expected, float actual) {
		verify.that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String message, long expected, long actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(long expected, long actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, boolean expected, boolean actual) {
		verify.because(message).thatBoolean(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(boolean expected, boolean actual) {
		verify.thatBoolean(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String message, byte expected, byte actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(byte expected, byte actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, char expected, char actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(char expected, char actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, int expected, int actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(int expected, int actual) {
		verify.that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String message, short expected, short actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(short expected, short actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, double[] expected, double[] actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(double[] expected, double[] actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, float[] expected, float[] actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(float[] expected, float[] actual) {
		verify.that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String message, long[] expected, long[] actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(long[] expected, long[] actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, boolean[] expected, boolean[] actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(boolean[] expected, boolean[] actual) {
		verify.that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String message, byte[] expected, byte[] actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(byte[] expected, byte[] actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, char[] expected, char[] actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(char[] expected, char[] actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertNotEquals(String message, int[] expected, int[] actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(int[] expected, int[] actual) {
		verify.that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(String message, short[] expected, short[] actual) {
		verify.because(message).that(actual).isDifferentTo(expected);
	}
	
	public void assertNotEquals(short[] expected, short[] actual) {
		verify.that(actual).isDifferentTo(expected);
	}

	public void assertInstanceOf(String message, Class instanceOf, Object object) {
		verify.because(message).that(object).isAnInstanceOf(instanceOf);
	}

	public void assertInstanceOf(Class instanceOf, Object object) {
		verify.that(object).isAnInstanceOf(instanceOf);
	}
}
