/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.reflection;

import java.lang.reflect.InvocationTargetException;

public interface Instantiator {
	<T> T instantiate(Class<T> toInstantiate) throws IllegalArgumentException,
		InstantiationException, IllegalAccessException, InvocationTargetException;
}