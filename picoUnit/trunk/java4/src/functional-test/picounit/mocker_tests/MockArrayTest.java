/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker_tests;

import picounit.Interface;
import picounit.Test;
import picounit.Verify;

public class MockArrayTest implements Test {
	private Interface[] oneDimensionalArray;
	private Interface[][] twoDimensionalArray;

	public void mock(Interface[] oneDimensionalArray, Interface[][] twoDimensionalArray) {
		this.oneDimensionalArray = oneDimensionalArray;
		this.twoDimensionalArray = twoDimensionalArray;
	}

	public void testHaveOneDimensionalInterfaceArray(Verify verify) {
		verify.because("array should not be null")
			.that(oneDimensionalArray).isNotNull();
	
		verify.because("array should have 1 element")
			.that(oneDimensionalArray.length).isEqualTo(1);

		verify.because("array element should not be null")
			.that(oneDimensionalArray[0]).isNotNull();
	}

	public void testHaveTwoDimensionalInterfaceArray(Verify verify) {
		verify.because("array should not be null")
			.that(twoDimensionalArray).isNotNull();

		verify.because("array should have 1 element")
			.that(twoDimensionalArray.length).isEqualTo(1);

		verify.because("array's array should have 1 element")
			.that(twoDimensionalArray[0].length).isEqualTo(1);

		verify.because("array's array element should not be null")
			.that(twoDimensionalArray[0][0]).isNotNull();
	}
}
