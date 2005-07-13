/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import java.util.Arrays;

public class ArrayStringer<T> implements Stringer<T[]> {
	public String toString(T[] value) {
		return Arrays.toString(value);
	}
}
