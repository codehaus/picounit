/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class ContainsElement extends Constraint {
	private final Object element;
	private ArrayModifier<Object, Object>modifier;

	public ContainsElement(Object element) {
		this(element, ArrayModifier.NULL);
	}
	
	@SuppressWarnings("unchecked")
	public ContainsElement(Object element, ArrayModifier modifier) {
		this.element = element;
		this.modifier = modifier;
	}

	public boolean evaluate(Object searchIn) {
		return searchIn != null && contains(modify((Object[]) searchIn), modify(element));
	}

	public String describeFailure() {
		return "does not contain <" + element + ">" + modifier.getName();
	}
	
	private boolean contains(Object[] searchIn, Object contains) {
		for(Object object : searchIn) {
			if (object != null && object.equals(contains)) {
				return true;
			}
		}
		
		return false;
	}
	
	private Object[] modify(Object[] values) {
		return (Object[]) modifier.modify(values);
	}
	
	private Object modify(Object value) {
		return modifier.modifyElement(value);
	}
}
