/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import java.util.Iterator;

public class CodeIterator {
	private final Iterator<Code> iterator;

	public CodeIterator(Iterator<Code> iterator) {
		this.iterator = iterator;
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public Code next() {
		if (iterator.hasNext()) {
			return iterator.next();
		}
		else {
			return NullCode.INSTANCE;
		}
	}
}
