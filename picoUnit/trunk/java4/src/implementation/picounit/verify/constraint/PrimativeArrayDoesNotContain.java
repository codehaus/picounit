/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import java.lang.reflect.Array;

public class PrimativeArrayDoesNotContain extends ModifiableConstraint {
	private final Object doesNotContain;

	public PrimativeArrayDoesNotContain(Object doesNotContain, Modifier modifier) {
		super(modifier);

		this.doesNotContain = doesNotContain;
	}

	public boolean evaluate(Object searchIn) {
		return searchIn == null || !contains(modify(searchIn), modify(doesNotContain));
	}

	public String describeFailureImpl() {
		return "contains <" + doesNotContain + ">";
	}

	private boolean contains(Object searchIn, Object doesNotContain) {
		int length = Array.getLength(searchIn);

		for (int index = 0; index < length; index++ ) {
			if (doesNotContain.equals(Array.get(searchIn, index))) {
				return true;
			}
		}

		return false;
	}
}
