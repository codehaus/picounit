/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class DefaultDoubleConstraints extends ExtensibleDoubleConstraints
	implements DoubleConstraints {

	public DefaultDoubleConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public DoubleConstraintsDeltaKnown withDelta(double delta) {
		setDelta(new Double(delta));
		
		return this;
	}
}
