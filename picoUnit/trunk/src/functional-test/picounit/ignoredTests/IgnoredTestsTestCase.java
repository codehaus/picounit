/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.ignoredTests;

import picounit.FunctionalTest;

public class IgnoredTestsTestCase implements FunctionalTest {
	public void testNotIgnoredTest() {
		NotIgnoredTest.validator.runSingle();
	}

	public void testIgnoredTest() {
		IgnoredTest.validator.runSingle();
	}

	public void testDatabaseTestThatIsIgnoredIfDatabaseIsDown() {
		DatabaseTestThatIsIgnoredIfDatabaseIsDown.validator.runSingle();
	}

	public void testWebServerTestThatIsIgnoredIfWebServerIsDown() {
		WebServerTestThatIsIgnoredIfWebServerIsDown.validator.runSingle();
	}
}
