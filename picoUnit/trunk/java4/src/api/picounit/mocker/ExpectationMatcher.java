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
	<T> ConsequenceMatcher<T> call(T ignore);

	PostConsequenceMatcher notCall(boolean ignore);
	PostConsequenceMatcher notCall(byte ignore);
	PostConsequenceMatcher notCall(char ignore);
	PostConsequenceMatcher notCall(double ignore);
	PostConsequenceMatcher notCall(float ignore);
	PostConsequenceMatcher notCall(int ignore);
	PostConsequenceMatcher notCall(long ignore);
	PostConsequenceMatcher notCall(short ignore);

	PostConsequenceMatcher notCall(String ignore);
	<T> PostConsequenceMatcher notCall(T ignore);

	/** @deprecated use call instead */
	BooleanConsequenceMatcher expect(boolean ignore);
	/** @deprecated use call instead */
	ByteConsequenceMatcher expect(byte ignore);
	/** @deprecated use call instead */
	CharConsequenceMatcher expect(char ignore);
	/** @deprecated use call instead */
	DoubleConsequenceMatcher expect(double ignore);
	/** @deprecated use call instead */
	FloatConsequenceMatcher expect(float ignore);
	/** @deprecated use call instead */
	IntConsequenceMatcher expect(int ignore);
	/** @deprecated use call instead */
	LongConsequenceMatcher expect(long ignore);
	/** @deprecated use call instead */
	ShortConsequenceMatcher expect(short ignore);

	/** @deprecated use call instead */
	StringConsequenceMatcher expect(String ignore);
	/** @deprecated use call instead */
	<T> ConsequenceMatcher<T> expect(T ignore);	

	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(boolean ignore);
	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(byte ignore);
	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(char ignore);
	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(double ignore);
	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(float ignore);
	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(int ignore);
	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(long ignore);
	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(short ignore);

	/** @deprecated use notCall instead */
	PostConsequenceMatcher doNotExpect(String ignore);
	/** @deprecated use notCall instead */
	<T> PostConsequenceMatcher doNotExpect(T ignore);
}
