/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface Constraints extends IntegerConstraints, LongConstraints, DoubleConstraints,
	StringConstraints, ObjectConstraints {

	Future future(Class futureType);
	<Type> Type instanceOf(Class instanceOf);
}
