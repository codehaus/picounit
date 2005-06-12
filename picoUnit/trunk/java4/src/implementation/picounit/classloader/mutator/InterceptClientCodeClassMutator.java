/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.mutator;

public class InterceptClientCodeClassMutator extends DefaultClassMutator {
	public InterceptClientCodeClassMutator() {
		super(new InterceptClientCodeClassAdapter());
	}

	public boolean shouldMutate(String className) {
		return true;
	}
}
