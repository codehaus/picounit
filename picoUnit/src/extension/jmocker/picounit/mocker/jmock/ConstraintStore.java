/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

import java.util.List;

public interface ConstraintStore {
	int putInteger(Constraint integerConstraint);
	String putString(Constraint constraint);

	Constraint getConstraint(Object parameter);
	Constraint[] getConstraints(List parameters);
}
