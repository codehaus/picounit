/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker;

import picounit.Occurences;

public interface OccurencesMatcher extends PostConsequenceMatcher {
	PostConsequenceMatcher once();
	PostConsequenceMatcher atLeastOnce();

	PostConsequenceMatcher occurs(int occurences);
	PostConsequenceMatcher occurs(int min, int max);
	PostConsequenceMatcher occurs(Occurences occurences);
}