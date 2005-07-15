/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public interface Stringer {
	String toString(Object value);

	Stringer DEFAULT = new Stringer() {
		public String toString(Object value) {
			return value == null ? "null" : value.toString();
		}
	};
}
