/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.DelegateVerify;
import picounit.Mocker;
import picounit.Verify;

import java.util.HashMap;
import java.util.Map;

public class DefaultDelegateVerify implements DelegateVerify {
	private static final String EXPECTED_OBJECT = "expectedObject";
	private static final String EXPECTED_STRING = "expectedValue";
	private static final Short EXPECTED_SHORT = new Short((short) 123);
	private static final Long EXPECTED_LONG = new Long(123456);
	private static final Integer EXPECTED_INTEGER = new Integer(123);
	private static final Float EXPECTED_FLOAT = new Float(456.123);
	private static final Double EXPECTED_DOUBLE = new Double(123.456);
	private static final Character EXPECTED_CHARACTER = new Character('e');
	private static final Byte EXPECTED_BYTE = new Byte((byte) 123);
	private static final Boolean EXPECTED_BOOLEAN = Boolean.TRUE;

	private final Mocker should;
	private final Verify verify;
	private final Map<Class,Object> expectedMap;

	public DefaultDelegateVerify(Mocker should, Verify verify) {
		this.should = should;
		this.verify = verify;

		this.expectedMap = new HashMap<Class, Object>();
		expectedMap.put(Boolean.class, EXPECTED_BOOLEAN);
		expectedMap.put(boolean.class, EXPECTED_BOOLEAN);

		expectedMap.put(Byte.class, EXPECTED_BYTE);
		expectedMap.put(byte.class, EXPECTED_BYTE);

		expectedMap.put(Character.class, EXPECTED_CHARACTER);
		expectedMap.put(char.class, EXPECTED_CHARACTER);

		expectedMap.put(Double.class, EXPECTED_DOUBLE);
		expectedMap.put(double.class, EXPECTED_DOUBLE);

		expectedMap.put(Float.class, EXPECTED_FLOAT);
		expectedMap.put(float.class, EXPECTED_FLOAT);

		expectedMap.put(Integer.class, EXPECTED_INTEGER);
		expectedMap.put(int.class, EXPECTED_INTEGER);

		expectedMap.put(Long.class, EXPECTED_LONG);
		expectedMap.put(long.class, EXPECTED_LONG);

		expectedMap.put(Short.class, EXPECTED_SHORT);
		expectedMap.put(short.class, EXPECTED_SHORT);

		expectedMap.put(String.class, EXPECTED_STRING);
		expectedMap.put(Object.class, EXPECTED_OBJECT);
	}

	public <Type> DelegateVerifier<Type> delegateTo(Type ignore) {
		Type expectedValue = (Type) generateExpected(ignore.getClass());

		should.call(ignore).andReturn(expectedValue);

		should.doAboveWhen();

		return new DelegateVerifierImpl<Type>(verify, expectedValue);
	}

	private Object generateExpected(Class aClass) {
		Object expected = expectedMap.get(aClass);

		if (expected == null) {
			throw new RuntimeException("Unimplemented for non built ins");
		}

		return expected;
	}

	private static class DelegateVerifierImpl<Type> implements DelegateVerifier<Type> {
		private final Verify verify;
		private final Type expectedValue;

		public DelegateVerifierImpl(Verify verify, Type expectedValue) {
			this.verify = verify;
			this.expectedValue = expectedValue;
		}

		public void whenCalling(Type actualValue) {
			verify.equal(expectedValue, actualValue);
		}
	}
}
