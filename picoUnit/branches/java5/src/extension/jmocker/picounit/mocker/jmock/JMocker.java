/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.CoreMock;
import org.jmock.core.InvocationMatcher;
import org.jmock.core.Stub;
import org.jmock.core.matcher.InvokeCountMatcher;
import org.jmock.core.stub.ReturnStub;
import org.jmock.core.stub.ThrowStub;

import picounit.Constraints;
import picounit.Occurences;
import picounit.PicoUnitException;
import picounit.mocker.Action;
import picounit.mocker.ConsequenceMatcher;
import picounit.mocker.ExpectationMatcher;
import picounit.mocker.MockerInterfaces;
import picounit.mocker.OccurencesMatcher;
import picounit.mocker.PostConsequenceMatcher;
import picounit.mocker.VoidAction;

import java.util.LinkedList;
import java.util.List;

public class JMocker implements MockerInterfaces {
	private final List<RecordingPlaybackMock> mocks = new LinkedList<RecordingPlaybackMock>();
	private final ConstraintStore constraintStore;
	private final JMockConstraints jMockConstraintFactory;

	private final RecordingPlaybackMockListener invocationListener = new RecordingPlaybackMockListener() {
		public void recordingPlaybackMockEvent(RecordingPlaybackMock recordingPlaybackMock) {
			JMocker.this.recordingPlaybackMock = recordingPlaybackMock;
		}
	};

	private RecordingPlaybackMock recordingPlaybackMock;

	public JMocker() {
		this(new HashMapConstraintStore());
	}

	public JMocker(ConstraintStore constraintStore) {
		this.constraintStore = constraintStore;
		this.jMockConstraintFactory = new JMockConstraints(constraintStore);
	}

	public Object mock(Class mockedType) {
		return mock(mockedType, CoreMock.mockNameFromClass(mockedType));
	}

	public Object mock(Class mockedType, String name) {
		RecordingPlaybackMock recordingPlaybackMock = new RecordingPlaybackMock(
			mockedType, name, invocationListener, this, constraintStore);

		mocks.add(recordingPlaybackMock);

		return recordingPlaybackMock.getMock();
	}

	public Constraints constraint() {
		return jMockConstraintFactory;
	}

	public <Type> ConsequenceMatcher<Type> call(Type ignore) {
		return expect(ignore);
	}

	public <Type> ConsequenceMatcher expect(Type ignore) {
		return this;
	}

	public OccurencesMatcher andReturn(Object result) {
		return will(new ReturnStub(result));
	}

	public OccurencesMatcher andRaise(Throwable toThrow) {
		return raise(toThrow);
	}

	public OccurencesMatcher andPerform(Action action) {
		return perform(action);
	}
	
	public <Type> PostConsequenceMatcher doNotExpect(Type ignore) {
		return throwExceptionIfInvoked();
	}
	
	public OccurencesMatcher raise(Throwable throwable) {
		return will(new ThrowStub(throwable));
	}
	
	public <Type> OccurencesMatcher perform(Action<Type> action) {
		return will(new ActionStub<Type>(action));
	}

	public OccurencesMatcher perform(VoidAction voidAction) {
		return will(new VoidActionStub(voidAction));
	}
	
	public PostConsequenceMatcher occurs(int occurences) {
		return occurs(new InvokeCountMatcher(occurences));
	}

	public PostConsequenceMatcher occurs(int min, int max) {
		return occurs(new InvokeRangeMatcher(min, max));
	}

	public PostConsequenceMatcher occurs(Occurences occurences) {
		return occurs(occurences.getMinimum(), occurences.getMaximum());
	}
	
	public PostConsequenceMatcher once() {
		return occurs(1);
	}
	
	public PostConsequenceMatcher atLeastOnce() {
		return occurs(1, Integer.MAX_VALUE);
	}
	
	public ExpectationMatcher because(String reason) {
		return this;
	}
	
	public void doAboveWhen() {
		replay();
	}

	public void replay() {
		for(RecordingPlaybackMock recordingPlaybackMock : mocks) {
			recordingPlaybackMock.replay();
		}
	}

	public void verify() {
		for(RecordingPlaybackMock recordingPlaybackMock : mocks) {
			recordingPlaybackMock.verify();
		}

    	reset();
	}

	public void reset() {
		for(RecordingPlaybackMock recordingPlaybackMock : mocks) {
			recordingPlaybackMock.reset();
		}
	}

	private JMocker will(Stub stub) {
		recordingPlaybackMock.setStub(stub);
		
		return this;
	}

	private PostConsequenceMatcher occurs(InvocationMatcher invocationMatcher) {
		recordingPlaybackMock.setInvocationMatcher(invocationMatcher);

		return this;
	}

	private PostConsequenceMatcher throwExceptionIfInvoked() {
		return raise(new PicoUnitException("should not occur")).occurs(0);
	}
}
