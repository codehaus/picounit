/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.Constraints;
import picounit.Test;
import picounit.Verify;
import picounit.mocker.MockFactory;

import java.awt.Color;

public class UseCustomVerify implements Test {
	private final Frog kermit = new Frog(Color.GREEN);
	private final Frog deadKermit = new Frog(Color.BLUE);
	
	public void testDefault(Verify verify) {
		verify.that("abc").ignoringCase().isEqualTo("ABC");

		verify.that('a').ignoringCase().isEqualTo('A');
		
		verify.that(new char[] {'a', 'b', 'c'}).ignoringCase().contains('A');
		
		verify.because("blah").that(new float[]{120f, 120.0f}).withDelta(21.0f).isEqualTo(100.0f, 100.0f);

		boolean[] boa = {true, true, true};
		boolean[] dboa = {false, true, false};

		verify.that(boa).doesNotContain(false);
		
		verify.that(boa).isEqualTo(boa);
		verify.that(boa).isDifferentTo(dboa);

		byte[] ba = {1, 2, 3};
		byte[] dba = {3, 2, 1};
		
		verify.that(ba).isEqualTo(ba);
		verify.that(ba).isDifferentTo(dba);

		verify.that(ba).contains((byte) 3);

		String[] sa = {"a", "b", "c"};
		String[] dsa = {"c", "b", "a"};
		
		verify.that(sa).isEqualTo(sa);
		verify.that(sa).isDifferentTo(dsa);

		verify.that(sa).contains("a");
		verify.that(sa).doesNotContain("z");
		
		verify.that(sa).ignoringCase().contains("A");
		verify.that(sa).ignoringCase().doesNotContain("Z");
		
		verify.that(sa).ignoringCase().isEqualTo("A", "B", "C");

		verify.that("abc").ignoringCase().isEqualTo("ABC");

		verify.that(120).withDelta(21).isEqualTo(100);
		verify.that(120).withDelta(20).isDifferentTo(100);
		
		int[] ints = new int[] {100, 150, 200};

		verify.that(ints).withDelta(21).contains(120);
		
		Frog[] frogs = new Frog[] {kermit};
		
		verify.that(frogs).contains(kermit);
		verify.that(frogs).doesNotContain(deadKermit);
	}
	
	public void testNewStringConstraints(FrogVerify verify) {
		verify.that("some string").isOfLength(11);

		verify.because("someting").that("abba").isAPalindrome();
		
		verify.that("BOOM").isEqualToBOOM();
		verify.that("boom").ignoringCase().isEqualToBOOM();

		verify.that(123.3).isNotSmall();
		verify.that(0.1).isNotSmall();
	}

	public void testFrogConstraitns(FrogVerify verify) {
		verify.because("kermit should be green")
			.that(kermit).isGreen();

		verify.that(deadKermit).isNotGreen();
	}
	
	interface Thing {
		
	}

	interface UsesThing {
		void use(Thing thing);
	}

	public void mockThing(UsesThing useThing, MockFactory mockFactory, Constraints is) {
		useThing.use(mockFactory.mock(Thing.class));
		
		useThing.use(is.instanceOf(Thing.class));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
