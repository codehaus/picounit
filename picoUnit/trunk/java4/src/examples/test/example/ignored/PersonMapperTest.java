/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.ignored;

import example.model.Database;

import picounit.Ignore;
import picounit.IgnoreReason;
import picounit.Test;

public class PersonMapperTest implements Test, Ignore {
	private final Database database;

	public PersonMapperTest(Database database) {
		this.database = database;
	}

	public void ignoredWhen(IgnoreReason ignoreReason) {
		ignoreReason.setCondition(!database.isConnected());
		ignoreReason.setWhy("Database not running");
	}

	public void testThatWontRun() {
	}
}
