package picounit.finder;

import picounit.Lifecycle;

import java.lang.reflect.InvocationTargetException;

public interface LifeCycleInstantiator {
	Lifecycle[] instantiate(Class testClass) throws IllegalArgumentException, InstantiationException,
		IllegalAccessException, ClassNotFoundException, InvocationTargetException;
}