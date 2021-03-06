/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import java.lang.reflect.Method;

public class AlwaysPassingTestFilter implements TestFilter {
	public boolean matches(Class testClass) {
		return true;
	}

	public boolean matches(Method testMethod) {
		return true;
	}
}
