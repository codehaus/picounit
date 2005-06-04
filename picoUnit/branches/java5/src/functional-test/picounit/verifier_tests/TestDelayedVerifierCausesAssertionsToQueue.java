/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verifier_tests;

import picounit.Interface;
import picounit.Mocker;
import picounit.Registry;
import picounit.SomeException;
import picounit.Test;
import picounit.Validator;
import picounit.Verify;
import picounit.verify.DelayedThrower;
import picounit.verify.Thrower;
import junit.framework.AssertionFailedError;
import junit.framework.TestResult;

public class TestDelayedVerifierCausesAssertionsToQueue implements Test {
	public static final Validator validator = new Validator(TestDelayedVerifierCausesAssertionsToQueue.class) {
		@Override
		public void validate(TestResult testResult) {
			matches("mockerThrew exceptionDispatcherThrew");
		}
	};

	private Interface mockInterface;

	public void mock(Interface mockInterface) {
		this.mockInterface = mockInterface;
	}

	public void setUp(Registry registry) {
		registry.register(Thrower.class, DelayedThrower.class);
	}

	public void test(Mocker mocker, Verify verify, Thrower thrower) throws SomeException {
		mockInterface.method();

		try {
			verify.that(false);
		}
		catch (AssertionFailedError assertionFailedError) {
			validator.record("verifyThrew");
		}

		try {
			mocker.replay();
			mocker.verify();
		}
		catch (AssertionFailedError assertionFailedError) {
			validator.record("mockerThrew");
		}
		
		try {
			thrower.dispatchError();
		}
		catch (AssertionFailedError assertionFailedError) {
			validator.record("exceptionDispatcherThrew");
		}
	}
}
