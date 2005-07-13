/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import picounit.verify.Constraints;
import picounit.verify.DefaultFloatConstraints;
import picounit.verify.constraint.Evaluator;

public class FloatConstraintsTest extends ConstraintsTest<Float, Delta> {
	protected Constraints<Float, Delta> constraints(Evaluator evaluator) {
		return new DefaultFloatConstraints(evaluator);
	}
}
