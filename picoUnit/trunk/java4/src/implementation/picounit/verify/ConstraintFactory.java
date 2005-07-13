/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.ComparesTo;
import picounit.verify.constraint.Constraint;
import picounit.verify.constraint.DifferentTo;
import picounit.verify.constraint.EqualTo;
import picounit.verify.constraint.Modifier;
import picounit.verify.constraint.PrimativeArrayContains;
import picounit.verify.constraint.PrimativeArrayDoesNotContain;
import picounit.verify.constraint.Stringer;

public class ConstraintFactory<T> {
	@SuppressWarnings("unchecked")
	public <M> Constraint<T> primativeArrayContains(Object contains, Modifier<T, M> modifier) {
		return new PrimativeArrayContains(contains, modifier);
	}
	
	@SuppressWarnings("unchecked")
	protected Constraint<T> primativeArrayDoesNotContain(Object doesNotContain) {
		return new PrimativeArrayDoesNotContain(doesNotContain);
	}

	public <M> Constraint<T> equalTo(T equalTo, Modifier<T, M> modifier, Stringer<T> stringer) {
		return new EqualTo<T, M>(equalTo, modifier, stringer);
	}

	public <M> Constraint<T> differentTo(T equalTo, Modifier<T, M> modifier, Stringer<T> stringer) {
		return new DifferentTo<T, M>(equalTo, modifier, stringer);
	}
	
	public <M extends Comparable<M>> Constraint<T> greaterThan(T greaterThan, Modifier<T, M> modifier) {
		return ComparesTo.greaterThan(greaterThan, modifier);
	}

	public <M extends Comparable<M>> Constraint<T> greaterThanOrEqualTo(T greaterThanOrEqualTo, Modifier<T, M> modifier) {
		return ComparesTo.greaterThanOrEqualTo(greaterThanOrEqualTo, modifier);
	}

	public <M extends Comparable<M>> Constraint<T> lessThan(T lessThan, Modifier<T, M> modifier) {
		return ComparesTo.lessThan(lessThan, modifier);
	}

	public <M extends Comparable<M>> Constraint<T> lessThanOrEqualTo(T lessThanOrEqualTo, Modifier<T, M> modifier) {
		return ComparesTo.lessThanOrEqualTo(lessThanOrEqualTo, modifier);
	}
}
