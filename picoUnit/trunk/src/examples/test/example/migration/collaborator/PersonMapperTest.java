/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.migration.collaborator;

import example.model.Database;
import example.model.Person;
import example.model.PersonMapper;
import picounit.migration.TestCase;

public class PersonMapperTest extends TestCase {
	private PersonMapper personMapper;

	private Database mockDatabase;

	public void mock(Database mockDatabase) {
		this.personMapper = new PersonMapper(mockDatabase);

		this.mockDatabase = mockDatabase;
	}

	public void testSavingAPersonInsertsARowIntoPeopleTable() {
		should.call(mockDatabase.insert("insert into people (name) values('Fred Dibner')"))
			.andReturn(true)
			.because("The PersonMapper should use a Database to save a Person");

		should.doAboveWhen();

		Person fred = new Person("Fred Dibner");
		personMapper.save(fred);
	}
}