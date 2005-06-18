/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker;

public interface ExpectationMatcher {
	BooleanConsequenceMatcher call(boolean ignore);
	ByteConsequenceMatcher call(byte ignore);
	CharConsequenceMatcher call(char ignore);
	DoubleConsequenceMatcher call(double ignore);
	FloatConsequenceMatcher call(float ignore);
	IntConsequenceMatcher call(int ignore);
	LongConsequenceMatcher call(long ignore);
	ShortConsequenceMatcher call(short ignore);
	StringConsequenceMatcher call(String ignore);
	ObjectConsequenceMatcher call(Object ignore);
	
	PostConsequenceMatcher notCall(boolean ignore);
	PostConsequenceMatcher notCall(byte ignore);
	PostConsequenceMatcher notCall(char ignore);
	PostConsequenceMatcher notCall(float ignore);
	PostConsequenceMatcher notCall(int ignore);
	PostConsequenceMatcher notCall(long ignore);
	PostConsequenceMatcher notCall(short ignore);
	PostConsequenceMatcher notCall(Object ignore);

	BooleanConsequenceMatcher expect(boolean ignore);
	ByteConsequenceMatcher expect(byte ignore);
	CharConsequenceMatcher expect(char ignore);
	DoubleConsequenceMatcher expect(double ignore);
	FloatConsequenceMatcher expect(float ignore);
	IntConsequenceMatcher expect(int ignore);
	LongConsequenceMatcher expect(long ignore);
	ShortConsequenceMatcher expect(short ignore);
	StringConsequenceMatcher expect(String ignore);
	ObjectConsequenceMatcher expect(Object ignore);

	PostConsequenceMatcher doNotExpect(boolean ignore);
	PostConsequenceMatcher doNotExpect(byte ignore);
	PostConsequenceMatcher doNotExpect(char ignore);
	PostConsequenceMatcher doNotExpect(float ignore);
	PostConsequenceMatcher doNotExpect(int ignore);
	PostConsequenceMatcher doNotExpect(long ignore);
	PostConsequenceMatcher doNotExpect(short ignore);
	PostConsequenceMatcher doNotExpect(Object ignore);
}
