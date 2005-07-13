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
import picounit.Verify;

public class MockerErrorsThrownBeforeVerifyErrors implements Test {
	private Interface mockInterface;

	public void mock(Interface mockInterface) {
		this.mockInterface = mockInterface;
	}

	public void testFailsDueToMissingCollaboration(Mocker should, Verify verify) {
		should.call(mockInterface.booleanMethod()).andReturn(true);

		should.expectAboveWhenTheFollowingOccurs();

		verify.because("verify error")
			.thatBoolean(false).isTrue();
	} 
}
