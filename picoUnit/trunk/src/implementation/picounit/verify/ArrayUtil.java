/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import java.util.Arrays;

public class ArrayUtil {
	// TODO: Recheck these numbers, they may not work for all sizes of double/float [9 June 2005]
	public final double SMALLEST_DOUBLE_DELTA = 0.000000000000001;
	public final float SMALLEST_FLOAT_DELTA = 0.0000001f;

	public boolean contains(boolean[] searchIn, boolean searchFor) {
		for (int index = 0; index < searchIn.length; index++ ) {
			if (searchIn[index] == searchFor) {
				return true;
			}
		}

		return false;
	}

	public boolean contains(byte[] searchIn, byte searchFor) {
		for (int index = 0; index < searchIn.length; index++ ) {
			if (searchIn[index] == searchFor) {
				return true;
			}
		}
		
		return false;
	}

	public boolean contains(char[] searchIn, char searchFor) {
		return new String(searchIn).indexOf(searchFor) != -1;
	}

	public boolean contains(double[] searchIn, double searchFor) {
		return contains(searchIn, searchFor, SMALLEST_DOUBLE_DELTA);
	}

	public boolean contains(double[] searchIn, double searchFor, double delta) {
		for (int index = 0; index < searchIn.length; index++ ) {
			if (Math.abs(searchIn[index] - searchFor) <= delta) {
				return true;
			}
		}

		return false;
	}
	
	public boolean contains(float[] searchIn, float searchFor) {
		return contains(searchIn, searchFor, SMALLEST_FLOAT_DELTA);		
	}

	public boolean contains(float[] searchIn, float searchFor, float delta) {
		for (int index = 0; index < searchIn.length; index++ ) {
			if (Math.abs(searchIn[index] - searchFor) <= delta) {
				return true;
			}
		}

		return false;
	}

	public boolean contains(int[] searchIn, int searchFor) {
		for (int index = 0; index < searchIn.length; index++ ) {
			if (searchIn[index] == searchFor) {
				return true;
			}
		}
		
		return false;
	}

	public boolean contains(long[] searchIn, long searchFor) {
		for (int index = 0; index < searchIn.length; index++ ) {
			if (searchIn[index] == searchFor) {
				return true;
			}
		}
		
		return false;
	}

	public boolean contains(short[] searchIn, short searchFor) {
		for (int index = 0; index < searchIn.length; index++ ) {
			if (searchIn[index] == searchFor) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean contains(Object[] searchIn, Object searchFor) {
		return Arrays.asList(searchIn).contains(searchFor);
	}

	public boolean equal(boolean[] left, boolean[] right) {
		if (left == null && right == null) {
			return true;
		}

		if (left == null || right == null || left.length != right.length) {
			return false;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return false;
			}
		}

		return true;
	}

	public boolean equal(byte[] left, byte[] right) {
		if (left == null && right == null) {
			return true;
		}

		if (left == null || right == null || left.length != right.length) {
			return false;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return false;
			}
		}

		return true;
	}

	public boolean equal(char[] left, char[] right) {
		if (left == null && right == null) {
			return true;
		}

		if (left == null || right == null || left.length != right.length) {
			return false;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return false;
			}
		}

