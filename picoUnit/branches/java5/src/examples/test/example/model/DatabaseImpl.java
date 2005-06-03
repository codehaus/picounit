/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.model;


public class DatabaseImpl implements Database {
	private final boolean isConnected;

	public DatabaseImpl(boolean isConnected) {
		this.isConnected = isConnected;
	}
	
	public boolean isConnected() {
		return isConnected;
	}

	public boolean insert(String insertQuery) {
		return isConnected();
	}

	public int queryCount(String queryCountSql) {
		return 0;
	}
}
