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

public class WebServerTestThatIsIgnoredIfWebServerIsDown implements Test, Ignore {
	private final WebServer webServer;

	public static final Validator validator = new Validator(WebServerTestThatIsIgnoredIfWebServerIsDown.class) {
		@Override
		public void validate(TestResult testResult) {
			matches("ignoredWhen");
		}
	};

	public WebServerTestThatIsIgnoredIfWebServerIsDown(WebServer webServer) {
		this.webServer = webServer;
	}

	public void testWebServer() {
		validator.record("testWebServer");
	}

	public void ignoredWhen(IgnoreReason ignoreReason) {
		validator.record("ignoredWhen");
		
		ignoreReason.setWhy("Web server not running");
		ignoreReason.setCondition(!webServer.isRunning());
	}
}
