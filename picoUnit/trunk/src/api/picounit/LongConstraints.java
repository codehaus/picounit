/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface LongConstraints {
	long aLong();
	long oneOf(long[] oneOf);
	long neitherOf(long[] neitherOf);
	long notEqualTo(long notEqual);
	long lessThan(long upperLimit);
	long lessThanOrEqualTo(long upperLimit);
	long greaterThan(long lowerLimit);
	long greaterThanOrEqualTo(long lowerLimit);
	long between(long lowerLimit, long upperLimit);
	long notBetween(long lowerLimit, long upperLimit);
}