/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public interface Configuration {
	/**
	 * @deprecated Mocker errors always thrown before verify errors 
	 */
	void throwVerifyErrorsBeforeMockerErrors();
	/**
	 * @deprecated Mocker errors always thrown before verify errors 
	 */
	void throwMockerErrorsBeforeVerifyErrors();
}