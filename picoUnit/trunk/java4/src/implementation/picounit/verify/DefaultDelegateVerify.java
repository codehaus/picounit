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
	public static final Boolean BOOLEAN_DELEGATE_RETURN = Boolean.TRUE;
	public static final Byte BYTE_DELEGATE_RETURN = new Byte((byte) 123);
	public static final Character CHAR_DELEGATE_RETURN = new Character('e');
	public static final Double DOUBLE_DELEGATE_RETURN = new Double(123.456);
	public static final Float FLOAT_DELEGATE_RETURN = new Float(456.123f);
	public static final Integer INT_DELEGATE_RETURN = new Integer(123);
	public static final Long LONG_DELEGATE_RETURN = new Long(123456);
	public static final Short SHORT_DELEGATE_RETURN = new Short((short) 123);

	public static final String STRING_DELEGATE_RETURN = "expectedValue";
	public static final String OBJECT_DELEGATE_RETURN = "expectedObject";
	
	private final Mocker should;
	private final Verify verify;
	private final Map expectedMap;
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

		this.expectedMap = new HashMap();
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
		boolean expectedValue = ((Boolean) generateExpected(boolean.class)).booleanValue();

		should.call(new Boolean(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new BooleanDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public ByteDelegateVerifier delegateTo(byte ignore) {
		byte expectedValue = ((Byte) generateExpected(byte.class)).byteValue();

		should.call(new Byte(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new ByteDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public CharacterDelegateVerifier delegateTo(char ignore) {
		char expectedValue = ((Character) generateExpected(char.class)).charValue();

		should.call(new Character(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new CharacterDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public DoubleDelegateVerifier delegateTo(double ignore) {
		double expectedValue = ((Double) generateExpected(double.class)).doubleValue();

		should.call(new Double(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new DoubleDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public FloatDelegateVerifier delegateTo(float ignore) {
		float expectedValue = ((Float) generateExpected(float.class)).floatValue();

		should.call(new Float(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new FloatDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public IntegerDelegateVerifier delegateTo(int ignore) {
		int expectedValue = ((Integer) generateExpected(int.class)).intValue();

		should.call(new Integer(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new IntegerDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public LongDelegateVerifier delegateTo(long ignore) {
		long expectedValue = ((Long) generateExpected(long.class)).longValue();

		should.call(new Long(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new LongDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public ShortDelegateVerifier delegateTo(short ignore) {
		short expectedValue = ((Short) generateExpected(short.class)).shortValue();

		should.call(new Short(ignore)).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new ShortDelegateVerifierImpl(verify, expectedValue, mockControl);
	}
	
	public StringDelegateVerifier delegateTo(String ignore) {
		String expectedValue = (String) generateExpected(String.class);

		should.call(ignore).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new StringDelegateVerifierImpl(verify, expectedValue, mockControl);
	}

	public DelegateVerifier delegateTo(Object ignore) {
		Object expectedValue = generateExpected(
			mockInvocationInspector.getLastInvocationReturnType());

		should.call(ignore).andReturn(expectedValue);

		should.expectAboveWhenTheFollowingOccurs();

		return new DelegateVerifierImpl(verify, expectedValue, mockControl);
	}

	private Object generateExpected(Class aClass) {
		Object expected = expectedMap.get(aClass);

		if (expected == null) {
			expected = mockFactory.mock(aClass);
		}

		return expected;
	}

	private static class DelegateVerifierImpl implements DelegateVerifier {
		private final Verify verify;
		private final Object expectedValue;
		private final MockControl mockControl;

		public DelegateVerifierImpl(Verify verify, Object expectedValue, MockControl mockControl) {
			this.verify = verify;
			this.expectedValue = expectedValue;
			this.mockControl = mockControl;
		}

		public void whenCalling(Object actualValue) {
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
}
