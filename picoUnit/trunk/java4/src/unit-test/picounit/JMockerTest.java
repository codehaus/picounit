/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.mocker.jmock.HashMapConstraintStore;
import picounit.mocker.jmock.JMocker;
import previous.picounit.Test;
import previous.picounit.Verify;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.AssertionFailedError;

public class JMockerTest implements Test {
	private Mocker should;
	private Mocker mockFactory;
	
	public void mock() {
		this.should = JMocker.create(new HashMapConstraintStore());
		this.mockFactory = should;
	}

	public void testKissing() {
		Boy mockBoy = mockFactory.mock(Boy.class);
		Girl girl = new Girl(mockBoy);

		should.call(mockBoy.money(125)).andReturn(4);
		mockBoy.kiss();

		should.expectAboveWhenTheFollowingOccurs();

		girl.kiss();

		should.verify();
	}

	public void testTalking(Verify verify) {
		Boy mockBoy = mockFactory.mock(Boy.class);
		Girl girl = new Girl(mockBoy);

		should.call(mockBoy.listen("blah blah")).andReturn("yada yada");

		should.expectAboveWhenTheFollowingOccurs();

		String result = girl.speak("blah blah");

		should.verify();

		verify.that(result).isEqualTo("yada yada");
	}
	
	public void testCanMockInterfaces(Verify verify) throws SomeException {
		Object mock = mockFactory.mock(Interface.class);

		verify.that(mock).isNotNull();
		verify.that(mock).isAnInstanceOf(Interface.class);

		Interface mockedInterface = (Interface) mock;

		mockedInterface.method();
		should.expectAboveWhenTheFollowingOccurs();

		assertObjectMethodsWork(mock);

		try {
			should.verify();
		}
		catch (AssertionFailedError assertionFailedError) {
			return;
		}

		verify.fail("AssertionFailedError: Expectation failure on verify");
	}

	public void testCanMockClasses(Verify verify) {
		Object mock = mockFactory.mock(Implementation.class);

		verify.that(mock).isNotNull();
		verify.that(mock).isAnInstanceOf(Implementation.class);

		Implementation mockedImplementation = (Implementation) mock;

		mockedImplementation.method();

		should.expectAboveWhenTheFollowingOccurs();
		assertObjectMethodsWork(mock);

		try {
			should.verify();
		}
		catch (AssertionFailedError assertionFailedError) {
			return;
		}

		verify.fail("AssertionFailedError: Expectation failure on verify");
	}
	
	public void testCanMockAbstractClasses(Verify verify) {
		Object mock = mockFactory.mock(AbstractClass.class);

		verify.that(mock).isNotNull();
		verify.that(mock).isAnInstanceOf(AbstractClass.class);

		AbstractClass mockedAbstractClass = (AbstractClass) mock;

		mockedAbstractClass.method();

		should.expectAboveWhenTheFollowingOccurs();
		assertObjectMethodsWork(mock);

		try {
			should.verify();
		}
		catch (AssertionFailedError assertionFailedError) {
			return;
		}

		verify.fail("AssertionFailedError: Expectation failure on verify");
	}

	private void assertObjectMethodsWork(Object mock) {
		mock.toString();
		mock.hashCode();
		mock.equals(mock);
	}

	public void testThrowsExceptionsCorrectly(Verify verify) throws SomeException {
		Interface mock = mockFactory.mock(Interface.class);

		mock.method();
		SomeException someException = new SomeException();
		should.raise(someException);
		should.expectAboveWhenTheFollowingOccurs();

		try {
			mock.method();
		}
		catch (SomeException exception) {
			verify.that(exception).isTheSameAs(someException);
		}

		should.verify();
	}

	public void testThrowsAwkwardExceptionsCorrectly(Verify verify) throws InvocationTargetException {
		Interface mock = mockFactory.mock(Interface.class);

		mock.awkwardMethod();
		InvocationTargetException invocationTargetException = new InvocationTargetException(
						new Throwable("cause"));
		should.raise(invocationTargetException);
		should.expectAboveWhenTheFollowingOccurs();

		try {
			mock.awkwardMethod();
		}
		catch (InvocationTargetException exception) {
			verify.that(exception).isTheSameAs(invocationTargetException);
		}

		should.verify();
	}
    
	public void testCannotFinishExpectationsWithoutSettingReturnValueForPrimativeMethods(Verify verify) {
		Interface mock = mockFactory.mock(Interface.class);

		mock.booleanMethod();

		try {
			should.expectAboveWhenTheFollowingOccurs();

			verify.fail("Exception expected: should express return type for primative methods");
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("a return value must be specified for methods returning primatives");
		}
	}
	
	public void testCannotMoveToNextExpectationWithoutSettingReturnValueForPrimativeMethods(Verify verify)
		throws SomeException {

		Interface mock = mockFactory.mock(Interface.class);

		mock.booleanMethod();

		try {
			mock.method();

			verify.fail("Exception expected: should express return type for primative methods");
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("a return value must be specified for methods returning primatives");
		}
	}

	public void xtestOmittingReturnValueForNonPrimativesSetsValueToNull(Verify verify) {
		Interface mockInterface = mockFactory.mock(Interface.class);

		mockInterface.objectMethod();

		should.expectAboveWhenTheFollowingOccurs();
		
		verify.that(mockInterface.objectMethod()).isEqualTo(null);
		
		should.verify();
	}
}
