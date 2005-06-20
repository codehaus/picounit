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

public class TestUsingLifeCycleTestCase implements FunctionalTest {
	private final TestRunner testRunner = new TestRunner();
	
	public void testUsingFixtureInConstructor(Verify verify) {
		MyLifeCycleTmp.myFixture = null;
		TestUsingFixtureInConstructor.myFixture = null;

		TestResult testResult = testRunner.runSingle(TestUsingFixtureInConstructor.class);

		verify.notNull(MyLifeCycleTmp.myFixture);
		verify.equal(1, testResult.runCount());
		verify.equal(0, testResult.failureCount() + testResult.errorCount());

		verify.same(MyLifeCycleTmp.myFixture, TestUsingFixtureInConstructor.myFixture);
	}

	public void testUsingFixtureInTestMethod(Verify verify) {
		MyLifeCycleTmp.myFixture = null;
		TestUsingFixtureInTestMethod.myFixture = null;

		TestResult testResult = testRunner.runSingle(TestUsingFixtureInTestMethod.class);

		verify.notNull(MyLifeCycleTmp.myFixture);
		verify.equal(1, testResult.runCount());
		verify.equal(0, testResult.failureCount() + testResult.errorCount());

		verify.same(MyLifeCycleTmp.myFixture, TestUsingFixtureInTestMethod.myFixture);
	}
}
