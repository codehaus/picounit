/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.verify.ArrayUtil;
import picounit.verify.StringUtil;
import previous.picounit.Test;
import previous.picounit.Verify;

public class ArrayUtilTest implements Test {
	private final ArrayUtil arrayUtil = new ArrayUtil();
	private final StringUtil stringUtil = new StringUtil();
	private final Verify verify;
	
	public ArrayUtilTest(Verify verify) {
		this.verify = verify;
	}
	
	public void testBooleanArrayContains() {
		assertDoesntContain(new boolean[] {}, true);
		assertDoesntContain(new boolean[] {}, false);
		assertDoesntContain(new boolean[] {false, false, false, false}, true);
		assertContains(new boolean[] {false, false, false, true}, true);
	}

	public void testByteArrayContains() {
		assertDoesntContain(new byte[] {}, (byte) 0);
		assertDoesntContain(new byte[] {1, 2, 3, 4}, (byte) 0);
		assertContains(new byte[] {1, 2, 3, 4}, (byte) 1);
	}
	
	public void testCharArrayContains() {
		assertDoesntContain(new char[] {}, 'a');
		assertDoesntContain(new char[] {'a', 'b', 'c', 'd'}, 'z');
		assertContains(new char[] {'a', 'b', 'c', 'd'}, 'a');
	}
	
	public void testDoubleArrayContains() {
		assertDoesntContain(new double[] {}, 0.0);
		assertDoesntContain(new double[] {0.0, 1.0, 1.5, 2.0}, -1.0);
		assertContains(new double[] {0.0, 1.0, 1.5, 2.0}, 1.5);

		assertDoesntContain(new double[] {0.0, 1.0, 1.5, 2.0}, smallestAdditionTo(1.0));
	}

	public void testFloatArrayContains() {
		assertDoesntContain(new float[] {}, 0.0f);
		assertDoesntContain(new float[] {0.0f, 1.0f, 1.5f, 2.0f}, -1.0f);
		assertContains(new float[] {0.0f, 1.0f, 1.5f, 2.0f}, 1.5f);

		assertDoesntContain(new float[] {0.0f, 1.0f, 1.5f, 2.0f}, smallestAdditionTo(1.0f));
	}
	
	public void testIntegerArrayContains() {
		assertDoesntContain(new int[] {}, 0);
		assertDoesntContain(new int[] {1, 2, 3, 4}, 0);
		assertContains(new int[] {1, 2, 3, 4}, 1);
	}
	
	public void testLongArrayContains() {
		assertDoesntContain(new long[] {}, 0l);
		assertDoesntContain(new long[] {1, 2, 3, 4}, 0l);
		assertContains(new long[] {1, 2, 3, 4}, 1);
	}
	
	public void testShortArrayContains() {
		assertDoesntContain(new short[] {}, (short) 0);
		assertDoesntContain(new short[] {1, 2, 3, 4}, (short) 0);
		assertContains(new short[] {1, 2, 3, 4}, (short) 1);
	}

	public void testBooleanEquals() {
		assertEquals((boolean[]) null, (boolean[]) null);
		assertEquals(new boolean[] {}, new boolean[] {});
		assertEquals(new boolean[] {true}, new boolean[] {true});
		assertEquals(new boolean[] {false, true}, new boolean[] {false, true});

		assertEqualsNot((boolean[]) null, new boolean[] {});
		assertEqualsNot(new boolean[] {}, (boolean[]) null);
		assertEqualsNot(new boolean[] {true}, new boolean[] {});
		assertEqualsNot(new boolean[] {}, new boolean[] {false});
		assertEqualsNot(new boolean[] {true}, new boolean[] {false});
		assertEqualsNot(new boolean[] {true, false}, new boolean[] {false, true});
	}
	
