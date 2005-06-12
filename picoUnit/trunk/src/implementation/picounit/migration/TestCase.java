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

public class TestCase implements Test {
	public Constraints is;
	public Mocker should;
	private Verify verify;
	interface PreventCollision {} 

	public void setUp(Constraints is, Mocker should, Verify verify, PreventCollision preventCollision) {
		this.is = is;
		this.should = should;
		this.verify = verify;
	}

	// JUnit equivalent methods 
	public void assertTrue(String message, boolean expression) {
		verify.that(message, expression);
	}

	public void assertTrue(boolean expression) {
		verify.that(expression);
	}
	
	public void assertFalse(String message, boolean expression) {
		verify.not(message, expression);
	}
	
	public void assertFalse(boolean expression) {
		verify.not(expression);
	}
	
	public void assertEquals(String message, Object expected, Object actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(Object expected, Object actual) {
		verify.equal(expected, actual);
	}
	
	public void assertEquals(String message, String expected, String actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(String expected, String actual) {
		verify.equal(expected, actual);
	}
	
	public void assertEquals(String message, double expected, double actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(double expected, double actual) {
		verify.equal(expected, actual);
	}

	public void assertEquals(String message, float expected, float actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(float expected, float actual) {
		verify.equal(expected, actual);
	}
	
	public void assertEquals(String message, long expected, long actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(long expected, long actual) {
		verify.equal(expected, actual);
	}

	public void assertEquals(String message, boolean expected, boolean actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(boolean expected, boolean actual) {
		verify.equal(expected, actual);
	}
	
	public void assertEquals(String message, byte expected, byte actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(byte expected, byte actual) {
		verify.equal(expected, actual);
	}

	public void assertEquals(String message, char expected, char actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(char expected, char actual) {
		verify.equal(expected, actual);
	}

	public void assertEquals(String message, int expected, int actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(int expected, int actual) {
		verify.equal(expected, actual);
	}
	
	public void assertEquals(String message, short expected, short actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(short expected, short actual) {
		verify.equal(expected, actual);
	}

	public void assertNotNull(String message, Object object) {
		verify.notNull(message, object);
	}
	
	public void assertNotNull(Object object) {
		verify.notNull(object);
	}
	
	public void assertNull(String message, Object object) {
		verify.isNull(message, object);
	}

	public void assertNull(Object object) {
		verify.isNull(object);
	}
	
	public void assertSame(String message, Object expected, Object actual) {
		verify.same(message, expected, actual);
	}

	public void assertSame(Object expected, Object actual) {
		verify.same(expected, actual);
	}
	
	public void assertNotSame(String message, Object notExpected, Object actual) {
		verify.notSame(message, notExpected, actual);
	}

	public void assertNotSame(Object notExpected, Object actual) {
		verify.notSame(notExpected, actual);
	}

	public void fail(String message) {
		verify.fail(message);
	}
	
	public void fail() {
		verify.fail();
	}
	
	// Additional Methods
	public void assertEquals(String message, double[] expected, double[] actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(double[] expected, double[] actual) {
		verify.equal(expected, actual);
	}

	public void assertEquals(String message, float[] expected, float[] actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(float[] expected, float[] actual) {
		verify.equal(expected, actual);
	}
	
	public void assertEquals(String message, long[] expected, long[] actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(long[] expected, long[] actual) {
		verify.equal(expected, actual);
	}

	public void assertEquals(String message, boolean[] expected, boolean[] actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(boolean[] expected, boolean[] actual) {
		verify.equal(expected, actual);
	}
	
	public void assertEquals(String message, byte[] expected, byte[] actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(byte[] expected, byte[] actual) {
		verify.equal(expected, actual);
	}

	public void assertEquals(String message, char[] expected, char[] actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(char[] expected, char[] actual) {
		verify.equal(expected, actual);
	}

	public void assertEquals(String message, int[] expected, int[] actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(int[] expected, int[] actual) {
		verify.equal(expected, actual);
	}
	
	public void assertEquals(String message, short[] expected, short[] actual) {
		verify.equal(message, expected, actual);
	}
	
	public void assertEquals(short[] expected, short[] actual) {
		verify.equal(expected, actual);
	}

	public void assertNotEquals(String message, Object expected, Object actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(Object expected, Object actual) {
		verify.notEqual(expected, actual);
	}
	
	public void assertNotEquals(String message, String expected, String actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(String expected, String actual) {
		verify.notEqual(expected, actual);
	}
	
	public void assertNotEquals(String message, double expected, double actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(double expected, double actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, float expected, float actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(float expected, float actual) {
		verify.notEqual(expected, actual);
	}
	
	public void assertNotEquals(String message, long expected, long actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(long expected, long actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, boolean expected, boolean actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(boolean expected, boolean actual) {
		verify.notEqual(expected, actual);
	}
	
	public void assertNotEquals(String message, byte expected, byte actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(byte expected, byte actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, char expected, char actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(char expected, char actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, int expected, int actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(int expected, int actual) {
		verify.notEqual(expected, actual);
	}
	
	public void assertNotEquals(String message, short expected, short actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(short expected, short actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, double[] expected, double[] actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(double[] expected, double[] actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, float[] expected, float[] actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(float[] expected, float[] actual) {
		verify.notEqual(expected, actual);
	}
	
	public void assertNotEquals(String message, long[] expected, long[] actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(long[] expected, long[] actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, boolean[] expected, boolean[] actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(boolean[] expected, boolean[] actual) {
		verify.notEqual(expected, actual);
	}
	
	public void assertNotEquals(String message, byte[] expected, byte[] actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(byte[] expected, byte[] actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, char[] expected, char[] actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(char[] expected, char[] actual) {
		verify.notEqual(expected, actual);
	}

	public void assertNotEquals(String message, int[] expected, int[] actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(int[] expected, int[] actual) {
		verify.notEqual(expected, actual);
	}
	
	public void assertNotEquals(String message, short[] expected, short[] actual) {
		verify.notEqual(message, expected, actual);
	}
	
	public void assertNotEquals(short[] expected, short[] actual) {
		verify.notEqual(expected, actual);
	}

	public void assertInstanceOf(String message, Class instanceOf, Object object) {
		verify.instanceOf(message, instanceOf, object);
	}

	public void assertInstanceOf(Class instanceOf, Object object) {
		verify.instanceOf(instanceOf, object);
	}
}
