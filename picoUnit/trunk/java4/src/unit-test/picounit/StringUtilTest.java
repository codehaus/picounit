/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.verify.StringUtil;
import previous.picounit.Test;
import previous.picounit.Verify;

public class StringUtilTest implements Test {
	private final StringUtil stringUtil = new StringUtil();
	private final Verify verify;

	public StringUtilTest(Verify verify) {
		this.verify = verify;
	}
	
	public void testFailsIfSearchInIsNull() {
		verify.thatBoolean(stringUtil.contains(null, "not null")).isFalse();
	}
	
	public void testFailsIfSearchForIsNull() {
		verify.thatBoolean(stringUtil.contains("not null", null)).isFalse();
	}
	
	public void testFailsIfSearchInDoesNotContainSearchFor() {
		verify.thatBoolean(stringUtil.contains("abc", "def")).isFalse();
	}
	
	public void testPassesIfSearchInDoesContainSearchFor() {
		verify.thatBoolean(stringUtil.contains("abcdef", "abc")).isTrue();
	}
	
	public void testProducesOriginalStringWhenAskedToReplaceAStringThatDoesNotOccur() {
		verify.that(stringUtil.replace("abcdef", "---", "+++")).isEqualTo("abcdef");
	}
	
	public void testReplaceSingleOccurence() {
		verify.that(stringUtil.replace("---abcdef", "---", "+++")).isEqualTo("+++abcdef");
		verify.that(stringUtil.replace("abc---def", "---", "+++")).isEqualTo("abc+++def");
		verify.that(stringUtil.replace("abcdef---", "---", "+++")).isEqualTo("abcdef+++");
	}
	
	public void testReplacesMultipleOccurences() {
		verify.that(stringUtil.replace("---abc---def---", "---", "+++")).isEqualTo("+++abc+++def+++");
	}
	
	public void testToString() {
		verify.that(stringUtil.toString((boolean[]) null)).isEqualTo("null");
		verify.that(stringUtil.toString((byte[]) null)).isEqualTo("null");
		verify.that(stringUtil.toString((char[]) null)).isEqualTo("null");
		verify.that(stringUtil.toString((double[]) null)).isEqualTo("null");
		verify.that(stringUtil.toString((float[]) null)).isEqualTo("null");
		verify.that(stringUtil.toString((int[]) null)).isEqualTo("null");
		verify.that(stringUtil.toString((long[]) null)).isEqualTo("null");
		verify.that(stringUtil.toString((short[]) null)).isEqualTo("null");

		verify.that(stringUtil.toString(new boolean[] {})).isEqualTo("[]");
		verify.that(stringUtil.toString(new byte[] {})).isEqualTo("[]");
		verify.that(stringUtil.toString(new char[] {})).isEqualTo("[]");
		verify.that(stringUtil.toString(new double[] {})).isEqualTo("[]");
		verify.that(stringUtil.toString(new float[] {})).isEqualTo("[]");
		verify.that(stringUtil.toString(new int[] {})).isEqualTo("[]");
		verify.that(stringUtil.toString(new long[] {})).isEqualTo("[]");
		verify.that(stringUtil.toString(new short[] {})).isEqualTo("[]");
	}
}
