package picounit;

public interface StringConstraints {
	String aString();
	String aNullString();
	String notANullString();
	String oneOf(String[] oneOf);
	String neitherOf(String[] neitherOf);
	String notEqualTo(String notEqual);
	String equaTolIgnoringCase(String toEqual);
	String aStringContaining(String toContain);
	String aStringMatching(String pattern);
}