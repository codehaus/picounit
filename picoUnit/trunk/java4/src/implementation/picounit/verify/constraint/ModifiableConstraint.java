/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

abstract public class ModifiableConstraint extends Constraint {
	private final Modifier modifier;

	public ModifiableConstraint(Modifier modifier) {
		this.modifier = modifier;
	}
	
	public String describeFailure() {
		return describeFailureImpl() + modifier.getName();
	}

	// Restating this so generated code have methods in correct order
	abstract public boolean evaluate(Object value);
	abstract protected String describeFailureImpl();

	protected final Object modify(Object value) {
		return value == null ? null : modifier.modify(value);
	}
}
