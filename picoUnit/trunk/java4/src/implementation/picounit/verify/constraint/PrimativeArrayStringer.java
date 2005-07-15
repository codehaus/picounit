/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import java.lang.reflect.Array;

public class PrimativeArrayStringer implements Stringer {
	public String toString(Object value) {
		if (value == null) {
			return null;
		}

		int length = Array.getLength(value);

		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append('[');

		for (int i = 0; i < length; i++ ) {
			stringBuffer.append(Array.get(value, i));
			if (i < length - 1) {
				stringBuffer.append(", ");
			}
		}

		stringBuffer.append(']');

		return stringBuffer.toString();
	}
}
