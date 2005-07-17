/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.CoreMock;
import org.jmock.core.Stub;
import org.jmock.core.stub.ThrowStub;

import picounit.Mocker;
import picounit.PicoUnitException;
import picounit.impl.Verifiable;
import picounit.mocker.BooleanConsequenceMatcher;
import picounit.mocker.ByteConsequenceMatcher;
import picounit.mocker.CharConsequenceMatcher;
import picounit.mocker.ConsequenceMatcher;
import picounit.mocker.DoubleConsequenceMatcher;
import picounit.mocker.FloatConsequenceMatcher;
import picounit.mocker.IntConsequenceMatcher;
import picounit.mocker.LongConsequenceMatcher;
import picounit.mocker.OccurencesMatcher;
import picounit.mocker.PostConsequenceMatcher;
import picounit.mocker.ShortConsequenceMatcher;
import picounit.mocker.StringConsequenceMatcher;
import picounit.mocker.VoidAction;
import picounit.mocker.jmock.action.VoidActionStub;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JMocker implements Mocker, Verifiable {
	private final List mocks = new LinkedList();

	private final BooleanConsequenceMatcher booleanConsequenceMatcher;
	private final ByteConsequenceMatcher byteConsequenceMatcher;
	private final CharConsequenceMatcher charConsequenceMatcher;
	private final DoubleConsequenceMatcher doubleConsequenceMatcher;
	private final FloatConsequenceMatcher floatConsequenceMatcher;
	private final IntConsequenceMatcher intConsequenceMatcher;
	private final LongConsequenceMatcher longConsequenceMatcher;
	private final ShortConsequenceMatcher shortConsequenceMatcher;
	private final StringConsequenceMatcher stringConsequenceMatcher;
	private final ConsequenceMatcher consequenceMatcher;

	private final OccurencesMatcher occurencesMatcher;

	private final ConstraintStore constraintStore;
	private final RecordingPlaybackMockListener recordingPlaybackMockListener;
	private final ActiveRecordingPlaybackMock activeRecordingPlaybackMock;

	private final MockInvocationObserver mockInvocationObserver;

	public static Mocker create(ConstraintStore constraintStore, MockInvocationObserver mockInvocationObserver) {
		CombinedConsequeuceMatcher combinedConsequeuceMatcher =
			new CombinedConsequeuceMatcher(mockInvocationObserver);

		return new JMocker(combinedConsequeuceMatcher, combinedConsequeuceMatcher,
			combinedConsequeuceMatcher, combinedConsequeuceMatcher, combinedConsequeuceMatcher,
			combinedConsequeuceMatcher, combinedConsequeuceMatcher, combinedConsequeuceMatcher,
			combinedConsequeuceMatcher, combinedConsequeuceMatcher, combinedConsequeuceMatcher,
			combinedConsequeuceMatcher, combinedConsequeuceMatcher, constraintStore,
			mockInvocationObserver);
	}

	public JMocker(BooleanConsequenceMatcher booleanConsequenceMatcher,
		ByteConsequenceMatcher byteConsequenceMatcher,
		CharConsequenceMatcher charConsequenceMatcher,
		DoubleConsequenceMatcher doubleConsequenceMatcher,
		FloatConsequenceMatcher floatConsequenceMatcher,
		IntConsequenceMatcher intConsequenceMatcher,
		LongConsequenceMatcher longConsequenceMatcher,
		ShortConsequenceMatcher shortConsequenceMatcher,
		StringConsequenceMatcher stringConsequenceMatcher,
		ConsequenceMatcher consequenceMatcher, OccurencesMatcher occurencesMatcher,
		ActiveRecordingPlaybackMock activeRecordingPlaybackMock,
		RecordingPlaybackMockListener recordingPlaybackMockListener,
		ConstraintStore constraintStore, MockInvocationObserver mockInvocationObserver) {

		this.booleanConsequenceMatcher = booleanConsequenceMatcher;
		this.byteConsequenceMatcher = byteConsequenceMatcher;
		this.charConsequenceMatcher = charConsequenceMatcher;
		this.doubleConsequenceMatcher = doubleConsequenceMatcher;
		this.floatConsequenceMatcher = floatConsequenceMatcher;
		this.intConsequenceMatcher = intConsequenceMatcher;
		this.longConsequenceMatcher = longConsequenceMatcher;
		this.shortConsequenceMatcher = shortConsequenceMatcher;
		this.stringConsequenceMatcher = stringConsequenceMatcher;
		this.consequenceMatcher = consequenceMatcher;
		this.occurencesMatcher = occurencesMatcher;
		this.recordingPlaybackMockListener = recordingPlaybackMockListener;
		this.activeRecordingPlaybackMock = activeRecordingPlaybackMock;
		this.constraintStore = constraintStore;
		this.mockInvocationObserver = mockInvocationObserver;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// MockFactory
	///////////////////////////////////////////////////////////////////////////////////////////////

	public Object mock(Class mockedType) {
		return mock(mockedType, CoreMock.mockNameFromClass(mockedType));
	}

	public Object mock(Class mockedType, String name) {
		RecordingPlaybackMock recordingPlaybackMock = new RecordingPlaybackMock(
			mockedType, name, recordingPlaybackMockListener, this, constraintStore,
			mockInvocationObserver);

		mocks.add(recordingPlaybackMock);

		return recordingPlaybackMock.getMock();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// VoidMethodMatcher
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public OccurencesMatcher raise(Throwable throwable) {
		return will(new ThrowStub(throwable));
	}
	
	public OccurencesMatcher perform(VoidAction voidAction) {
		return will(new VoidActionStub(voidAction));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// call
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public BooleanConsequenceMatcher call(boolean ignore) {
		return booleanConsequenceMatcher;
	}
	
	public ByteConsequenceMatcher call(byte ignore) {
		return byteConsequenceMatcher;
	}
	
	public CharConsequenceMatcher call(char ignore) {
		return charConsequenceMatcher;
	}
	
	public DoubleConsequenceMatcher call(double ignore) {
		return doubleConsequenceMatcher;
	}
	
	public FloatConsequenceMatcher call(float ignore) {
		return floatConsequenceMatcher;
	}
	
	public IntConsequenceMatcher call(int ignore) {
		return intConsequenceMatcher;
	}
	
	public LongConsequenceMatcher call(long ignore) {
		return longConsequenceMatcher;
	}
	
	public ShortConsequenceMatcher call(short ignore) {
		return shortConsequenceMatcher;
	}
	
	public StringConsequenceMatcher call(String ignore) {
		return stringConsequenceMatcher;
	}

	public ConsequenceMatcher call(Object ignore) {
		return consequenceMatcher;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// notCall
	///////////////////////////////////////////////////////////////////////////////////////////////

	public PostConsequenceMatcher notCall(boolean ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(byte ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(char ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(double ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(float ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(int ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(long ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(short ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(String ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher notCall(Object ignore) {
		return throwExceptionIfInvoked();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// expect
	///////////////////////////////////////////////////////////////////////////////////////////////

	public BooleanConsequenceMatcher expect(boolean ignore) {
		return booleanConsequenceMatcher;
	}
	
	public ByteConsequenceMatcher expect(byte ignore) {
		return byteConsequenceMatcher;
	}
	
	public CharConsequenceMatcher expect(char ignore) {
		return charConsequenceMatcher;
	}
	
	public DoubleConsequenceMatcher expect(double ignore) {
		return doubleConsequenceMatcher;
	}
	
	public FloatConsequenceMatcher expect(float ignore) {
		return floatConsequenceMatcher;
	}
	
	public IntConsequenceMatcher expect(int ignore) {
		return intConsequenceMatcher;
	}
	
	public LongConsequenceMatcher expect(long ignore) {
		return longConsequenceMatcher;
	}
	
	public ShortConsequenceMatcher expect(short ignore) {
		return shortConsequenceMatcher;
	}
	
	public StringConsequenceMatcher expect(String ignore) {
		return stringConsequenceMatcher;
	}
	
	public ConsequenceMatcher expect(Object ignore) {
		return consequenceMatcher;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// doNotExpect
	///////////////////////////////////////////////////////////////////////////////////////////////

	public PostConsequenceMatcher doNotExpect(boolean ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(byte ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(char ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(double ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(float ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(int ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(long ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(short ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(String ignore) {
		return throwExceptionIfInvoked();
	}

	public PostConsequenceMatcher doNotExpect(Object ignore) {
		return throwExceptionIfInvoked();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// MockControl
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void doAboveWhen() {
		expectAboveWhenTheFollowingOccurs();
	}
	
	public void expectAboveWhenTheFollowingOccurs() {
		replay();
	}

	public void replay() {
		for (Iterator iterator = mocks.iterator(); iterator.hasNext();) {
			RecordingPlaybackMock recordingPlaybackMock = (RecordingPlaybackMock) iterator.next();
			recordingPlaybackMock.replay();
		}
	}

	public void verify() {
		for (Iterator iterator = mocks.iterator(); iterator.hasNext();) {
			RecordingPlaybackMock recordingPlaybackMock = (RecordingPlaybackMock) iterator.next();
			recordingPlaybackMock.verify();
		}

    	reset();
	}

	public void reset() {
		for (Iterator iterator = mocks.iterator(); iterator.hasNext();) {
			RecordingPlaybackMock recordingPlaybackMock = (RecordingPlaybackMock) iterator.next();
			recordingPlaybackMock.reset();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Private
	///////////////////////////////////////////////////////////////////////////////////////////////

	private OccurencesMatcher will(Stub stub) {
		activeRecordingPlaybackMock.setStub(stub);

		return occurencesMatcher;
	}

	private PostConsequenceMatcher throwExceptionIfInvoked() {
		return raise(new PicoUnitException("should not occur")).occurs(0);
	}
}
