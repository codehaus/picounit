/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import java.lang.reflect.Array;

public class EqualTo extends ModifiableConstraint {
	private final Object equalTo;
	private Stringer stringer;

	public EqualTo(Object equalTo, Modifier modifier, Stringer stringer) {
		super(modifier);

		this.equalTo = equalTo;
		
		this.stringer = stringer;
	}

	public boolean evaluate(Object value) {
		return (value == null && equalTo == null) || equals(modify(value), modify(equalTo));
	}

	private boolean equals(Object value, Object equalTo) {
		return value.getClass().isArray() && arrayEquals(value, equalTo) ||
			value.equals(equalTo);
	}

	private boolean arrayEquals(Object value, Object equalTo) {
		if (!equalTo.getClass().isArray()) {
			return false;
		}
		
		int length = Array.getLength(value);
		
		if (Array.getLength(equalTo) != length) {
			return false;
		}
		
		for (int index = 0; index < length; index++ ) {
			if (!elementsEqual(Array.get(value, index), Array.get(equalTo, index))) {
				return false;
			}
		}
		
		return true;
	}

	private boolean elementsEqual(Object element, Object otherElement) {
		return (element == null && otherElement == null) ||
			(element != null && otherElement != null && element.equals(otherElement));
	}

	public String describeFailureImpl() {
		return "is not equal to <" + stringer.toString(equalTo) + ">";
	}
	
	protected boolean equalsImpl(Object other) {
		EqualTo comparesTo = (EqualTo) other;

		return equalTo.equals(comparesTo.equalTo);
	}

	public int hashCode() {
		return equalTo.hashCode();
	}

	public String toString() {
		return "Is equal to: " + equalTo;
	}
}
