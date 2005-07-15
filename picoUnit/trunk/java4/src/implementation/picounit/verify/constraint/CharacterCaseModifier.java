/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class CharacterCaseModifier implements Modifier {
	public static final CharacterCaseModifier INSTANCE = new CharacterCaseModifier();

	public Object modify(Object value) {
		return new Character(Character.toLowerCase(((Character) value).charValue()));
	}

	public String getName() {
		return " (ignoring case)";
	}
}
