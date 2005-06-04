/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.util;

import java.util.HashSet;
import java.util.Set;

public class Console {
	private static final Set<String> printCache = new HashSet<String>();
	private static final Set<String> printErrorCache = new HashSet<String>();

	public void println(String toPrint) {
		if (!cacheContains(printCache, toPrint)) {
//			System.out.println(toPrint);
		}
	}

	public void printErrorLn(String toPrint) {
		if (!cacheContains(printErrorCache, toPrint)) {
//			System.err.println(toPrint);
		}
	}

	public void ignore(String toIgnore) {
	}

	private static boolean cacheContains(Set<String> cache, String item) {
		boolean contains = cache.contains(item);
		
		cache.add(item);
		
		return contains;
	}
}
