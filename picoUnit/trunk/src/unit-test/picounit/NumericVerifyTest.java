/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import junit.framework.AssertionFailedError;
import picounit.verify.DefaultNumericVerify;
import picounit.verify.DefaultVerify;
import previous.picounit.Test;
import previous.picounit.Verify;

public class NumericVerifyTest implements Test {
	private final NumericVerify numericVerify = new DefaultNumericVerify(new DefaultVerify());

	///////////////////////////////////////////////////////////////////////////////////////////////
	// greaterThan
	///////////////////////////////////////////////////////////////////////////////////////////////

	// --- int ------------------------------------------------------------------------------------

	public void testAnIntegerIsGreaterThanOneLessThanItself() {
		numericVerify.isGreaterThan(100, 99);
	}
	
	public void testAnIntegerIsNotGreaterThanItself(Verify verify) {
		try {
			numericVerify.isGreaterThan(100, 100);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.equal("100 is not greater than 100", assertionFailedError.getMessage());

			return;
		}

		verify.fail("AssertionFailedError expected");
	}

	// --- long -----------------------------------------------------------------------------------

	public void testALongIsGreaterThanOneLessThanItself(Verify verify) {
		numericVerify.isGreaterThan(100L, 99L);
	}

	public void testALongIsNotGreaterThanItself(Verify verify) {
		try {
			numericVerify.isGreaterThan(100L, 100L);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.equal("100 is not greater than 100", assertionFailedError.getMessage());

			return;
		}

		verify.fail("AssertionFailedError expected");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// greaterThanOrEqualTo
	///////////////////////////////////////////////////////////////////////////////////////////////

	// --- int ------------------------------------------------------------------------------------

	public void testAnIntegerIsGreaterThanOrEqualToItself() {
		numericVerify.isGreaterThanOrEqualTo(100, 100);
	}
	
	public void testAnIntegerIsNotGreaterThanOrEqualToOneMoreThanItself(Verify verify) {
		try {
			numericVerify.isGreaterThanOrEqualTo(100, 101);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.equal("100 is not greater than or equal to 101", assertionFailedError.getMessage());

			return;
		}

		verify.fail("AssertionFailedError expected");
	}
	
	// --- long -----------------------------------------------------------------------------------

	public void testALongIsGreaterThanOrEqualToItself() {
		numericVerify.isGreaterThanOrEqualTo(100L, 100L);
	}
	
	public void testALongIsNotGreaterThanOrEqualToOneMoreThanItself(Verify verify) {
		try {
			numericVerify.isGreaterThanOrEqualTo(100L, 101L);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.equal("100 is not greater than or equal to 101", assertionFailedError.getMessage());

			return;
		}

		verify.fail("AssertionFailedError expected");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// lessThan
	///////////////////////////////////////////////////////////////////////////////////////////////

	// --- int ------------------------------------------------------------------------------------

	public void testAIntegerIsLessThanOneMoreThanItself() {
		numericVerify.isLessThan(100, 101);
	}
	
	public void testAnIntegerIsNotLessThanItself(Verify verify) {
		try {
			numericVerify.isLessThan(100, 100);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.equal("100 is not less than 100", assertionFailedError.getMessage());
			
			return;
		}
		
		verify.fail("AssertionFailedError expected");
	}

	// --- long -----------------------------------------------------------------------------------

	public void testALongIsLessThanOneMoreThanItself() {
		numericVerify.isLessThan(1L, 100L);
	}
	
	public void testALongIsNotLessThanItself(Verify verify) {
		try {
			numericVerify.isLessThan(100L, 100L);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.equal("100 is not less than 100", assertionFailedError.getMessage());
			
			return;
		}
		
		verify.fail("AssertionFailedError expected");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// lessThanOrEqualTo
	///////////////////////////////////////////////////////////////////////////////////////////////

	// --- int ------------------------------------------------------------------------------------
	
	public void testAnIntegerIsLessThanOrEqualToItself() {
		numericVerify.isLessThanOrEqualTo(100, 100);
	}
	
	public void testAnIntegerIsNotLessThanOrEqualToOneLessThanItself(Verify verify) {
		 try {
			 numericVerify.isLessThanOrEqualTo(100, 99);
		 }
		 catch (AssertionFailedError assertionFailedError) {
			 verify.equal("100 is not less than or equal to 99", assertionFailedError.getMessage());
			 
			 return;
		 }
		 
		 verify.fail("AssertionFailedError expected");
	}

	// --- long -----------------------------------------------------------------------------------

	public void testALongIsLessThanOrEqualToItself() {
		numericVerify.isLessThanOrEqualTo(100L, 100L);
	}
	
	public void testALongIsNotLessThanOrEqualToOneLessThanItself(Verify verify) {
		 try {
			 numericVerify.isLessThanOrEqualTo(100L, 99L);
		 }
		 catch (AssertionFailedError assertionFailedError) {
			 verify.equal("100 is not less than or equal to 99", assertionFailedError.getMessage());
			 
			 return;
		 }
		 
		 verify.fail("AssertionFailedError expected");
	}
}
