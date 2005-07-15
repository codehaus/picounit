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
import picounit.mocker.BooleanConsequenceMatcher;
import picounit.mocker.ByteConsequenceMatcher;
import picounit.mocker.CharConsequenceMatcher;
import picounit.mocker.ConsequenceMatcher;
import picounit.mocker.DoubleConsequenceMatcher;
import picounit.mocker.FloatConsequenceMatcher;
import picounit.mocker.IntConsequenceMatcher;
import picounit.mocker.LongConsequenceMatcher;
import picounit.mocker.MockFactory;
import picounit.mocker.MockInvocationInspector;
import picounit.mocker.ShortConsequenceMatcher;
import picounit.mocker.StringConsequenceMatcher;
import previous.picounit.Test;

public class DelegateVerifyTest implements Test{
	private DelegateVerify delegateVerify;

	private final previous.picounit.Mocker should;

	private Mocker mockShould;
	private Verify mockVerify;

	private MockInvocationInspector mockInvocationInspector;
	private CustomType mockCustomType;

	private BooleanConsequenceMatcher mockBooleanConsequenceMatcher;
	private ByteConsequenceMatcher mockByteConsequenceMatcher;
	private CharConsequenceMatcher mockCharConsequenceMatcher;
	private DoubleConsequenceMatcher mockDoubleConsequenceMatcher;
	private FloatConsequenceMatcher mockFloatConsequenceMatcher;
	private IntConsequenceMatcher mockIntConsequenceMatcher;
	private LongConsequenceMatcher mockLongConsequenceMatcher;
	private ShortConsequenceMatcher mockShortConsequenceMatcher;
	private StringConsequenceMatcher mockStringConsequenceMatcher;
	private ConsequenceMatcher mockConsequenceMatcher;
	private ByteConstraints mockByteConstraints;
	private CharacterConstraints mockCharacterConstraints;
	private DoubleConstraints mockDoubleConstraints;
	private FloatConstraints mockFloatConstraints;
	private IntegerConstraints mockIntegerConstraints;
	private LongConstraints mockLongConstraints;
	private ShortConstraints mockShortConstraints;
	private StringConstraints mockStringConstraints;
	private TypedConstraints mockTypedConstraints;

	private BooleanConstraints mockBooleanConstraints;

	private MockFactory mockMockFactory;

	public static class CustomType {
		private final String value;

		public CustomType(String value) {
			this.value = value;
		}
		
		public String toString() {
			return "CustomType: " + value;
		}
		
		public boolean equals(Object object) {
			if (object == null || !getClass().equals(object.getClass())) {
				return false;
			}

			CustomType other = (CustomType) object;

			return value.equals(other.value);
		}
		
		public int hashCode() {
			return value.hashCode();
		}
	}

	public DelegateVerifyTest(previous.picounit.Mocker should) {
		this.should = should;
	}

	public void mock(Mocker mockShould, MockFactory mockMockFactory,
		MockInvocationInspector mockMockInvocationInspector,
		Verify mockVerify, CustomType mockCustomType,
		BooleanConsequenceMatcher mockBooleanConsequenceMatcher,
		ByteConsequenceMatcher mockByteConsequenceMatcher,
		CharConsequenceMatcher mockCharConsequenceMatcher,
		DoubleConsequenceMatcher mockDoubleConsequenceMatcher,
		FloatConsequenceMatcher mockFloatConsequenceMatcher,
		IntConsequenceMatcher mockIntConsequenceMatcher,
		LongConsequenceMatcher mockLongConsequenceMatcher,
		ShortConsequenceMatcher mockShortConsequenceMatcher,
		StringConsequenceMatcher mockStringConsequenceMatcher,
		ConsequenceMatcher mockConsequenceMatcher,
		BooleanConstraints mockBooleanConstraints,
		ByteConstraints mockByteConstraints,
		CharacterConstraints mockCharacterConstraints,
		DoubleConstraints mockDoubleConstraints,
		FloatConstraints mockFloatConstraints,
		IntegerConstraints mockIntegerConstraints,
		LongConstraints mockLongConstraints,
		ShortConstraints mockShortConstraints,
		StringConstraints mockStringConstraints,
		TypedConstraints mockTypedConstraints
		) {

		this.mockMockFactory = mockMockFactory;
		this.mockBooleanConstraints = mockBooleanConstraints;
		this.mockByteConstraints = mockByteConstraints;
		this.mockCharacterConstraints = mockCharacterConstraints;
		this.mockDoubleConstraints = mockDoubleConstraints;
		this.mockFloatConstraints = mockFloatConstraints;
		this.mockIntegerConstraints = mockIntegerConstraints;
		this.mockLongConstraints = mockLongConstraints;
		this.mockShortConstraints = mockShortConstraints;
		this.mockStringConstraints = mockStringConstraints;
		this.mockTypedConstraints = mockTypedConstraints;
		this.delegateVerify = new DefaultDelegateVerify(mockShould, mockMockFactory, 
			mockMockInvocationInspector, mockVerify);

		this.mockShould = mockShould;

		this.mockInvocationInspector = mockMockInvocationInspector;
		this.mockVerify = mockVerify;
		this.mockCustomType = mockCustomType;
	
		this.mockBooleanConsequenceMatcher = mockBooleanConsequenceMatcher;
		this.mockByteConsequenceMatcher = mockByteConsequenceMatcher;
		this.mockCharConsequenceMatcher = mockCharConsequenceMatcher;
		this.mockDoubleConsequenceMatcher = mockDoubleConsequenceMatcher;
		this.mockFloatConsequenceMatcher = mockFloatConsequenceMatcher;
		this.mockIntConsequenceMatcher = mockIntConsequenceMatcher;
		this.mockLongConsequenceMatcher = mockLongConsequenceMatcher;
		this.mockShortConsequenceMatcher = mockShortConsequenceMatcher;
		this.mockStringConsequenceMatcher = mockStringConsequenceMatcher;
		this.mockConsequenceMatcher = mockConsequenceMatcher;
	}

