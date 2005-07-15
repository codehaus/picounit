/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker;

public interface IntConsequenceMatcher extends ThrowsConsequenceMatcher {
	OccurencesMatcher andReturn(int result);
	OccurencesMatcher andReturn(int[] results);

	OccurencesMatcher andPerform(IntAction intAction);
}
