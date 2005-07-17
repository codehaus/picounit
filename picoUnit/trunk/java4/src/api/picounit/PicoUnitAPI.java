package picounit;

import junit.framework.Test;

public interface PicoUnitAPI {

	public Test generateJUnitTest();

	public Test generateJUnitTest(JUnitTestGenerator generator);

	public Test generateJUnitTest(Class startingClass);

	public Test generateSingleJUnitTest(Class testClass);

	public PicoUnit setType(Class type);

	public void register(Class type, Object instance);

	public void register(Class type, Class implementation);

	public void register(Class implementation);

}
