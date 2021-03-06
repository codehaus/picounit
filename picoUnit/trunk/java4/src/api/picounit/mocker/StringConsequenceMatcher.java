/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker;

public interface StringConsequenceMatcher extends ThrowsConsequenceMatcher,
	OccurencesMatcher {

	OccurencesMatcher andReturn(String result);
	OccurencesMatcher andReturn(String[] results);

	OccurencesMatcher andPerform(StringAction stringAction);
}
