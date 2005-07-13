/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.LongConstraints;
import picounit.verify.LongConstraintsDeltaKnown;
import picounit.verify.constraint.Evaluator;

public class DefaultLongConstraints extends ExtensibleLongConstraints<LongConstraintsDeltaKnown> 
	implements LongConstraints {

	public DefaultLongConstraints(Evaluator evaluator) {
		super(evaluator);

		setConstraintsStage(this);
	}
}
