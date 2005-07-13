/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import java.io.File;

public interface FileConstraints {
	void isEqualTo(File equalTo);
	void isDifferentTo(File differentTo);
	void isGreaterThan(File greaterThan);
	void isGreaterThanOrEqualTo(File greaterThanOrEqualTo);
	void isLessThan(File lessThan);
	void isLessThanOrEqualTo(File lessThanOrEqualTo);
	
	void isTheSameTypeAs(File sameTypeAs);
	void hasTheSameContentsAs(File sameContentsAs);
}
