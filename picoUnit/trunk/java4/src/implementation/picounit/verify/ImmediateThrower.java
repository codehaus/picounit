/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

public class ImmediateThrower implements Thrower {
	private Error previousError;

	public void errorEvent(Error error) {
		throwPreviousErrorIfDifferentTo(error);

		previousError = error;

		throw error;
	}

	private void throwPreviousErrorIfDifferentTo(Error error) {
		if (previousError != null && !previousError.equals(error)) {
			throw previousError;
		}
	}

	public void dispatchError() {
	}

	public void clearError() {
		previousError = null;
	}
}
