/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import junit.framework.Test;

public class ExampleSuite {
	public static Test suite() {
		return new PicoUnit().generateJUnitTest();
	}
	
	public static Test anotherSuite() {
		return new PicoUnit().generateJUnitTest(new ExplicitGenerator(ExampleTest.class));
//
//		return new ExplicitGenerator(ExampleTest.class).generate();
	}
	
	public static class ExplicitGenerator implements JUnitTestGenerator {
		public ExplicitGenerator(Class class1) {
		}

		public Test generateJUnitTest() {
			return null;
		}
	}
}
