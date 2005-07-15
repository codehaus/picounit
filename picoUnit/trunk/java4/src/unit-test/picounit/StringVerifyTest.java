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
	
	private Verify mockVerify;
	private StringUtil mockStringUtil;

	private final Mocker should;
	
	public StringVerifyTest(Mocker should) {
		this.should = should;	
	}
	
	public void mock(Verify mockVerify, StringUtil mockStringUtil) {
		this.stringVerify = new DefaultStringVerify(mockVerify, mockStringUtil);

		this.mockVerify = mockVerify;
		this.mockStringUtil = mockStringUtil;
	}
	
	public void testContainsFailsIfSearchInIsNull() {
		mockVerify.fail("<searchIn> is null");

		should.expectAboveWhenTheFollowingOccurs();

		stringVerify.contains(null, "not null");
	}

	public void testContainsFailsIfSearchForIsNull() {
		mockVerify.fail("<searchFor> is null");

		should.expectAboveWhenTheFollowingOccurs();

		stringVerify.contains("not null", null);
	}

	public void testContainsFailsIfSearchInDoesNotContainSearchFor() {
		should.call(mockStringUtil.contains("abc", "def")).andReturn(false);
		mockVerify.fail("<abc> does not contain <def>");

		should.expectAboveWhenTheFollowingOccurs();

		stringVerify.contains("abc", "def");
	}
	
	public void testContainsPassesIfSearchInDoesContainSearchFor() {
		should.call(mockStringUtil.contains("abcdef", "abc")).andReturn(true);

		should.expectAboveWhenTheFollowingOccurs();
		
		stringVerify.contains("abcdef", "abc");
	}
	
	public void testDoesNotContainFailsIfSearchInIsNull() {
		mockVerify.fail("<searchIn> is null");

		should.expectAboveWhenTheFollowingOccurs();

		stringVerify.doesNotContain(null, "not null");
	}

	public void testDoesNotContainFailsIfSearchForIsNull() {
		mockVerify.fail("<searchFor> is null");

		should.expectAboveWhenTheFollowingOccurs();

		stringVerify.doesNotContain("not null", null);
	}

	public void testDoesNotContainFailsIfSearchInContainsSearchFor() {
		should.call(mockStringUtil.contains("abcdef", "def")).andReturn(true);
		mockVerify.fail("<abcdef> contains <def>");

		should.expectAboveWhenTheFollowingOccurs();

		stringVerify.doesNotContain("abcdef", "def");
	}

	public void testDoesNotContainPassesIfSearchInDoesNotContainSearchFor() {
		should.call(mockStringUtil.contains("abcdef", "xyz")).andReturn(false);

		should.expectAboveWhenTheFollowingOccurs();

		stringVerify.doesNotContain("abcdef", "xyz");
	}
}
