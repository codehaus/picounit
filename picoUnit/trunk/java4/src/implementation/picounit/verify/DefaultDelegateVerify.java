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
import picounit.mocker.MockControl;
import picounit.mocker.MockFactory;
import picounit.mocker.MockInvocationInspector;

import java.util.HashMap;
import java.util.Map;

public class DefaultDelegateVerify implements DelegateVerify {
	public static final boolean BOOLEAN_DELEGATE_RETURN = true;
	public static final byte BYTE_DELEGATE_RETURN = (byte) 123;
	public static final char CHAR_DELEGATE_RETURN = 'e';
	public static final double DOUBLE_DELEGATE_RETURN = 123.456;
	public static final float FLOAT_DELEGATE_RETURN = 456.123f;
	public static final int INT_DELEGATE_RETURN = 123;
	public static final long LONG_DELEGATE_RETURN = 123456;
	public static final short SHORT_DELEGATE_RETURN = (short) 123;

	public static final String STRING_DELEGATE_RETURN = "expectedValue";
	public static final String OBJECT_DELEGATE_RETURN = "expectedObject";
	
	private final Mocker should;
	private final Verify verify;
	private final Map<Class,Object> expectedMap;
	private final MockInvocationInspector mockInvocationInspector;
	private final MockFactory mockFactory;
	private final MockControl mockControl;
	
	public DefaultDelegateVerify(Mocker mocker, MockFactory mockFactory, MockInvocationInspector mockInvocationInspector,
		Verify verify) {

		this.should = mocker;
		this.mockControl = mocker;
		this.mockInvocationInspector = mockInvocationInspector;
		this.verify = verify;
		this.mockFactory = mockFactory;

		this.expectedMap = new HashMap<Class, Object>();
		expectedMap.put(Boolean.class, BOOLEAN_DELEGATE_RETURN);
		expectedMap.put(boolean.class, BOOLEAN_DELEGATE_RETURN);

		expectedMap.put(Byte.class, BYTE_DELEGATE_RETURN);
		expectedMap.put(byte.class, BYTE_DELEGATE_RETURN);

		expectedMap.put(Character.class, CHAR_DELEGATE_RETURN);
		expectedMap.put(char.class, CHAR_DELEGATE_RETURN);

		expectedMap.put(Double.class, DOUBLE_DELEGATE_RETURN);
		expectedMap.put(double.class, DOUBLE_DELEGATE_RETURN);

		expectedMap.put(Float.class, FLOAT_DELEGATE_RETURN);
		expectedMap.put(float.class, FLOAT_DELEGATE_RETURN);

		expectedMap.put(Integer.class, INT_DELEGATE_RETURN);
		expectedMap.put(int.class, INT_DELEGATE_RETURN);

		expectedMap.put(Long.class, LONG_DELEGATE_RETURN);
		expectedMap.put(long.class, LONG_DELEGATE_RETURN);

		expectedMap.put(Short.class, SHORT_DELEGATE_RETURN);
		expectedMap.put(short.class, SHORT_DELEGATE_RETURN);

		expectedMap.put(String.class, STRING_DELEGATE_RETURN);
		expectedMap.put(Object.class, OBJECT_DELEGATE_RETURN);
	}
	
