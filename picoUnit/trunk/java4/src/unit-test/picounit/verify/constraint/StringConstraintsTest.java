/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import picounit.Ignore;
import picounit.IgnoreReason;
import picounit.Mocker;
import picounit.verify.Constraints;
import picounit.verify.DefaultStringConstraints;
import picounit.verify.constraint.Evaluator;

public class StringConstraintsTest extends ConstraintsTest<String, String> implements Ignore {
	private DefaultStringConstraints stringConstraints;

	public void mock() {
		this.stringConstraints = new DefaultStringConstraints(evaluator());
	}

	protected Constraints<String, String> constraints(Evaluator evaluator) {
		return new DefaultStringConstraints(evaluator);
	}

	public void testIsEqualTo(Mocker should) {
//		evaluate(new StringIsEqual("abc", false));
		
		should.expectAboveWhenTheFollowingOccurs();
		
		stringConstraints.isEqualTo("abc");
	}
	
	public void testIsEqualToIgnoringCase(Mocker should) {
//		evaluate(new StringIsEqual("abc", true));
		
		should.expectAboveWhenTheFollowingOccurs();
		
		stringConstraints.ignoringCase().isEqualTo("abc");
	}

	public void ignoredWhen(IgnoreReason ignoreReason) {
		ignoreReason.setWhy("Have done yet!");
	}
}
