/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.verify.constraint.ByteConstraint;
import picounit.verify.constraint.ByteToBinaryStringer;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.MatchesMask;

public class ExtensibleByteConstraints extends NumberConstraints {
	private final ByteToBinaryStringer binaryStringer = new ByteToBinaryStringer();

	public ExtensibleByteConstraints(Evaluator evaluator) {
		super(evaluator);
	}

	public void isEqualTo(byte equalTo) {
		passes(constraintFactory.equalTo(new Byte(equalTo), modifier(), stringer()));
	}

	public void isDifferentTo(byte differentTo) {
		passes(constraintFactory.differentTo(new Byte(differentTo), modifier(), stringer()));
	}

	public void isGreaterThan(byte greaterThan) {
		passes(constraintFactory.greaterThan(new Byte(greaterThan), modifier()));
	}

	public void isGreaterThanOrEqualTo(byte greaterThanOrEqualTo) {
		passes(constraintFactory.greaterThanOrEqualTo(new Byte(greaterThanOrEqualTo), modifier()));
	}

	public void isLessThan(byte lessThan) {
		passes(constraintFactory.lessThan(new Byte(lessThan), modifier()));
	}

	public void isLessThanOrEqualTo(byte lessThanOrEqualTo) {
		passes(constraintFactory.lessThanOrEqualTo(new Byte(lessThanOrEqualTo), modifier()));
	}

	public void matchesMask(byte mask) {
		passes(new MatchesMask(mask), binaryStringer);
	}

	public void passes(ByteConstraint byteConstraint) {
		passes(createDelegate(byte.class, byteConstraint));
	}
}
