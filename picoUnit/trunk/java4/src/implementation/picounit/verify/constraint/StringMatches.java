/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class StringMatches extends ModifiableConstraint<String, String> {
	private final String matches;

	public StringMatches(String matches, Modifier<String, String> modifier) {
		super(modifier);

		this.matches = matches;
	}

	public boolean evaluate(String value) {
		return value != null && modify(value).matches(modify(matches));
	}

	protected String describeFailureImpl() {
		return "does not match <" + matches + ">";
	}
}
