/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.Evaluator;

public class DefaultByteArrayConstraints extends PrimativeArrayConstraints<byte[], byte[]>
	implements ByteArrayConstraints {

	public DefaultByteArrayConstraints(Evaluator evaluator) {
		super(evaluator);
	}
	
	public ByteArrayConstraintsDeltaKnown withDelta(byte delta) {
		setDelta(delta);

		return this;
	}
	
	public void contains(byte contains) {
		passes(constraintFactory.primativeArrayContains(contains, modifier()));
	}

	public void doesNotContain(byte doesNotContain) {
		passes(constraintFactory.primativeArrayDoesNotContain(doesNotContain));
	}
}
