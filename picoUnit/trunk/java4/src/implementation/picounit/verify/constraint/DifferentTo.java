/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class DifferentTo<T, M> extends ModifiableConstraint<T, M> {
	private final T differentTo;
	private Stringer<T> stringer;

	public DifferentTo(T differentTo, Modifier<T, M> modifier, Stringer<T> stringer) {
		super(modifier);

		this.differentTo = differentTo;
		
		this.stringer = stringer; 
	}

	public boolean evaluate(T value) {
		return (value == null && differentTo != null) ||
			(value != null && differentTo == null) ||
			!modify(value).equals(modify(differentTo));
	}

	public String describeFailureImpl() {
		return "is not different to <" + toString(differentTo) + ">";
	}
	
	protected String toString(T value) {
		return stringer.toString(	value);
	}

	@SuppressWarnings("unchecked")
	protected boolean equalsImpl(Object other) {
		DifferentTo<T, M> comparesTo = (DifferentTo<T, M>) other;

		return differentTo.equals(comparesTo.differentTo);
	}

	public int hashCode() {
		return differentTo.hashCode();
	}

	public String toString() {
		return "Is different to: " + differentTo;
	}
}
