/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public class IgnoreReason {
	private String why;
	private boolean condition;

	public IgnoreReason() {
		this.condition = true;
		this.why = "[no reason supplied]"; 
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}

	public boolean isIgnored() {
		return condition;
	}

	public String ignoreWhy() {
		return why;
	}
}
