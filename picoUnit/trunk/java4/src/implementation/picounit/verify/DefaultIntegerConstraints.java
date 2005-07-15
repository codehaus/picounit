/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.IntegerConstraints;
import picounit.verify.IntegerConstraintsDeltaKnown;
import picounit.verify.constraint.Evaluator;

public class DefaultIntegerConstraints extends ExtensibleIntegerConstraints
	implements IntegerConstraints {

	public DefaultIntegerConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public IntegerConstraintsDeltaKnown withDelta(int delta) {
		setDelta(new Integer(delta));
		
		return this;
	}
}
