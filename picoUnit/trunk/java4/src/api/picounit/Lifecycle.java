/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

/**
 * Interface for registering dependancies for use in tests. Implement this interface and place
 * the implementation in the same package or higher than your tests, your implementation will be
 * instantiated and its setUp methods will be called before the test is run, after the test has
 * run your implementations's tearDown methods will be called. 
 * 
 * @author Stacy Curl
 */
public interface LifeCycle extends Context {
}
