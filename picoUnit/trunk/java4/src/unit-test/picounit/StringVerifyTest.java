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

@SuppressWarnings("deprecation")
public class StringVerifyTest implements Test {
	private StringVerify stringVerify;
	
	private Verify verify;
	private StringUtil stringUtil;

	private final Mocker should;
	
	public StringVerifyTest(Mocker should) {
		this.should = should;	
	}
	
	public void mock(Verify verify, StringUtil stringUtil) {
		this.stringVerify = new DefaultStringVerify(verify, stringUtil);

		this.verify = verify;
		this.stringUtil = stringUtil;
	}
	
	public void testContainsFailsIfSearchInIsNull() {
		verify.fail("<searchIn> is null");

		should.doAboveWhen();

		stringVerify.contains(null, "not null");
	}

	public void testContainsFailsIfSearchForIsNull() {
		verify.fail("<searchFor> is null");

		should.doAboveWhen();

		stringVerify.contains("not null", null);
	}

	public void testContainsFailsIfSearchInDoesNotContainSearchFor() {
		should.call(stringUtil.contains("abc", "def")).andReturn(false);
		verify.fail("<abc> does not contain <def>");

		should.doAboveWhen();

		stringVerify.contains("abc", "def");
	}
	
	public void testContainsPassesIfSearchInDoesContainSearchFor() {
		should.call(stringUtil.contains("abcdef", "abc")).andReturn(true);
		should.doAboveWhen();
		
		stringVerify.contains("abcdef", "abc");
	}
	
	public void testDoesNotContainFailsIfSearchInIsNull() {
		verify.fail("<searchIn> is null");

		should.doAboveWhen();

		stringVerify.doesNotContain(null, "not null");
	}

	public void testDoesNotContainFailsIfSearchForIsNull() {
		verify.fail("<searchFor> is null");

		should.doAboveWhen();

		stringVerify.doesNotContain("not null", null);
	}

	public void testDoesNotContainFailsIfSearchInContainsSearchFor() {
		should.call(stringUtil.contains("abcdef", "def")).andReturn(true);
		verify.fail("<abcdef> contains <def>");

		should.doAboveWhen();

		stringVerify.doesNotContain("abcdef", "def");
	}

	public void testDoesNotContainPassesIfSearchInDoesNotContainSearchFor() {
		should.call(stringUtil.contains("abcdef", "xyz")).andReturn(false);
		should.doAboveWhen();

		stringVerify.doesNotContain("abcdef", "xyz");
	}
}
