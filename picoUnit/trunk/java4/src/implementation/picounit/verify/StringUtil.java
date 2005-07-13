/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;


import java.util.Arrays;

public class StringUtil {
	private final ArrayUtil arrayUtil = new ArrayUtil();

	public boolean equalsIgnoringCase(String expected, String actual) {
		return expected != null &&
			actual != null &&
			expected.equalsIgnoreCase(actual);
	}

	public boolean contains(String searchIn, String searchFor) {
		return searchIn != null &&
			searchFor != null &&
			searchIn.indexOf(searchFor) != -1;
	}
	
	public boolean matches(String searchIn, String pattern) {
		return searchIn != null &&
			pattern != null &&
			searchIn.matches(pattern);
	}

	public String replace(String replaceIn, String toReplace, String replaceWith) {
		int searchFrom = 0;
		int index = replaceIn.indexOf(toReplace, searchFrom);

		String replacement = "";

		while (index != -1) {
			replacement += replaceIn.substring(searchFrom, index) + replaceWith;

			searchFrom = index + toReplace.length();

			index = replaceIn.indexOf(toReplace, searchFrom);
		}

		replacement += replaceIn.substring(searchFrom);

		return replacement;
	}

	public String toString(boolean[] booleans) {
		return booleans == null ? "null" : toString(arrayUtil.boxBooleans(booleans));
	}

	public String toString(byte[] bytes) {
		return bytes == null ? "null" : toString(arrayUtil.boxBytes(bytes));
	}

	public String toString(char[] characters) {
		return characters == null ? "null" : toString(arrayUtil.boxCharacters(characters));
	}

	public String toString(double[] doubles) {
		return doubles == null ? "null" : toString(arrayUtil.boxDoubles(doubles));
	}

	public String toString(float[] floats) {
		return floats == null ? "null" : toString(arrayUtil.boxFloats(floats));
	}

	public String toString(int[] integers) {
		return integers == null ? "null" : toString(arrayUtil.boxIntegers(integers));
	}

	public String toString(long[] longs) {
		return longs == null ? "null" : toString(arrayUtil.boxLongs(longs));
	}

	public String toString(short[] shorts) {
		return shorts == null ? "null" : toString(arrayUtil.boxShorts(shorts));
	}

	public String toString(Object[] toString) {
		return toString == null ? "null" : Arrays.asList(toString).toString();
	}
}
