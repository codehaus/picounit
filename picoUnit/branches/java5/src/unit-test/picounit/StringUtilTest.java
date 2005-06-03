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
		verify.not(stringUtil.contains(null, "not null"));
	}
	
	public void testFailsIfSearchForIsNull() {
		verify.not(stringUtil.contains("not null", null));
	}
	
	public void testFailsIfSearchInDoesNotContainSearchFor() {
		verify.not(stringUtil.contains("abc", "def"));
	}
	
	public void testPassesIfSearchInDoesContainSearchFor() {
		verify.that(stringUtil.contains("abcdef", "abc"));
	}
	
	public void testProducesOriginalStringWhenAskedToReplaceAStringThatDoesNotOccur() {
		verify.equal("abcdef", stringUtil.replace("abcdef", "---", "+++"));
	}
	
	public void testReplaceSingleOccurence() {
		verify.equal("+++abcdef", stringUtil.replace("---abcdef", "---", "+++"));
		verify.equal("abc+++def", stringUtil.replace("abc---def", "---", "+++"));
		verify.equal("abcdef+++", stringUtil.replace("abcdef---", "---", "+++"));
	}
	
	public void testReplacesMultipleOccurences() {
		verify.equal("+++abc+++def+++", stringUtil.replace("---abc---def---", "---", "+++"));
	}
	
	public void testToString() {
		verify.equal("null", stringUtil.toString((boolean[]) null));
		verify.equal("null", stringUtil.toString((byte[]) null));
		verify.equal("null", stringUtil.toString((char[]) null));
		verify.equal("null", stringUtil.toString((double[]) null));
		verify.equal("null", stringUtil.toString((float[]) null));
		verify.equal("null", stringUtil.toString((int[]) null));
		verify.equal("null", stringUtil.toString((long[]) null));
		verify.equal("null", stringUtil.toString((short[]) null));

		verify.equal("[]", stringUtil.toString(new boolean[] {}));
		verify.equal("[]", stringUtil.toString(new byte[] {}));
		verify.equal("[]", stringUtil.toString(new char[] {}));
		verify.equal("[]", stringUtil.toString(new double[] {}));
		verify.equal("[]", stringUtil.toString(new float[] {}));
		verify.equal("[]", stringUtil.toString(new int[] {}));
		verify.equal("[]", stringUtil.toString(new long[] {}));
		verify.equal("[]", stringUtil.toString(new short[] {}));
	}
}
