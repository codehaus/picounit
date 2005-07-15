/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.ShortConstraints;
import picounit.verify.ShortConstraintsDeltaKnown;
import picounit.verify.constraint.Evaluator;

public class DefaultShortConstraints extends ExtensibleShortConstraints
	implements ShortConstraints {

	public DefaultShortConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public ShortConstraintsDeltaKnown withDelta(short delta) {
		setDelta(new Short(delta));
		
		return this;
	}
}
