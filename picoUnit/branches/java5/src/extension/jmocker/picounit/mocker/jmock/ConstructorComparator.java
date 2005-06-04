/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import java.lang.reflect.Constructor;
import java.util.Comparator;

public class ConstructorComparator implements Comparator<Constructor> {
	public int compare(Constructor left, Constructor right) {
		Visibility leftVisibility = new Visibility(left.getModifiers());
		Visibility rightVisibility = new Visibility(right.getModifiers());

		if (leftVisibility.moreVisible(rightVisibility)) {
			return -1;
		}
		else if (rightVisibility.moreVisible(leftVisibility)) {
			return 1;
		}

		Parameters leftParameters = new Parameters(left);
		Parameters rightParameters = new Parameters(right);

		if (leftParameters.less(rightParameters)) {
			return -1;
		}
		else if (rightParameters.less(leftParameters)) {
			return 1;
		}

		if (leftParameters.lessClasses(rightParameters)) {
			return -1;
		}
		if (rightParameters.lessClasses(leftParameters)) {
			return 1;
		}

		if (leftParameters.lessInterfaces(rightParameters)) {
			return -1;
		}
		else if (rightParameters.lessInterfaces(leftParameters)) {
			return 1;
		}

		if (leftParameters.lessCircular(rightParameters)) {
			return -1;
		}
		else if (rightParameters.lessCircular(leftParameters)) {
			return 1;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		return object != null && object.getClass().equals(getClass());
	}
}