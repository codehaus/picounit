/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.register_fixture;

import picounit.FunctionalTest;
import picounit.PicoUnit;
import previous.picounit.Verify;
import junit.framework.TestResult;

public class TestUsingContextTestCase implements FunctionalTest {
	private TestResult testResult = new TestResult();
	
	public void testUsingFixtureInConstructor(Verify verify) {
		MyContext.myFixture = null;
		TestUsingFixtureInConstructor.myFixture = null;

		new PicoUnit().generateSingleJUnitTest(TestUsingFixtureInConstructor.class).run(testResult);

		verify.notNull(MyContext.myFixture);
		verify.equal(1, testResult.runCount());
		verify.equal(0, testResult.failureCount() + testResult.errorCount());

		verify.same(MyContext.myFixture, TestUsingFixtureInConstructor.myFixture);
	}

	public void testUsingFixtureInTestMethod(Verify verify) {
		MyContext.myFixture = null;
		TestUsingFixtureInTestMethod.myFixture = null;

		new PicoUnit().generateSingleJUnitTest(TestUsingFixtureInTestMethod.class).run(testResult);

		verify.notNull(MyContext.myFixture);
		verify.equal(1, testResult.runCount());
		verify.equal(0, testResult.failureCount() + testResult.errorCount());

		verify.same(MyContext.myFixture, TestUsingFixtureInTestMethod.myFixture);
	}
}
