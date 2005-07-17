package picounit;

import junit.framework.Test;

public interface PicoUnitAPI {
	Test generateJUnitTest();
	Test generateJUnitTest(JUnitTestGenerator generator);
	Test generateJUnitTest(Class startingClass);
	Test generateSingleJUnitTest(Class testClass);
	PicoUnit setType(Class type);
	void register(Class type, Object instance);
	void register(Class type, Class implementation);
	void register(Class implementation);
}
