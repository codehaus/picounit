/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;


import java.lang.reflect.Modifier;

public class ImplementsCondition implements Condition {
	private final Class toImplement;

	public ImplementsCondition(Class toImplement) {
		this.toImplement = toImplement;
	}

	public boolean matches(Class aClass) {
		return toImplement.isAssignableFrom(aClass) &&
			!Modifier.isAbstract(aClass.getModifiers());
	}

	public boolean equals(Object object) {
		if (object == null || !getClass().equals(object.getClass())) {
			return false;
		}

		ImplementsCondition other = (ImplementsCondition) object;

		return toImplement.equals(other.toImplement);
	}

	public int hashCode() {
		return toImplement.hashCode();
	}

	public String toString() {
		return getClass().getName() + ": " + toImplement;
	}
}
