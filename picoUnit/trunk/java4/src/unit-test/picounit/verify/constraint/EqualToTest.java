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

public abstract class EqualToTest<T, M> extends ConstraintsTest<T, M> {
	public EqualTo<T, M> equalTo(T equalTo) {
		return new EqualTo<T, M>(equalTo, nullModifier(), new SimpleStringer<T>());
	}

	public DifferentTo<T, M> differentTo(T differentTo) {
		return new DifferentTo<T, M>(differentTo, nullModifier(), new SimpleStringer<T>());
	}

	@SuppressWarnings("unchecked")
	protected final Modifier<T, M> nullModifier() {
		return Modifier.NULL;
	}

	abstract protected Class<T> type();
}
