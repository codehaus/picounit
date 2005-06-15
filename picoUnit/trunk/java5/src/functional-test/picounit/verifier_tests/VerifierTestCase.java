/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verifier_tests;

import picounit.FunctionalTest;

public class VerifierTestCase implements FunctionalTest {
	public void testDefaultVerifierThrowsExceptionsForAssertsBeforeMocks() {
		TestDefaultVerifierCausesAssertionsToThrowExceptionsImmediately.validator.runSingle();
	}

	public void testDelayedVerifierThrowsExceptionsForMocksBeforeAsserts() {
		TestDelayedVerifierCausesAssertionsToQueue.validator.runSingle();
	}
}
