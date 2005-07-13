/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Stub;
import org.jmock.core.stub.ReturnStub;
import org.jmock.core.stub.StubSequence;
import org.jmock.core.stub.ThrowStub;

import picounit.mocker.Action;
import picounit.mocker.BooleanAction;
import picounit.mocker.BooleanConsequenceMatcher;
import picounit.mocker.ByteAction;
import picounit.mocker.ByteConsequenceMatcher;
import picounit.mocker.CharAction;
import picounit.mocker.CharConsequenceMatcher;
import picounit.mocker.ConsequenceMatcher;
import picounit.mocker.DoubleAction;
import picounit.mocker.DoubleConsequenceMatcher;
import picounit.mocker.FloatAction;
import picounit.mocker.FloatConsequenceMatcher;
import picounit.mocker.IntAction;
import picounit.mocker.IntConsequenceMatcher;
import picounit.mocker.LongAction;
import picounit.mocker.LongConsequenceMatcher;
import picounit.mocker.OccurencesMatcher;
import picounit.mocker.ShortAction;
import picounit.mocker.ShortConsequenceMatcher;
import picounit.mocker.StringAction;
import picounit.mocker.StringConsequenceMatcher;
import picounit.mocker.jmock.action.ActionStub;
import picounit.mocker.jmock.action.BooleanActionStub;
import picounit.mocker.jmock.action.ByteActionStub;
import picounit.mocker.jmock.action.CharActionStub;
import picounit.mocker.jmock.action.DoubleActionStub;
import picounit.mocker.jmock.action.FloatActionStub;
import picounit.mocker.jmock.action.IntActionStub;
import picounit.mocker.jmock.action.LongActionStub;
import picounit.mocker.jmock.action.ShortActionStub;
import picounit.mocker.jmock.action.StringActionStub;

public class CombinedConsequeuceMatcher extends OccurencesMatcherImpl implements
	BooleanConsequenceMatcher, ByteConsequenceMatcher, CharConsequenceMatcher,
	DoubleConsequenceMatcher, FloatConsequenceMatcher, IntConsequenceMatcher,
	LongConsequenceMatcher, ShortConsequenceMatcher, StringConsequenceMatcher,
	ConsequenceMatcher {

	///////////////////////////////////////////////////////////////////////////////////////////////
	// <Type>ConsequenceMatcher andReturn - single
	///////////////////////////////////////////////////////////////////////////////////////////////

	public OccurencesMatcher andReturn(boolean result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(byte result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(char result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(double result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(float result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(int result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(long result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(short result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(String result) {
		return will(returnValue(result));
	}

	public OccurencesMatcher andReturn(Object result) {
		return will(returnValue(result));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// andReturn - multiple
	///////////////////////////////////////////////////////////////////////////////////////////////

	public OccurencesMatcher andReturn(boolean ... results) {
		return will(returnConsequetiveValues(results));
	}
	
	public OccurencesMatcher andReturn(byte ... results) {
		return will(returnConsequetiveValues(results));
	}
	
	public OccurencesMatcher andReturn(char ... results) {
		return will(returnConsequetiveValues(results));
	}
	
	public OccurencesMatcher andReturn(double ... results) {
		return will(returnConsequetiveValues(results));
	}
	
	public OccurencesMatcher andReturn(float ... results) {
		return will(returnConsequetiveValues(results));
	}
	
	public OccurencesMatcher andReturn(int ... results) {
		return will(returnConsequetiveValues(results));
	}
	
	public OccurencesMatcher andReturn(long ... results) {
		return will(returnConsequetiveValues(results));
	}
	
	public OccurencesMatcher andReturn(short ... results) {
		return will(returnConsequetiveValues(results));
	}

	public OccurencesMatcher andReturn(String ... results) {
		return will(returnConsequetiveValues((Object[]) results));
	}

	public OccurencesMatcher andReturn(Object ... results) {
		return will(returnConsequetiveValues(results));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// andRaise
	///////////////////////////////////////////////////////////////////////////////////////////////

	public OccurencesMatcher andRaise(Throwable toThrow) {
		return will(new ThrowStub(toThrow));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// andPerform
	///////////////////////////////////////////////////////////////////////////////////////////////

	public OccurencesMatcher andPerform(BooleanAction booleanAction) {
		return will(new BooleanActionStub(booleanAction));
	}

	public OccurencesMatcher andPerform(ByteAction byteAction) {
		return will(new ByteActionStub(byteAction));
	}

	public OccurencesMatcher andPerform(CharAction charAction) {
		return will(new CharActionStub(charAction));
	}

	public OccurencesMatcher andPerform(DoubleAction doubleAction) {
		return will(new DoubleActionStub(doubleAction));
	}

	public OccurencesMatcher andPerform(FloatAction floatAction) {
		return will(new FloatActionStub(floatAction));
	}

	public OccurencesMatcher andPerform(IntAction intAction) {
		return will(new IntActionStub(intAction));
	}

	public OccurencesMatcher andPerform(LongAction longAction) {
		return will(new LongActionStub(longAction));
	}

	public OccurencesMatcher andPerform(ShortAction shortAction) {
		return will(new ShortActionStub(shortAction));
	}

	public OccurencesMatcher andPerform(StringAction stringAction) {
		return will(new StringActionStub(stringAction));
	}

	@SuppressWarnings("unchecked")
	public OccurencesMatcher andPerform(Action action) {
		return will(new ActionStub(action));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Implementation
	///////////////////////////////////////////////////////////////////////////////////////////////

	private ReturnStub returnValue(Object result) {
		return new ReturnStub(result);
	}

	private StubSequence returnConsequetiveValues(Object ... results) {
		Stub[] returnStubs = new Stub[results.length];

		for (int index = 0; index < returnStubs.length; index++ ) {
			returnStubs[index] = new ReturnStub(results[index]);
		}

		return new StubSequence(returnStubs);
	}
}
