/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.verify.ArrayUtil;
import picounit.verify.DefaultVerify;
import picounit.verify.ImmediateThrower;
import picounit.verify.StringUtil;

import java.util.Arrays;

import junit.framework.AssertionFailedError;

public class VerifyTest implements previous.picounit.Test {
	private Verify verify;
	
	private ArrayUtil arrayUtil;
	private StringUtil stringUtil;
	
	private final previous.picounit.Mocker mocker;
	private final previous.picounit.Verify previousVerify;

	public VerifyTest(previous.picounit.Mocker mocker, previous.picounit.Verify previousVerify) {
		this.mocker = mocker;
		this.previousVerify = previousVerify;
	}

	public void mock(ArrayUtil arrayUtil, StringUtil stringUtil) {
		this.verify = new DefaultVerify(arrayUtil, stringUtil, new ImmediateThrower());

		this.arrayUtil = arrayUtil;
		this.stringUtil = stringUtil;
	}

	public void testFail() {
		try {
			verify.fail();
		}
		catch (AssertionFailedError assertionFailedError) {
			return;
		}

		fail();
	}
	
	public void testFailWithMessage() {
	 	try {
	 		verify.fail("message");
	 	}
	 	catch (AssertionFailedError assertionFailedError) {
	 		if (!"message".equals(assertionFailedError.getMessage())) {
	 			throw new AssertionFailedError();
	 		}

	 		return;
	 	}
	 	
	 	fail();
	}
	
	public void testThat() {
		verify.that(true);

		try {
			verify.that(false);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage("true", "false", assertionFailedError.getMessage());

			return;
		}

		fail();
	}

	public void testThatWithMessage() {
		verify.that("message", true);

		try {
			verify.that("message", false);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage("message", "true", "false", assertionFailedError.getMessage());

			return;
		}

		fail();
	}
	
