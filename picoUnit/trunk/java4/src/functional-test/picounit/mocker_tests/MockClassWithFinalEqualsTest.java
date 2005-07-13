/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker_tests;

import picounit.Test;

public class MockClassWithFinalEqualsTest implements Test {
	public static class ClassWithFinalEquals {
		public final boolean equals(Object obj) {
			return super.equals(obj);
		}
	}

	@SuppressWarnings("unused") 
	public void mock(ClassWithFinalEquals classWithFinalEquals) {
	}
}
