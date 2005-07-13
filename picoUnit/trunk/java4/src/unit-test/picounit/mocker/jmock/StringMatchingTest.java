/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import previous.picounit.Test;
import previous.picounit.Verify;

public class StringMatchingTest implements Test {
	public void testIsEqualWhenPatternIsTheSame(Verify verify) {
		verify.that(stringMatching("pattern"))
			.isEqualTo(stringMatching("pattern"));
	}

	private StringMatching stringMatching(String pattern) {
		return new StringMatching(pattern);
	}
}
