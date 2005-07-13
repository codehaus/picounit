/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class MatchesMask extends Constraint<Byte> {
	private final byte mask;

	public MatchesMask(byte mask) {
		this.mask = mask;
	}

	public boolean evaluate(Byte value) {
		return (value.byteValue() & mask) != 0;
	}

	public String describeFailure() {
		return "doesn't match the mask <" + toBinary(mask) + ">";
	}

	private String toBinary(byte aByte) {
		return Integer.toBinaryString(aByte);
	}
}