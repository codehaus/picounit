/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

public class ExtendedOr implements Constraint {
	private final Constraint[] constraints;

	public ExtendedOr(Constraint[] constraints) {
		this.constraints = constraints;
	}

    public boolean eval( Object o ) {
    	for (int index = 0; index < constraints.length; index++ ) {
			if (constraints[index].eval(o)) {
				return true;
			}
		}
    	
        return false;
    }

    public StringBuffer describeTo( StringBuffer buffer ) {
        buffer.append("(");

        for (int index = 0; index < constraints.length; index++ ) {
			constraints[index].describeTo(buffer);

			if (index < constraints.length) {
				buffer.append(" or ");
			}
		}

        buffer.append(")");

        return buffer;
    }
}
