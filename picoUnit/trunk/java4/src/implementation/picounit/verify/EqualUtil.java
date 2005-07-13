/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import java.util.Arrays;

public class EqualUtil {
	public boolean equal(Object left, Object right) {
		return bothNull(left, right) ||
			neitherNull(left, right) && (sameReference(left, right) ||
				arrayEquals(left, right) ||
				ordinaryEquals(left, right));
	}

	private boolean sameReference(Object left, Object right) {
		return left == right;
	}

	private boolean bothNull(Object left, Object right) {
		return left == null && right == null;
	}

	private boolean neitherNull(Object left, Object right) {
		return left != null && right != null;
	}

	private boolean arrayEquals(Object left, Object right) {
		return left.getClass().isArray() && right.getClass().isArray() &&
			Arrays.equals((Object[]) left, (Object[]) right);
	}

	private boolean ordinaryEquals(Object left, Object right) {
		return left.equals(right);
	}
}