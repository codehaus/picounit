/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class DeltaModifier<T extends Number> implements Modifier<T, Delta> {
	private final T delta;

	public DeltaModifier(T delta) {
		this.delta = delta;
	}
	
	public Delta modify(T value) {
		return new Delta(value, delta);
	}

	public String getName() {
		return " (with delta: " + delta + ")";
	}
}
