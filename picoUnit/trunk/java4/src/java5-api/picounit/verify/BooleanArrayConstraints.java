/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public interface BooleanArrayConstraints {
	void isEqualTo(boolean ... equalTo);
	void isDifferentTo(boolean ... differentTo);
	
	void contains(boolean contains);
	void doesNotContain(boolean doesNotContain);
}
