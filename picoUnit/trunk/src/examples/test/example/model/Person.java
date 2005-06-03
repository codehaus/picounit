/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.model;

public class Person {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}
}
