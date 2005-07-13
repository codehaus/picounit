/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class ComparesTo<T, M extends Comparable<M>> extends ModifiableConstraint<T, M> {
	private final Comparitor comparitor;
	private final String comparisonType;
	private final T compareTo;

	public static <E, F extends Comparable<F>>
		ComparesTo<E, F> greaterThan(E greaterThan, Modifier<E, F> modifier) {

		return new ComparesTo<E, F>(greaterThan, modifier, GREATER_THAN, "greater than");
	}

	public static <E, F extends Comparable<F>>
		ComparesTo<E, F> greaterThanOrEqualTo(E greaterThanOrEqualTo, Modifier<E, F> modifier) {

		return new ComparesTo<E, F>(greaterThanOrEqualTo, modifier, GREATER_THAN_OR_EQUAL_TO, "greater than or equal to");
	}

	public static <E, F extends Comparable<F>>
		ComparesTo<E, F> lessThan(E lessThan, Modifier<E, F> modifier) {

		return new ComparesTo<E, F>(lessThan, modifier, LESS_THAN, "less than");
	}

	public static <E, F extends Comparable<F>>
		ComparesTo<E, F> lessThanOrEqualTo(E lessThanOrEqualTo, Modifier<E, F> modifier) {

		return new ComparesTo<E, F>(lessThanOrEqualTo, modifier, LESS_THAN_OR_EQUAL_TO, "less than or equal to");
	}

	public ComparesTo(T compareTo, Modifier<T, M> modifier, Comparitor comparitor, String comparisonType) {
		super(modifier);
		
		this.compareTo = compareTo;
		this.comparitor = comparitor;
		this.comparisonType = comparisonType;
	}

	public boolean evaluate(T value) {
		return comparitor.matches(modify(value).compareTo(modify(compareTo)));
	}
	
	public String describeFailureImpl() {
		return "is not " + comparisonType + " <" + compareTo + ">";
	}

	@SuppressWarnings("unchecked")
	protected boolean equalsImpl(Object other) {
		ComparesTo<T, M> comparesTo = (ComparesTo<T, M>) other;
	
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
}
