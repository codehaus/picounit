/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import java.io.File;

public interface VerifyStage {
	BooleanConstraints thatBoolean(boolean value);
	ByteConstraints that(byte value);
	CharacterConstraints that(char value);
	DoubleConstraints that(double value);
	FloatConstraints that(float value);
	IntegerConstraints that(int value);
	LongConstraints that(long value);
	ShortConstraints that(short value);

	FileConstraints that(File value);
	StringConstraints that(String value);

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
}
