/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import junit.framework.AssertionFailedError;
import junit.framework.Test;

public interface DelegateTestResult {
	void startTest(Test test);

	void endTest(Test test);

	void addError(Test error, Throwable throwable);

	void addFailure(Test failure, AssertionFailedError assertionFailedError);
}