		return true;
	}

	public boolean equal(double[] left, double[] right) {
		return equal(left, right, SMALLEST_DOUBLE_DELTA);
	}

	public boolean equal(double[] left, double[] right, double delta) {
		if (left == null && right == null) {
			return true;
		}

		if (left == null || right == null || left.length != right.length) {
			return false;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (Math.abs(left[index] - right[index]) > delta) {
				return false;
			}
		}

		return true;
	}

	public boolean equal(float[] left, float[] right) {
		return equal(left, right, SMALLEST_FLOAT_DELTA);
	}

	public boolean equal(float[] left, float[] right, float delta) {
		if (left == null && right == null) {
			return true;
		}

		if (left == null || right == null || left.length != right.length) {
			return false;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (Math.abs(left[index] - right[index]) > delta) {
				return false;
			}
		}

		return true;
	}

	public boolean equal(int[] left, int[] right) {
		if (left == null && right == null) {
			return true;
		}

		if (left == null || right == null || left.length != right.length) {
			return false;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return false;
			}
		}

		return true;
	}

	public boolean equal(long[] left, long[] right) {
		if (left == null && right == null) {
			return true;
		}

		if (left == null || right == null || left.length != right.length) {
			return false;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return false;
			}
		}

		return true;
	}

	public boolean equal(short[] left, short[] right) {
		if (left == null && right == null) {
			return true;
		}

		if (left == null || right == null || left.length != right.length) {
			return false;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return false;
			}
		}

		return true;
	}

	public boolean notEqual(boolean[] left, boolean[] right) {
		if (left == null && right == null) {
			return false;
		}

		if ((left == null && right != null) || (left != null && right == null) || (left.length != right.length)) {
			return true;
		}
		
		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return true;
			}
		}

		return false;
	}

	public boolean notEqual(byte[] left, byte[] right) {
		if (left == null && right == null) {
			return false;
		}

		if ((left == null && right != null) || (left != null && right == null) || (left.length != right.length)) {
			return true;
		}
		
		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return true;
			}
		}

		return false;
	}

	public boolean notEqual(char[] left, char[] right) {
		if (left == null && right == null) {
			return false;
		}

		if ((left == null && right != null) || (left != null && right == null) || (left.length != right.length)) {
			return true;
		}
		
		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return true;
			}
		}

		return false;
	}

	public boolean notEqual(double[] left, double[] right) {
		return notEqual(left, right, SMALLEST_DOUBLE_DELTA);
	}

	public boolean notEqual(double[] left, double[] right, double delta) {
		if (left == null && right == null) {
			return false;
		}

		if ((left == null && right != null) || (left != null && right == null) || (left.length != right.length)) {
			return true;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (Math.abs(left[index] - right[index]) >= delta) {
				return true;
			}
		}

		return false;
	}

	public boolean notEqual(float[] left, float[] right) {
		return notEqual(left, right, SMALLEST_FLOAT_DELTA);
	}

	public boolean notEqual(float[] left, float[] right, float delta) {
		if (left == null && right == null) {
			return false;
		}

		if ((left == null && right != null) || (left != null && right == null) || (left.length != right.length)) {
			return true;
		}

		for (int index = 0; index < right.length; index++ ) {
			if (Math.abs(left[index] - right[index]) >= delta) {
				return true;
			}
		}

		return false;
	}

	public boolean notEqual(int[] left, int[] right) {
		if (left == null && right == null) {
			return false;
		}

		if ((left == null && right != null) || (left != null && right == null) || (left.length != right.length)) {
			return true;
		}
		
		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return true;
			}
		}

		return false;
	}

	public boolean notEqual(long[] left, long[] right) {
		if (left == null && right == null) {
			return false;
		}

		if ((left == null && right != null) || (left != null && right == null) || (left.length != right.length)) {
			return true;
		}
		
		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return true;
			}
		}

		return false;
	}

	public boolean notEqual(short[] left, short[] right) {
		if (left == null && right == null) {
			return false;
		}

		if ((left == null && right != null) || (left != null && right == null) || (left.length != right.length)) {
			return true;
		}
		
		for (int index = 0; index < right.length; index++ ) {
			if (left[index] != right[index]) {
				return true;
			}
		}

		return false;
	}
	
	public Boolean[] boxBooleans(boolean[] toBox) {
		Boolean[] booleans = new Boolean[toBox.length];

		for (int index = 0; index < booleans.length; index++ ) {
			booleans[index] = new Boolean(toBox[index]);
		}

		return booleans;
	}

	public Byte[] boxBytes(byte[] toBox) {
		Byte[] bytes = new Byte[toBox.length];

		for (int index = 0; index < bytes.length; index++ ) {
			bytes[index] = new Byte(toBox[index]);
		}

		return bytes;
	}

	public Character[] boxCharacters(char[] toBox) {
		Character[] characters = new Character[toBox.length];

		for (int index = 0; index < characters.length; index++ ) {
			characters[index] = new Character(toBox[index]);
		}

		return characters;
	}

	public Double[] boxDoubles(double[] toBox) {
		Double[] doubles = new Double[toBox.length];

		for (int index = 0; index < doubles.length; index++ ) {
			doubles[index] = new Double(toBox[index]);
		}

		return doubles;
	}

	public Float[] boxFloats(float[] toBox) {
		Float[] floats = new Float[toBox.length];

		for (int index = 0; index < floats.length; index++ ) {
			floats[index] = new Float(toBox[index]);
		}

		return floats;
	}

	public Integer[] boxIntegers(int[] toBox) {
		Integer[] integers = new Integer[toBox.length];

		for (int index = 0; index < integers.length; index++ ) {
			integers[index] = new Integer(toBox[index]);
		}

		return integers;
	}

	public Long[] boxLongs(long[] toBox) {
		Long[] longs = new Long[toBox.length];

		for (int index = 0; index < longs.length; index++ ) {
			longs[index] = new Long(toBox[index]);
		}

		return longs;
	}

	public Short[] boxShorts(short[] toBox) {
		Short[] shorts = new Short[toBox.length];

		for (int index = 0; index < shorts.length; index++ ) {
			shorts[index] = new Short(toBox[index]);
		}

		return shorts;
	}
}

