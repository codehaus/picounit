/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public interface LongArrayConstraintsDeltaKnown {
	void isEqualTo(long[] equalTo);
	void isDifferentTo(long[] differentTo);

	void contains(long contains);
	void doesNotContain(long doesNotContain);
}
