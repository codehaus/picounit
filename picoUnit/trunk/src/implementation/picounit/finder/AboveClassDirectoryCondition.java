/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.finder;

import java.io.File;

public class AboveClassDirectoryCondition implements DirectoryCondition {
	private final File aboveWhat;
	private final File sourceRoot;

	public AboveClassDirectoryCondition(File aboveWhat, File sourceRoot) {
		this.aboveWhat = aboveWhat;
		this.sourceRoot = sourceRoot;
	}

	public boolean matches(File toMatch) {
		File above = aboveWhat;

		do {
			if (toMatch.equals(above)) {
				return true;
			}

			above = above.getParentFile();
		} while (above != null && !above.equals(sourceRoot));

		return false;
	}
}