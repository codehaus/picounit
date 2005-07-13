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

public class FutureImpl<T> implements Future<T> {
	private final Class<T> futureType;
	private final Verify verify;
	private final Set<T> comparedTo = new HashSet<T>();
	private T value;

	public FutureImpl(Class<T> futureType) {
		this.futureType = futureType;
		this.verify = DefaultVerify.create();
	}

	public void setValue(T value) {
		verify.that(value).isAnInstanceOf(futureType);

		for (T compareTo : comparedTo) {
			verify.that(compareTo).isEqualTo(value);
		}

		this.value = value;
	}

	public T getValue() {
		return value;
	}
	
	public int hashCode() {
		return futureType.hashCode();
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object object) {
		comparedTo.add((T) object);

		return true;
	}
}