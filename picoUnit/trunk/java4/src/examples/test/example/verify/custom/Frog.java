/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import java.awt.Color;

public class Frog {
	private final Color color;

	public Frog(Color color) {
		this.color = color;
	}

	public boolean isColor(Color color) {
		return this.color.equals(color);
	}
	
	public String toString() {
		return "Frog: " + color;
	}
}
