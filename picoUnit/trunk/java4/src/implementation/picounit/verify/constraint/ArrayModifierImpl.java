/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import java.lang.reflect.Array;

public class ArrayModifierImpl implements ArrayModifier {
	private final Modifier modifier;
	private final Class modifiedClass;

	public ArrayModifierImpl(Modifier modifier, Class modifiedClass) {
		this.modifier = modifier;
		this.modifiedClass = modifiedClass;
	}

	public Object modify(Object value) {
		if (value == null) {
			return null;
		}

		if (!value.getClass().isArray()) {
			return modifyElement(value);
		}

		int length = Array.getLength(value);

		Object modified = Array.newInstance(modifiedClass, length);

		for (int index = 0; index < length; index++ ) {
			Array.set(modified, index, modifier.modify(Array.get(value, index)));
		}

		return modified;
	}

	public String getName() {
		return modifier.getName();
	}

	public Object modifyElement(Object element) {
		return modifier.modify(element);
	}
}
