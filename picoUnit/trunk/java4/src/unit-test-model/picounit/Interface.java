/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.util.MethodUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface Interface {
	void method() throws SomeException;

	void awkwardMethod() throws InvocationTargetException;
    
    boolean booleanMethod();
    
    Object objectMethod(); 
    
	Method method = new MethodUtil().getMethod(Interface.class, "method");
}