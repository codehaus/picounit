/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.IntegerConstraint;

public interface IntegerConstraintsDeltaKnown {
	void isEqualTo(int equalTo);
	void isDifferentTo(int differentTo);
	void isGreaterThan(int greaterThan);
	void isGreaterThanOrEqualTo(int greaterThanOrEqualTo);
	void isLessThan(int lessThan);
	void isLessThanOrEqualTo(int lessThanOrEqualTo);

	void passes(IntegerConstraint integerConstraint);
}
