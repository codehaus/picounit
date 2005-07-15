/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.StringConstraint;

public interface StringConstraintsCaseConsiderationKnown {
	void isEqualTo(String equalTo);
	void isDifferentTo(String differentTo);

	void isGreaterThanOrEqualTo(String greaterThanOrEqualTo);
	void isLessThan(String lessThan);
	void isLessThanOrEqualTo(String lessThanOrEqualTo);
	
	void isNull();
	void isNotNull();

	void contains(String contains);
	void doesNotContain(String doesNotContain);

	void matches(String matches);
	void doesNotMatch(String doesNotMatch);

	void passes(StringConstraint stringConstraint);
}
