/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;


public class DelayedThrower implements Thrower {
	private Error error;

	public void errorEvent(Error error) {
		if (this.error == null) {
			this.error = error;
		}
	}

	public void dispatchError() {
		if (error != null) {
			throw error;
		}
	}

	public void clearError() {
		error = null;
	}
}
