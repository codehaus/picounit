/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Instrumentation;

public class InstrumentationImpl implements Instrumentation, InstrumentationController {
	private boolean isLast;
	private boolean isFirst;

	public void setIsFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	
	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public boolean isLast() {
		return isLast;
	}
}
