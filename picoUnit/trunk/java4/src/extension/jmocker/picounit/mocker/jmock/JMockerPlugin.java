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
import picounit.impl.Verifiable;
import picounit.mocker.MockFactory;
import picounit.mocker.MockInvocationInspector;

import java.util.Properties;

public class JMockerPlugin implements Plugin {
	public void insert(Registry registry, Properties pluginProperties) {
		ConstraintStore constraintStore = new HashMapConstraintStore();

		Mocker mocker = JMocker.create(constraintStore);

		registry.register(Mocker.class, mocker);
		registry.register(MockFactory.class, mocker);
		registry.register(Verifiable.class, mocker);
		registry.register(MockInvocationInspector.class, mocker);

		registry.register(Constraints.class, new JMockConstraints(constraintStore));
	}
}
