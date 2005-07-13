package picounit;

/** @deprecated use conversational api instead
 * 
 * In the next release (1.3) these methods will be deleted
 */
public interface LegacyVerify {
	void equal(byte expected, byte actual);
	void equal(String message, byte expected, byte actual);
	
	void equal(char expected, char actual);
	void equal(String message, char expected, char actual);

	void equal(double expected, double actual);
	void equal(String message, double expected, double actual);

	void equal(double expected, double actual, double delta);
	void equal(String message, double expected, double actual, double delta);
	
	void equal(float expected, float actual);
	void equal(String message, float expected, float actual);

	void equal(float expected, float actual, float delta);
	void equal(String message, float expected, float actual, float delta);

	void equal(int expected, int actual);
	void equal(String message, int expected, int actual);
	
	void equal(long expected, long actual);
	void equal(String message, long expected, long actual);

	void equal(short expected, short actual);
	void equal(String message, short expected, short actual);
	
	void equal(Object expected, Object actual);
	void equal(String message, Object expected, Object actual);

	void equal(boolean[] expected, boolean[] actual);
	void equal(String message, boolean[] expected, boolean[] actual);
	
	void equal(byte[] expected, byte[] actual);
	void equal(String message, byte[] expected, byte[] actual);

	void equal(char[] expected, char[] actual);
	void equal(String message, char[] expected, char[] actual);
	
	void equal(double[] expected, double[] actual);
	void equal(String message, double[] expected, double[] actual);

	void equal(double[] expected, double[] actual, double delta);
	void equal(String message, double[] expected, double[] actual, double delta);
	
	void equal(float[] expected, float[] actual);
	void equal(String message, float[] expected, float[] actual);

	void equal(float[] expected, float[] actual, float delta);
	void equal(String message, float[] expected, float[] actual, float delta);

	void equal(int[] expected, int[] actual);
	void equal(String message, int[] expected, int[] actual);

	void equal(long[] expected, long[] actual);
	void equal(String message, long[] expected, long[] actual);
	
	void equal(short[] expected, short[] actual);
	void equal(String message, short[] expected, short[] actual);

	void notEqual(byte notExpected, byte actual);
	void notEqual(String message, byte notExpected, byte actual);
	
	void notEqual(char notExpected, char actual);
	void notEqual(String message, char notExpected, char actual);

	void notEqual(double notExpected, double actual);
	void notEqual(String message, double notExpected, double actual);
	
	void notEqual(double notExpected, double actual, double delta);
	void notEqual(String message, double notExpected, double actual, double delta);

	void notEqual(float notExpected, float actual);
	void notEqual(String message, float notExpected, float actual);
	
	void notEqual(float notExpected, float actual, float delta);
	void notEqual(String message, float notExpected, float actual, float delta);

	void notEqual(int notExpected, int actual);
	void notEqual(String message, int notExpected, int actual);
	
	void notEqual(long notExpected, long actual);
	void notEqual(String message, long notExpected, long actual);

	void notEqual(short notExpected, short actual);
	void notEqual(String message, short notExpected, short actual);
	
	void notEqual(Object notExpected, Object actual);
	void notEqual(String message, Object notExpected, Object actual);
	
	void notEqual(boolean[] notExpected, boolean[] actual);
	void notEqual(String message, boolean[] notExpected, boolean[] actual);
	
	void notEqual(byte[] notExpected, byte[] actual);
	void notEqual(String message, byte[] notExpected, byte[] actual);

	void notEqual(char[] notExpected, char[] actual);
	void notEqual(String message, char[] notExpected, char[] actual);
	
	void notEqual(double[] notExpected, double[] actual);
	void notEqual(String message, double[] notExpected, double[] actual);

	void notEqual(double[] notExpected, double[] actual, double delta);
	void notEqual(String message, double[] notExpected, double[] actual, double delta);

	void notEqual(float[] notExpected, float[] actual);
	void notEqual(String message, float[] notExpected, float[] actual);

	void notEqual(float[] notExpected, float[] actual, float delta);
	void notEqual(String message, float[] notExpected, float[] actual, float delta);

	void notEqual(int[] notExpected, int[] actual);
	void notEqual(String message, int[] notExpected, int[] actual);

	void notEqual(long[] notExpected, long[] actual);
	void notEqual(String message, long[] notExpected, long[] actual);

	void notEqual(short[] notExpected, short[] actual);
	void notEqual(String message, short[] notExpected, short[] actual);

	void same(Object expected, Object actual);
	void same(String message, Object expected, Object actual);

	void notSame(Object notExpected, Object actual);
	void notSame(String message, Object notExpected, Object actual);

	void isNull(Object object);
	void isNull(String message, Object object);

	void notNull(Object object);
	void notNull(String message, Object object);

	void instanceOf(Class instanceOf, Object object);
	void instanceOf(String message, Class instanceOf, Object object);

	void notInstanceOf(Class notInstanceOf, Object object);
	void notInstanceOf(String message, Class notInstanceOf, Object object);
}
