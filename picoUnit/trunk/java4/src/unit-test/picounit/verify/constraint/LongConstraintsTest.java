/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import picounit.verify.Constraints;
import picounit.verify.DefaultLongConstraints;

public class LongConstraintsTest extends ConstraintsTest {
	protected Constraints constraints(Evaluator evaluator) {
		return	new DefaultLongConstraints(evaluator);
	}
}
