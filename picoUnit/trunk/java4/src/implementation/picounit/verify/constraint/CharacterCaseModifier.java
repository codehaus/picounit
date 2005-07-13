/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class CharacterCaseModifier implements Modifier<Character, Character> {
	public static final CharacterCaseModifier INSTANCE = new CharacterCaseModifier();

	public Character modify(Character value) {
		return Character.toLowerCase(value);
	}

	public String getName() {
		return " (ignoring case)";
	}
}
