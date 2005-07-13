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

		verify.that(noTestsFoundException(StartingClass.class).getMessage())
			.isEqualTo("No tests found");
	}

	public void testEquals() {
		class StartingClassOne {}
		class StartingClassTwo {}

		verify.because("Instances with same starting class should be equal,")
			.that(noTestsFoundException(StartingClassOne.class))
			.isEqualTo(noTestsFoundException(StartingClassOne.class));

		verify.because("Instances with different starting class should not be equal")
			.that(noTestsFoundException(StartingClassTwo.class))
			.isDifferentTo(noTestsFoundException(StartingClassOne.class));
	}
}
