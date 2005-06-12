/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.multi_tests_with_different_marker;

import picounit.Marker;

public class FirstTest implements Marker {
	public static boolean testInvoked;

	public void testOne() {
		testInvoked = true;
	}
}