	public void testNot() {
		verify.not(false);
		
		try {
			verify.not(true);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage("false", "true", assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}

	public void testNotWithMessage() {
		verify.not("message", false);

		try {
			verify.not("message", true);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage("message", "false", "true", assertionFailedError.getMessage());

			return;
		}

		fail();
	}

	public void testBooleanEquals() {
	 	verify.equal(true, true);
	 	
	 	try {
	 		verify.equal(true, false);
	 	}
	 	catch (AssertionFailedError assertionFailedError) {
			expectMessage("true", "false", assertionFailedError.getMessage());

	 		return;
	 	}

	 	fail();
	}
	
	public void testBooleanEqualWithMessage() {
	 	verify.equal("message", true, true);
	 	
	 	try {
	 		verify.equal("message", true, false);
	 	}
	 	catch (AssertionFailedError assertionFailedError) {
	 		expectMessage("message", "true", "false", assertionFailedError.getMessage());

	 		return;
	 	}

	 	fail();
	}
	
	public void testLongEquals() {
		verify.equal(1, 1);
		
		try {
			verify.equal(1, 2);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage("1", "2", assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}

	public void testLongEqualsWithMessage() {
		verify.equal("message", 1, 1);
		
		try {
			verify.equal("message", 1, 2);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage("message", "1", "2", assertionFailedError.getMessage());

			return;
		}

		fail();
	}

	public void testObjectEquals() {
		verify.equal("one", "one");
		verify.equal((Object) null, (Object) null);

		try {
			verify.equal("one", "two");
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage("one", "two", assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}

	public void testObjectEqualsWithMessage() {
		verify.equal("message", "one", "one");
		
		try {
			verify.equal("message","expected", "actual");
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage("message", "expected", "actual", assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}
	
	public void testBooleanArrayEqualsWhenTrue() {
		boolean[] expected = new boolean[] {};
		boolean[] actual = new boolean[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(true);
		
		mocker.replay();

		verify.equal(expected, actual);
	}

	public void testByteArrayEqualsWhenTrue() {
		byte[] expected = new byte[] {};
		byte[] actual = new byte[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(true);
		
		mocker.replay();

		verify.equal(expected, actual);
	}
	
	public void testCharArrayEqualsWhenTrue() {
		char[] expected = new char[] {};
		char[] actual = new char[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(true);
		
		mocker.replay();

		verify.equal(expected, actual);
	}
	
	public void testDoubleArrayEqualsWhenTrue() {
		double[] expected = new double[] {};
		double[] actual = new double[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(true);
		
		mocker.replay();

		verify.equal(expected, actual);
	}
	
	public void testDoubleArrayEqualsWithDeltaWhenTrue() {
		double[] expected = new double[] {};
		double[] actual = new double[] {};
		double delta = 0.0;
		
		mocker.expect(arrayUtil.equal(expected, actual, delta)).andReturn(true);
		
		mocker.replay();

		verify.equal(expected, actual, delta);
	}
	
	public void testFloatArrayEqualsWhenTrue() {
		float[] expected = new float[] {};
		float[] actual = new float[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(true);
		
		mocker.replay();

		verify.equal(expected, actual);
	}
	
	public void testFloatArrayEqualsWithDeltaWhenTrue() {
		float[] expected = new float[] {};
		float[] actual = new float[] {};
		float delta = 0.0f;
		
		mocker.expect(arrayUtil.equal(expected, actual, delta)).andReturn(true);
		
		mocker.replay();
		
		verify.equal(expected, actual, delta);
	}
	
	public void testIntArrayEqualsWhenTrue() {
		int[] expected = new int[] {};
		int[] actual = new int[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.equal(expected, actual);
	}
	
	public void testLongArrayEqualsWhenTrue() {
		long[] expected = new long[] {};
		long[] actual = new long[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.equal(expected, actual);
	}
	
	public void testShortArrayEqualsWhenTrue() {
		short[] expected = new short[] {};
		short[] actual = new short[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(true);
		
		mocker.replay();

		verify.equal(expected, actual);
	}

	public void testBooleanArrayEqualsWhenFalse() {
		boolean[] expected = new boolean[] {};
		boolean[] actual = new boolean[] {};

		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");

		mocker.replay();

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]>", assertionFailedError.getMessage());

			return;
		}

		fail("AssertionFailedError expected");
	}

	public void testByteArrayEqualsWhenFalse() {
		byte[] expected = new byte[] {};
		byte[] actual = new byte[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}

	public void testCharArrayEqualsWhenFalse() {
		char[] expected = new char[] {};
		char[] actual = new char[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");;
	}

	public void testDoubleArrayEqualsWhenFalse() {
		double[] expected = new double[] {};
		double[] actual = new double[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");;
	}

	public void testDoubleArrayEqualsWithDeltaWhenFalse() {
		double[] expected = new double[] {};
		double[] actual = new double[] {};
		double delta = 0.0;
		
		mocker.expect(arrayUtil.equal(expected, actual, delta)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual, delta);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]> (with delta: " + delta + ")",
				assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");;
	}

	public void testFloatArrayEqualsWhenFalse() {
		float[] expected = new float[] {};
		float[] actual = new float[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");;
	}

	public void testFloatArrayEqualsWithDeltaWhenFalse() {
		float[] expected = new float[] {};
		float[] actual = new float[] {};
		float delta = 0.0f;
		
		mocker.expect(arrayUtil.equal(expected, actual, delta)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual, delta);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]> (with delta: " + delta + ")",
				assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");;
	}

	public void testIntArrayEqualsWhenFalse() {
		int[] expected = new int[] {};
		int[] actual = new int[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");;
	}

	public void testLongArrayEqualsWhenFalse() {
		long[] expected = new long[] {};
		long[] actual = new long[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");;
	}

	public void testShortArrayEqualsWhenFalse() {
		short[] expected = new short[] {};
		short[] actual = new short[] {};
		
		mocker.expect(arrayUtil.equal(expected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(expected)).andReturn("[expected]");
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		
		mocker.replay();

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected: <[expected]>, but was: <[actual]>", assertionFailedError.getMessage());
	
			return;
		}
		
		fail("AssertionFailedError expected");;
	}

	public void testObjectArraysEquals() {
		verify.equal(new String[] {"one", "two"}, new String[] {"one", "two"});
		
		String[] expected = new String[] {"one", "two"};
		String[] actual = new String[] {"two", "one"};

		try {
			verify.equal(expected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectMessage(toString(expected), toString(actual), assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}

	public void testBooleanNotEquals() {
	 	verify.notEqual(true, false);
	 	
	 	try {
	 		verify.notEqual(true, true);
	 	}
	 	catch (AssertionFailedError assertionFailedError) {
	 		expectNotEqualMessage("true", assertionFailedError.getMessage());

	 		return;
	 	}

	 	fail();
	}
	
	public void testBooleanNotEqualWithMessage() {
	 	verify.notEqual("message", true, false);
	 	
	 	try {
	 		verify.notEqual("message", true, true);
	 	}
	 	catch (AssertionFailedError assertionFailedError) {
	 		expectNotEqualMessage("message", "true", assertionFailedError.getMessage());

			return;
	 	}

	 	fail();
	}
	
	public void testLongNotEquals() {
		verify.notEqual(1, 2);
		
		try {
			verify.notEqual(1, 1);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectNotEqualMessage("1", assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}

	public void testLongNotEqualsWithMessage() {
		verify.notEqual("message", 1, 2);
		
		try {
			verify.notEqual("message", 1, 1);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectNotEqualMessage("message", "1", assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}

	public void testBooleanArrayNotEqualsWhenTrue() {
		boolean[] notExpected = new boolean[] {};
		boolean[] actual = new boolean[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual);
	}

	public void testByteArrayNotEqualsWhenTrue() {
		byte[] notExpected = new byte[] {};
		byte[] actual = new byte[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual);
	}
	
	public void testCharArrayNotEqualsWhenTrue() {
		boolean[] notExpected = new boolean[] {};
		boolean[] actual = new boolean[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual);
	}
	
	public void testDoubleArrayNotEqualsWhenTrue() {
		double[] notExpected = new double[] {};
		double[] actual = new double[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual);
	}
	
	public void testDoubleArrayNotEqualsWithDeltaWhenTrue() {
		double[] notExpected = new double[] {};
		double[] actual = new double[] {};
		double delta = 0.0;
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual, delta)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual, delta);
	}
	
	public void testFloatArrayNotEqualsWhenTrue() {
		float[] notExpected = new float[] {};
		float[] actual = new float[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual);
	}
	
	public void testFloatArrayNotEqualsWithDeltaWhenTrue() {
		float[] notExpected = new float[] {};
		float[] actual = new float[] {};
		float delta = 0.0f;
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual, delta)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual, delta);
	}
	
	public void testIntArrayNotEqualsWhenTrue() {
		int[] notExpected = new int[] {};
		int[] actual = new int[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual);
	}
	
	public void testLongArrayNotEqualsWhenTrue() {
		long[] notExpected = new long[] {};
		long[] actual = new long[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual);
	}
	
	public void testShortArrayNotEqualsWhenTrue() {
		short[] notExpected = new short[] {};
		short[] actual = new short[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(true);
		
		mocker.replay();
		
		verify.notEqual(notExpected, actual);
	}

	public void testBooleanArrayNotEqualsWhenFalse() {
		boolean[] notExpected = new boolean[] {};
		boolean[] actual = new boolean[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected not equal: <[notExpected]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}

	public void testByteArrayNotEqualsWhenFalse() {
		byte[] notExpected = new byte[] {};
		byte[] actual = new byte[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected not equal: <[notExpected]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}
	
	public void testCharArrayNotEqualsWhenFalse() {
		boolean[] notExpected = new boolean[] {};
		boolean[] actual = new boolean[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected not equal: <[notExpected]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}
	
	public void testDoubleArrayNotEqualsWhenFalse() {
		double[] notExpected = new double[] {};
		double[] actual = new double[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected not equal: <[notExpected]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}
	
	public void testDoubleArrayNotEqualsWithDeltaWhenFalse() {
		double[] notExpected = new double[] {};
		double[] actual = new double[] {};
		double delta = 0.0;
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual, delta)).andReturn(false);
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual, delta);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected <[actual]> not equal <[notExpected]> (with delta: " + delta + ")",
				assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}
	
	public void testFloatArrayNotEqualsWhenFalse() {
		float[] notExpected = new float[] {};
		float[] actual = new float[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected not equal: <[notExpected]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}
	
	public void testFloatArrayNotEqualsWithDeltaWhenFalse() {
		float[] notExpected = new float[] {};
		float[] actual = new float[] {};
		float delta = 0.0f;
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual, delta)).andReturn(false);
		mocker.expect(stringUtil.toString(actual)).andReturn("[actual]");
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual, delta);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected <[actual]> not equal <[notExpected]> (with delta: " + delta + ")",
				assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}
	
	public void testIntArrayNotEqualsWhenFalse() {
		int[] notExpected = new int[] {};
		int[] actual = new int[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected not equal: <[notExpected]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}
	
	public void testLongArrayNotEqualsWhenFalse() {
		long[] notExpected = new long[] {};
		long[] actual = new long[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected not equal: <[notExpected]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}
	
	public void testShortArrayNotEqualsWhenFalse() {
		short[] notExpected = new short[] {};
		short[] actual = new short[] {};
		
		mocker.expect(arrayUtil.notEqual(notExpected, actual)).andReturn(false);
		mocker.expect(stringUtil.toString(notExpected)).andReturn("[notExpected]");

		mocker.replay();

		try {
			verify.notEqual(notExpected, actual);
		}
		catch (AssertionFailedError assertionFailedError) {
			assertEquals("expected not equal: <[notExpected]>", assertionFailedError.getMessage());

			return;
		}
		
		fail("AssertionFailedError expected");
	}

	public void testObjectNotEquals() {
		verify.notEqual("one", "two");
		
		try {
			verify.notEqual("one", "one");
		}
		catch (AssertionFailedError assertionFailedError) {
			expectNotEqualMessage("one", assertionFailedError.getMessage());

			return;
		}

		fail();
	}
	
	public void testObjectNotEqualsWithMessage() {
		verify.notEqual("message", "one", "two");
		
		try {
			verify.notEqual("message","one", "one");
		}
		catch (AssertionFailedError assertionFailedError) {
			expectNotEqualMessage("message", "one", assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}
	
	public void testObjectSame() {
		String same = "same";
		String notSame = "notSame";

		verify.same(same, same);
		
		try {
			verify.same(same, notSame);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectSameMessage("same", "notSame", assertionFailedError.getMessage());

			return;
		}
		
		fail();
	}
	
	public void testObjectSameWithMessage() {
		String same = "same";
		String notSame = "notSame";

		verify.same("message", same, same);

		try {
			verify.same("message", same, notSame);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectSameMessage("message", "same", "notSame", assertionFailedError.getMessage());

			return;	
		}

		fail();
	}
	
	public void testObjectNotSame() {
		String same = "same";
		String notSame = "notSame";

		verify.notSame(same, notSame);

		try {
			verify.notSame(same, same);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectNotSameMessage("same", assertionFailedError.getMessage());

			return;
		}

		fail();
	}
	
	public void testObjectNotSameWithMessage() {
		String same = "same";
		String notSame = "notSame";

		verify.notSame("message", same, notSame);

		try {
			verify.notSame("message", same, same);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectNotSameMessage("message", same, assertionFailedError.getMessage());

			return;
		}

		fail();
	}

	public void testIsNull() {
		verify.isNull(null);

		try {
			verify.isNull("something");
		}
		catch (AssertionFailedError assertionFailedError) {
			return;
		}

		fail();
	}
	
	public void testNotNull() {
		verify.notNull("not null");

		try {
			verify.notNull(null);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectNotEqualMessage(null, assertionFailedError.getMessage());

			return;
		}

		fail();
	}
	
	public void testNotNullWithMessage() {
		verify.notNull("message", "not null");

		try {
			verify.notNull("message", null);
		}
		catch (AssertionFailedError assertionFailedError) {
			expectNotEqualMessage("message", null, assertionFailedError.getMessage());
			
			return;
		}

		fail();
	}

	private void expectMessage(String expected, String actual, String message) {
		expectMessage("", expected, actual, message);
	}

	private void expectMessage(String prefix, String expected, String actual, String message) {
		if (prefix.length() != 0) {
			prefix = prefix + ", ";
		}

		String expectedMessage = prefix + "expected: <" + expected + ">, but was: <" + actual + ">";
		String actualMessage = message;

		if (!expectedMessage.equals(actualMessage)) {
			fail("expected: \"" + expectedMessage + "\" but was: " + actualMessage); 
		}
	}
	
	private void expectNotEqualMessage(String expected, String message) {
		expectNotEqualMessage("", expected, message);
	}
	
	private void expectNotEqualMessage(String prefix, String expected, String actual) {
		if (prefix.length() != 0) {
			prefix = prefix + ", ";
		}

		String expectedMessage = prefix + "expected not equal: <" + expected + ">";

		if (!expectedMessage.equals(actual)) {
			fail("expected: \"" + expectedMessage + "\" but was: " + actual); 
		}
	}
	
	private void expectSameMessage(String expected, String actual, String message) {
		expectSameMessage("", expected, actual, message);
	}
	
	private void expectSameMessage(String prefix, String expected, String actual, String message) {
		if (prefix.length() != 0) {
			prefix = prefix + ", ";
		}

		String expectedMessage = prefix + "expected same: <" + expected + ">, but was: <" + actual + ">";
		String actualMessage = message;

		if (!expectedMessage.equals(actualMessage)) {
			fail("expected: \"" + expectedMessage + "\" but was: " + actualMessage); 
		}
	}
	
	private void expectNotSameMessage(String expected, String message) {
		expectNotSameMessage("", expected, message);
	}
	
	private void expectNotSameMessage(String prefix, String expected, String actual) {
		if (prefix.length() != 0) {
			prefix = prefix + ", ";
		}

		String expectedMessage = prefix + "expected not same: <" + expected + ">";

		if (!expectedMessage.equals(actual)) {
			fail("expected: \"" + expectedMessage + "\" but was: " + actual); 
		}
	}

	private String toString(String[] expected) {
		return Arrays.asList(expected).toString();
	}

	private void assertEquals(String expected, String actual) {
		previousVerify.equal(expected, actual);
	}

	private void fail() {
		previousVerify.fail();
	}

	private void fail(String message) {
		previousVerify.fail(message);
	}
}
