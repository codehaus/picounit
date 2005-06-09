/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.NumericVerify;
import picounit.Verify;

public class DefaultNumericVerify implements NumericVerify {
	private final Verify verify;

	public DefaultNumericVerify(Verify verify) {
		this.verify = verify;
	}

	public void isGreaterThan(int greaterThan, int actual) {
		isGreaterThan((long) greaterThan, (long) actual);
	}

	public void isGreaterThan(long greaterThan, long actual) {
		if (greaterThan <= actual) {
			verify.fail(greaterThan + " is not greater than " + actual);
		}
	}

	public void isGreaterThanOrEqualTo(int greaterThanOrEqual, int actual) {
		isGreaterThanOrEqualTo((long) greaterThanOrEqual, (long) actual);
	}
	
	public void isGreaterThanOrEqualTo(long greaterThanOrEqual, long actual) {
		if (greaterThanOrEqual < actual) {
			verify.fail(greaterThanOrEqual + " is not greater than or equal to " + actual);
		}
	}

	public void isLessThan(int lessThan, int actual) {
		isLessThan((long) lessThan, (long) actual);
	}

	public void isLessThan(long lessThan, long actual) {
		if (lessThan >= actual) {
			verify.fail(lessThan + " is not less than " + actual);
		}
	}

	public void isLessThanOrEqualTo(int lessThanOrEqualTo, int actual) {
		isLessThanOrEqualTo((long) lessThanOrEqualTo, (long) actual);
	}

	public void isLessThanOrEqualTo(long lessThanOrEqualTo, long actual) {
		if (lessThanOrEqualTo > actual) {
			verify.fail(lessThanOrEqualTo + " is not less than or equal to " + actual);
		}
	}
}
