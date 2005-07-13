/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.verify.ArrayUtil;
import picounit.verify.DefaultArrayVerify;
import picounit.verify.StringUtil;
import previous.picounit.Mocker;
import previous.picounit.Test;

@SuppressWarnings("deprecation")
public class ArrayVerifyTest implements Test {
	private final Mocker should;
	
	private Verify verify;
	private ArrayUtil arrayUtil;
	private StringUtil stringUtil;

	private ArrayVerify arrayVerify;
	
	public ArrayVerifyTest(Mocker mocker) {
		this.should = mocker;
	}
	
	public void mock(Verify verify, ArrayUtil arrayUtil, StringUtil stringUtil) {
		this.arrayVerify = new DefaultArrayVerify(verify, arrayUtil, stringUtil);
		
		this.verify = verify;
		this.arrayUtil = arrayUtil;
		this.stringUtil = stringUtil;
	}
	
	public void testBooleanArrayContains() {
		boolean[] searchIn = new boolean[] {};
		boolean searchFor = true;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();

		arrayVerify.contains(searchIn, searchFor);
	}

	public void testByteArrayContains() {
		byte[] searchIn = new byte[] {};
		byte searchFor = 0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor);
	}
	
	public void testCharArrayContains() {
		char[] searchIn = new char[] {};
		char searchFor = 'a';

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor);
	}
	
	public void testDoubleArrayContains() {
		double[] searchIn = new double[] {};
		double searchFor = 0.0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor);
	}
	
	public void testDoubleArrayContainsUsingDelta() {
		double[] searchIn = new double[] {};
		double searchFor = 0.0;
		double delta = 0.0;

		should.call(arrayUtil.contains(searchIn, searchFor, delta)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor, delta);
	}

	public void testFloatArrayContains() {
		float[] searchIn = new float[] {};
		float searchFor = 0.0f;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor);
	}
	
	public void testFloatArrayContainsUsingDelta() {
		float[] searchIn = new float[] {};
		float searchFor = 0.0f;
		float delta = 0.0f;

		should.call(arrayUtil.contains(searchIn, searchFor, delta)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor, delta);
	}

	public void testIntegerArrayContains() {
		int[] searchIn = new int[] {};
		int searchFor = 0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor);
	}

	public void testLongArrayContains() {
		long[] searchIn = new long[] {};
		long searchFor = 0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor);
	}

	public void testShortArrayContains() {
		short[] searchIn = new short[] {};
		short searchFor = 0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.that("[searchIn] should contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.contains(searchIn, searchFor);
	}
	
	///
	
	public void testBooleanArrayDoesNotContain() {
		boolean[] searchIn = new boolean[] {};
		boolean searchFor = true;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor);
	}

	public void testByteArrayDoesNotContain() {
		byte[] searchIn = new byte[] {};
		byte searchFor = 0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor);
	}
	
	public void testCharArrayDoesNotContain() {
		char[] searchIn = new char[] {};
		char searchFor = 'a';

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor);
	}
	
	public void testDoubleArrayDoesNotContain() {
		double[] searchIn = new double[] {};
		double searchFor = 0.0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor);
	}
	
	public void testDoubleArrayDoesNotContainUsingDelta() {
		double[] searchIn = new double[] {};
		double searchFor = 0.0;
		double delta = 0.0;

		should.call(arrayUtil.contains(searchIn, searchFor, delta)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor, delta);
	}

	public void testFloatArrayDoesNotContain() {
		float[] searchIn = new float[] {};
		float searchFor = 0.0f;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor);
	}
	
	public void testFloatArrayDoesNotContainUsingDelta() {
		float[] searchIn = new float[] {};
		float searchFor = 0.0f;
		float delta = 0.0f;

		should.call(arrayUtil.contains(searchIn, searchFor, delta)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor, delta);
	}

	public void testIntegerArrayDoesNotContain() {
		int[] searchIn = new int[] {};
		int searchFor = 0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor);
	}

	public void testLongArrayDoesNotContain() {
		long[] searchIn = new long[] {};
		long searchFor = 0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();
		
		arrayVerify.doesNotContain(searchIn, searchFor);
	}

	public void testShortArrayDoesNotContain() {
		short[] searchIn = new short[] {};
		short searchFor = 0;

		should.call(arrayUtil.contains(searchIn, searchFor)).andReturn(true);
		should.call(stringUtil.toString(searchIn)).andReturn("[searchIn]");
		verify.not("[searchIn] should not contain '" + searchFor + "'", true);

		should.doAboveWhen();

		arrayVerify.doesNotContain(searchIn, searchFor);
	}
}
