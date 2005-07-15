package picounit.verify;

import picounit.verify.constraint.CharacterConstraint;

public interface CharacterConstraintsCaseKnown {
	public void isEqualTo(char equalTo);
	public void isDifferentTo(char differentTo);
	public void isGreaterThan(char greaterThan);
	public void isGreaterThanOrEqualTo(char greaterThanOrEqualTo);
	public void isLessThan(char lessThan);
	public void isLessThanOrEqualTo(char lessThanOrEqualTo);

	public void passes(CharacterConstraint characterConstraint);
}
