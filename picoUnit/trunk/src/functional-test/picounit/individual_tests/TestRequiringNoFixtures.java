/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.individual_tests;

import picounit.Operations;
import picounit.Test;

public class TestRequiringNoFixtures implements Test {
	public static final Operations operations = new Operations(); 

	public void testOne() {
		operations.record("testOne");
	}
}