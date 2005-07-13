/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class InstanceOf extends Constraint<Object> {
	private final Class instanceOf;

	public InstanceOf(Class instanceOf) {
		this.instanceOf = instanceOf;
	}

	public boolean evaluate(Object value) {
		return instanceOf.isInstance(value);
	}

	public String describeFailure() {
		return "is not an instance of <" + instanceOf.getName() + ">";
	}
}
