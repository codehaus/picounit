/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

/** @deprecated Use Verify.that(<numeric>) instead */
public interface NumericVerify {
	void isGreaterThan(double greaterThan, double actual);
	void isGreaterThan(String message, double greaterThan, double actual);
	void isGreaterThan(String message, double greaterThan, double actual, double delta);

	void isGreaterThan(float greaterThan, float actual);
	void isGreaterThan(long greaterThan, long actual);

	void isGreaterThanOrEqualTo(double greaterThanOrEqual, double actual);
	void isGreaterThanOrEqualTo(String message, double greaterThanOrEqual, double actual);
	void isGreaterThanOrEqualTo(String message, double greaterThanOrEqual, double actual, double delta);
	
	void isGreaterThanOrEqualTo(float greaterThanOrEqual, float actual);
	void isGreaterThanOrEqualTo(long greaterThanOrEqual, long actual);

	void isLessThan(double lessThan, double actual);
	void isLessThan(String message, double lessThan, double actual);
	void isLessThan(String message, double lessThan, double actual, double delta);

	void isLessThan(float lessThan, float actual);
	void isLessThan(long lessThan, long actual);

	void isLessThanOrEqualTo(double lessThanOrEqualTo, double actual);
	void isLessThanOrEqualTo(String message, double lessThanOrEqualTo, double actual);
	void isLessThanOrEqualTo(String message, double lessThanOrEqualTo, double actual, double delta);
	
	void isLessThanOrEqualTo(float lessThanOrEqualTo, float actual);
	void isLessThanOrEqualTo(long lessThanOrEqualTo, long actual);
}
