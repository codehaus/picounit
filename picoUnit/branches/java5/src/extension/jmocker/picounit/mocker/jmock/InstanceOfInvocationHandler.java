/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

public class InstanceOfInvocationHandler extends EqualsInvocationHandler {
	private final Class instanceOfWhat;

	public InstanceOfInvocationHandler(Class instanceOfWhat) {
		this.instanceOfWhat = instanceOfWhat;
	}

	@Override
	public boolean equals(Object object) {
		return instanceOfWhat.isAssignableFrom(object.getClass());
	}
	
	@Override
	public String toString() {
		return "instanceOf<" + instanceOfWhat.getName() + ">";
	}
}
