/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.register_fixture;

import picounit.Test;

public class TestUsingFixtureInTestMethod implements Test {
	public static MyFixture myFixture;

	public void testOne(MyFixture myFixture) {
		TestUsingFixtureInTestMethod.myFixture = myFixture;
	}
}
