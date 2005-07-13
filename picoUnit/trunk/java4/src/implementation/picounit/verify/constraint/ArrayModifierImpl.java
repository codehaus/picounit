/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

import java.lang.reflect.Array;

public class ArrayModifierImpl<T, M> implements ArrayModifier<T, M>  {
	private final Modifier<Object, Object> modifier;
	private final Class modifiedClass;

	@SuppressWarnings("unchecked")
	public ArrayModifierImpl(Modifier modifier, Class modifiedClass) {
		this.modifier = modifier;
		this.modifiedClass = modifiedClass;
	}

	@SuppressWarnings("unchecked")
	public M modify(T value) {
		if (value == null) {
			return null;
		}

		if (!value.getClass().isArray()) {
			return (M) modifyElement(value);
		}

		int length = Array.getLength(value);

		M modified = (M) Array.newInstance(modifiedClass, length);

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
