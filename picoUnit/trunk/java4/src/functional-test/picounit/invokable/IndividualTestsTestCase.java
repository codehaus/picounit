/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.invokable;

import picounit.FunctionalTest;
import picounit.PicoUnit;
import previous.picounit.Verify;
import junit.framework.TestResult;

public class IndividualTestsTestCase implements FunctionalTest {
	public void testInvokablesInvoked(Verify verify) {
		UseInvokableTest.operations.clear();
		
		new PicoUnit().generateSingleJUnitTest(UseInvokableTest.class).run(new TestResult());

		UseInvokableTest.operations.matches("invokable.invoked");
		verify.equal("invokable.invoked", UseInvokableTest.operations.operations());
	}
}
