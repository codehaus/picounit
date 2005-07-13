package picounit.verify;

import picounit.verify.constraint.FloatConstraint;

public interface FloatConstraintsDeltaKnown {

	public void isEqualTo(float equalTo);

	public void isDifferentTo(float differentTo);

	public void isGreaterThan(float greaterThan);

	public void isGreaterThanOrEqualTo(float greaterThanOrEqualTo);

	public void isLessThan(float lessThan);

	public void isLessThanOrEqualTo(float lessThanOrEqualTo);

	public void passes(FloatConstraint floatConstraint);

}
