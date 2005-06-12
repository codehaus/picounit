/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.migration.dependency;

import example.model.Database;
import example.model.Person;
import example.model.PersonMapper;
import picounit.migration.TestCase;

public class PersonMapperTest extends TestCase {
	private final PersonMapper personMapper;

	private final Database database;

	public PersonMapperTest(Database database) {
		this.personMapper = new PersonMapper(database);

		this.database = database;
	}

	public void testSavingAPersonAddsOneMorePersonToTheDatabase() {
		int previousPopulation = countPeople();

		Person fred = new Person("Fred Dibner");
		personMapper.save(fred);

		assertEquals(previousPopulation + 1, countPeople());
	}

	private int countPeople() {
		return database.queryCount("select count(*) from people");
	}
}
