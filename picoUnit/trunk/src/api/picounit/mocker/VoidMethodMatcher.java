/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker;

public interface VoidMethodMatcher {
	OccurencesMatcher raise(Throwable throwable);

	OccurencesMatcher perform(BooleanAction booleanAction);
	OccurencesMatcher perform(ByteAction byteAction);
	OccurencesMatcher perform(CharAction charAction);
	OccurencesMatcher perform(DoubleAction doubleAction);
	OccurencesMatcher perform(FloatAction floatAction);
	OccurencesMatcher perform(IntAction intAction);
	OccurencesMatcher perform(LongAction longAction);
	OccurencesMatcher perform(ShortAction shortAction);
	OccurencesMatcher perform(StringAction stringAction);
	OccurencesMatcher perform(ObjectAction objectAction);
	OccurencesMatcher perform(VoidAction voidAction);
}
