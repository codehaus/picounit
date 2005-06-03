/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

public class StringEqualIgnoreCase implements Constraint {
	private final String toEqual;

	public StringEqualIgnoreCase(String toEqual) {
		this.toEqual = toEqual;
	}

	public boolean eval(Object o) {
		return o instanceof String && ((String)o).toLowerCase().equals(toEqual.toLowerCase());
	}

	public StringBuffer describeTo(StringBuffer buffer) {
        return buffer.append("a string equal to (ignoring case) \"")
        .append(toEqual)
        .append("\"");
	}
}
