/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class DoesNotContainElement extends Constraint {
	private final Object element;
	private final ArrayModifier modifier;

	public DoesNotContainElement(Object element, ArrayModifier modifier) {
		this.element = element;

		this.modifier = modifier;
	}

	public boolean evaluate(Object searchIn) {
		return searchIn == null || doesNotContain(modify((Object[]) searchIn), modify(element));
	}

	public String describeFailure() {
		return "contains <" + element + ">" + modifier.getName();
	}

	private boolean doesNotContain(Object[] searchIn, Object doesNotContain) {
		for (int index = 0; index < searchIn.length; index++ ) {
			if (searchIn[index].equals(doesNotContain)) {
				return false;
			}
		}
		
		return true;
	}
	
	private Object[] modify(Object[] values) {
		return (Object[]) modifier.modify(values);
	}
	
	private Object modify(Object value) {
		return modifier.modifyElement(value);
	}
}
