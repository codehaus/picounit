/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.util.MethodUtil;

import java.lang.reflect.Method;

public class Implementation {
	public void method() {
	}
	
	public static final Method method = new MethodUtil().getMethod(Implementation.class, "method");
}