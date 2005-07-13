/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker_tests;

import picounit.Interface;
import picounit.Mocker;
import picounit.Test;

public class OverzealousCatchTest implements Test {
	private Interface mockInterface;

	public void mock(Interface mockInterface) {
		this.mockInterface = mockInterface;
	}

	public void testOverzealousCatch(Mocker should) {
		should.notCall(mockInterface.booleanMethod());

		should.expectAboveWhenTheFollowingOccurs();

		try {
			mockInterface.booleanMethod();
		}
		catch (Throwable throwable) {
			// squish!
		}
	}
}
