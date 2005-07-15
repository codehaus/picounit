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

public abstract class ComparableToTest extends EqualToTest {
	public EqualTo equalTo(Object equalTo) {
		return new EqualTo(equalTo, nullModifier(), new SimpleStringer());
	}

	public DifferentTo differentTo(Object differentTo) {
		return new DifferentTo(differentTo, nullModifier(), new SimpleStringer());
	}

	public ComparesTo greaterThan(Object greaterThan) {
		return ComparesTo.greaterThan(greaterThan, nullModifier());
	}

	public ComparesTo greaterThanOrEqualTo(Object greaterThanOrEqualTo) {
		return ComparesTo.greaterThanOrEqualTo(greaterThanOrEqualTo, nullModifier());
	}

	public ComparesTo lessThan(Object lessThan) {
		return ComparesTo.lessThan(lessThan, nullModifier());
	}

	public ComparesTo lessThanOrEqualTo(Object lessThanOrEqualTo) {
		return ComparesTo.lessThanOrEqualTo(lessThanOrEqualTo, nullModifier());
	}
}
