/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.register_fixture;

import picounit.FunctionalTest;
import picounit.TestRunner;
import previous.picounit.Verify;
import junit.framework.TestResult;

public class TestUsingLifecycleTestCase implements FunctionalTest {
	private final TestRunner testRunner = new TestRunner();
	
	public void testUsingFixtureInConstructor(Verify verify) {
		MyLifecycle.myFixture = null;
		TestUsingFixtureInConstructor.myFixture = null;

		TestResult testResult = testRunner.runSingle(TestUsingFixtureInConstructor.class);

		verify.that(MyLifecycle.myFixture).isNotNull();
		
		verify.that(testResult.runCount()).isEqualTo(1);
		verify.that(testResult.failureCount() + testResult.errorCount()).isEqualTo(0);

		verify.that(TestUsingFixtureInConstructor.myFixture).isTheSameAs(MyLifecycle.myFixture);
	}

	public void testUsingFixtureInTestMethod(Verify verify) {
		MyLifecycle.myFixture = null;
		TestUsingFixtureInTestMethod.myFixture = null;

		TestResult testResult = testRunner.runSingle(TestUsingFixtureInTestMethod.class);

		verify.that(MyLifecycle.myFixture).isNotNull();
		verify.that(testResult.runCount()).isEqualTo(1);
		verify.that(testResult.failureCount() + testResult.errorCount()).isEqualTo(0);

		verify.that(TestUsingFixtureInTestMethod.myFixture).isTheSameAs(MyLifecycle.myFixture);
	}
}