	public void testByteEquals() {
		assertEquals((byte[]) null, (byte[]) null);
		assertEquals(new byte[] {}, new byte[] {});
		assertEquals(new byte[] {1}, new byte[] {1});
		assertEquals(new byte[] {0, 1}, new byte[] {0, 1});

		assertEqualsNot((byte[]) null, new byte[] {});
		assertEqualsNot(new byte[] {}, (byte[]) null);
		assertEqualsNot(new byte[] {1}, new byte[] {});
		assertEqualsNot(new byte[] {}, new byte[] {0});
		assertEqualsNot(new byte[] {1}, new byte[] {0});
		assertEqualsNot(new byte[] {1, 0}, new byte[] {0, 1});
	}
	
	public void testCharEquals() {
		assertEquals((char[]) null, (char[]) null);
		assertEquals(new char[] {}, new char[] {});
		assertEquals(new char[] {'1'}, new char[] {'1'});
		assertEquals(new char[] {'0', '1'}, new char[] {'0', '1'});

		assertEqualsNot((char[]) null, new char[] {});
		assertEqualsNot(new char[] {}, (char[]) null);
		assertEqualsNot(new char[] {'1'}, new char[] {});
		assertEqualsNot(new char[] {}, new char[] {'0'});
		assertEqualsNot(new char[] {'1'}, new char[] {'0'});
		assertEqualsNot(new char[] {'1', '0'}, new char[] {'0', '1'});
	}
	
	public void testDoubleEquals() {
		assertEquals((double[]) null, (double[]) null);
		assertEquals(new double[] {}, new double[] {});
		assertEquals(new double[] {1.0}, new double[] {1.0});
		assertEquals(new double[] {0.0, 1.0}, new double[] {0.0, 1.0});

		assertEqualsNot((double[]) null, new double[] {});
		assertEqualsNot(new double[] {}, (double[]) null);
		assertEqualsNot(new double[] {1.0}, new double[] {});
		assertEqualsNot(new double[] {}, new double[] {0.0});
		assertEqualsNot(new double[] {1.0}, new double[] {0.0});
		assertEqualsNot(new double[] {1.0, 0.0}, new double[] {0.0, 1.0});
	}
	
	public void testDoubleEqualsWithDelta() {
		double delta = 0.1;
		double difference = delta * 0.9;

		assertEquals((double[]) null, (double[]) null, delta);
		assertEquals(new double[] {}, new double[] {}, delta);
		assertEquals(new double[] {1.0}, new double[] {1.0 + difference}, delta);
		assertEquals(new double[] {0.0, 1.0}, new double[] {0.0 - difference, 1.0 - difference}, delta);
		
		difference = delta * 1.1;

		assertEqualsNot((double[]) null, new double[] {}, delta);
		assertEqualsNot(new double[] {}, (double[]) null, delta);
		assertEqualsNot(new double[] {1.0}, new double[] {}, delta);
		assertEqualsNot(new double[] {}, new double[] {0.0}, delta);
		assertEqualsNot(new double[] {1.0}, new double[] {0.0}, delta);
		assertEqualsNot(new double[] {1.0, 0.0}, new double[] {0.0, 1.0}, delta);
		assertEqualsNot(new double[] {1.0}, new double[] {1.0 + difference}, delta);
		assertEqualsNot(new double[] {0.0, 1.0}, new double[] {0.0 - difference, 1.0 - difference}, delta);
	}
	
	public void testFloatEquals() {
		assertEquals((float[]) null, (float[]) null);
		assertEquals(new float[] {}, new float[] {});
		assertEquals(new float[] {1.0f}, new float[] {1.0f});
		assertEquals(new float[] {0.0f, 1.0f}, new float[] {0.0f, 1.0f});

		assertEqualsNot((float[]) null, new float[] {});
		assertEqualsNot(new float[] {}, (float[]) null);
		assertEqualsNot(new float[] {1.0f}, new float[] {});
		assertEqualsNot(new float[] {}, new float[] {0.0f});
		assertEqualsNot(new float[] {1.0f}, new float[] {0.0f});
		assertEqualsNot(new float[] {1.0f, 0.0f}, new float[] {0.0f, 1.0f});
	}
	
