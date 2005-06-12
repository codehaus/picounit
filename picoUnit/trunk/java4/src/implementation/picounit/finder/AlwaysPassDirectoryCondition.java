/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;


import java.io.File;

public class AlwaysPassDirectoryCondition implements DirectoryCondition {
	public boolean matches(File file) {
		return true;
	}

	public boolean equals(Object object) {
		return object != null && getClass().equals(object.getClass());
	}
	
	public int hashCode() {
		return getClass().hashCode();
	}
}