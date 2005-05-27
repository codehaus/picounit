/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import junit.framework.AssertionFailedError;

public class RecordingPlaybackMock implements DynamicMock, MethodInterceptor {
	private static final Map defaultMap = createDefaultMap();
	private final BetterCGLIBCoreMock playbackCoreMock;
	private final RecordingPlaybackMockListener invocationListener;
	private final MethodUtil methodUtil = new MethodUtil();
	private final MockFactory mockFactory;
	private final Mock playbackMock;
	private final Object proxy;

	private boolean recording = true;
	private int expectationSequenceNumber = 0;
	private boolean breakInExpectations;
	private final ConstraintStore constraintStore;
	private Stub stub;
	private InvocationMatcher invocationMatcher = new InvokeOnceMatcher();
	private Invocation invocation;
	
	public RecordingPlaybackMock(Class mockedType, String roleName,
		RecordingPlaybackMockListener invocationListener, MockFactory mockFactory,
		ConstraintStore constraintStore) {

		this.mockFactory = mockFactory;
		this.constraintStore = constraintStore;
		this.playbackCoreMock = new BetterCGLIBCoreMock(mockedType, roleName);
		this.playbackMock = new Mock(playbackCoreMock);

        this.proxy = new ProxyFactory().create(mockedType, this); 

		this.invocationListener = invocationListener;
	}

	public Class getMockedType() {
		return playbackCoreMock.getMockedType();
	}

	public Object proxy() {
		return proxy;
	}
	
	public Object getMock() {
		return new Mock(this).proxy();
	}

	public Mock playbackMock() {
		return playbackMock;
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

	public Object intercept(Object thisProxy, Method method, Object[] args, MethodProxy superProxy)
		throws Throwable {

		Invocation invocation = new Invocation(proxy, method, args);

		if (methodUtil.isEquals(method)) {
			return new Boolean(thisProxy == args[0]);
		}

		if (recording && !isDefault(invocation)) {
			completeExpectation();
			this.invocation = invocation;

			invocationListener.recordingPlaybackMockEvent(this);

			return defaultValue(invocation.invokedMethod.getReturnType());	
		}
		else {
			return playbackCoreMock.intercept(thisProxy, method, args, superProxy);
		}
	}

	private Object defaultValue(Class returnType) {
		if (void.class.equals(returnType)) {
			return null;
		}
		if (returnType.isArray()) {
			return Array.newInstance(returnType.getComponentType(), 0);
		}

		Object defaultValue = defaultMap.get(returnType);

		return defaultValue != null ? defaultValue : mockFactory.mock(returnType);
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

	private static Map createDefaultMap() {
		Map defaultMap = new HashMap();
		defaultMap.put(boolean.class, Boolean.FALSE);
		defaultMap.put(byte.class, new Byte((byte)0));
		defaultMap.put(short.class, new Short((short)0));
		defaultMap.put(int.class, new Integer(0));
		defaultMap.put(long.class, new Long(0L));
		defaultMap.put(char.class, new Character('\0'));
		defaultMap.put(float.class, new Float(0.0F));
		defaultMap.put(double.class, new Double(0.0));
		defaultMap.put(Boolean.class, Boolean.FALSE);
		defaultMap.put(Byte.class, new Byte((byte)0));
		defaultMap.put(Short.class, new Short((short)0));
		defaultMap.put(Integer.class, new Integer(0));
		defaultMap.put(Long.class, new Long(0L));
		defaultMap.put(Character.class, new Character('\0'));
		defaultMap.put(Float.class, new Float(0.0F));
		defaultMap.put(Double.class, new Double(0.0));
		defaultMap.put(String.class, "");
		defaultMap.put(Object.class, new Object());
		defaultMap.put(Class.class, Class.class);
		return defaultMap;
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
		return !returnType.equals(Void.TYPE) && returnType.isPrimitive();
	}
}
