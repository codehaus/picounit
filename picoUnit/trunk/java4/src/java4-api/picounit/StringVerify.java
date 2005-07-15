/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

/** @deprecated use conversational api instead
 * 
 * In the next release (1.3) these methods will be deleted 
 */
public interface StringVerify {
	void equalsIgnoringCase(String message, String expected, String actual);
	void doesNotEqualIgnoringCase(String message, String expected, String actual);

	void contains(String searchIn, String searchFor);
	void contains(String message, String searchIn, String searchFor);

	void doesNotContain(String searchIn, String searchFor);
	void doesNotContain(String message, String searchIn, String searchFor);

	void matches(String message, String searchIn, String pattern);
	void doesNotMatch(String message, String searchIn, String pattern);
}