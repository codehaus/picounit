/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class DeltaModifier implements Modifier {
	private final Number delta;

	public DeltaModifier(Number delta) {
		this.delta = delta;
	}
	
	public Object modify(Object value) {
		return new Delta((Number) value, delta);
	}

	public String getName() {
		return " (with delta: " + delta + ")";
	}
}
