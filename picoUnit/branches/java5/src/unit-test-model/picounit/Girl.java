/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit;


public class Girl {
	private final Boy boy;

	public Girl(Boy boy) {
		this.boy = boy;
	}
	
	public void kiss() {
		boy.money(125);
		boy.kiss();
	}
	
	public String speak(String words) {
		return boy.listen(words);
	}
}