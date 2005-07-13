/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.invokable;

import picounit.FunctionalTest;
import picounit.TestRunner;
import previous.picounit.Verify;

public class IndividualTestsTestCase implements FunctionalTest {
	public void testInvokablesInvoked(Verify verify) {
		UseInvokableTest.operations.clear();
		
		new TestRunner().runSingle(UseInvokableTest.class);

		UseInvokableTest.operations.matches("invokable.invoked");
		verify.that(UseInvokableTest.operations.operations()).isEqualTo("invokable.invoked");
	}
}
