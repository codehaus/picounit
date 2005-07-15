/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import picounit.mocker.MockFactory;

import java.util.HashMap;
import java.util.Map;

public class DefaultStubFactory implements StubFactory {
	private final Map finalTypeMap;

	public DefaultStubFactory() {
		this(Boolean.FALSE, new Byte((byte)0), new Character('\0'), new Double(0.0),
			new Float(0.0F), new Integer(0), new Long(0L), new Short((short)0), "", new Object(),
			Class.class);
	}

	public DefaultStubFactory(Boolean defaultBoolean, Byte defaultByte, Character defaultCharacter,
		Double defaultDouble, Float defaultFloat, Integer defaultInteger, Long defaultLong,
		Short defaultShort, String defaultString, Object defaultObject, Class defaultClass) {

		this.finalTypeMap = new HashMap();

		put(boolean.class, defaultBoolean);
		put(byte.class, defaultByte);
		put(char.class, defaultCharacter);
		put(double.class, defaultDouble);
		put(float.class, defaultFloat);
		put(int.class, defaultInteger);
		put(long.class, defaultLong);
		put(short.class, defaultShort);

		put(Boolean.class, defaultBoolean);
		put(Byte.class, defaultByte);
		put(Character.class, defaultCharacter);
		put(Double.class, defaultDouble);
		put(Float.class, defaultFloat);
		put(Integer.class, defaultInteger);
		put(Long.class, defaultLong);
		put(Short.class, defaultShort);

		put(String.class, defaultString);
		put(Object.class, defaultObject);
		put(defaultClass, defaultClass);
	}

	public final Object create(Class type, MockFactory mockFactory) {
		if (finalTypeMap.containsKey(type)) {
			return finalTypeMap.get(type); 
		}
		else {
			return mockFactory.mock(type);
		}
	}

	protected final void put(Class type, Object value) {
		finalTypeMap.put(type, value);
	}
}
