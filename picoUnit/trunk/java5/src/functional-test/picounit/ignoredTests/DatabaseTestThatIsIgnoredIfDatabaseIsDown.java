/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.ignoredTests;

import picounit.Ignore;
import picounit.IgnoreReason;
import picounit.Test;
import picounit.Validator;
import junit.framework.TestResult;

public class DatabaseTestThatIsIgnoredIfDatabaseIsDown implements Test, Ignore {
	private final Database database;
	
	public static final Validator validator = new Validator(DatabaseTestThatIsIgnoredIfDatabaseIsDown.class) {
		@Override
		public void validate(TestResult testResult) {
			matches("ignoredWhen testDatabase");
		}
	};
	
	public DatabaseTestThatIsIgnoredIfDatabaseIsDown(Database database) {
		this.database = database;
	}

	public void testDatabase() {
		validator.record("testDatabase");
	}

	public void ignoredWhen(IgnoreReason ignoreReason) {
		validator.record("ignoredWhen");

		ignoreReason.setCondition(!database.isRunning());
		ignoreReason.setWhy("Database not running");
	}
}
