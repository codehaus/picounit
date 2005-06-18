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

public class DefaultDelegateVerify implements DelegateVerify, DelegateVerify.BooleanVerifier,
	DelegateVerify.ByteVerifier, DelegateVerify.CharVerifier, DelegateVerify.DoubleVerifier,
	DelegateVerify.FloatVerifier, DelegateVerify.IntVerifier, DelegateVerify.LongVerifier,
	DelegateVerify.ShortVerifier, DelegateVerify.ObjectVerifier, DelegateVerify.StringVerifier {

	private final Mocker should;
	private final Verify verify;

	public static final boolean BOOLEAN_DELEGATE_RETURN = true;
	public static final byte BYTE_DELEGATE_RETURN = (byte) 0xEE;
	public static final char CHAR_DELEGATE_RETURN = 'e';
	public static final double DOUBLE_DELEGATE_RETURN = 123.456;
	public static final float FLOAT_DELEGATE_RETURN = 456.123f;
	public static final int INT_DELEGATE_RETURN = 123456;
	public static final long LONG_DELEGATE_RETURN = 123456789123456789L;
	public static final short SHORT_DELEGATE_RETURN = 123;
	public static final String STRING_DELEGATE_RETURN = "expected";
	public static final Object OBJECT_DELEGATE_RETURN = new Object() {
		public String toString() {
			return "expectedObject";
		};
	};

	public DefaultDelegateVerify(Mocker should, Verify verify) {
		this.should = should;
		this.verify = verify;
	}

	public BooleanVerifier delegateTo(boolean ignore) {
		should.call(ignore).andReturn(BOOLEAN_DELEGATE_RETURN);

		should.doAboveWhen();

		return this;
	}

	public ByteVerifier delegateTo(byte ignore) {
		should.call(ignore).andReturn(BYTE_DELEGATE_RETURN);

		should.doAboveWhen();

		return this;
	}

	public CharVerifier delegateTo(char ignore) {
		should.call(ignore).andReturn(CHAR_DELEGATE_RETURN);

		should.doAboveWhen();
		
		return this;
	}

	public DoubleVerifier delegateTo(double ignore) {
		should.call(ignore).andReturn(DOUBLE_DELEGATE_RETURN);

		should.doAboveWhen();

		return this;
	}

	public FloatVerifier delegateTo(float ignore) {
		should.call(ignore).andReturn(FLOAT_DELEGATE_RETURN);

		should.doAboveWhen();
		
		return this;
	}

	public IntVerifier delegateTo(int ignore) {
		should.call(ignore).andReturn(INT_DELEGATE_RETURN);

		should.doAboveWhen();
		
		return this;
	}

	public LongVerifier delegateTo(long ignore) {
		should.call(ignore).andReturn(LONG_DELEGATE_RETURN);

		should.doAboveWhen();
		
		return this;
	}

	public ShortVerifier delegateTo(short ignore) {
		should.call(ignore).andReturn(SHORT_DELEGATE_RETURN);

		should.doAboveWhen();
		
		return this;
	}

	public ObjectVerifier delegateTo(Object ignore) {
		should.call(ignore).andReturn(OBJECT_DELEGATE_RETURN);

		should.doAboveWhen();
		
		return this;
	}

	public StringVerifier delegateTo(String ignore) {
		should.call(ignore).andReturn(STRING_DELEGATE_RETURN);

		should.doAboveWhen();
		
		return this;
	}

	public void whenCalling(boolean actualValue) {
		verify.equal(BOOLEAN_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(byte actualValue) {
		verify.equal(BYTE_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(char actualValue) {
		verify.equal(CHAR_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(double actualValue) {
		verify.equal(DOUBLE_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(float actualValue) {
		verify.equal(FLOAT_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(int actualValue) {
		verify.equal(INT_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(long actualValue) {
		verify.equal(LONG_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(short actualValue) {
		verify.equal(SHORT_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(Object actualValue) {
		verify.equal(OBJECT_DELEGATE_RETURN, actualValue);
	}

	public void whenCalling(String actualValue) {
		verify.equal(STRING_DELEGATE_RETURN, actualValue);
	}
}
