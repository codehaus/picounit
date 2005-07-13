/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class StringCaseModifier implements Modifier<String, String> {
	public static final StringCaseModifier INSTANCE = new StringCaseModifier();

	public String modify(String value) {
		return value.toLowerCase();
	}

	public String getName() {
		return " (ignoring case)";
	}
}
