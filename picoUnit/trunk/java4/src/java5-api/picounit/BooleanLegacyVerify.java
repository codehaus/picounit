package picounit;

/**
 * @deprecated use 'thatBoolean' instead
 * 
 * In the next release (1.3) these methods will be deleted and 'thatBoolean' will be renamed to 'that'
 */
public interface BooleanLegacyVerify {
	public void that(boolean expression);
	public void that(String message, boolean expression);

	public void not(boolean expression);
	public void not(String message, boolean expression);

	public void equal(boolean expected, boolean actual);
	public void equal(String message, boolean expected, boolean actual);

	public void notEqual(boolean notExpected, boolean actual);
	public void notEqual(String message, boolean notExpected, boolean actual);
}