	public BooleanDelegateVerifier delegateTo(boolean ignore) {
		boolean expectedValue = generateExpected(boolean.class);

		should.call(box(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new BooleanDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public ByteDelegateVerifier delegateTo(byte ignore) {
		byte expectedValue = generateExpected(byte.class);

		should.call(box(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new ByteDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public CharacterDelegateVerifier delegateTo(char ignore) {
		char expectedValue = generateExpected(char.class);

		should.call(box(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new CharacterDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public DoubleDelegateVerifier delegateTo(double ignore) {
		double expectedValue = generateExpected(double.class);

		should.call(box(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new DoubleDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public FloatDelegateVerifier delegateTo(float ignore) {
		float expectedValue = generateExpected(float.class);

		should.call(box(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new FloatDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public IntegerDelegateVerifier delegateTo(int ignore) {
		int expectedValue = generateExpected(int.class);

		should.call(box(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new IntegerDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public LongDelegateVerifier delegateTo(long ignore) {
		long expectedValue = generateExpected(long.class);

		should.call(box(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new LongDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public ShortDelegateVerifier delegateTo(short ignore) {
		short expectedValue = generateExpected(short.class);

		should.call(box(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new ShortDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public StringDelegateVerifier delegateTo(String ignore) {
		String expectedValue = generateExpected(String.class);

		should.call(ignore).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new StringDelegateVerifierImpl(verify, expectedValue, mockControl);
	}

	@SuppressWarnings("unchecked")
	public <T> DelegateVerifier<T> delegateTo(T ignore) {
		T expectedValue = (T) generateExpected(
			mockInvocationInspector.getLastInvocationReturnType());

		should.call(ignore).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new DelegateVerifierImpl<T>(verify, expectedValue, mockControl);
	}

	@SuppressWarnings("unchecked")
	private <T> T generateExpected(Class<T> aClass) {
		T expected = (T) expectedMap.get(aClass);

		if (expected == null) {
			expected = mockFactory.mock(aClass);
		}

		return expected;
	}

	private static class DelegateVerifierImpl<T> implements DelegateVerifier<T> {
		private final Verify verify;
		private final T expectedValue;
		private final MockControl mockControl;

		public DelegateVerifierImpl(Verify verify, T expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(T actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}
	
	private static class BooleanDelegateVerifierImpl implements BooleanDelegateVerifier {
		private final Verify verify;
		private final boolean expectedValue;
		private final MockControl mockControl;

		public BooleanDelegateVerifierImpl(Verify verify, boolean expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(boolean actualValue) {
			verify.thatBoolean(actualValue).isEqualTo(expectedValue);
			
			mockControl.verify();
		}
	}

	private static class ByteDelegateVerifierImpl implements ByteDelegateVerifier {
		private final Verify verify;
		private final byte expectedValue;
		private final MockControl mockControl;

		public ByteDelegateVerifierImpl(Verify verify, byte expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(byte actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}

	private static class CharacterDelegateVerifierImpl implements CharacterDelegateVerifier {
		private final Verify verify;
		private final char expectedValue;
		private final MockControl mockControl;

		public CharacterDelegateVerifierImpl(Verify verify, char expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(char actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}
	
	private static class DoubleDelegateVerifierImpl implements DoubleDelegateVerifier {
		private final Verify verify;
		private final double expectedValue;
		private final MockControl mockControl;

		public DoubleDelegateVerifierImpl(Verify verify, double expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(double actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}
	
	private static class FloatDelegateVerifierImpl implements FloatDelegateVerifier {
		private final Verify verify;
		private final float expectedValue;
		private final MockControl mockControl;

		public FloatDelegateVerifierImpl(Verify verify, float expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(float actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}
	
	private static class IntegerDelegateVerifierImpl implements IntegerDelegateVerifier {
		private final Verify verify;
		private final int expectedValue;
		private final MockControl mockControl;

		public IntegerDelegateVerifierImpl(Verify verify, int expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(int actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}
	
	private static class LongDelegateVerifierImpl implements LongDelegateVerifier {
		private final Verify verify;
		private final long expectedValue;
		private final MockControl mockControl;

		public LongDelegateVerifierImpl(Verify verify, long expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(long actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}
	
	private static class ShortDelegateVerifierImpl implements ShortDelegateVerifier {
		private final Verify verify;
		private final short expectedValue;
		private final MockControl mockControl;

		public ShortDelegateVerifierImpl(Verify verify, short expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(short actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}
	
	private static class StringDelegateVerifierImpl implements StringDelegateVerifier {
		private final Verify verify;
		private final String expectedValue;
		private final MockControl mockControl;

		public StringDelegateVerifierImpl(Verify verify, String expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(String actualValue) {
			verify.that(actualValue).isEqualTo(expectedValue);

			mockControl.verify();
		}
	}

	private <T> T box(T value) {
		return value;
	}	
}