	public void testFloatEqualsWithDelta() {
		float delta = 0.1f;
		float difference = delta * 0.9f;

		assertEquals((float[]) null, (float[]) null, delta);
		assertEquals(new float[] {}, new float[] {}, delta);
		assertEquals(new float[] {1.0f}, new float[] {1.0f + difference}, delta);
		assertEquals(new float[] {0.0f, 1.0f}, new float[] {0.0f - difference, 1.0f - difference}, delta);
		
		difference = delta * 1.1f;

		assertEqualsNot((float[]) null, new float[] {}, delta);
		assertEqualsNot(new float[] {}, (float[]) null, delta);
		assertEqualsNot(new float[] {1.0f}, new float[] {}, delta);
		assertEqualsNot(new float[] {}, new float[] {0.0f}, delta);
		assertEqualsNot(new float[] {1.0f}, new float[] {0.0f}, delta);
		assertEqualsNot(new float[] {1.0f, 0.0f}, new float[] {0.0f, 1.0f}, delta);
		assertEqualsNot(new float[] {1.0f}, new float[] {1.0f + difference}, delta);
		assertEqualsNot(new float[] {0.0f, 1.0f}, new float[] {0.0f - difference, 1.0f - difference}, delta);
	}
	
	public void testIntEquals() {
		assertEquals((int[]) null, (int[]) null);
		assertEquals(new int[] {}, new int[] {});
		assertEquals(new int[] {1}, new int[] {1});
		assertEquals(new int[] {0, 1}, new int[] {0, 1});

		assertEqualsNot((int[]) null, new int[] {});
		assertEqualsNot(new int[] {}, (int[]) null);
		assertEqualsNot(new int[] {1}, new int[] {});
		assertEqualsNot(new int[] {}, new int[] {0});
		assertEqualsNot(new int[] {1}, new int[] {0});
		assertEqualsNot(new int[] {1, 0}, new int[] {0, 1});
	}
	
	public void testLongEquals() {
		assertEquals((long[]) null, (long[]) null);
		assertEquals(new long[] {}, new long[] {});
		assertEquals(new long[] {1}, new long[] {1});
		assertEquals(new long[] {0, 1}, new long[] {0, 1});

		assertEqualsNot((long[]) null, new long[] {});
		assertEqualsNot(new long[] {}, (long[]) null);
		assertEqualsNot(new long[] {1}, new long[] {});
		assertEqualsNot(new long[] {}, new long[] {0});
		assertEqualsNot(new long[] {1}, new long[] {0});
		assertEqualsNot(new long[] {1, 0}, new long[] {0, 1});
	}

	public void testShortEquals() {
		assertEquals((short[]) null, (short[]) null);
		assertEquals(new short[] {}, new short[] {});
		assertEquals(new short[] {1}, new short[] {1});
		assertEquals(new short[] {0, 1}, new short[] {0, 1});

		assertEqualsNot((short[]) null, new short[] {});
		assertEqualsNot(new short[] {}, (short[]) null);
		assertEqualsNot(new short[] {1}, new short[] {});
		assertEqualsNot(new short[] {}, new short[] {0});
		assertEqualsNot(new short[] {1}, new short[] {0});
		assertEqualsNot(new short[] {1, 0}, new short[] {0, 1});
	}

	public void testBooleanNotEquals() {
		assertNotEquals((boolean[]) null, new boolean[] {});
		assertNotEquals(new boolean[] {}, (boolean[]) null);
		assertNotEquals(new boolean[] {true}, new boolean[] {});
		assertNotEquals(new boolean[] {}, new boolean[] {false});
		assertNotEquals(new boolean[] {true}, new boolean[] {false});
		assertNotEquals(new boolean[] {true, false}, new boolean[] {false, true});

		assertNotEqualsNot((boolean[]) null, (boolean[]) null);
		assertNotEqualsNot(new boolean[] {}, new boolean[] {});
		assertNotEqualsNot(new boolean[] {true}, new boolean[] {true});
		assertNotEqualsNot(new boolean[] {false, true}, new boolean[] {false, true});
	}
	
