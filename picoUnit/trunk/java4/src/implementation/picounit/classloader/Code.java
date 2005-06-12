/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader;

import java.io.IOException;
import java.io.InputStream;

public interface Code {
	byte[] findClass(String className);

	void visit(CodeVisitor codeVisitor);

	boolean equals(Code code);

	InputStream openFile(String fileName) throws IOException;
}
