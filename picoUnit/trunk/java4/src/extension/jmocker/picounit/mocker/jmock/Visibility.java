/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import java.lang.reflect.Modifier;

public class Visibility {
	private static final int publicVisibility = 0;
	private static final int packageVisibility = 1;
	private static final int protectedVisibility = 2;
	private static final int privateVisibility = 3;

	private final int visibility;

	public Visibility(int modifiers) {
		if (Modifier.isPublic(modifiers)) {
			visibility = publicVisibility;
		}
		else if (Modifier.isProtected(modifiers)) {
			visibility = protectedVisibility;
		}
		else if (Modifier.isPrivate(modifiers)) {
			visibility = privateVisibility;
		}
		else {
			visibility = packageVisibility;
		}
	}

	public boolean moreVisible(Visibility other) {
		return visibility < other.visibility;
	}
}