	public void testByteNotEquals() {
		assertNotEquals((byte[]) null, new byte[] {});
		assertNotEquals(new byte[] {}, (byte[]) null);
		assertNotEquals(new byte[] {1}, new byte[] {});
		assertNotEquals(new byte[] {}, new byte[] {0});
		assertNotEquals(new byte[] {1}, new byte[] {0});
		assertNotEquals(new byte[] {1, 0}, new byte[] {0, 1});

		assertNotEqualsNot((byte[]) null, (byte[]) null);
		assertNotEqualsNot(new byte[] {}, new byte[] {});
		assertNotEqualsNot(new byte[] {1}, new byte[] {1});
		assertNotEqualsNot(new byte[] {0, 1}, new byte[] {0, 1});
	}
	
	public void testCharNotEquals() {
		assertNotEquals((char[]) null, new char[] {});
		assertNotEquals(new char[] {}, (char[]) null);
		assertNotEquals(new char[] {'1'}, new char[] {});
		assertNotEquals(new char[] {}, new char[] {'0'});
		assertNotEquals(new char[] {'1'}, new char[] {'0'});
		assertNotEquals(new char[] {'1', '0'}, new char[] {'0', '1'});
		
		assertNotEqualsNot((char[]) null, (char[]) null);
		assertNotEqualsNot(new char[] {}, new char[] {});
		assertNotEqualsNot(new char[] {'1'}, new char[] {'1'});
		assertNotEqualsNot(new char[] {'0', '1'}, new char[] {'0', '1'});
	}
	
	public void testDoubleNotEquals() {
		assertNotEquals((double[]) null, new double[] {});
		assertNotEquals(new double[] {}, (double[]) null);
		assertNotEquals(new double[] {1.0}, new double[] {});
		assertNotEquals(new double[] {}, new double[] {0.0});
		assertNotEquals(new double[] {1.0}, new double[] {0.0});
		assertNotEquals(new double[] {1.0, 0.0}, new double[] {0.0, 1.0});

		assertNotEqualsNot((double[]) null, (double[]) null);
		assertNotEqualsNot(new double[] {}, new double[] {});
		assertNotEqualsNot(new double[] {1.0}, new double[] {1.0});
		assertNotEqualsNot(new double[] {0.0, 1.0}, new double[] {0.0, 1.0});
	}
	
	public void testDoubleNotEqualsWithDelta() {
		double delta = 0.1;
		double difference = delta * 1.1;

		assertNotEquals((double[]) null, new double[] {}, delta);
		assertNotEquals(new double[] {}, (double[]) null, delta);
		assertNotEquals(new double[] {1.0}, new double[] {}, delta);
		assertNotEquals(new double[] {}, new double[] {0.0}, delta);
		assertNotEquals(new double[] {1.0}, new double[] {0.0}, delta);
		assertNotEquals(new double[] {1.0, 0.0}, new double[] {0.0, 1.0}, delta);
		assertNotEquals(new double[] {1.0}, new double[] {1.0 + difference}, delta);
		assertNotEquals(new double[] {0.0, 1.0}, new double[] {0.0 - difference, 1.0 - difference}, delta);

		difference = delta * 0.9;

		assertNotEqualsNot((double[]) null, (double[]) null, delta);
		assertNotEqualsNot(new double[] {}, new double[] {}, delta);
		assertNotEqualsNot(new double[] {1.0}, new double[] {1.0 + difference}, delta);
		assertNotEqualsNot(new double[] {0.0, 1.0}, new double[] {0.0 - difference, 1.0 - difference}, delta);
	}
	
