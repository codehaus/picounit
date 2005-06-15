/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.registry;

import picounit.Configuration;

public class DefaultConfiguration implements Configuration {
	/**
	 * @deprecated Mocker errors always thrown before verify errors 
	 */
	public void throwVerifyErrorsBeforeMockerErrors() {
	}

	/**
	 * @deprecated Mocker errors always thrown before verify errors 
	 */
	public void throwMockerErrorsBeforeVerifyErrors() {
	}
}