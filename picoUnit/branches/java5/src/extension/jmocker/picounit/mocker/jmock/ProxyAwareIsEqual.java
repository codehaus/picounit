/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;
import org.jmock.core.constraint.IsEqual;

public class ProxyAwareIsEqual extends IsEqual implements Constraint {
	private final Object equalArg;

	public ProxyAwareIsEqual(Object equalArg) {
		super(equalArg);

		this.equalArg = equalArg;
	}

	@Override
	public StringBuffer describeTo( StringBuffer buffer ) {
		return isProxy() ? describeProxy(buffer) : super.describeTo(buffer); 
	}

	@Override
	public String toString() {
		return isProxy() ? equalArg.toString() : super.toString(); 
	}

	private StringBuffer describeProxy(StringBuffer buffer) {
		return buffer.append(equalArg);
	}

	private boolean isProxy() {
		return ProxyFactory.isProxy(equalArg);
	}
}