	public void testTestsActualInvocationReturnsSameBooleanValueAsDelegateToInvocation() {
		boolean actualValue = false;

		should.call(mockShould.call(false))
			.andReturn(mockBooleanConsequenceMatcher);

		shouldCall:
			mockBooleanConsequenceMatcher.andReturn(DefaultDelegateVerify.BOOLEAN_DELEGATE_RETURN.booleanValue());
			mockShould.expectAboveWhenTheFollowingOccurs();
			
		should.call(mockVerify.thatBoolean(actualValue))
			.andReturn(mockBooleanConstraints);
		
		shouldCall:
			mockBooleanConstraints.isEqualTo(DefaultDelegateVerify.BOOLEAN_DELEGATE_RETURN.booleanValue());
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(false).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameByteValueAsDelegateToInvocation() {
		byte ignored = (byte) 0xBB;
		byte actualValue = (byte) 0xAA;

		should.call(mockShould.call(ignored))
			.andReturn(mockByteConsequenceMatcher);
		
		shouldCall:
			mockByteConsequenceMatcher.andReturn(DefaultDelegateVerify.BYTE_DELEGATE_RETURN.byteValue());
			mockShould.expectAboveWhenTheFollowingOccurs();
			
		should.call(mockVerify.that(actualValue))
			.andReturn(mockByteConstraints);
		
		shouldCall:
			mockByteConstraints.isEqualTo(DefaultDelegateVerify.BYTE_DELEGATE_RETURN.byteValue());
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameCharValueAsDelegateToInvocation() {
		char ignored = 'i';
		char actualValue = 'a';

		should.call(mockShould.call(ignored))
			.andReturn(mockCharConsequenceMatcher);
		
		shouldCall:
			mockCharConsequenceMatcher.andReturn(DefaultDelegateVerify.CHAR_DELEGATE_RETURN.charValue());
			mockShould.expectAboveWhenTheFollowingOccurs();
		
		should.call(mockVerify.that(actualValue))
			.andReturn(mockCharacterConstraints);
		
		shouldCall:
			mockCharacterConstraints.isEqualTo(DefaultDelegateVerify.CHAR_DELEGATE_RETURN.charValue());
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}
	
	public void testTestsActualInvocationReturnsSameDoubleValueAsDelegateToInvocation() {
		double ignored = 123.456f;
		double actualValue = 456.123f;

		should.call(mockShould.call(ignored))
			.andReturn(mockDoubleConsequenceMatcher);
		
		shouldCall:
			mockDoubleConsequenceMatcher.andReturn(DefaultDelegateVerify.DOUBLE_DELEGATE_RETURN.doubleValue());
			mockShould.expectAboveWhenTheFollowingOccurs();
			
		should.call(mockVerify.that(actualValue))
			.andReturn(mockDoubleConstraints);
		
		shouldCall:
			mockDoubleConstraints.isEqualTo(DefaultDelegateVerify.DOUBLE_DELEGATE_RETURN.doubleValue());
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameFloatValueAsDelegateToInvocation() {
		float ignored = 123.456f;
		float actualValue = 456.123f;

		should.call(mockShould.call(ignored))
			.andReturn(mockFloatConsequenceMatcher);
		
		shouldCall:
			mockFloatConsequenceMatcher.andReturn(DefaultDelegateVerify.FLOAT_DELEGATE_RETURN.floatValue());
			mockShould.expectAboveWhenTheFollowingOccurs();
		
		should.call(mockVerify.that(actualValue))
			.andReturn(mockFloatConstraints);
		
		shouldCall:
			mockFloatConstraints.isEqualTo(DefaultDelegateVerify.FLOAT_DELEGATE_RETURN.floatValue());
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameIntValueAsDelegateToInvocation() {
		int ignored = 123;
		int actualValue = 456;

		should.call(mockShould.call(ignored))
			.andReturn(mockIntConsequenceMatcher);
		
		shouldCall:
			mockIntConsequenceMatcher.andReturn(DefaultDelegateVerify.INT_DELEGATE_RETURN.intValue());
			mockShould.expectAboveWhenTheFollowingOccurs();
			
		should.call(mockVerify.that(actualValue))
			.andReturn(mockIntegerConstraints);
		
		shouldCall:
			mockIntegerConstraints.isEqualTo(DefaultDelegateVerify.INT_DELEGATE_RETURN.intValue());
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameLongValueAsDelegateToInvocation() {
		long ignored = 123L;
		long actualValue = 456L;
		
		should.call(mockShould.call(ignored))
			.andReturn(mockLongConsequenceMatcher);
		
		shouldCall:
			mockLongConsequenceMatcher.andReturn(DefaultDelegateVerify.LONG_DELEGATE_RETURN.longValue());
			mockShould.expectAboveWhenTheFollowingOccurs();
		
		should.call(mockVerify.that(actualValue))
			.andReturn(mockLongConstraints);

		shouldCall:
			mockLongConstraints.isEqualTo(DefaultDelegateVerify.LONG_DELEGATE_RETURN.longValue());
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameShortValueAsDelegateToInvocation() {
		short ignored = 123;
		short actualValue = 456;

		should.call(mockShould.call(ignored))
			.andReturn(mockShortConsequenceMatcher);
		
		shouldCall:
			mockShortConsequenceMatcher.andReturn(DefaultDelegateVerify.SHORT_DELEGATE_RETURN.shortValue());
			mockShould.expectAboveWhenTheFollowingOccurs();
			
		should.call(mockVerify.that(actualValue))
			.andReturn(mockShortConstraints);
		
		shouldCall:
			mockShortConstraints.isEqualTo(DefaultDelegateVerify.SHORT_DELEGATE_RETURN.shortValue());
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameObjectValueAsDelegateToInvocation() {
		Object ignored = new Object();
		Object actualValue = new Object();

		should.call(mockInvocationInspector.getLastInvocationReturnType())
			.andReturn(Object.class);
		
		should.call(mockShould.call(ignored))
			.andReturn(mockConsequenceMatcher);

		shouldCall:
			mockConsequenceMatcher.andReturn(DefaultDelegateVerify.OBJECT_DELEGATE_RETURN);
			mockShould.expectAboveWhenTheFollowingOccurs();
			
		should.call(mockVerify.that(actualValue))
			.andReturn(mockTypedConstraints);
		
		shouldCall:
			mockTypedConstraints.isEqualTo(DefaultDelegateVerify.OBJECT_DELEGATE_RETURN);
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}
	
	public void testTestsActualInvocationReturnsSameStringValueAsDelegateToInvocation() {
		String ignored = "ignored";
		String actualValue = "actual";

		should.call(mockShould.call(ignored))
			.andReturn(mockStringConsequenceMatcher);
		
		shouldCall:
			mockStringConsequenceMatcher.andReturn(DefaultDelegateVerify.STRING_DELEGATE_RETURN);
			mockShould.expectAboveWhenTheFollowingOccurs();
		
		should.call(mockVerify.that(actualValue))
			.andReturn(mockStringConstraints);

		shouldCall:
			mockStringConstraints.isEqualTo(DefaultDelegateVerify.STRING_DELEGATE_RETURN);
			mockShould.verify();

		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameCustomTypeValueAsDelegateToInvocation() {
		CustomType ignored = new CustomType("ignored");
		CustomType actualValue = new CustomType("ignored");

		should.call(mockInvocationInspector.getLastInvocationReturnType())
			.andReturn(ignored.getClass());

		should.call((Object) mockMockFactory.mock(CustomType.class))
			.andReturn(mockCustomType);

		should.call(mockShould.call(ignored))
			.andReturn(mockConsequenceMatcher);
		
		shouldCall:
			mockConsequenceMatcher.andReturn(mockCustomType);
			mockShould.expectAboveWhenTheFollowingOccurs();

		should.call(mockVerify.that(actualValue))
			.andReturn(mockTypedConstraints);
		
		shouldCall:
			mockTypedConstraints.isEqualTo(mockCustomType);
			mockShould.verify();
		
		should.expectAboveWhenTheFollowingOccurs();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}
}
