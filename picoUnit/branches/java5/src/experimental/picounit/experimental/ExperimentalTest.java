/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.experimental;

import picounit.Constraints;
import picounit.Mocker;
import picounit.Verify;

interface Test {
	interface should {
	}

	interface shouldNot {
	}

	interface ignore {
		interface should {
		}
		interface shouldNot {
		}
	}

	interface all extends should, shouldNot, ignore, ignore.should, ignore.shouldNot {
	}

	all done = new all() {};
}

public class ExperimentalTest implements Test {
	private final Component component;
	private final Collaborator mockCollaborator;

	public ExperimentalTest(Collaborator mockCollaborator) {
		this.component = new Component(mockCollaborator);
		this.mockCollaborator = mockCollaborator;
	}

	should useCollaboratorToPerformService(Constraints is, Mocker should, Verify verify) {
		should.call(mockCollaborator.collaborate(is.notANullString())).andReturn("result");
		
		should.doAboveWhen();
		
		verify.equal("result", component.service("parameter"));
		
		return done;
	}

	shouldNot useWidgetToPerformFunction() {
		return done;
	}

	ignore.should doSomething() {
		return done;
	}

	interface Collaborator {
		String collaborate(String parameter);

		String perform(String parameter);
	}
	
	class Component {
		private final Collaborator collaborator;

		Component(Collaborator collaborator) {
			this.collaborator = collaborator;
			
		}
		
		String service(String parameter) {
			return collaborator.perform(parameter);
		}
	}	
}
