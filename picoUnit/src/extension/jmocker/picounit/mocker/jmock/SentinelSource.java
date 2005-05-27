/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

public class SentinelSource {
	private int currentInt;

	public SentinelSource() {
		this(0xBEEFCACA);
	}

	public SentinelSource(int startingInt) {
		this.currentInt = startingInt;
	}

	public int nextInt() {
		return currentInt++;
	}

	public String nextString() {
		return "[sentinel:" + currentInt++ + "]";
	}
}
