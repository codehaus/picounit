/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.util;

import picounit.PicoUnitException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {
	public byte[] readContents(File file) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);

			byte[] data = new byte[(int) file.length()];

			fileInputStream.read(data, 0, data.length);

			fileInputStream.close();

			return data;
		}
		catch (IOException ioException) {
			throw new PicoUnitException(ioException);
		}
	}
}
