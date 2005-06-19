/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.ignoredTests;

import picounit.Lifecycle;
import picounit.Registry;

public class LifeCycleForIgnoredTests implements Lifecycle {
	public void setUp(Registry registry) {
		registry.register(WebServer.class, new WebServer() {
			public boolean isRunning() {
				return false;
			}
		});
		
		registry.register(Database.class, new Database() {
			public boolean isRunning() {
				return true;
			}
		});
	}
}
