/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class ComparesTo extends ModifiableConstraint {
	private final Comparitor comparitor;
	private final String comparisonType;
	private final Object compareTo;

	public static ComparesTo greaterThan(Object greaterThan, Modifier modifier) {

		return new ComparesTo(greaterThan, modifier, GREATER_THAN, "greater than");
	}

	public static ComparesTo greaterThanOrEqualTo(Object greaterThanOrEqualTo, Modifier modifier) {
		return new ComparesTo(greaterThanOrEqualTo, modifier, GREATER_THAN_OR_EQUAL_TO, "greater than or equal to");
	}

	public static ComparesTo lessThan(Object lessThan, Modifier modifier) {

		return new ComparesTo(lessThan, modifier, LESS_THAN, "less than");
	}

	public static ComparesTo lessThanOrEqualTo(Object lessThanOrEqualTo, Modifier modifier) {
		return new ComparesTo(lessThanOrEqualTo, modifier, LESS_THAN_OR_EQUAL_TO, "less than or equal to");
	}

	public ComparesTo(Object compareTo, Modifier modifier, Comparitor comparitor, String comparisonType) {
		super(modifier);
		
		this.compareTo = compareTo;
		this.comparitor = comparitor;
		this.comparisonType = comparisonType;
	}
	
	public boolean evaluate(Object value) {
		return comparitor.matches(modifyToComparable(value).compareTo(modifyToComparable(compareTo)));
	}
	
	public String describeFailureImpl() {
		return "is not " + comparisonType + " <" + compareTo + ">";
	}

	protected boolean equalsImpl(Object other) {
		ComparesTo comparesTo = (ComparesTo) other;
	
		return compareTo.equals(comparesTo.compareTo) &&
			comparisonType.equals(comparesTo.comparisonType);
	}

	public int hashCode() {
		return compareTo.hashCode() ^ comparisonType.hashCode();
	}

	public String toString() {
		return "Is " + comparisonType + ": " + compareTo;
	}

	private static final Comparitor GREATER_THAN = new Comparitor(1, 1);
	private static final Comparitor GREATER_THAN_OR_EQUAL_TO = new Comparitor(0, 1);
	private static final Comparitor LESS_THAN = new Comparitor(-1, -1);
	private static final Comparitor LESS_THAN_OR_EQUAL_TO = new Comparitor(-1, 0);
	
	public static final class Comparitor {
		private final int min;
		private final int max;

		public Comparitor(int min, int max) {
			this.min = min;
			this.max = max;
		}

		public boolean matches(int comparisionResult) {
			return comparisionResult == min || comparisionResult == max;
		}
	}
	
	private Comparable modifyToComparable(Object object) {
		return (Comparable) super.modify(object);
	}
}
