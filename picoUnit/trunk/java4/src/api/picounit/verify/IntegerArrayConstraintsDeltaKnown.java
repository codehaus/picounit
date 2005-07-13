/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public interface IntegerArrayConstraintsDeltaKnown {
	void isEqualTo(int... equalTo);
	void isDifferentTo(int... differentTo);
	
	void contains(int contains);
	void doesNotContain(int doesNotContain);
}
