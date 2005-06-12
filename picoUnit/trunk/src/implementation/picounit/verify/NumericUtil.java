/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public class NumericUtil {
	public boolean isEqual(double first, double second) {
		return Double.doubleToLongBits(first) == Double.doubleToLongBits(second);
	}

	public boolean isEqual(double first, double second, double delta) {
		return Math.abs(first - second) <= delta;
	}

	public boolean isEqual(float first, float second) {
		return Float.floatToIntBits(first) == Float.floatToIntBits(second);
	}

	public boolean isEqual(float first, float second, float delta) {
		return Math.abs(first - second) <= delta;
	}
}