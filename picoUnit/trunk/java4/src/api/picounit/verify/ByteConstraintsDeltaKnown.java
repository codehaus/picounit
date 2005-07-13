package picounit.verify;

import picounit.verify.constraint.ByteConstraint;

public interface ByteConstraintsDeltaKnown {

	public void isEqualTo(byte equalTo);

	public void isDifferentTo(byte differentTo);

	public void isGreaterThan(byte greaterThan);

	public void isGreaterThanOrEqualTo(byte greaterThanOrEqualTo);

	public void isLessThan(byte lessThan);

	public void isLessThanOrEqualTo(byte lessThanOrEqualTo);

	public void passes(ByteConstraint booleanConstraint);

}
