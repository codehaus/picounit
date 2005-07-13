/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Constraint;

public interface TypedConstraints<T> {
	void isEqualTo(T equalTo);
	void isDifferentTo(T differentTo);

	void isAnInstanceOf(Class instanceOf);
	void isNotAnInstanceOf(Class notInstanceOf);

	void isTheSameAs(T sameAs);
	void isNotTheSameAs(T notTheSameAs);

	void isNull();
	void isNotNull();

	void passes(Constraint<T> constraint);
}
