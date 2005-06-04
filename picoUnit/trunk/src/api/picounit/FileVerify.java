/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import java.io.File;

public interface FileVerify {
	void sameType(File expected, File actual);
	void sameContents(File expected, File actual);
}