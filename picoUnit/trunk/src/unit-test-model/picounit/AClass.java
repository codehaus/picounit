/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;


public class AClass {
	public interface Public {}
	public interface Package {}
	public interface Protected {}
	public interface Private {}
	
	public AClass() {}
	public AClass(Public var) {}
	AClass(Package var) {}
	protected AClass(Protected var) {}
	private AClass(Private var) {}
	
	public AClass(Interface var) {}
	public AClass(int var) {}
	public AClass(String var) {}
	public AClass(Class var) {}
	public AClass(AClass var) {}
}