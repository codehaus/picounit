package picounit;

public interface DoubleConstraints {
	double aDouble();
	double oneOf(double[] oneOf);
	double neitherOf(double[] neitherOf);
	double notEqualTo(double notEqual);
	double lessThan(double upperLimit);
	double lessThanOrEqualTo(double upperLimit);
	double greaterThan(double lowerLimit);
	double greaterThanOrEqualTo(double lowerLimit);
	double between(double lowerLimit, double upperLimit);
	double notBetween(double lowerLimit, double upperLimit);
}