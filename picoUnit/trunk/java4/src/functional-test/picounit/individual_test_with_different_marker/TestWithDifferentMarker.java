/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.individual_test_with_different_marker;

import picounit.Marker;
import picounit.Operations;

public class TestWithDifferentMarker implements Marker {
	public static final Operations operations = new Operations();

	public void testOne() {
		operations.record("testOne");
	}
}
