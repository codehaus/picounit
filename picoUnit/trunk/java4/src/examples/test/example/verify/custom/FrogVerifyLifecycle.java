/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.Lifecycle;
import picounit.Registry;

public class FrogVerifyLifecycle implements Lifecycle {
	public void setUp(Registry registry) {
		registry.register(FrogVerify.class);
		registry.register(CustomDoubleConstraints.class);
		registry.register(CustomStringConstraints.class);
	}
}