/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface IntegerConstraints {
	int anInteger();
	int oneOf(int[] oneOf);
	int neitherOf(int[] neitherOf);
	int notEqualTo(int notEqual);
	int lessThan(int upperLimit);
	int lessThanOrEqualTo(int upperLimit);
	int greaterThan(int lowerLimit);
	int greaterThanOrEqualTo(int lowerLimit);
	int between(int lowerLimit, int upperLimit);
	int notBetween(int lowerLimit, int upperLimit);
	int almostEqualTo(int equalTo, int errorAllowed);
}
