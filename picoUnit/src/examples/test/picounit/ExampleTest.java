/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public class ExampleTest implements Test {
	private final Database database;

	public ExampleTest(Database database) {
		this.database = database;
	}

	public void mock() {
	}

	public void setUp() {
	}

	public void tearDown() {
	}

	public void testSomething(Verify verify, Mocker will, ConstraintFactory is) {
		verify.that(true);
	}
}
