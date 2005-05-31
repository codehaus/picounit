/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import picounit.Constraints;
import picounit.Mocker;
import picounit.Plugin;
import picounit.Registry;

import java.util.Properties;

public class JMockerPlugin implements Plugin {
	private final Registry registry;

	public JMockerPlugin(Registry registry) {
		this.registry = registry;
	}

	public void insert(Properties pluginProperties) {
		ConstraintStore constraintStore = new HashMapConstraintStore();

		registry.register(Mocker.class, new JMocker(constraintStore));
		registry.register(Constraints.class, new JMockConstraintFactory(constraintStore));
	}
}
