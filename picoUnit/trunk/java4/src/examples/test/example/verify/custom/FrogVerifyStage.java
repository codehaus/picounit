/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.BooleanArrayConstraints;
import picounit.verify.BooleanConstraints;
import picounit.verify.ByteArrayConstraints;
import picounit.verify.ByteConstraints;
import picounit.verify.CharacterArrayConstraints;
import picounit.verify.CharacterConstraints;
import picounit.verify.DoubleArrayConstraints;
import picounit.verify.FileConstraints;
import picounit.verify.FloatArrayConstraints;
import picounit.verify.FloatConstraints;
import picounit.verify.IntegerArrayConstraints;
import picounit.verify.IntegerConstraints;
import picounit.verify.LongArrayConstraints;
import picounit.verify.LongConstraints;
import picounit.verify.ShortArrayConstraints;
import picounit.verify.ShortConstraints;
import picounit.verify.StringArrayConstraints;
import picounit.verify.TypedArrayConstraints;
import picounit.verify.TypedConstraints;

import java.io.File;

public interface FrogVerifyStage {
	BooleanConstraints thatBoolean(boolean value);
	ByteConstraints that(byte value);
	CharacterConstraints that(char value);
	FloatConstraints that(float value);
	IntegerConstraints that(int value);
	LongConstraints that(long value);
	ShortConstraints that(short value);

	FileConstraints that(File value);

	<T> TypedConstraints<T> that(T value);

	BooleanArrayConstraints that(boolean ... value);
	ByteArrayConstraints that(byte ... value);
	CharacterArrayConstraints that(char ... value);
	DoubleArrayConstraints that(double ... value);
	FloatArrayConstraints that(float ... value);
	IntegerArrayConstraints that(int ... value);
	LongArrayConstraints that(long ... value);
	ShortArrayConstraints that(short ... value);

	StringArrayConstraints that(String ... value);

	<T> TypedArrayConstraints<T> that(T ... value);
	
	CustomDoubleConstraints that(double value);
	CustomStringConstraints that(String string);

	FrogConstraints that(Frog frog);
}