/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface DelegateVerify {
	BooleanVerifier delegateTo(boolean ignore);
	ByteVerifier delegateTo(byte ignore);
	CharVerifier delegateTo(char ignore);
	DoubleVerifier delegateTo(double ignore);
	FloatVerifier delegateTo(float ignore);
	IntVerifier delegateTo(int ignore);
	LongVerifier delegateTo(long ignore);
	ShortVerifier delegateTo(short ignore);
	ObjectVerifier delegateTo(Object ignore);
	StringVerifier delegateTo(String ignore);
	
	interface BooleanVerifier {
		void whenCalling(boolean actualValue);
	}

	interface ByteVerifier {
		void whenCalling(byte actualValue);
	}

	interface CharVerifier {
		void whenCalling(char actualValue);
	}

	interface DoubleVerifier {
		void whenCalling(double actualValue);
	}

	interface FloatVerifier {
		void whenCalling(float actualValue);
	}

	interface IntVerifier {
		void whenCalling(int actualValue);
	}

	interface LongVerifier {
		void whenCalling(long actualValue);
	}

	interface ShortVerifier {
		void whenCalling(short actualValue);
	}

	interface ObjectVerifier {
		void whenCalling(Object actualValue);
	}

	interface StringVerifier {
		void whenCalling(String actualValue);
	}
}