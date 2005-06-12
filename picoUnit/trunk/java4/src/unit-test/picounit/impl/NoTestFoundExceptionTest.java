/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.finder.NoTestsFoundException;
import previous.picounit.Test;
import previous.picounit.Verify;

public class NoTestFoundExceptionTest implements Test {
	private final Verify verify;

	public NoTestFoundExceptionTest(Verify verify) {
		this.verify = verify;
	}
	
	private NoTestsFoundException noTestsFoundException(Class startingClass) {
		return new NoTestsFoundException(startingClass);
	}
	
	public void testGetMessage() {
		class StartingClass {}

		verify.equal("No tests found", noTestsFoundException(StartingClass.class).getMessage());
	}

	public void testEquals() {
		class StartingClassOne {}
		class StartingClassTwo {}

		verify.equal("Instances with same starting class should be equal,",
			noTestsFoundException(StartingClassOne.class), noTestsFoundException(StartingClassOne.class));

		verify.notEqual("Instances with different starting class should not be equal",
			noTestsFoundException(StartingClassOne.class), noTestsFoundException(StartingClassTwo.class));
	}
}
