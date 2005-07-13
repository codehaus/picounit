/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.instrumentation;

import junit.framework.TestResult;
import picounit.FunctionalTest;
import picounit.Instrumentation;
import picounit.TestRunner;
import picounit.Verify;

public class InstrumentationTestCase implements FunctionalTest {
	private final TestRunner testRunner = new TestRunner();

	public static class Test1 {
		public void testIsFirst(Instrumentation instrumentation, Verify verify) {
			verify.thatBoolean(instrumentation.isFirst()).isTrue();
		}

		public void testIsNeitherFirstNorLast(Instrumentation instrumentation, Verify verify) {
			verify.because("should not be first")
				.thatBoolean(instrumentation.isFirst()).isFalse();

			verify.because("should not be last")
				.thatBoolean(instrumentation.isLast()).isFalse();
		}

		public void testIsLast(Instrumentation instrumentation, Verify verify) {
			verify.thatBoolean(instrumentation.isLast()).isTrue();
		}
	}

	public void testInstrumentationWithOneTestWithMultipleMethods(previous.picounit.Verify verify) {
		TestResult testResult = testRunner.runSingle(Test1.class, Test1.class);

		verify.that(testResult.runCount()).isEqualTo(3);
		verify.because("should be no failures").that(testResult.failureCount()).isEqualTo(0);
		if (testResult.errorCount() > 0) {
			System.out.println(testResult.errors().nextElement());
		}
		verify.because("should be no errors").that(testResult.errorCount()).isEqualTo(0);
	}
	
	interface Something {
	}
	
	public static class Test2 implements Something {
		public void testIsFirst(Instrumentation instrumentation, Verify verify) {
			verify.thatBoolean(instrumentation.isFirst()).isTrue();
		}
		
		public void testIsNotLast(Instrumentation instrumentation, Verify verify) {
			verify.thatBoolean(instrumentation.isLast()).isFalse();
		}
	}

	public static class Test3 implements Something {
		public void testIsNotFirst(Instrumentation instrumentation, Verify verify) {
			verify.thatBoolean(instrumentation.isFirst()).isFalse();
		}
		
	}
	
	public static class Test4 implements Something {
		public void testIsLast(Instrumentation instrumentation, Verify verify) {
			verify.thatBoolean(instrumentation.isLast()).isTrue();
		}
	}

	public void testInstrumentationWithTwoTestWithMultipleMethods(previous.picounit.Verify verify) {
		TestResult testResult = testRunner.run(Test2.class, Something.class);
		
		verify.that(testResult.runCount()).isEqualTo(4);
		if (testResult.failureCount() > 0) {
			System.out.println(testResult.failures().nextElement());
		}

		verify.because("should be no failures")
			.that(testResult.failureCount()).isEqualTo(0);

		if (testResult.errorCount() > 0) {
			System.out.println(testResult.errors().nextElement());
		}
		verify.because("should be no errors").that(testResult.errorCount()).isEqualTo(0);
	}
}
