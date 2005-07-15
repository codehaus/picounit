/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public class PicoUnitException extends RuntimeException {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	public PicoUnitException() {
		super();
	}

	public PicoUnitException(String message) {
		super(message);
	}

	public PicoUnitException(Throwable cause) {
		super(cause);
	}

	public PicoUnitException(String message, Throwable cause) {
		super(message, cause);
	}
}
