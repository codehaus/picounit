/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import picounit.Mocker;
import picounit.verify.Constraints;
import picounit.verify.DefaultIntegerConstraints;
import picounit.verify.constraint.Evaluator;

public class IntConstraintsTest extends ComparableToTest {
	private static final int intValue = 123;
	private static final Integer integerValue = new Integer(intValue);
	private DefaultIntegerConstraints intConstraints;	
	
	protected Constraints constraints(Evaluator evaluator) {
		return intConstraints;
	}

	public void mock() {
		this.intConstraints = new DefaultIntegerConstraints(evaluator());
	}
	
	public void testIsEqualTo(Mocker should) {
		evaluate(equalTo(integerValue));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isEqualTo(intValue);
	}

	public void testIsDifferentTo(Mocker should) {
		evaluate(differentTo(integerValue));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isDifferentTo(intValue);
	}

	public void testIsGreaterThan(Mocker should) {
		evaluate(greaterThan(integerValue)); 

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isGreaterThan(intValue);
	}

	public void testIsGreaterThanOrEqualTo(Mocker should) {
		evaluate(greaterThanOrEqualTo(integerValue));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isGreaterThanOrEqualTo(intValue);
	}

	public void testIsLessThan(Mocker should) {
		evaluate(lessThan(integerValue));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isLessThan(intValue);
	}

	public void testIsLessThanOrEqualTo(Mocker should) {
		evaluate(lessThanOrEqualTo(integerValue));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isLessThanOrEqualTo(intValue);
	}

	protected Class type() {
		return int.class;
	}
}
