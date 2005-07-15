package picounit.verify;

import picounit.verify.constraint.DoubleConstraint;

public interface DoubleConstraintsDeltaKnown {

	public void isEqualTo(double equalTo);

	public void isDifferentTo(double differentTo);

	public void isGreaterThan(double greaterThan);

	public void isGreaterThanOrEqualTo(double greaterThanOrEqualTo);

	public void isLessThan(double lessThan);

	public void isLessThanOrEqualTo(double lessThanOrEqualTo);

	public void passes(DoubleConstraint doubleConstraint);

}
