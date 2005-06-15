/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

public class NoTestsFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final Class startingClass;

	public NoTestsFoundException(Class startingClass) {
		super("No tests found");

		this.startingClass = startingClass;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !getClass().equals(object.getClass())) {
			return false;
		}
		
		NoTestsFoundException other = (NoTestsFoundException) object;
		
		return startingClass.equals(other.startingClass);
	}
	
	@Override
	public int hashCode() {
		return startingClass.hashCode();
	}
}
