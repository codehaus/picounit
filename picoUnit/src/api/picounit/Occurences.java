/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

public class Occurences {
	private final int min;
	private final int max;

	public Occurences(int occurences) {
		this.min = this.max = occurences;
	}

	public Occurences(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMinimum() {
		return min;
	}

	public int getMaximum() {
		return max;
	}
}
