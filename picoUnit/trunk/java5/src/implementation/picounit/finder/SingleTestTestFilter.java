/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import java.lang.reflect.Method;

public class SingleTestTestFilter implements TestFilter {
	private final Class testClass;

	public SingleTestTestFilter(Class testClass) {
		this.testClass = testClass;
	}

	public boolean matches(Class testClass) {
		return this.testClass.equals(testClass);
	}

	public boolean matches(Method testMethod) {
		return true;
	}
	
	@Override
	public String toString() {
		return "SingleTestTestFilter(" + testClass.getName() + ")";
	}
}
