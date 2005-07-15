package picounit.verify;

public interface StringArrayConstraintsCaseKnown {
	void isEqualTo(String ... equalTo);
	void isDifferentTo(String ... differentTo);

	void contains(String contains);
	void doesNotContain(String doesNotContain);
}
