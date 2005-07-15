/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public interface CharacterArrayConstraintsCaseKnown {
	public void isEqualTo(char... equalTo);
	public void isDifferentTo(char... differentTo);

	public void contains(char contains);
	public void doesNotContain(char doesNotContain);
}
