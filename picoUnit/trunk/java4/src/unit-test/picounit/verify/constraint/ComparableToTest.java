/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import picounit.verify.constraint.ComparesTo;
import picounit.verify.constraint.DifferentTo;
import picounit.verify.constraint.EqualTo;

public abstract class ComparableToTest<T extends Comparable<T>, M extends Comparable<M>>
	extends EqualToTest<T, M> {

	public EqualTo<T, M> equalTo(T equalTo) {
		return new EqualTo<T, M>(equalTo, nullModifier(), new SimpleStringer<T>());
	}

	public DifferentTo<T, M> differentTo(T differentTo) {
		return new DifferentTo<T, M>(differentTo, nullModifier(), new SimpleStringer<T>());
	}

	public ComparesTo<T, M> greaterThan(T greaterThan) {
		return ComparesTo.greaterThan(greaterThan, nullModifier());
	}

	public ComparesTo<T, M> greaterThanOrEqualTo(T greaterThanOrEqualTo) {
		return ComparesTo.greaterThanOrEqualTo(greaterThanOrEqualTo, nullModifier());
	}

	public ComparesTo<T, M> lessThan(T lessThan) {
		return ComparesTo.lessThan(lessThan, nullModifier());
	}

	public ComparesTo<T, M> lessThanOrEqualTo(T lessThanOrEqualTo) {
		return ComparesTo.lessThanOrEqualTo(lessThanOrEqualTo, nullModifier());
	}
}
