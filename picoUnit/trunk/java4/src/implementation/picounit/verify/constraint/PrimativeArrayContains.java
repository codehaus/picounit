/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import java.lang.reflect.Array;

public class PrimativeArrayContains extends ModifiableConstraint {
	private final Object contains;

	@SuppressWarnings("unchecked")
	public PrimativeArrayContains(Object contains, Modifier modifier) {
		super(modifier);

		this.contains = contains;
	}

	@SuppressWarnings("unchecked")
	public boolean evaluate(Object searchIn) {
		return searchIn != null && contains(modify(searchIn), modify(contains));
	}

	public String describeFailureImpl() {
		return "does not contain <" + contains + ">";
	}

	private boolean contains(Object searchIn, Object contains) {
		int length = Array.getLength(searchIn);

		for (int index = 0; index < length; index++ ) {
			if (contains.equals(Array.get(searchIn, index))) {
				return true;
			}
		}

		return false;
	}
}