	public void testFloatNotEquals() {
		assertNotEquals((float[]) null, new float[] {});
		assertNotEquals(new float[] {}, (float[]) null);
		assertNotEquals(new float[] {1.0f}, new float[] {});
		assertNotEquals(new float[] {}, new float[] {0.0f});
		assertNotEquals(new float[] {1.0f}, new float[] {0.0f});
		assertNotEquals(new float[] {1.0f, 0.0f}, new float[] {0.0f, 1.0f});

		assertNotEqualsNot((float[]) null, (float[]) null);
		assertNotEqualsNot(new float[] {}, new float[] {});
		assertNotEqualsNot(new float[] {1.0f}, new float[] {1.0f});
		assertNotEqualsNot(new float[] {0.0f, 1.0f}, new float[] {0.0f, 1.0f});
	}
	
	public void testFloatNotEqualsWithDelta() {
		float delta = 0.1f;
		float difference = delta * 1.1f;

		assertNotEquals((float[]) null, new float[] {}, delta);
		assertNotEquals(new float[] {}, (float[]) null, delta);
		assertNotEquals(new float[] {1.0f}, new float[] {}, delta);
		assertNotEquals(new float[] {}, new float[] {0.0f}, delta);
		assertNotEquals(new float[] {1.0f}, new float[] {0.0f}, delta);
		assertNotEquals(new float[] {1.0f, 0.0f}, new float[] {0.0f, 1.0f}, delta);
		assertNotEquals(new float[] {1.0f}, new float[] {1.0f + difference}, delta);
		assertNotEquals(new float[] {0.0f, 1.0f}, new float[] {0.0f - difference, 1.0f - difference}, delta);

		difference = delta * 0.9f;

		assertNotEqualsNot((float[]) null, (float[]) null, delta);
		assertNotEqualsNot(new float[] {}, new float[] {}, delta);
		assertNotEqualsNot(new float[] {1.0f}, new float[] {1.0f + difference}, delta);
		assertNotEqualsNot(new float[] {0.0f, 1.0f}, new float[] {0.0f - difference, 1.0f - difference}, delta);
	}
	
	public void testIntNotEquals() {
		assertNotEquals((int[]) null, new int[] {});
		assertNotEquals(new int[] {}, (int[]) null);
		assertNotEquals(new int[] {1}, new int[] {});
		assertNotEquals(new int[] {}, new int[] {0});
		assertNotEquals(new int[] {1}, new int[] {0});
		assertNotEquals(new int[] {1, 0}, new int[] {0, 1});

		assertNotEqualsNot((int[]) null, (int[]) null);
		assertNotEqualsNot(new int[] {}, new int[] {});
		assertNotEqualsNot(new int[] {1}, new int[] {1});
		assertNotEqualsNot(new int[] {0, 1}, new int[] {0, 1});
	}
	
	public void testLongNotEquals() {
		assertNotEquals((long[]) null, new long[] {});
		assertNotEquals(new long[] {}, (long[]) null);
		assertNotEquals(new long[] {1}, new long[] {});
		assertNotEquals(new long[] {}, new long[] {0});
		assertNotEquals(new long[] {1}, new long[] {0});
		assertNotEquals(new long[] {1, 0}, new long[] {0, 1});

		assertEquals((long[]) null, (long[]) null);
		assertEquals(new long[] {}, new long[] {});
		assertEquals(new long[] {1}, new long[] {1});
		assertEquals(new long[] {0, 1}, new long[] {0, 1});
	}

	public void testShortNotEquals() {
		assertNotEquals((short[]) null, new short[] {});
		assertNotEquals(new short[] {}, (short[]) null);
		assertNotEquals(new short[] {1}, new short[] {});
		assertNotEquals(new short[] {}, new short[] {0});
		assertNotEquals(new short[] {1}, new short[] {0});
		assertNotEquals(new short[] {1, 0}, new short[] {0, 1});

		assertNotEqualsNot((short[]) null, (short[]) null);
		assertNotEqualsNot(new short[] {}, new short[] {});
		assertNotEqualsNot(new short[] {1}, new short[] {1});
		assertNotEqualsNot(new short[] {0, 1}, new short[] {0, 1});
	}

