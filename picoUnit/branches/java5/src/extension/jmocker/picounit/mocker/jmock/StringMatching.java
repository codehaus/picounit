/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

public class StringMatching implements Constraint {
	private final String pattern;

	public StringMatching(String pattern) {
		this.pattern = pattern;
	}

	public boolean eval(Object object) {
		return object instanceof String && matchesPattern((String) object);
	}

	private boolean matchesPattern(String string) {
		return string.matches(pattern);
	}

	public StringBuffer describeTo(StringBuffer buffer) {
        return buffer.append("a string matching \"")
        	.append(pattern)
        	.append("\"");
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !object.getClass().equals(getClass())) {
			return false;
		}
		
		StringMatching other = (StringMatching) object;

		return pattern.equals(other.pattern);
	}
	
	@Override
	public int hashCode() {
		return pattern.hashCode();
	}
}
