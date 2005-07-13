/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify;

import picounit.Test;
import picounit.Verify;
import picounit.verify.IntegerConstraints;

public class UseDefaultVerifyTest implements Test {
	public void testOriginalConstraints(Verify verify) {
		verify.that("some string").isEqualTo("some string");
		
		verify.that("abc").ignoringCase().isEqualTo("ABC");
		
		IntegerConstraints that = verify.that(123);
		that.isEqualTo(123);
		that.isGreaterThan(100);
		that.isLessThan(1000);
		that.isLessThanOrEqualTo(123);
		that.isLessThanOrEqualTo(124);
		that.isGreaterThanOrEqualTo(123);
		that.isGreaterThanOrEqualTo(122);
	}
}
