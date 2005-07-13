/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.Verify;

@SuppressWarnings("deprecation")
public class DefaultStringVerify implements picounit.StringVerify {
	private final StringUtil stringUtil;
	private final Verify verify;

	public DefaultStringVerify(Verify verify, StringUtil stringUtil) {
		this.verify = verify;
		this.stringUtil = stringUtil;
	}

	public void equalsIgnoringCase(String message, String expected, String actual) {
		if (!bothNull(expected, actual) && !stringUtil.equalsIgnoringCase(expected, actual)) {
			verify.fail(failureMessage(message, "<" + actual + "> does not equal <" + expected +
				">, ignoring case"));
		}
	}

	public void doesNotEqualIgnoringCase(String message, String expected, String actual) {
		if (bothNull(expected, actual) || stringUtil.equalsIgnoringCase(expected, actual)) {
			verify.fail(failureMessage(message, "<" + actual + "> does equals <" + expected +
				">, ignoring case"));
		}
	}

	public void contains(String searchIn, String searchFor) {
		if (searchIn == null) {
			verify.fail("<searchIn> is null");
		}
		else if (searchFor == null) {
			verify.fail("<searchFor> is null");	
		}
		else if (!stringUtil.contains(searchIn, searchFor)) {
			verify.fail("<" + searchIn + "> does not contain <" + searchFor + ">");
		}
	}
	
	public void contains(String message, String searchIn, String searchFor) {
		if (searchIn == null) {
			verify.fail(failureMessage(message, "<searchIn> is null"));
		}
		else if (searchFor == null) {
			verify.fail(failureMessage(message, "<searchFor> is null"));	
		}
		else if (!stringUtil.contains(searchIn, searchFor)) {
			verify.fail(failureMessage(message, "<" + searchIn + "> does not contain <" + searchFor + ">"));
		}
	}

	public void doesNotContain(String searchIn, String searchFor) {
		if (searchIn == null) {
			verify.fail("<searchIn> is null");
		}
		else if (searchFor == null) {
			verify.fail("<searchFor> is null");	
		}
		else if (stringUtil.contains(searchIn, searchFor)) {
			verify.fail("<" + searchIn + "> contains <" + searchFor + ">");
		}
	}
	
	public void doesNotContain(String message, String searchIn, String searchFor) {
		if (searchIn == null) {
			verify.fail(failureMessage(message, "<searchIn> is null"));
		}
		else if (searchFor == null) {
			verify.fail(failureMessage(message, "<searchFor> is null"));	
		}
		else if (stringUtil.contains(searchIn, searchFor)) {
			verify.fail(failureMessage(message, "<" + searchIn + "> contains <" + searchFor + ">"));
		}
	}
	
	public void matches(String message, String searchIn, String pattern) {
		if (searchIn == null) {
			verify.fail(failureMessage(message, "<searchIn> is null"));
		}
		else if (pattern == null) {
			verify.fail(failureMessage(message, "<pattern> is null"));
		}
		else if (!stringUtil.matches(searchIn, pattern)) {
			verify.fail(failureMessage(message, "<" + searchIn + "> does not match '" + pattern + ">"));
		}
	}

	public void doesNotMatch(String message, String searchIn, String pattern) {
		if (searchIn == null) {
			verify.fail(failureMessage(message, "<searchIn> is null"));
		}
		else if (pattern == null) {
			verify.fail(failureMessage(message, "<pattern> is null"));
		}
		else if (stringUtil.matches(searchIn, pattern)) {
			verify.fail(failureMessage(message, "<" + searchIn + "> matches '" + pattern + ">"));
		}
	}

	private String failureMessage(String prefix, String message) {
		return prefix == null ? message : prefix + ", " + message;
	}

	private boolean bothNull(String expected, String actual) {
		return expected == null && actual == null;
	}
}
