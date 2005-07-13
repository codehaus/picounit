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
public class DefaultNumericVerify implements picounit.NumericVerify {
	private final Verify verify;

	public DefaultNumericVerify(Verify verify) {
		this.verify = verify;
	}

	public void isGreaterThan(double greaterThan, double actual) {
		if (toLong(greaterThan) <= toLong(actual)) {
			verify.fail(greaterThan + " is not greater than " + actual);
		}
	}

	public void isGreaterThan(String message, double greaterThan, double actual) {
		if (toLong(greaterThan) <= toLong(actual)) {
			verify.fail(failureMessage(message, greaterThan + " is not greater than " + actual));
		}
	}

	public void isGreaterThan(String message, double greaterThan, double actual, double delta) {
		if (toLong(greaterThan + delta) <= toLong(actual)) {
			verify.fail(failureMessage(message, greaterThan + " is not greater than " + actual
				+ " with delta: " + delta));
		}
	}

	public void isGreaterThan(float greaterThan, float actual) {
		if (toInt(greaterThan) <= toInt(actual)) {
			verify.fail(greaterThan + " is not greater than " + actual);
		}
	}

	public void isGreaterThan(long greaterThan, long actual) {
		if (greaterThan <= actual) {
			verify.fail(greaterThan + " is not greater than " + actual);
		}
	}

	public void isGreaterThanOrEqualTo(double greaterThanOrEqual, double actual) {
		if (toLong(greaterThanOrEqual) < toLong(actual)) {
			verify.fail(greaterThanOrEqual + " is not greater than or equal to " + actual);
		}
	}

	public void isGreaterThanOrEqualTo(String message, double greaterThanOrEqual, double actual) {
		if (toLong(greaterThanOrEqual) < toLong(actual)) {
			verify.fail(failureMessage(message, greaterThanOrEqual
				+ " is not greater than or equal to " + actual));
		}
	}

	public void isGreaterThanOrEqualTo(String message, double greaterThanOrEqual, double actual,
		double delta) {

		if (toLong(greaterThanOrEqual + delta) < toLong(actual)) {
			verify.fail(failureMessage(message, greaterThanOrEqual
				+ " is not greater than or equal to " + actual + " with delta: " + delta));
		}
	}

	public void isGreaterThanOrEqualTo(float greaterThanOrEqual, float actual) {
		if (toInt(greaterThanOrEqual) < toInt(actual)) {
			verify.fail(greaterThanOrEqual + " is not greater than or equal to " + actual);
		}
	}

	public void isGreaterThanOrEqualTo(long greaterThanOrEqual, long actual) {
		if (greaterThanOrEqual < actual) {
			verify.fail(greaterThanOrEqual + " is not greater than or equal to " + actual);
		}
	}

	public void isLessThan(double lessThan, double actual) {
		if (toLong(lessThan) >= toLong(actual)) {
			verify.fail(lessThan + " is not less than " + actual);
		}
	}

	public void isLessThan(String message, double lessThan, double actual) {
		if (toLong(lessThan) >= toLong(actual)) {
			verify.fail(failureMessage(message, lessThan + " is not less than " + actual));
		}
	}

	public void isLessThan(String message, double lessThan, double actual, double delta) {
		if (toLong(lessThan - delta) >= toLong(actual)) {
			verify.fail(failureMessage(message, lessThan + " is not less than " + actual
				+ " with delta: " + delta));
		}
	}

	public void isLessThan(float lessThan, float actual) {
		if (toInt(lessThan) >= toInt(actual)) {
			verify.fail(lessThan + " is not less than " + actual);
		}
	}

	public void isLessThan(long lessThan, long actual) {
		if (lessThan >= actual) {
			verify.fail(lessThan + " is not less than " + actual);
		}
	}

	public void isLessThanOrEqualTo(double lessThanOrEqualTo, double actual) {
		if (toLong(lessThanOrEqualTo) > toLong(actual)) {
			verify.fail(lessThanOrEqualTo + " is not less than or equal to " + actual);
		}
	}

	public void isLessThanOrEqualTo(String message, double lessThanOrEqualTo, double actual) {
		if (toLong(lessThanOrEqualTo) > toLong(actual)) {
			verify.fail(failureMessage(message, lessThanOrEqualTo
				+ " is not less than or equal to " + actual));
		}
	}

	public void isLessThanOrEqualTo(String message, double lessThanOrEqualTo, double actual,
		double delta) {

		if (toLong(lessThanOrEqualTo - delta) > toLong(actual)) {
			verify.fail(failureMessage(message, lessThanOrEqualTo
				+ " is not less than or equal to " + actual + " with delta: " + delta));
		}
	}

	public void isLessThanOrEqualTo(float lessThanOrEqualTo, float actual) {
		if (toInt(lessThanOrEqualTo) > toInt(actual)) {
			verify.fail(lessThanOrEqualTo + " is not less than or equal to " + actual);
		}
	}

	public void isLessThanOrEqualTo(long lessThanOrEqualTo, long actual) {
		if (lessThanOrEqualTo > actual) {
			verify.fail(lessThanOrEqualTo + " is not less than or equal to " + actual);
		}
	}

	private long toLong(double actual) {
		return Double.doubleToLongBits(actual);
	}

	private long toInt(float actual) {
		return Float.floatToIntBits(actual);
	}

	private String failureMessage(String prefix, String error) {
		return prefix == null ? error : prefix + ", " + error;
	}
}
