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

import junit.framework.AssertionFailedError;

public class JMockerTest implements Test {
	private Mocker mocker;

	public void mock() {
		this.mocker = new JMocker(new HashMapConstraintStore());
	}

	public void testKissing() {
		Boy mockBoy = (Boy) mocker.mock(Boy.class);
		Girl girl = new Girl(mockBoy);

		mocker.expect(mockBoy.money(125)).andReturn(4);
		mockBoy.kiss();

		mocker.replay();

		girl.kiss();

		mocker.verify();
	}

	public void testTalking(Verify verify) {
		Boy mockBoy = (Boy) mocker.mock(Boy.class);
		Girl girl = new Girl(mockBoy);

		mocker.expect(mockBoy.listen("blah blah")).andReturn("yada yada");

		mocker.replay();

		String result = girl.speak("blah blah");

		mocker.verify();

		verify.equal("yada yada", result);
	}
	
	public void testCanMockInterfaces(Verify verify) throws SomeException {
		Object mock = mocker.mock(Interface.class);

		verify.notNull(mock);
		verify.that(mock instanceof Interface);

		Interface mockedInterface = (Interface) mock;

		mockedInterface.method();
		mocker.replay();

		assertObjectMethodsWork(mock);

		try {
			mocker.verify();
		}
		catch (AssertionFailedError assertionFailedError) {
			return;
		}

		verify.fail("AssertionFailedError: Expectation failure on verify");
	}

	public void testCanMockClasses(Verify verify) {
		Object mock = mocker.mock(Implementation.class);

		verify.notNull(mock);
		verify.that(mock instanceof Implementation);

		Implementation mockedImplementation = (Implementation) mock;

		mockedImplementation.method();

		mocker.replay();
		assertObjectMethodsWork(mock);

		try {
			mocker.verify();
		}
		catch (AssertionFailedError assertionFailedError) {
			return;
		}

		verify.fail("AssertionFailedError: Expectation failure on verify");
	}
	
	public void testCanMockAbstractClasses(Verify verify) {
		Object mock = mocker.mock(AbstractClass.class);

		verify.notNull(mock);
		verify.that(mock instanceof AbstractClass);

		AbstractClass mockedAbstractClass = (AbstractClass) mock;

		mockedAbstractClass.method();

		mocker.replay();
		assertObjectMethodsWork(mock);

		try {
			mocker.verify();
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
		Interface mock = (Interface) mocker.mock(Interface.class);

		mock.method();
		SomeException someException = new SomeException();
		mocker.raise(someException);
		mocker.replay();

		try {
			mock.method();
		}
		catch (SomeException exception) {
			verify.same(someException, exception);
		}

		mocker.verify();
	}

	public void testThrowsAwkwardExceptionsCorrectly(Verify verify) throws InvocationTargetException {
		Interface mock = (Interface) mocker.mock(Interface.class);

		mock.awkwardMethod();
		InvocationTargetException invocationTargetException = new InvocationTargetException(
						new Throwable("cause"));
		mocker.raise(invocationTargetException);
		mocker.replay();

		try {
			mock.awkwardMethod();
		}
		catch (InvocationTargetException exception) {
			verify.same(invocationTargetException, exception);
		}

		mocker.verify();
	}
    
	public void testCannotFinishExpectationsWithoutSettingReturnValueForPrimativeMethods(Verify verify) {
		Interface mock = (Interface) mocker.mock(Interface.class);

		mock.booleanMethod();

		try {
			mocker.replay();

			verify.fail("Exception expected: should express return type for primative methods");
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.equal("a return value must be specified for methods returning primatives", assertionFailedError.getMessage());
		}
	}
	
	public void testCannotMoveToNextExpectationWithoutSettingReturnValueForPrimativeMethods(Verify verify)
		throws SomeException {

		Interface mock = (Interface) mocker.mock(Interface.class);

		mock.booleanMethod();

		try {
			mock.method();

			verify.fail("Exception expected: should express return type for primative methods");
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.equal("a return value must be specified for methods returning primatives", assertionFailedError.getMessage());
		}
	}

	public void testOmittingReturnValueForNonPrimativesSetsValueToNull(Verify verify) {
		Interface mockInterface = (Interface) mocker.mock(Interface.class);

		mockInterface.objectMethod();

		mocker.doAboveWhen();
		
		verify.equal(null, mockInterface.objectMethod());
		
		mocker.verify();
	}
}
