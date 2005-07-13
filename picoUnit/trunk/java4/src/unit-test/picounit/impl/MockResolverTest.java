/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import picounit.Mocker;
import picounit.classloader.MethodParameterRegistry;
import picounit.finder.MockResolver;
import previous.picounit.Test;
import previous.picounit.Verify;

public class MockResolverTest implements Test {
	private Mocker mockedMocker;
	
	private MockResolver mockResolver;

	public interface FirstToMock {}
	public interface SecondToMock {}
	
	private FirstToMock firstToMock;
	private SecondToMock secondToMock;
	
	public void mock(Mocker mockedMocker, MethodParameterRegistry methodParameterRegistry,
		FirstToMock firstToMock, SecondToMock secondToMock) {

		this.mockResolver = new MockResolver(mockedMocker, methodParameterRegistry);
		this.mockedMocker = mockedMocker;
		this.firstToMock = firstToMock;
		this.secondToMock = secondToMock;
	}

	public void testObtainsAllClassViaMocker(previous.picounit.Mocker should, Verify verify) {	
		should.call((Object) mockedMocker.mock(FirstToMock.class)).andReturn(firstToMock);
		should.call((Object) mockedMocker.mock(SecondToMock.class)).andReturn(secondToMock);

		should.expectAboveWhenTheFollowingOccurs();

		Object[] objects = mockResolver.get(new Class[] {FirstToMock.class, SecondToMock.class});

		verify.that(objects).isNotNull();
		verify.that(objects.length).isEqualTo(2);
		verify.that(objects[0]).isEqualTo(firstToMock);
		verify.that(objects[1]).isEqualTo(secondToMock);
	}
}
