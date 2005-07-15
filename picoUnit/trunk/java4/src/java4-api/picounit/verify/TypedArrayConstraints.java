/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public interface TypedArrayConstraints {
	void isEqualTo(Object[] equalTo);
	void isDifferenTo(Object[] differentTo);
	
	void contains(Object element);
	void doesNotContain(Object element);

	void isTheSameAs(Object[] sameAs);
	void isNotTheSameAs(Object[] notTheSameAs);

	void isNull();
	void isNotNull();
}
