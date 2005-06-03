/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.registry;

import picounit.Configuration;
import picounit.Registry;
import picounit.verify.DelayedThrower;
import picounit.verify.ImmediateThrower;
import picounit.verify.Thrower;

public class DefaultConfiguration implements Configuration {
	private final Registry registry;

	public DefaultConfiguration(Registry registry) {
		this.registry = registry;
	}

	public void throwVerifyErrorsBeforeMockerErrors() {
		registry.register(Thrower.class, ImmediateThrower.class);
	}

	public void throwMockerErrorsBeforeVerifyErrors() {
		registry.register(Thrower.class, DelayedThrower.class);
	}
}