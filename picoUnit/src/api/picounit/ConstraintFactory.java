/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface ConstraintFactory {
	Future future(Class futureType);
	Object instanceOf(Class instanceOf);
	
	int oneOf(int[] oneOf);
	int noneOf(int[] neitherOf);
	int notEqual(int notEqual);
	int lessThan(int upperLimit);
	int lessThanOrEqualTo(int upperLimit);
	int greaterThan(int lowerLimit);
	int greaterThanOrEqualTo(int lowerLimit);
	int between(int lowerLimit, int upperLimit);
	int anInteger();

	String notEqual(String notEqual);
	String containing(String toContain);
	String equalIgnoringCase(String toEqual);

	Object notEqual(Object notEqual);
}
