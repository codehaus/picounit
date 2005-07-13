/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

import previous.picounit.Test;
import previous.picounit.Verify;

public class BasicConstraintsTest implements Test {
	private BasicConstraints basicConstraints = new BasicConstraints();
	
	public void testBetween(Verify verify) {
		Constraint isBetween100and200 = basicConstraints.isBetween(new Long(100), new Long(200));

		verify.because("101 is between 100 and 200")
			.thatBoolean(isBetween100and200.eval(new Long(101))).isTrue();
		verify.because("199 is between 100 and 200")
			.thatBoolean(isBetween100and200.eval(new Long(199))).isTrue();

		verify.because("100 is not between 100 and 200")
			.thatBoolean(isBetween100and200.eval(new Long(100))).isFalse();
		verify.because("200 is not between 100 and 200")
			.thatBoolean(isBetween100and200.eval(new Long(200))).isFalse();
	}
}
