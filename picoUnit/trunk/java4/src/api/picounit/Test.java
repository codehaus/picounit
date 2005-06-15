/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;

/**
 * Interface to be used as a marker for your test classes.
 * 
 * The PicoUnit test lifecycle is as follows:
 * 
 * For each test method in the Test implementation
 * 
 * - Apply all the relevant LifeCycle classes
 * - Instantiate the test, passing in dependancies declared as parameters.
 * - invoke the setUp method, passing in dependancies declared as parameters
 * - invoke the mock method, passing in mocked dependancies declared as parameters
 * - invoke the test method
 * - invoke the tearDown method, passing in the dependancies declared as parameters.
 * 
 * @author Stacy Curl
 */
public interface Test {
}
