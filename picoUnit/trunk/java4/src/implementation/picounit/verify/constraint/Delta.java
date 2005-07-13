/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class Delta implements Comparable<Delta>{
	private final Number value;
	private final double delta;

	public Delta(Number value, Number delta) {
		this.value = value;
		this.delta = delta.doubleValue();
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object object) {
		if (object == null || !object.getClass().equals(getClass())) {
			return false;
		}

		Delta other = (Delta) object;

		double difference = value.doubleValue() - other.value.doubleValue();

		return Math.abs(difference) < delta; 
	}

	public int compareTo(Delta other) {
		double difference = value.doubleValue() - other.value.doubleValue();

		return Math.abs(difference) < delta ? 0 : difference < 0 ? -1 : 1;
	}
}
