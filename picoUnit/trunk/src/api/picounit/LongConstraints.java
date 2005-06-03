package picounit;

public interface LongConstraints {
	long aLong();
	long oneOf(long[] oneOf);
	long neitherOf(long[] neitherOf);
	long notEqualTo(long notEqual);
	long lessThan(long upperLimit);
	long lessThanOrEqualTo(long upperLimit);
	long greaterThan(long lowerLimit);
	long greaterThanOrEqualTo(long lowerLimit);
	long between(long lowerLimit, long upperLimit);
	long notBetween(long lowerLimit, long upperLimit);
}