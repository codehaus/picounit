/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import picounit.Context;
import picounit.reflection.Instantiator;

public class FailingContextClass implements ContextClass {
	private final ClassNotFoundException classNotFoundException;

	public FailingContextClass(ClassNotFoundException classNotFoundException) {
		this.classNotFoundException = classNotFoundException;
	}

	public Context getContext(Instantiator instantiator) throws ClassNotFoundException {
		throw classNotFoundException;
	}
}
