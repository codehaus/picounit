/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;


public class StringContains extends ModifiableConstraint<String, String>
	implements StringConstraint {

	private final String contains;

	public StringContains(String contains, Modifier<String, String> modifier) {
		super(modifier);

		this.contains = contains;
	}

	public boolean evaluate(String string) {
		return string != null && modify(string).indexOf(modify(contains)) > -1;
	}

	public String describeFailureImpl() {
		return "does not contain <" + contains + ">";
	}
}
