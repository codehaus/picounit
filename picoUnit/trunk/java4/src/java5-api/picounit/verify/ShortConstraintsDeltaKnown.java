package picounit.verify;

import picounit.verify.constraint.ShortConstraint;

public interface ShortConstraintsDeltaKnown {

	public void isEqualTo(short equalTo);

	public void isDifferentTo(short differentTo);

	public void isGreaterThan(short greaterThan);

	public void isGreaterThanOrEqualTo(short greaterThanOrEqualTo);

	public void isLessThan(short lessThan);

	public void isLessThanOrEqualTo(short lessThanOrEqualTo);

	public void passes(ShortConstraint shortConstraint);

}
