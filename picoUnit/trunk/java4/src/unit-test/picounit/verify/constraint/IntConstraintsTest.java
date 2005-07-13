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

public class IntConstraintsTest extends ComparableToTest<Integer, Delta> {
	private DefaultIntegerConstraints intConstraints;	
	
	protected Constraints<Integer, Delta> constraints(Evaluator evaluator) {
		return intConstraints;
	}

	public void mock() {
		this.intConstraints = new DefaultIntegerConstraints(evaluator());
	}
	
	public void testIsEqualTo(Mocker should) {
		evaluate(equalTo(123));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isEqualTo(123);
	}

	public void testIsDifferentTo(Mocker should) {
		evaluate(differentTo(123));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isDifferentTo(123);
	}

	public void testIsGreaterThan(Mocker should) {
		evaluate(greaterThan(123)); 

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isGreaterThan(123);
	}

	public void testIsGreaterThanOrEqualTo(Mocker should) {
		evaluate(greaterThanOrEqualTo(123));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isGreaterThanOrEqualTo(123);
	}

	public void testIsLessThan(Mocker should) {
		evaluate(lessThan(123));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isLessThan(123);
	}

	public void testIsLessThanOrEqualTo(Mocker should) {
		evaluate(lessThanOrEqualTo(123));

		should.expectAboveWhenTheFollowingOccurs();

		intConstraints.isLessThanOrEqualTo(123);
	}

	protected Class<Integer> type() {
		return int.class;
	}
}
