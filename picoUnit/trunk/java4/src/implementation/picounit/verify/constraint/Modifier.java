/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public interface Modifier<T, M> {
	M modify(T value);

	String getName();

	Modifier NULL = new Modifier() {
		@SuppressWarnings("unchecked")
		public Object modify(Object value) {
			return value;
		}

		public String getName() {
			return "";
		}
	};
}
