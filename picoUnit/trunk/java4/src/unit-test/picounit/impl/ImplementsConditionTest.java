/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Test;
import picounit.finder.ImplementsCondition;
import previous.picounit.Verify;

public class ImplementsConditionTest implements previous.picounit.Test {
	private ImplementsCondition implementsCondition = new ImplementsCondition(Test.class);

	public void testMatchesWithClassThatImplements(Verify verify) {
		class ImplementsTest implements Test {}

		verify.thatBoolean(implementsCondition.matches(ImplementsTest.class)).isTrue();
	}

	public void testDoesNotMatchAbstractClassThatImplements(Verify verify) {
		abstract class ImplementsTest implements Test {}

		verify.thatBoolean(implementsCondition.matches(ImplementsTest.class)).isFalse();
	}

	interface Extends extends Test {}
	public void testDoesNotMatchInterfacesThatExtends(Verify verify) {
		verify.thatBoolean(implementsCondition.matches(Extends.class)).isFalse();
	}

	public void testDoesNotMatchWithClassThatDoesNotImplement(Verify verify) {
		class DoesNotImplementTest {}

		verify.thatBoolean(implementsCondition.matches(DoesNotImplementTest.class)).isFalse();
	}
}
