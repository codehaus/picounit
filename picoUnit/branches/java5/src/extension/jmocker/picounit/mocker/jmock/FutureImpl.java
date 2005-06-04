/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import picounit.Future;
import picounit.Verify;
import picounit.verify.DefaultVerify;

import java.util.HashSet;
import java.util.Set;

public class FutureImpl implements Future {
	private final Verify verify = new DefaultVerify();
	private final Class futureType;
	private Object value;
	private final Set<Object> compareTo = new HashSet<Object>();

	public FutureImpl(Class futureType) {
		this.futureType = futureType;
	}

	public void setValue(Object value) {
		verify.instanceOf(futureType, value);

		for (Object item : compareTo) {
			verify.equal(value, item);
		}

		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return futureType.hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		compareTo.add(object);

		return true;
	}
}