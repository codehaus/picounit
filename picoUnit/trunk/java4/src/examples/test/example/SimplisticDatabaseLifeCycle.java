/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example;

import example.model.Database;
import picounit.LifeCycle;
import picounit.Registry;

public class SimplisticDatabaseLifeCycle implements LifeCycle {
	public void setUp(Registry registry) {
		registry.register(Database.class, new SimplisticDatabase());
	}
	
	public static class SimplisticDatabase implements Database {
		private int count = 0;

		public boolean isConnected() {
			return true;
		}

		public boolean insert(String insertQuery) {
			count++;
			
			return true;
		}

		public int queryCount(String queryCountSql) {
			return count;
		}
	}
}
