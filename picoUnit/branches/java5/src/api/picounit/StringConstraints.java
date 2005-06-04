/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface StringConstraints {
	String aString();
	String aNullString();
	String notANullString();
	String oneOf(String ... oneOf);
	String neitherOf(String ... neitherOf);
	String notEqualTo(String notEqual);
	String equaTolIgnoringCase(String toEqual);
	String aStringContaining(String toContain);
	String aStringMatching(String pattern);
}