/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;


public class StringDoesNotContain extends ModifiableConstraint
	implements StringConstraint{

	private final String doesNotContain;

	public StringDoesNotContain(String doesNotContain, Modifier modifier) {
		super(modifier);

		this.doesNotContain = doesNotContain;
	}
	
	public boolean evaluate(Object value) {
		return evaluate((String) value);
	}

	public boolean evaluate(String value) {
		return value == null || modify(value).indexOf(modify(doesNotContain)) == -1;
	}

	protected String describeFailureImpl() {
		return "does contain <" + doesNotContain + ">";
	}
	
	private String modify(String string) {
		return (String) super.modify(string);
	}
}
