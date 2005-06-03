/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.verify.DefaultStringVerify;
import picounit.verify.StringUtil;
import previous.picounit.Mocker;
import previous.picounit.Test;

public class StringVerifyTest implements Test {
	private StringVerify stringVerify;
	
	private Verify verify;
	private StringUtil stringUtil;

	private final Mocker mocker;
	
	public StringVerifyTest(Mocker mocker) {
		this.mocker = mocker;	
	}
	
	public void mock(Verify verify, StringUtil stringUtil) {
		this.stringVerify = new DefaultStringVerify(verify, stringUtil);

		this.verify = verify;
		this.stringUtil = stringUtil;
	}
	
	public void testFailsIfSearchInIsNull() {
		verify.fail("<searchIn> is null !");

		mocker.replay();

		stringVerify.contains(null, "not null");
	}

	public void testFailsIfSearchForIsNull() {
		verify.fail("<searchFor> is null !");

		mocker.replay();

		stringVerify.contains("not null", null);
	}

	public void testFailsIfSearchInDoesNotContainSearchFor() {
		mocker.expect(stringUtil.contains("abc", "def")).andReturn(false);
		verify.fail("'abc' does not contain 'def'");

		mocker.replay();

		stringVerify.contains("abc", "def");
	}
	
	public void testPassesIfSearchInDoesContainSearchFor() {
		mocker.expect(stringUtil.contains("abcdef", "abc")).andReturn(true);
		mocker.replay();
		
		stringVerify.contains("abcdef", "abc");
	}
}
