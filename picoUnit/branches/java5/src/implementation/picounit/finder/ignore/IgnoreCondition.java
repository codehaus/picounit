/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder.ignore;

import picounit.Ignore;
import picounit.IgnoreReason;
import picounit.finder.ImplementsCondition;
import picounit.finder.TestInstantiator;

import java.util.HashMap;
import java.util.Map;

public class IgnoreCondition {
	private final ImplementsCondition implementsCondition;
	private final TestInstantiator testInstantiator;
	private final Map ignoredReasons = new HashMap();

	public IgnoreCondition(ImplementsCondition implementsCondition, TestInstantiator testInstantiator) {
		this.implementsCondition = implementsCondition;
		this.testInstantiator = testInstantiator;
	}

	public boolean matches(Class aClass) {
		return implementsCondition.matches(aClass) && isIgnored(aClass);
	}
	
	public String whyIgnored(Class testClass) {
		return (String) ignoredReasons.get(testClass);
	}

	private boolean isIgnored(Class aClass) {
		IgnoreReason ignoreReason = new IgnoreReason();

		try {
			Ignore ignore = (Ignore) testInstantiator.instantiate(aClass);

			ignore.ignoredWhen(ignoreReason);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		if (ignoreReason.isIgnored()) {
			ignoredReasons.put(aClass, ignoreReason.ignoreWhy());
		}

		return ignoreReason.isIgnored();
	}

	public boolean equals(Object object) {
		if (object == null || !getClass().equals(object.getClass())) {
			return false;
		}

		IgnoreCondition other = (IgnoreCondition) object;

		return implementsCondition.equals(other.implementsCondition) &&
			testInstantiator.equals(other.testInstantiator);
	}
	
	public int hashCode() {
		return getClass().hashCode();
	}

	public String toString() {
		return "Ignore[" + implementsCondition + "," + testInstantiator + "]";
	}
}
