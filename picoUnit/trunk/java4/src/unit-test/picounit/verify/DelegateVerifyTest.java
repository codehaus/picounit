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
import picounit.mocker.DoubleConsequenceMatcher;
import picounit.mocker.FloatConsequenceMatcher;
import picounit.mocker.IntConsequenceMatcher;
import picounit.mocker.LongConsequenceMatcher;
import picounit.mocker.ObjectConsequenceMatcher;
import picounit.mocker.ShortConsequenceMatcher;
import picounit.mocker.StringConsequenceMatcher;
import previous.picounit.Test;

public class DelegateVerifyTest implements Test {
	private DelegateVerify delegateVerify;

	private final previous.picounit.Mocker should;

	private Mocker mockShould;
	private Verify mockVerify;

	private BooleanConsequenceMatcher mockBooleanConsequenceMatcher;
	private ByteConsequenceMatcher mockByteConsequenceMatcher;
	private CharConsequenceMatcher mockCharConsequenceMatcher;
	private DoubleConsequenceMatcher mockDoubleConsequenceMatcher;
	private FloatConsequenceMatcher mockFloatConsequenceMatcher;
	private IntConsequenceMatcher mockIntConsequenceMatcher;
	private LongConsequenceMatcher mockLongConsequenceMatcher;
	private ShortConsequenceMatcher mockShortConsequenceMatcher;
	private ObjectConsequenceMatcher mockObjectConsequenceMatcher;
	private StringConsequenceMatcher mockStringConsequenceMatcher;

	public DelegateVerifyTest(previous.picounit.Mocker should) {
		this.should = should;
	}

	public void mock(Mocker mockShould, Verify mockVerify,
		BooleanConsequenceMatcher mockBooleanConsequenceMatcher,
		ByteConsequenceMatcher mockByteConsequenceMatcher,
		CharConsequenceMatcher mockCharConsequenceMatcher,
		DoubleConsequenceMatcher mockDoubleConsequenceMatcher,
		FloatConsequenceMatcher mockFloatConsequenceMatcher,
		IntConsequenceMatcher mockIntConsequenceMatcher,
		LongConsequenceMatcher mockLongConsequenceMatcher,
		ShortConsequenceMatcher mockShortConsequenceMatcher,
		ObjectConsequenceMatcher mockObjectConsequenceMatcher,
		StringConsequenceMatcher mockStringConsequenceMatcher) {

		this.mockBooleanConsequenceMatcher = mockBooleanConsequenceMatcher;
		this.mockByteConsequenceMatcher = mockByteConsequenceMatcher;
		this.mockCharConsequenceMatcher = mockCharConsequenceMatcher;
		this.mockDoubleConsequenceMatcher = mockDoubleConsequenceMatcher;
		this.mockFloatConsequenceMatcher = mockFloatConsequenceMatcher;
		this.mockIntConsequenceMatcher = mockIntConsequenceMatcher;
		this.mockLongConsequenceMatcher = mockLongConsequenceMatcher;
		this.mockShortConsequenceMatcher = mockShortConsequenceMatcher;
		this.mockObjectConsequenceMatcher = mockObjectConsequenceMatcher;
		this.mockStringConsequenceMatcher = mockStringConsequenceMatcher;
		this.delegateVerify = new DefaultDelegateVerify(mockShould, mockVerify);

		this.mockShould = mockShould;
		this.mockVerify = mockVerify;
	}

	public void testTestsActualInvocationReturnsSameBooleanValueAsDelegateToInvocation() {
		boolean ignored = false;
		boolean actualValue = false;

		should.call(mockShould.call(ignored)).andReturn(mockBooleanConsequenceMatcher);
		mockBooleanConsequenceMatcher.andReturn(DefaultDelegateVerify.BOOLEAN_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.BOOLEAN_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameByteValueAsDelegateToInvocation() {
		byte ignored = (byte) 0xBB;
		byte actualValue = (byte) 0xAA;

		should.call(mockShould.call(ignored)).andReturn(mockByteConsequenceMatcher);
		mockByteConsequenceMatcher.andReturn(DefaultDelegateVerify.BYTE_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.BYTE_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameCharValueAsDelegateToInvocation() {
		char ignored = 'i';
		char actualValue = 'a';

		should.call(mockShould.call(ignored)).andReturn(mockCharConsequenceMatcher);
		mockCharConsequenceMatcher.andReturn(DefaultDelegateVerify.CHAR_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.CHAR_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}
	
	public void testTestsActualInvocationReturnsSameDoubleValueAsDelegateToInvocation() {
		double ignored = 123.456f;
		double actualValue = 456.123f;

		should.call(mockShould.call(ignored)).andReturn(mockDoubleConsequenceMatcher);
		mockDoubleConsequenceMatcher.andReturn(DefaultDelegateVerify.DOUBLE_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.DOUBLE_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameFloatValueAsDelegateToInvocation() {
		float ignored = 123.456f;
		float actualValue = 456.123f;

		should.call(mockShould.call(ignored)).andReturn(mockFloatConsequenceMatcher);
		mockFloatConsequenceMatcher.andReturn(DefaultDelegateVerify.FLOAT_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.FLOAT_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameIntValueAsDelegateToInvocation() {
		int ignored = 123;
		int actualValue = 456;

		should.call(mockShould.call(ignored)).andReturn(mockIntConsequenceMatcher);
		mockIntConsequenceMatcher.andReturn(DefaultDelegateVerify.INT_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.INT_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameLongValueAsDelegateToInvocation() {
		long ignored = 123L;
		long actualValue = 456L;

		should.call(mockShould.call(ignored)).andReturn(mockLongConsequenceMatcher);
		mockLongConsequenceMatcher.andReturn(DefaultDelegateVerify.LONG_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.LONG_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameShortValueAsDelegateToInvocation() {
		short ignored = 123;
		short actualValue = 456;

		should.call(mockShould.call(ignored)).andReturn(mockShortConsequenceMatcher);
		mockShortConsequenceMatcher.andReturn(DefaultDelegateVerify.SHORT_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.SHORT_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameObjectValueAsDelegateToInvocation() {
		Object ignored = new Object() {public String toString() {return "ignoredObject";}};
		Object actualValue = new Object() {public String toString() {return "actualObject";}};

		should.call(mockShould.call(ignored)).andReturn(mockObjectConsequenceMatcher);
		mockObjectConsequenceMatcher.andReturn(DefaultDelegateVerify.OBJECT_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.OBJECT_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}

	public void testTestsActualInvocationReturnsSameStringValueAsDelegateToInvocation() {
		String ignored = "ignored";
		String actualValue = "actual";

		should.call(mockShould.call(ignored)).andReturn(mockStringConsequenceMatcher);
		mockStringConsequenceMatcher.andReturn(DefaultDelegateVerify.STRING_DELEGATE_RETURN);

		mockShould.doAboveWhen();
		mockVerify.equal(DefaultDelegateVerify.STRING_DELEGATE_RETURN, actualValue);

		should.doAboveWhen();

		delegateVerify.delegateTo(ignored).whenCalling(actualValue);
	}
}
