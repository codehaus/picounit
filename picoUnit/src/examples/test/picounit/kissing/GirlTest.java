/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.kissing;

import picounit.Mocker;
import picounit.Test;
import picounit.Verify;
import picounit.registry.DefaultConfiguration;

public class GirlTest implements Test {
	private Girl girl;

	private Kissable kissable;
	
	public void setUp(DefaultConfiguration configuration) {
		configuration.throwMockerErrorsBeforeVerifyErrors();
	}

	public void mock(Kissable kissable) {
		this.girl = new Girl(kissable);

		this.kissable = kissable;
	}

	public void testGirlKissesKissable(Mocker should, Verify verify) {
		should.expect(kissable.kiss()).andReturn("kissed")
			.because("a girl should kiss the kissable she was constructed with");

		should.replay();

		verify.equal("kissed", girl.kiss());
	}
}
