/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

public class SentinelSource {
	private long currentValue;

	public SentinelSource() {
		this(0xBEEFCACA);
	}

	public SentinelSource(long startingPoint) {
		this.currentValue = startingPoint;
	}

	public int nextInt() {
		return (int) nextLong();
	}

	public long nextLong() {
		return currentValue++;
	}

	public double nextDouble() {
		return nextLong();
	}

	public String nextString() {
		return "[sentinel:" + nextLong() + "]";
	}
}
