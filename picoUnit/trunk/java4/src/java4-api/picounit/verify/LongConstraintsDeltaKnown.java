/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.LongConstraint;

public interface LongConstraintsDeltaKnown {
	void isEqualTo(long equalTo);
	void isDifferentTo(long differentTo);
	void isGreaterThan(long greaterThan);
	void isGreaterThanOrEqualTo(long greaterThanOrEqualTo);
	void isLessThan(long lessThan);
	void isLessThanOrEqualTo(long lessThanOrEqualTo);

	void passes(LongConstraint longConstraint);
}
