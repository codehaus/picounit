package picounit.finder;

import picounit.LifeCycle;

import java.lang.reflect.InvocationTargetException;

public interface LifeCycleInstantiator {
	LifeCycle[] instantiate(Class testClass) throws IllegalArgumentException, InstantiationException,
		IllegalAccessException, ClassNotFoundException, InvocationTargetException;
}