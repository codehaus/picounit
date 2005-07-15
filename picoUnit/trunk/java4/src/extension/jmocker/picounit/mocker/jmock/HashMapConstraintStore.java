/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.mocker.jmock;

import org.jmock.core.Constraint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HashMapConstraintStore implements ConstraintStore {
	private final Map sentinelToConstraintMap = new HashMap();
	private final SentinelSource sentinelSource;
	
	public HashMapConstraintStore() {
		this(new SentinelSource());
	}
	
	public HashMapConstraintStore(SentinelSource sentinelSource) {
		this.sentinelSource = sentinelSource;
	}
	
	public int putInteger(Constraint integerConstraint) {
		int sentinel = sentinelSource.nextInt();

		sentinelToConstraintMap.put(new Integer(sentinel), integerConstraint);

		return sentinel;
	}
	
	public long putLong(Constraint longConstraint) {
		long sentinel = sentinelSource.nextLong();

		sentinelToConstraintMap.put(new Long(sentinel), longConstraint);

		return sentinel;
	}
	
	public double putDouble(Constraint doubleConstraint) {
		double sentinel = sentinelSource.nextDouble();

		sentinelToConstraintMap.put(new Double(sentinel), doubleConstraint);

		return sentinel;
	}

	public String putString(Constraint constraint) {
		String sentinelValue = sentinelSource.nextString();

		sentinelToConstraintMap.put(sentinelValue, constraint);

		return sentinelValue;
	}

	public Constraint getConstraint(Object parameter) {
		Constraint constraint = (Constraint) sentinelToConstraintMap.get(parameter);

		return constraint != null ? constraint : new ProxyAwareIsEqual(parameter); 
	}

	public Constraint[] getConstraints(List parameters) {
		Constraint[] constraints = new Constraint[parameters.size()];

		int index = 0;
		for (Iterator iterator = parameters.iterator(); iterator.hasNext();) {
			constraints[index++] = getConstraint(iterator.next());
		}

		return constraints;
	}
}
