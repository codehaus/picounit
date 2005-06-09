/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface NumericVerify {
	void isGreaterThan(int greaterThan, int actual);
	void isGreaterThan(long greaterThan, long actual);
	
	void isGreaterThanOrEqualTo(int greaterThanOrEqual, int actual);
	void isGreaterThanOrEqualTo(long greaterThanOrEqual, long actual);

	void isLessThan(int lessThan, int actual);
	void isLessThan(long lessThan, long actual);
	
	void isLessThanOrEqualTo(int lessThanOrEqualTo, int actual);
	void isLessThanOrEqualTo(long lessThanOrEqualTo, long actual);
}
