/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Constraint;

public interface TypedConstraints {
	void isEqualTo(Object equalTo);
	void isDifferentTo(Object differentTo);

	void isAnInstanceOf(Class instanceOf);
	void isNotAnInstanceOf(Class notInstanceOf);

	void isTheSameAs(Object sameAs);
	void isNotTheSameAs(Object notTheSameAs);

	void isNull();
	void isNotNull();

	void passes(Constraint constraint);
}