	private void assertContains(boolean[] searchIn, boolean searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertContains(byte[] searchIn, byte searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertContains(char[] searchIn, char searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertContains(double[] searchIn, double searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertContains(float[] searchIn, float searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertContains(int[] searchIn, int searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertContains(long[] searchIn, long searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertContains(short[] searchIn, short searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertDoesntContain(boolean[] searchIn, boolean searchFor) {
		verify.not(shouldntContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertDoesntContain(byte[] searchIn, byte searchFor) {
		verify.not(shouldntContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertDoesntContain(char[] searchIn, char searchFor) {
		verify.not(shouldntContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertDoesntContain(double[] searchIn, double searchFor) {
		verify.not(shouldntContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertDoesntContain(float[] searchIn, float searchFor) {
		verify.not(shouldntContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertDoesntContain(int[] searchIn, int searchFor) {
		verify.not(shouldntContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertDoesntContain(long[] searchIn, long searchFor) {
		verify.not(shouldntContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private void assertDoesntContain(short[] searchIn, short searchFor) {
		verify.not(shouldntContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	private String shouldContain(boolean[] searchIn, boolean searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}
	
	private String shouldContain(byte[] searchIn, byte searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}

	private String shouldContain(char[] searchIn, char searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}

	private String shouldContain(double[] searchIn, double searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}

	private String shouldContain(float[] searchIn, float searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}

	private String shouldContain(int[] searchIn, int searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}

	private String shouldContain(long[] searchIn, long searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}

	private String shouldContain(short[] searchIn, short searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}

	private String shouldntContain(boolean[] searchIn, boolean searchFor) {
		return stringUtil.toString(searchIn) + " shouldn't contain '" + searchFor + "'";
	}

	private String shouldntContain(byte[] searchIn, byte searchFor) {
		return stringUtil.toString(searchIn) + " shouldn't contain '" + searchFor + "'";
	}

	private String shouldntContain(char[] searchIn, char searchFor) {
		return stringUtil.toString(searchIn) + " shouldn't contain '" + searchFor + "'";
	}

	private String shouldntContain(double[] searchIn, double searchFor) {
		return stringUtil.toString(searchIn) + " shouldn't contain '" + searchFor + "'";
	}

	private String shouldntContain(float[] searchIn, float searchFor) {
		return stringUtil.toString(searchIn) + " shouldn't contain '" + searchFor + "'";
	}

	private String shouldntContain(int[] searchIn, int searchFor) {
		return stringUtil.toString(searchIn) + " shouldn't contain '" + searchFor + "'";
	}

	private String shouldntContain(long[] searchIn, long searchFor) {
		return stringUtil.toString(searchIn) + " shouldn't contain '" + searchFor + "'";
	}

	private String shouldntContain(short[] searchIn, short searchFor) {
		return stringUtil.toString(searchIn) + " shouldn't contain '" + searchFor + "'";
	}
	
	public void assertEquals(boolean[] expected, boolean[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			verify.fail(shouldEqual(expected, actual));
		}
	}

	public void assertEquals(byte[] expected, byte[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			verify.fail(shouldEqual(expected, actual));
		}
	}

	public void assertEquals(char[] expected, char[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			verify.fail(shouldEqual(expected, actual));
		}
	}

	public void assertEquals(double[] expected, double[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			verify.fail(shouldEqual(expected, actual));
		}
	}

	public void assertEquals(double[] expected, double[] actual, double delta) {
		if (!arrayUtil.equal(expected, actual, delta)) {
			verify.fail(shouldEqual(expected, actual, delta));
		}
	}

	public void assertEquals(float[] expected, float[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			verify.fail(shouldEqual(expected, actual));
		}
	}

	public void assertEquals(float[] expected, float[] actual, float delta) {
		if (!arrayUtil.equal(expected, actual, delta)) {
			verify.fail(shouldEqual(expected, actual, delta));
		}
	}

	public void assertEquals(int[] expected, int[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			verify.fail(shouldEqual(expected, actual));
		}
	}

	public void assertEquals(long[] expected, long[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			verify.fail(shouldEqual(expected, actual));
		}
	}

	public void assertEquals(short[] expected, short[] actual) {
		if (!arrayUtil.equal(expected, actual)) {
			verify.fail(shouldEqual(expected, actual));
		}
	}

	public void assertEqualsNot(boolean[] expected, boolean[] actual) {
		if (arrayUtil.equal(expected, actual)) {
			verify.fail(shouldNotEqual(expected, actual));
		}
	}

	public void assertEqualsNot(byte[] expected, byte[] actual) {
		if (arrayUtil.equal(expected, actual)) {
			verify.fail(shouldNotEqual(expected, actual));
		}
	}

	public void assertEqualsNot(char[] expected, char[] actual) {
		if (arrayUtil.equal(expected, actual)) {
			verify.fail(shouldNotEqual(expected, actual));
		}
	}

	public void assertEqualsNot(double[] expected, double[] actual) {
		if (arrayUtil.equal(expected, actual)) {
			verify.fail(shouldNotEqual(expected, actual));
		}
	}

	public void assertEqualsNot(double[] expected, double[] actual, double delta) {
		if (arrayUtil.equal(expected, actual, delta)) {
			verify.fail(shouldNotEqual(expected, actual, delta));
		}
	}

	public void assertEqualsNot(float[] expected, float[] actual) {
		if (arrayUtil.equal(expected, actual)) {
			verify.fail(shouldNotEqual(expected, actual));
		}
	}

	public void assertEqualsNot(float[] expected, float[] actual, float delta) {
		if (arrayUtil.equal(expected, actual, delta)) {
			verify.fail(shouldNotEqual(expected, actual, delta));
		}
	}

	public void assertEqualsNot(int[] expected, int[] actual) {
		if (arrayUtil.equal(expected, actual)) {
			verify.fail(shouldNotEqual(expected, actual));
		}
	}

	public void assertEqualsNot(long[] expected, long[] actual) {
		if (arrayUtil.equal(expected, actual)) {
			verify.fail(shouldNotEqual(expected, actual));
		}
	}

	public void assertEqualsNot(short[] expected, short[] actual) {
		if (arrayUtil.equal(expected, actual)) {
			verify.fail(shouldNotEqual(expected, actual));
		}
	}
	
	public void assertNotEquals(boolean[] notExpected, boolean[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldNotEqual(notExpected, actual));
		}
	}
	
	public void assertNotEquals(byte[] notExpected, byte[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldNotEqual(notExpected, actual));
		}
	}

	public void assertNotEquals(char[] notExpected, char[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldNotEqual(notExpected, actual));
		}
	}

	public void assertNotEquals(double[] notExpected, double[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldNotEqual(notExpected, actual));
		}
	}

	public void assertNotEquals(double[] notExpected, double[] actual, double delta) {
		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			verify.fail(shouldNotEqual(notExpected, actual, delta));
		}
	}

	public void assertNotEquals(float[] notExpected, float[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldNotEqual(notExpected, actual));
		}
	}

	public void assertNotEquals(float[] notExpected, float[] actual, float delta) {
		if (!arrayUtil.notEqual(notExpected, actual, delta)) {
			verify.fail(shouldNotEqual(notExpected, actual, delta));
		}
	}

	public void assertNotEquals(int[] notExpected, int[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldNotEqual(notExpected, actual));
		}
	}

	public void assertNotEquals(long[] notExpected, long[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldNotEqual(notExpected, actual));
		}
	}

	public void assertNotEquals(short[] notExpected, short[] actual) {
		if (!arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldNotEqual(notExpected, actual));
		}
	}

	public void assertNotEqualsNot(boolean[] notExpected, boolean[] actual) {
		if (arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldEqual(notExpected, actual));
		}
	}
	
	public void assertNotEqualsNot(byte[] notExpected, byte[] actual) {
		if (arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldEqual(notExpected, actual));
		}
	}

	public void assertNotEqualsNot(char[] notExpected, char[] actual) {
		if (arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldEqual(notExpected, actual));
		}
	}

	public void assertNotEqualsNot(double[] notExpected, double[] actual) {
		if (arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldEqual(notExpected, actual));
		}
	}

	public void assertNotEqualsNot(double[] notExpected, double[] actual, double delta) {
		if (arrayUtil.notEqual(notExpected, actual, delta)) {
			verify.fail(shouldEqual(notExpected, actual, delta));
		}
	}

	public void assertNotEqualsNot(float[] notExpected, float[] actual) {
		if (arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldEqual(notExpected, actual));
		}
	}

	public void assertNotEqualsNot(float[] notExpected, float[] actual, float delta) {
		if (arrayUtil.notEqual(notExpected, actual, delta)) {
			verify.fail(shouldEqual(notExpected, actual, delta));
		}
	}

	public void assertNotEqualsNot(int[] notExpected, int[] actual) {
		if (arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldEqual(notExpected, actual));
		}
	}

	public void assertNotEqualsNot(long[] notExpected, long[] actual) {
		if (arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldEqual(notExpected, actual));
		}
	}

	public void assertNotEqualsNot(short[] notExpected, short[] actual) {
		if (arrayUtil.notEqual(notExpected, actual)) {
			verify.fail(shouldEqual(notExpected, actual));
		}
	}

	private String shouldEqual(boolean[] expected, boolean[] actual) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected);
	}

	private String shouldEqual(byte[] expected, byte[] actual) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected);
	}

	private String shouldEqual(char[] expected, char[] actual) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected);
	}

	private String shouldEqual(double[] expected, double[] actual) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected);
	}

