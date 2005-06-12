/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.ArrayVerify;
import picounit.Verify;

public class DefaultArrayVerify implements ArrayVerify {
	private final Verify verify;
	private final ArrayUtil arrayUtil;
	private final StringUtil stringUtil;

	public DefaultArrayVerify(Verify verify, ArrayUtil arrayUtil, StringUtil stringUtil) {
		this.verify = verify;
		this.arrayUtil = arrayUtil;
		this.stringUtil = stringUtil;
	}

	public void contains(boolean[] searchIn, boolean searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void contains(byte[] searchIn, byte searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void contains(char[] searchIn, char searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void contains(double[] searchIn, double searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void contains(double[] searchIn, double searchFor, double delta) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor, delta));
	}
	
	public void contains(float[] searchIn, float searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void contains(float[] searchIn, float searchFor, float delta) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor, delta));
	}

	public void contains(int[] searchIn, int searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void contains(long[] searchIn, long searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}
	
	public void contains(Object[] searchIn, Object searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void contains(short[] searchIn, short searchFor) {
		verify.that(shouldContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}
	
	public void doesNotContain(boolean[] searchIn, boolean searchFor) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void doesNotContain(byte[] searchIn, byte searchFor) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void doesNotContain(char[] searchIn, char searchFor) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void doesNotContain(double[] searchIn, double searchFor) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void doesNotContain(double[] searchIn, double searchFor, double delta) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor, delta));
	}
	
	public void doesNotContain(float[] searchIn, float searchFor) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void doesNotContain(float[] searchIn, float searchFor, float delta) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor, delta));
	}

	public void doesNotContain(int[] searchIn, int searchFor) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void doesNotContain(long[] searchIn, long searchFor) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
	}

	public void doesNotContain(short[] searchIn, short searchFor) {
		verify.not(shouldNotContain(searchIn, searchFor), arrayUtil.contains(searchIn, searchFor));
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

	private String shouldContain(Object[] searchIn, Object searchFor) {
		return stringUtil.toString(searchIn) + " should contain '" + searchFor + "'";
	}

	private String shouldNotContain(boolean[] searchIn, boolean searchFor) {
		return stringUtil.toString(searchIn) + " should not contain '" + searchFor + "'";
	}
	
	private String shouldNotContain(byte[] searchIn, byte searchFor) {
		return stringUtil.toString(searchIn) + " should not contain '" + searchFor + "'";
	}

	private String shouldNotContain(char[] searchIn, char searchFor) {
		return stringUtil.toString(searchIn) + " should not contain '" + searchFor + "'";
	}

	private String shouldNotContain(double[] searchIn, double searchFor) {
		return stringUtil.toString(searchIn) + " should not contain '" + searchFor + "'";
	}

	private String shouldNotContain(float[] searchIn, float searchFor) {
		return stringUtil.toString(searchIn) + " should not contain '" + searchFor + "'";
	}

	private String shouldNotContain(int[] searchIn, int searchFor) {
		return stringUtil.toString(searchIn) + " should not contain '" + searchFor + "'";
	}

	private String shouldNotContain(long[] searchIn, long searchFor) {
		return stringUtil.toString(searchIn) + " should not contain '" + searchFor + "'";
	}

	private String shouldNotContain(short[] searchIn, short searchFor) {
		return stringUtil.toString(searchIn) + " should not contain '" + searchFor + "'";
	}
}
