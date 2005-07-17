/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

import junit.framework.AssertionFailedError;
import picounit.verify.ArrayUtil;
import picounit.verify.DefaultNumericVerify;
import picounit.verify.DefaultVerify;
import picounit.verify.ImmediateThrower;
import picounit.verify.NullVerifiable;
import picounit.verify.NumericUtil;
import picounit.verify.StringUtil;
import previous.picounit.Test;

public class NumericVerifyTest implements Test {
	private final Verify defaultVerify = DefaultVerify.create(new NumericUtil(), new ArrayUtil(),
		new StringUtil(), new ImmediateThrower(), new NullVerifiable());
	private final NumericVerify numericVerify = new DefaultNumericVerify(defaultVerify);
	private final previous.picounit.Verify verify;

	public NumericVerifyTest(previous.picounit.Verify verify) {
		this.verify = verify;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// greaterThan
	///////////////////////////////////////////////////////////////////////////////////////////////

	// --- double ---------------------------------------------------------------------------------

	public void testADoubleIsGreaterThanABitLessThanItself() {
		numericVerify.isGreaterThan(100.0, deductSmallestIncrementFrom(100.0));
	}
	
	public void testADoubleIsNotGreaterThanItself() {
		try {
			numericVerify.isGreaterThan(100.0, 100.0);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100.0 is not greater than 100.0");

			return;
		}

		assertionFailedErrorExpected();
	}

	// --- float ----------------------------------------------------------------------------------
	
	public void testAFloatIsGreaterThanABitLessThanItself() {
		numericVerify.isGreaterThan(100.0f, deductSmallestIncrementFrom(100.0f));
	}
	
	public void testAFloatIsNotGreaterThanItself() {
		try {
			numericVerify.isGreaterThan(100.0f, 100.0f);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100.0 is not greater than 100.0");

			return;
		}

		assertionFailedErrorExpected();
	}

	// --- int ------------------------------------------------------------------------------------

	public void testAnIntegerIsGreaterThanOneLessThanItself() {
		numericVerify.isGreaterThan(100, 99);
	}
	
	public void testAnIntegerIsNotGreaterThanItself() {
		try {
			numericVerify.isGreaterThan(100, 100);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100 is not greater than 100");

			return;
		}

		assertionFailedErrorExpected();
	}

	// --- long -----------------------------------------------------------------------------------

	public void testALongIsGreaterThanOneLessThanItself() {
		numericVerify.isGreaterThan(100L, 99L);
	}

	public void testALongIsNotGreaterThanItself() {
		try {
			numericVerify.isGreaterThan(100L, 100L);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100 is not greater than 100");

			return;
		}

		assertionFailedErrorExpected();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// greaterThanOrEqualTo
	///////////////////////////////////////////////////////////////////////////////////////////////

	// --- double ---------------------------------------------------------------------------------

	public void testADoubleIsGreaterThanOrEqualToItself() {
		numericVerify.isGreaterThanOrEqualTo(100.0, 100.0); 
	}
	
	public void testADoubleIsNotGreaterThanOrEqualToABitMoreThanItself() {
		double aLittleMoreThan100 = addSmallestIncrementTo(100.0);

		try {
			numericVerify.isGreaterThanOrEqualTo(100.0, aLittleMoreThan100);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100.0 is not greater than or equal to " + aLittleMoreThan100);

			return;
		}
		
		assertionFailedErrorExpected();
	}
	
	// --- float ----------------------------------------------------------------------------------

	public void testAFloatIsGreaterThanOrEqualToItself() {
		numericVerify.isGreaterThanOrEqualTo(100.0f, 100.0f); 
	}
	
	public void testAFloatIsNotGreaterThanOrEqualToABitMoreThanItself() {
		float aLittleMoreThan100 = addSmallestIncrementTo(100.0f);

		try {
			numericVerify.isGreaterThanOrEqualTo(100.0f, aLittleMoreThan100);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100.0 is not greater than or equal to " + aLittleMoreThan100);
			
			return;
		}
		
		assertionFailedErrorExpected();
	}

	// --- int ------------------------------------------------------------------------------------

	public void testAnIntegerIsGreaterThanOrEqualToItself() {
		numericVerify.isGreaterThanOrEqualTo(100, 100);
	}
	
	public void testAnIntegerIsNotGreaterThanOrEqualToOneMoreThanItself() {
		try {
			numericVerify.isGreaterThanOrEqualTo(100, 101);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100 is not greater than or equal to 101");

			return;
		}

		assertionFailedErrorExpected();
	}
	
	// --- long -----------------------------------------------------------------------------------

	public void testALongIsGreaterThanOrEqualToItself() {
		numericVerify.isGreaterThanOrEqualTo(100L, 100L);
	}
	
	public void testALongIsNotGreaterThanOrEqualToOneMoreThanItself() {
		try {
			numericVerify.isGreaterThanOrEqualTo(100L, 101L);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100 is not greater than or equal to 101");

			return;
		}

		assertionFailedErrorExpected();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// lessThan
	///////////////////////////////////////////////////////////////////////////////////////////////

	// --- double ---------------------------------------------------------------------------------
	
	public void testADoubleIsLessThanALittleBitMoreThanItself() {
		numericVerify.isLessThan(100.0, addSmallestIncrementTo(100.0)); 
	}
	
	public void testADoubleIsNotLessThanItself() {
		try {
			numericVerify.isLessThan(100.0, 100.0);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100.0 is not less than 100.0");
			
			return;
		}
		
		assertionFailedErrorExpected();
	}

	// --- float ----------------------------------------------------------------------------------

	public void testAFloatIsLessThanALittleBitMoreThanItself() {
		numericVerify.isLessThan(100.0f, addSmallestIncrementTo(100.0f)); 
	}
	
	public void testAFloatIsNotLessThanItself() {
		try {
			numericVerify.isLessThan(100.0f, 100.0f);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100.0 is not less than 100.0");
			
			return;
		}
		
		assertionFailedErrorExpected();
	}

	// --- int ------------------------------------------------------------------------------------

	public void testAIntegerIsLessThanOneMoreThanItself() {
		numericVerify.isLessThan(100, 101);
	}
	
	public void testAnIntegerIsNotLessThanItself() {
		try {
			numericVerify.isLessThan(100, 100);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100 is not less than 100");
			
			return;
		}
		
		assertionFailedErrorExpected();
	}

	// --- long -----------------------------------------------------------------------------------

	public void testALongIsLessThanOneMoreThanItself() {
		numericVerify.isLessThan(1L, 100L);
	}
	
	public void testALongIsNotLessThanItself() {
		try {
			numericVerify.isLessThan(100L, 100L);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100 is not less than 100");
			
			return;
		}
		
		assertionFailedErrorExpected();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// lessThanOrEqualTo
	///////////////////////////////////////////////////////////////////////////////////////////////

	// --- double ---------------------------------------------------------------------------------
	
	public void testADoubleIsLessThanOrEqualToItself() {
		numericVerify.isLessThanOrEqualTo(100.0, 100.0); 
	}
	
	public void testADoubleIsNotLessThanOrEqualToALittleLessThanItself() {
		double aLittleLessThan100 = deductSmallestIncrementFrom(100.0);

		try {
			numericVerify.isLessThanOrEqualTo(100.0, aLittleLessThan100);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100.0 is not less than or equal to " + aLittleLessThan100);

			return;
		}
		
		assertionFailedErrorExpected();
	}

	// --- float ----------------------------------------------------------------------------------

	public void testAFloatIsLessThanOrEqualToItself() {
		numericVerify.isLessThanOrEqualTo(100.0f, 100.0f); 
	}
	
	public void testAFloatIsNotLessThanOrEqualToALittleLessThanItself() {
		float aLittleLessThan100 = deductSmallestIncrementFrom(100.0f);

		try {
			numericVerify.isLessThanOrEqualTo(100.0f, aLittleLessThan100);
		}
		catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100.0 is not less than or equal to " + aLittleLessThan100);

			return;
		}
		
		assertionFailedErrorExpected();
	}

	// --- int ------------------------------------------------------------------------------------
	
	public void testAnIntegerIsLessThanOrEqualToItself() {
		numericVerify.isLessThanOrEqualTo(100, 100);
	}
	
	public void testAnIntegerIsNotLessThanOrEqualToOneLessThanItself() {
		 try {
			 numericVerify.isLessThanOrEqualTo(100, 99);
		 }
		 catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100 is not less than or equal to 99");

			 return;
		 }
		 
		 assertionFailedErrorExpected();
	}

	// --- long -----------------------------------------------------------------------------------

	public void testALongIsLessThanOrEqualToItself() {
		numericVerify.isLessThanOrEqualTo(100L, 100L);
	}
	
	public void testALongIsNotLessThanOrEqualToOneLessThanItself() {
		 try {
			 numericVerify.isLessThanOrEqualTo(100L, 99L);
		 }
		 catch (AssertionFailedError assertionFailedError) {
			verify.that(assertionFailedError.getMessage())
				.isEqualTo("100 is not less than or equal to 99");

			 return;
		 }
		 
		 assertionFailedErrorExpected();
	}

	private void assertionFailedErrorExpected() {
		verify.fail("AssertionFailedError expected");
	}

	private double addSmallestIncrementTo(double value) {
		return Double.longBitsToDouble(Double.doubleToLongBits(value) + 1);
	}

	private double deductSmallestIncrementFrom(double value) {
		return Double.longBitsToDouble(Double.doubleToLongBits(value) - 1);
	}

	private float addSmallestIncrementTo(float value) {
		return Float.intBitsToFloat(Float.floatToIntBits(value) + 1);
	}

	private float deductSmallestIncrementFrom(float value) {
		return Float.intBitsToFloat(Float.floatToIntBits(value) - 1);
	}
}
