/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.ignored;

import example.model.Database;
import example.model.DatabaseImpl;
import picounit.LifeCycle;
import picounit.Registry;

public class DisconnectedDatabaseLifeCycle implements LifeCycle {
	public void setUp(Registry registry) {
		registry.register(Database.class, new DatabaseImpl(false));
	}
}