	private String shouldEqual(double[] expected, double[] actual, double delta) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected) +
			" (with delta: " + delta + ")";
	}

	private String shouldEqual(float[] expected, float[] actual) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected);
	}

	private String shouldEqual(float[] expected, float[] actual, float delta) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected) +
		" (with delta: " + delta + ")";
	}

	private String shouldEqual(int[] expected, int[] actual) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected);
	}

	private String shouldEqual(long[] expected, long[] actual) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected);
	}

	private String shouldEqual(short[] expected, short[] actual) {
		return stringUtil.toString(actual) + " should equal " + stringUtil.toString(expected);
	}

	private String shouldNotEqual(boolean[] expected, boolean[] actual) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected);
	}

	private String shouldNotEqual(byte[] expected, byte[] actual) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected);
	}

	private String shouldNotEqual(char[] expected, char[] actual) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected);
	}

	private String shouldNotEqual(double[] expected, double[] actual) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected);
	}

	private String shouldNotEqual(double[] expected, double[] actual, double delta) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected) +
			" (with delta: " + delta + ")";
	}

	private String shouldNotEqual(float[] expected, float[] actual) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected);
	}

	private String shouldNotEqual(float[] expected, float[] actual, float delta) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected) +
		" (with delta: " + delta + ")";
	}

	private String shouldNotEqual(int[] expected, int[] actual) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected);
	}

	private String shouldNotEqual(long[] expected, long[] actual) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected);
	}

	private String shouldNotEqual(short[] expected, short[] actual) {
		return stringUtil.toString(actual) + " should not equal " + stringUtil.toString(expected);
	}

	private double smallestAdditionTo(double value) {
		return Double.longBitsToDouble(Double.doubleToLongBits(value) + 1);
	}

	private float smallestAdditionTo(float value) {
		return Float.intBitsToFloat(Float.floatToIntBits(value) + 1);
	}
}
