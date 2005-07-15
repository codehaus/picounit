/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.constraint.Constraint;

public class IsPalindrome extends Constraint {
	public IsPalindrome() {
		super();
	}

	public boolean evaluate(Object object) {
		String string = (String) object;
		int left = 0;
		int right = string.length() - 1;

		while (left < right) {
			if (string.charAt(left) != string.charAt(right)) {
				return false;
			}
			left++ ;
			right-- ;
		}

		return true;
	}

	public String describeFailure() {
		return "is not a palindrome";
	}
}
