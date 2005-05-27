/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.kissing;

import picounit.Mocker;
import picounit.Verify;
import picounit.registry.DefaultConfiguration;

public class ExperimentalGirlTest implements InjectingTest {
	private final Girl girl;
	private final Kissable kissable;

	public void setUp(DefaultConfiguration configuration) {
		configuration.throwMockerErrorsBeforeVerifyErrors();
	}

	public ExperimentalGirlTest(Girl girl, Kissable kissable) {
		this.girl = girl;
		this.kissable = kissable;
	}

	public void testGirlKissesKissable(Mocker mocker, Verify verify) {
		mocker.expect(kissable.kiss()).andReturn("kissed");

		mocker.replay();

		verify.equal("kissed", girl.kiss());
	}
}
