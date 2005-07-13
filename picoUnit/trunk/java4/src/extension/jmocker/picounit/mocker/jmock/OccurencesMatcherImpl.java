/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.InvocationMatcher;
import org.jmock.core.Stub;

import picounit.Occurences;
import picounit.mocker.OccurencesMatcher;
import picounit.mocker.PostConsequenceMatcher;

public class OccurencesMatcherImpl implements OccurencesMatcher,
	RecordingPlaybackMockListener, ActiveRecordingPlaybackMock {

	private RecordingPlaybackMock activeRcordingPlaybackMock;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// OccurencesMatcher
	///////////////////////////////////////////////////////////////////////////////////////////////

	public PostConsequenceMatcher once() {
		return occurs(1);
	}

	public PostConsequenceMatcher atLeastOnce() {
		return occurs(1, Integer.MAX_VALUE);
	}

	public PostConsequenceMatcher occurs(int occurences) {
		return occurs(new InvokeCountMatcher(occurences));
	}

	public PostConsequenceMatcher occurs(int min, int max) {
		return occurs(new InvokeRangeMatcher(min, max));
	}

	public PostConsequenceMatcher occurs(Occurences occurences) {
		return occurs(new InvokeOccurencesMatcher(occurences));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// PostConsequenceMatcher
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void because(String reason) {
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// RecordingPlaybackMockListener
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void recordingPlaybackMockEvent(RecordingPlaybackMock activeRcordingPlaybackMock) {
		this.activeRcordingPlaybackMock = activeRcordingPlaybackMock;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// ActiveRecordingPlaybackMock
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void setStub(Stub stub) {
		activeRcordingPlaybackMock.setStub(stub);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Implementation
	///////////////////////////////////////////////////////////////////////////////////////////////

	protected final OccurencesMatcher will(Stub stub) {
		activeRcordingPlaybackMock.setStub(stub);

		return this;
	}

	private PostConsequenceMatcher occurs(InvocationMatcher invocationMatcher) {
		activeRcordingPlaybackMock.setInvocationMatcher(invocationMatcher);

		return this;
	}
}
