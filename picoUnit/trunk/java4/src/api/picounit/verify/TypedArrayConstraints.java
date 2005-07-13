/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public interface TypedArrayConstraints<T> {
	void isEqualTo(T ... equalTo);
	void isDifferenTo(T ... differentTo);
	
	void contains(T element);
	void doesNotContain(T element);

	void isTheSameAs(T[] sameAs);
	void isNotTheSameAs(T[] notTheSameAs);

	void isNull();
	void isNotNull();
}
