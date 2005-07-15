/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.registry;

import picounit.Configuration;
import picounit.DelegateVerify;
import picounit.Environment;
import picounit.Plugin;
import picounit.Registry;
import picounit.Verify;
import picounit.impl.EnvironmentImpl;
import picounit.util.FileReader;
import picounit.verify.ArrayUtil;
import picounit.verify.BooleanArrayConstraints;
import picounit.verify.BooleanArrayConstraintsImpl;
import picounit.verify.BooleanConstraints;
import picounit.verify.ByteArrayConstraints;
import picounit.verify.ByteConstraints;
import picounit.verify.CharacterArrayConstraints;
import picounit.verify.CharacterArrayConstraintsImpl;
import picounit.verify.CharacterConstraints;
import picounit.verify.DefaultArrayVerify;
import picounit.verify.DefaultBooleanConstraints;
import picounit.verify.DefaultByteArrayConstraints;
import picounit.verify.DefaultByteConstraints;
import picounit.verify.DefaultCharacterConstraints;
import picounit.verify.DefaultDelegateVerify;
import picounit.verify.DefaultDoubleArrayConstraints;
import picounit.verify.DefaultDoubleConstraints;
import picounit.verify.DefaultFileConstraints;
import picounit.verify.DefaultFileVerify;
import picounit.verify.DefaultFloatConstraints;
import picounit.verify.DefaultIntegerConstraints;
import picounit.verify.DefaultLongConstraints;
import picounit.verify.DefaultNumericVerify;
import picounit.verify.DefaultShortConstraints;
import picounit.verify.DefaultStringConstraints;
import picounit.verify.DefaultStringVerify;
import picounit.verify.DefaultVerify;
import picounit.verify.DoubleArrayConstraints;
import picounit.verify.DoubleConstraints;
import picounit.verify.FileConstraints;
import picounit.verify.FloatArrayConstraints;
import picounit.verify.FloatArrayConstraintsImpl;
import picounit.verify.FloatConstraints;
import picounit.verify.ImmediateThrower;
import picounit.verify.IntegerArrayConstraints;
import picounit.verify.IntegerArrayConstraintsImpl;
import picounit.verify.IntegerConstraints;
import picounit.verify.LongArrayConstraints;
import picounit.verify.LongArrayConstraintsImpl;
import picounit.verify.LongConstraints;
import picounit.verify.NumericUtil;
import picounit.verify.ShortArrayConstraints;
import picounit.verify.ShortArrayConstraintsImpl;
import picounit.verify.ShortConstraints;
import picounit.verify.StringArrayConstraints;
import picounit.verify.StringArrayConstraintsImpl;
import picounit.verify.StringConstraints;
import picounit.verify.StringUtil;
import picounit.verify.Thrower;
import picounit.verify.TypedArrayConstraints;
import picounit.verify.TypedArrayConstraintsImpl;
import picounit.verify.TypedConstraints;
import picounit.verify.TypedConstraintsImpl;
import picounit.verify.constraint.Evaluator;

import java.util.Properties;

public class DefaultPlugin implements Plugin {
	public void insert(Registry registry, Properties pluginProperties) {
		registry.register(picounit.ArrayVerify.class, DefaultArrayVerify.class);
		registry.register(picounit.FileVerify.class, DefaultFileVerify.class);
		registry.register(picounit.NumericVerify.class, DefaultNumericVerify.class);
		registry.register(picounit.StringVerify.class, DefaultStringVerify.class);
		registry.register(DelegateVerify.class, DefaultDelegateVerify.class);
		registry.register(Evaluator.class);
		
		registry.register(BooleanConstraints.class, DefaultBooleanConstraints.class);
		registry.register(ByteConstraints.class, DefaultByteConstraints.class);
		registry.register(CharacterConstraints.class, DefaultCharacterConstraints.class);
		registry.register(DoubleConstraints.class, DefaultDoubleConstraints.class);
		registry.register(FloatConstraints.class, DefaultFloatConstraints.class);
		registry.register(IntegerConstraints.class, DefaultIntegerConstraints.class);
		registry.register(LongConstraints.class, DefaultLongConstraints.class);
		registry.register(ShortConstraints.class, DefaultShortConstraints.class);

		registry.register(FileConstraints.class, DefaultFileConstraints.class);
		registry.register(StringConstraints.class, DefaultStringConstraints.class);
		registry.register(TypedConstraints.class, TypedConstraintsImpl.class);

		registry.register(BooleanArrayConstraints.class, BooleanArrayConstraintsImpl.class);
		registry.register(ByteArrayConstraints.class, DefaultByteArrayConstraints.class);
		registry.register(CharacterArrayConstraints.class, CharacterArrayConstraintsImpl.class);
		registry.register(DoubleArrayConstraints.class, DefaultDoubleArrayConstraints.class);
		registry.register(FloatArrayConstraints.class, FloatArrayConstraintsImpl.class);
		registry.register(IntegerArrayConstraints.class, IntegerArrayConstraintsImpl.class);
		registry.register(LongArrayConstraints.class, LongArrayConstraintsImpl.class);
		registry.register(ShortArrayConstraints.class, ShortArrayConstraintsImpl.class);
		registry.register(StringArrayConstraints.class, StringArrayConstraintsImpl.class);
		registry.register(TypedArrayConstraints.class, TypedArrayConstraintsImpl.class);

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
