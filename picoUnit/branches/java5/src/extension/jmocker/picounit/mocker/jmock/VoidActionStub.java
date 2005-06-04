/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Invocation;
import org.jmock.core.Stub;

import picounit.mocker.VoidAction;

public class VoidActionStub implements Stub {
	private final VoidAction voidAction;

	public VoidActionStub(VoidAction voidAction) {
		this.voidAction = voidAction;
	}

	public Object invoke(Invocation invocation) throws Throwable {
		voidAction.perform();

		return null;
	}

	public StringBuffer describeTo(StringBuffer  stringBuffer) {
		return null;
	}
}
