/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import picounit.verify.constraint.DifferentTo;
import picounit.verify.constraint.EqualTo;
import picounit.verify.constraint.Modifier;

public abstract class EqualToTest extends ConstraintsTest {
	public EqualTo equalTo(Object equalTo) {
		return new EqualTo(equalTo, nullModifier(), new SimpleStringer());
	}

	public DifferentTo differentTo(Object differentTo) {
		return new DifferentTo(differentTo, nullModifier(), new SimpleStringer());
	}

	protected final Modifier nullModifier() {
		return Modifier.NULL;
	}

	abstract protected Class type();
}
