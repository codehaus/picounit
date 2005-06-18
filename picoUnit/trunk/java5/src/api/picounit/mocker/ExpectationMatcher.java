/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker;

public interface ExpectationMatcher {
	<Type> ConsequenceMatcher<Type> call(Type ignore);
	<Type> PostConsequenceMatcher notCall(Type ignore);

	<Type> ConsequenceMatcher<Type> expect(Type ignore);	
	<Type> PostConsequenceMatcher doNotExpect(Type ignore);
}
