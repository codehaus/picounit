/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.registry;

import picounit.ArrayVerify;
import picounit.Configuration;
import picounit.Environment;
import picounit.FileVerify;
import picounit.NumericVerify;
import picounit.Plugin;
import picounit.Registry;
import picounit.StringVerify;
import picounit.Verify;
import picounit.impl.EnvironmentImpl;
import picounit.util.FileReader;
import picounit.verify.ArrayUtil;
import picounit.verify.DefaultArrayVerify;
import picounit.verify.DefaultFileVerify;
import picounit.verify.DefaultNumericVerify;
import picounit.verify.DefaultStringVerify;
import picounit.verify.DefaultVerify;
import picounit.verify.ImmediateThrower;
import picounit.verify.NumericUtil;
import picounit.verify.StringUtil;
import picounit.verify.Thrower;

import java.util.Properties;

public class DefaultPlugin implements Plugin {
	private final Registry registry;

	public DefaultPlugin(Registry registry) {
		this.registry = registry;
	}

	public void insert(Properties pluginProperties) {
		registry.register(ArrayVerify.class, DefaultArrayVerify.class);
		registry.register(FileVerify.class, DefaultFileVerify.class);
		registry.register(NumericVerify.class, DefaultNumericVerify.class);
		registry.register(StringVerify.class, DefaultStringVerify.class);
		registry.register(Verify.class, DefaultVerify.class);
		registry.register(Thrower.class, ImmediateThrower.class);
		registry.register(Environment.class, EnvironmentImpl.class);
		registry.register(Configuration.class, DefaultConfiguration.class);

		registry.register(ArrayUtil.class);
		registry.register(FileReader.class);
		registry.register(NumericUtil.class);
		registry.register(StringUtil.class);
	}
}
