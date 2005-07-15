/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import picounit.verify.VerifyStage;

public interface Verify extends VerifyStage, LegacyVerify, BooleanLegacyVerify {
	VerifyStage because(String reason);

	void fail();
	void fail(String message);
}