/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import java.lang.reflect.Array;


public class PrimativeArrayDoesNotContain extends Constraint {
	private final Object doesNotContain;

	public PrimativeArrayDoesNotContain(Object doesNotContain) {
		this.doesNotContain = doesNotContain;
	}

	public boolean evaluate(Object searchIn) {
		return searchIn == null || !contains(searchIn);
	}

	public String describeFailure() {
		return "contains <" + doesNotContain + ">";
	}

	private boolean contains(Object searchIn) {
		int length = Array.getLength(searchIn);

		for (int index = 0; index < length; index++ ) {
			if (doesNotContain.equals(Array.get(searchIn, index))) {
				return true;
			}
		}

		return false;
	}
}
