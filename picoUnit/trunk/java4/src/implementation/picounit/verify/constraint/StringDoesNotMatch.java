/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;


public class StringDoesNotMatch extends ModifiableConstraint
	implements StringConstraint {

	private final String doesNotMatch;

	public StringDoesNotMatch(String doesNotMatch, Modifier modifier) {
		super(modifier);

		this.doesNotMatch = doesNotMatch;
	}
	
	public boolean evaluate(Object value) {
		return evaluate((String) value);
	}

	public boolean evaluate(String value) {
		return value == null || !modify(value).matches(modify(doesNotMatch));
	}

	protected String describeFailureImpl() {
		return "matches <" + doesNotMatch + ">";
	}

	private String modify(String string) {
		return (String) super.modify(string);
	}
}
