/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class DifferentTo extends ModifiableConstraint {
	private final Object differentTo;
	private Stringer stringer;

	public DifferentTo(Object differentTo, Modifier modifier, Stringer stringer) {
		super(modifier);

		this.differentTo = differentTo;
		
		this.stringer = stringer; 
	}

	public boolean evaluate(Object value) {
		return (value == null && differentTo != null) ||
			(value != null && differentTo == null) ||
			!modify(value).equals(modify(differentTo));
	}

	public String describeFailureImpl() {
		return "is not different to <" + toString(differentTo) + ">";
	}
	
	protected String toString(Object value) {
		return stringer.toString(	value);
	}

	protected boolean equalsImpl(Object other) {
		DifferentTo comparesTo = (DifferentTo) other;

		return differentTo.equals(comparesTo.differentTo);
	}

	public int hashCode() {
		return differentTo.hashCode();
	}

	public String toString() {
		return "Is different to: " + differentTo;
	}
}
