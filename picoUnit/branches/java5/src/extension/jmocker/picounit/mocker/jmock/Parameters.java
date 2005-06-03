/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import java.lang.reflect.Constructor;

public class Parameters {
	private final int numParameters;
	private final int numInterfaces;
	private final int numClasses;
	private final int numCircular;

	public Parameters(Constructor constructor) {
		Class thisClass = constructor.getDeclaringClass();
		Class[] parameterTypes = constructor.getParameterTypes();

		numParameters = parameterTypes.length;

		int numInterfaces = 0;
		int numClasses = 0;
		int numCircular = 0;

		for (int index = 0; index < parameterTypes.length; index++ ) {
			Class parameterType = parameterTypes[index];

			if (parameterType.isInterface()) {
				numInterfaces++;
			}
			else if (!isPrimative(parameterType)) {
				numClasses++;
			}

			if (thisClass.equals(parameterType)) {
				numCircular++;
			}
		}

		this.numInterfaces = numInterfaces;
		this.numClasses = numClasses;
		this.numCircular = numCircular;
	}

	private boolean isPrimative(Class aClass) {
		return aClass.isPrimitive() || aClass.equals(String.class);
	}

	public boolean less(Parameters other) {
		return numParameters < other.numParameters;
	}

	public boolean lessInterfaces(Parameters other) {
		return numInterfaces < other.numInterfaces;
	}

	public boolean lessClasses(Parameters other) {
		return numClasses < other.numClasses;
	}

	public boolean lessCircular(Parameters other) {
		return numCircular < other.numCircular;
	}
}
