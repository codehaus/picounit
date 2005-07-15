/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public interface DoubleArrayConstraintsDeltaKnown {
	void isEqualTo(double[] equalTo);
	void isDifferentTo(double[] differentTo);

	void contains(double contains);
	void doesNotContain(double doesNotContain);
}
