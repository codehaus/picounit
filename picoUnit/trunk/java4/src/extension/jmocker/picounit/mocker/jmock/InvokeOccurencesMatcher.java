/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import picounit.Occurences;

public class InvokeOccurencesMatcher extends ExtendedInvokedRecorder {
	private final Occurences occurences;

	public InvokeOccurencesMatcher(Occurences occurences) {
		this.occurences = occurences;
	}

	public void verify() {
		occurences.verify(getInvocationCount());
	}
}