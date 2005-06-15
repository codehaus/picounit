/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.model;


public class PersonMapper {
	private final Database database;

	public PersonMapper(Database database) {
		this.database = database;
	}

	public void save(Person person) {
		database.insert("insert into people (name) values('" + person.name() + "')");
	}
}
