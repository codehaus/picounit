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

import picounit.ConstraintFactory;
import picounit.Occurences;
import picounit.PicoUnitException;
import picounit.mocker.BooleanAction;
import picounit.mocker.BooleanConsequenceMatcher;
import picounit.mocker.ByteAction;
import picounit.mocker.ByteConsequenceMatcher;
import picounit.mocker.CharAction;
import picounit.mocker.CharConsequenceMatcher;
import picounit.mocker.DoubleAction;
import picounit.mocker.DoubleConsequenceMatcher;
import picounit.mocker.ExpectationMatcher;
import picounit.mocker.FloatAction;
import picounit.mocker.FloatConsequenceMatcher;
import picounit.mocker.IntAction;
import picounit.mocker.IntConsequenceMatcher;
import picounit.mocker.LongAction;
import picounit.mocker.LongConsequenceMatcher;
import picounit.mocker.MockerInterfaces;
import picounit.mocker.ObjectAction;
import picounit.mocker.ObjectConsequenceMatcher;
import picounit.mocker.OccurencesMatcher;
import picounit.mocker.PostConsequenceMatcher;
import picounit.mocker.ShortAction;
import picounit.mocker.ShortConsequenceMatcher;
import picounit.mocker.StringAction;
import picounit.mocker.StringConsequenceMatcher;
import picounit.mocker.VoidAction;
import picounit.mocker.jmock.action.BooleanActionStub;
import picounit.mocker.jmock.action.ByteActionStub;
import picounit.mocker.jmock.action.CharActionStub;
import picounit.mocker.jmock.action.DoubleActionStub;
import picounit.mocker.jmock.action.FloatActionStub;
import picounit.mocker.jmock.action.IntActionStub;
import picounit.mocker.jmock.action.LongActionStub;
import picounit.mocker.jmock.action.ObjectActionStub;
import picounit.mocker.jmock.action.ShortActionStub;
import picounit.mocker.jmock.action.StringActionStub;
import picounit.mocker.jmock.action.VoidActionStub;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JMocker implements MockerInterfaces {
	private final List mocks = new LinkedList();
	private final ConstraintStore constraintStore = new HashMapConstraintStore();
	private final JMockConstraintFactory jMockConstraintFactory =
		new JMockConstraintFactory(constraintStore);

	private final RecordingPlaybackMockListener invocationListener = new RecordingPlaybackMockListener() {
		public void recordingPlaybackMockEvent(RecordingPlaybackMock recordingPlaybackMock) {
			JMocker.this.recordingPlaybackMock = recordingPlaybackMock;
		}
	};

	private RecordingPlaybackMock recordingPlaybackMock;

	public Object mock(Class mockedType) {
		return mock(mockedType, CoreMock.mockNameFromClass(mockedType));
	}

	public Object mock(Class mockedType, String name) {
		RecordingPlaybackMock recordingPlaybackMock = new RecordingPlaybackMock(
			mockedType, name, invocationListener, this, constraintStore);

		mocks.add(recordingPlaybackMock);

		return recordingPlaybackMock.getMock();
	}

	public ConstraintFactory constraint() {
		return jMockConstraintFactory;
	}

	public BooleanConsequenceMatcher call(boolean ignore) {
		return this;
	}

	public ByteConsequenceMatcher call(byte ignore) {
		return this;
	}

	public CharConsequenceMatcher call(char ignore) {
		return this;
	}

	public DoubleConsequenceMatcher call(double ignore) {
		return this;
	}

	public FloatConsequenceMatcher call(float ignore) {
		return this;
	}

	public IntConsequenceMatcher call(int ignore) {
		return this;
	}

	public LongConsequenceMatcher call(long ignore) {
		return this;
	}

	public ShortConsequenceMatcher call(short ignore) {
		return this;
	}

	public StringConsequenceMatcher call(String ignore) {
		return this;
	}

	public ObjectConsequenceMatcher call(Object ignore) {
		return this;
	}

	public BooleanConsequenceMatcher expect(boolean ignore) {
		return this;
	}

	public ByteConsequenceMatcher expect(byte ignore) {
		return this;
	}

	public CharConsequenceMatcher expect(char ignore) {
		return this;
	}

	public DoubleConsequenceMatcher expect(double ignore) {
		return this;
	}

	public FloatConsequenceMatcher expect(float ignore) {
		return this;
	}

	public IntConsequenceMatcher expect(int ignore) {
		return this;
	}

	public LongConsequenceMatcher expect(long ignore) {
		return this;
	}

	public ShortConsequenceMatcher expect(short ignore) {
		return this;
	}

	public StringConsequenceMatcher expect(String ignore) {
		return this;
	}

	public ObjectConsequenceMatcher expect(Object ignore) {
		return this;
	}

	public OccurencesMatcher andReturn(boolean result) {
		return andReturn(new Boolean(result));
	}

	public OccurencesMatcher andReturn(byte result) {
		return andReturn(new Byte(result));
	}

	public OccurencesMatcher andReturn(char result) {
		return andReturn(new Character(result));
	}

	public OccurencesMatcher andReturn(double result) {
		return andReturn(new Double(result));
	}

	public OccurencesMatcher andReturn(float result) {
		return andReturn(new Float(result));
	}

	public OccurencesMatcher andReturn(int result) {
		return andReturn(new Integer(result));
	}

	public OccurencesMatcher andReturn(long result) {
		return andReturn(new Long(result));
	}

	public OccurencesMatcher andReturn(short result) {
		return andReturn(new Short(result));
	}
	
	public OccurencesMatcher andReturn(String result) {
		return will(new ReturnStub(result));
	}

	public OccurencesMatcher andReturn(Object result) {
		return will(new ReturnStub(result));
	}

	public OccurencesMatcher andRaise(Throwable toThrow) {
		return raise(toThrow);
	}

	public OccurencesMatcher andPerform(BooleanAction booleanAction) {
		return perform(booleanAction);
	}
	
	public OccurencesMatcher andPerform(ByteAction byteAction) {
		return perform(byteAction);
	}
	
	public OccurencesMatcher andPerform(CharAction charAction) {
		return perform(charAction);
	}
	
	public OccurencesMatcher andPerform(DoubleAction doubleAction) {
		return perform(doubleAction);
	}
	
	public OccurencesMatcher andPerform(FloatAction floatAction) {
		return perform(floatAction);
	}
	
	public OccurencesMatcher andPerform(IntAction intAction) {
		return perform(intAction);
	}
	
	public OccurencesMatcher andPerform(LongAction longAction) {
		return perform(longAction);
	}
	
	public OccurencesMatcher andPerform(ObjectAction objectAction) {
		return perform(objectAction);
	}
	
	public OccurencesMatcher andPerform(ShortAction shortAction) {
		return perform(shortAction);
	}
	
	public OccurencesMatcher andPerform(StringAction stringAction) {
		return perform(stringAction);
	}
	
	public PostConsequenceMatcher doNotExpect(boolean ignore) {
		return throwExceptionIfInvoked();
	}

	public PostConsequenceMatcher doNotExpect(byte ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(char ignore) {
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
	
	public PostConsequenceMatcher doNotExpect(Object ignore) {
		return throwExceptionIfInvoked();
	}
	
	public PostConsequenceMatcher doNotExpect(short ignore) {
		return throwExceptionIfInvoked();
	}
	
	public OccurencesMatcher raise(Throwable throwable) {
		return will(new ThrowStub(throwable));
	}
	
	public OccurencesMatcher perform(BooleanAction booleanAction) {
		return will(new BooleanActionStub(booleanAction));
	}

	public OccurencesMatcher perform(ByteAction byteAction) {
		return will(new ByteActionStub(byteAction));
	}

	public OccurencesMatcher perform(CharAction charAction) {
		return will(new CharActionStub(charAction));
	}

	public OccurencesMatcher perform(DoubleAction doubleAction) {
		return will(new DoubleActionStub(doubleAction));
	}

	public OccurencesMatcher perform(FloatAction floatAction) {
		return will(new FloatActionStub(floatAction));
	}

	public OccurencesMatcher perform(IntAction intAction) {
		return will(new IntActionStub(intAction));
	}

	public OccurencesMatcher perform(LongAction longAction) {
		return will(new LongActionStub(longAction));
	}

	public OccurencesMatcher perform(ObjectAction objectAction) {
		return will(new ObjectActionStub(objectAction));
	}

	public OccurencesMatcher perform(ShortAction shortAction) {
		return will(new ShortActionStub(shortAction));
	}

	public OccurencesMatcher perform(StringAction stringAction) {
		return will(new StringActionStub(stringAction));
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
