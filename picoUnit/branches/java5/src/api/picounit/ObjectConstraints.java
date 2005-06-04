package picounit;

public interface ObjectConstraints {
	Object anObject();
	Object aNullObject();
	Object notANullObject();
	Object oneOf(Object ... oneOf);
	Object neitherOf(Object ... neitherOf);
	Object notEqual(Object notEqual);
}