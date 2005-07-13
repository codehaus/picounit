/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.builder.MatchBuilder;
import org.jmock.cglib.Mock;
import org.jmock.core.DynamicMock;
import org.jmock.core.Invocation;
import org.jmock.core.InvocationMatcher;
import org.jmock.core.Invokable;
import org.jmock.core.Stub;
import org.jmock.core.matcher.InvokeOnceMatcher;

import picounit.mocker.MockFactory;
import picounit.util.MethodUtil;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import junit.framework.AssertionFailedError;

public class RecordingPlaybackMock<T> implements DynamicMock, InvocationHandler {
	private static final StubFactory stubFactory = new DefaultStubFactory();

	private final BetterCGLIBCoreMock<T> playbackCoreMock;
	private final RecordingPlaybackMockListener invocationListener;
	private final MethodUtil methodUtil = new MethodUtil();
	private final MockFactory mockFactory;
	private final Mock playbackMock;
	private final T proxy;

	private final ConstraintStore constraintStore;
	private final MockInvocationListener mockInvocationListener;
	
	private boolean recording = true;
	private int expectationSequenceNumber = 0;
	private boolean breakInExpectations;
	private Stub stub;
	private InvocationMatcher invocationMatcher = new InvokeOnceMatcher();
	private Invocation invocation;

	public RecordingPlaybackMock(Class<T> mockedType, String roleName,
		RecordingPlaybackMockListener invocationListener, MockFactory mockFactory,
		ConstraintStore constraintStore, MockInvocationListener mockInvocationListener) {

		this.mockFactory = mockFactory;
		this.constraintStore = constraintStore;
		this.mockInvocationListener = mockInvocationListener;
		this.playbackCoreMock = new BetterCGLIBCoreMock<T>(mockedType, roleName);
		this.playbackMock = new Mock(playbackCoreMock);

        this.proxy = new ProxyFactory().create(mockedType, this); 

		this.invocationListener = invocationListener;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getMockedType() {
		return playbackCoreMock.getMockedType();
	}

	public T proxy() {
		return proxy;
	}

	@SuppressWarnings("unchecked")
	public T getMock() {
		return (T) new Mock(this).proxy();
	}

	public void setDefaultStub(Stub newDefaultStub) {
		playbackCoreMock.setDefaultStub(newDefaultStub);
	}

	public void addInvokable(Invokable invokable) {
		playbackCoreMock.addInvokable(invokable);
	}

	private void addSequenceConstraint(MatchBuilder matchBuilder) {
		matchBuilder.id("" + expectationSequenceNumber);
		if (expectationSequenceNumber > 0  && !breakInExpectations) {
			matchBuilder.after("" + (expectationSequenceNumber - 1));
		}
		
		breakInExpectations = false;
		expectationSequenceNumber++;
	}

	public void reset() {
		recording = true;
		playbackCoreMock.reset();
		invocationMatcher = new InvokeOnceMatcher();
		stub = null;
		invocation = null;
	}

	public void verify() {
		playbackCoreMock.verify();
		breakInExpectations = true;
	}

	public void replay() {
		completeExpectation();
		recording = false;
	}
	
	public void setStub(Stub stub) {
		this.stub = stub;
	}

	public void setInvocationMatcher(InvocationMatcher invocationMatcher) {
		this.invocationMatcher = invocationMatcher;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Invocation invocation = new Invocation(proxy, method, args);
		
		mockInvocationListener.fire(invocation);
		
		if (methodUtil.isEquals(method)) {
			return new Boolean(proxy == args[0]);
		}
		
		if (recording && !isDefault(invocation)) {
			completeExpectation();
			this.invocation = invocation;
		
			invocationListener.recordingPlaybackMockEvent(this);
		
			return defaultValue(invocation.invokedMethod.getReturnType());	
		}
		else {
			try {
				return playbackCoreMock.intercept(proxy, method, args, null);
			}
			catch (AssertionFailedError assertionFailedError) {
				throw assertionFailedError;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Object defaultValue(Class returnType) {
		if (void.class.equals(returnType)) {
			return null;
		}

		if (returnType.isArray()) {
			return Array.newInstance(returnType.getComponentType(), 0);
		}

		return stubFactory.create(returnType, mockFactory);
	}
	
	/*
	 * It would be better to access the default matchers registered in the AbstractDynamicMock.setupDefaultBehaviour
	 */
	private boolean isDefault(Invocation invocation) {
		Method invokedMethod = invocation.invokedMethod;

		return methodUtil.isEquals(invokedMethod) ||
			methodUtil.isHashCode(invokedMethod) ||
			methodUtil.isToString(invokedMethod);
	}

	private void completeExpectation() {
		if (invocation == null) {
			return;
		}

		Class returnType = invocation.invokedMethod.getReturnType();

		if (isPrimative(returnType) && stub == null) {
			throw new AssertionFailedError(
				"a return value must be specified for methods returning primatives");
		}

		MatchBuilder matchBuilder = playbackMock.expects(invocationMatcher)
			.method(invocation.invokedMethod.getName())
			.with(constraintStore.getConstraints(invocation.parameterValues));

		addSequenceConstraint(matchBuilder);

		if (returnType.equals(Void.TYPE)) {
			matchBuilder.isVoid();
		}
		else if (stub != null) {
			matchBuilder.will(stub);
		}

		invocationMatcher = new InvokeOnceMatcher();
		stub = null;
		invocation = null;
	}
	
	private boolean isPrimative(Class returnType) {
		return returnType.isPrimitive() && !returnType.equals(Void.TYPE);
	}
}
