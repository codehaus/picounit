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

public class NullCode implements Code {
	public static final Code INSTANCE = new NullCode();

	private NullCode() {
	}

	public byte[] findClass(String className) {
		return new byte[0];
	}

	public void visit(CodeVisitor codeVisitor) {
	}

	public boolean equals(Code code) {
		return this == code;
	}

	public InputStream openFile(String fileName) throws IOException {
		return null;
	}
	
	@Override
	public String toString() {
		return "NullCode";
	}
}
