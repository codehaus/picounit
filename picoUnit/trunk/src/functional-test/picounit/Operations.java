/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

public class Operations {
	private StringBuffer operations = new StringBuffer();
	private final Map data = new HashMap();

	public Operations clear() {
		operations = new StringBuffer();
		data.clear();
		
		return this;
	}

	public Operations record(String operation) {
		operations.append(operation);
		operations.append(' ');
		
		return this;
	}

	public String operations() {
		return operations.toString().trim();
	}

	private Operations put(String key, Object value) {
		data.put(key, value);

		return this;
	}

	public Object get(String key) {
		return data.get(key);
	}

	public Operations record(String methodName, Object parameter) {
		record(methodName);
		put(methodName, parameter);

		return this;
	}

	public void matches(String toMatch) {
		Assert.assertEquals(toMatch, operations());
	}
}