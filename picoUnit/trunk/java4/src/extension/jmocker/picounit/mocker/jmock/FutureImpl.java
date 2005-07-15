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
import java.util.Iterator;
import java.util.Set;

public class FutureImpl implements Future {
	private final Class futureType;
	private final Verify verify;
	private final Set comparedTo = new HashSet();
	private Object value;

	public FutureImpl(Class futureType) {
		this.futureType = futureType;
		this.verify = DefaultVerify.create();
	}

	public void setValue(Object value) {
		verify.that(value).isAnInstanceOf(futureType);

		for (Iterator iterator = comparedTo.iterator(); iterator.hasNext();) {
			verify.that(iterator.next()).isEqualTo(value);			
		}

		this.value = value;
	}

	public Object getValue() {
		return value;
	}
	
	public int hashCode() {
		return futureType.hashCode();
	}

	public boolean equals(Object object) {
		comparedTo.add(object);

		return true;
	}
}