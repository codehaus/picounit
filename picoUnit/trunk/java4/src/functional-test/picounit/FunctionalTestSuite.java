/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import previous.picounit.PicoUnit;
import junit.framework.Test;

public class FunctionalTestSuite {
	public static Test suite() {
		return new PicoUnit("PicoUnit Functional Tests").setType(FunctionalTest.class).generateJUnitTest();
	}
}
