/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface ArrayVerify {
	void contains(boolean[] searchIn, boolean searchFor);
	void contains(byte[] searchIn, byte searchFor);
	void contains(char[] searchIn, char searchFor);
	void contains(double[] searchIn, double searchFor);
	void contains(double[] searchIn, double searchFor, double delta);
	void contains(float[] searchIn, float searchFor);
	void contains(float[] searchIn, float searchFor, float delta);
	void contains(int[] searchIn, int searchFor);
	void contains(long[] searchIn, long searchFor);
	void contains(Object[] searchIn, Object searchFor);
	void contains(short[] searchIn, short searchFor);

	void doesNotContain(boolean[] searchIn, boolean searchFor);
	void doesNotContain(byte[] searchIn, byte searchFor);
	void doesNotContain(char[] searchIn, char searchFor);
	void doesNotContain(double[] searchIn, double searchFor);
	void doesNotContain(double[] searchIn, double searchFor, double delta);
	void doesNotContain(float[] searchIn, float searchFor);
	void doesNotContain(float[] searchIn, float searchFor, float delta);
	void doesNotContain(int[] searchIn, int searchFor);
	void doesNotContain(long[] searchIn, long searchFor);
	void doesNotContain(short[] searchIn, short searchFor);
}