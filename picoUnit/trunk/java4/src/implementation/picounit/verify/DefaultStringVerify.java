/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify;

import picounit.StringVerify;
import picounit.Verify;

public class DefaultStringVerify implements StringVerify {
	private final StringUtil stringUtil;
	private final Verify verify;

	public DefaultStringVerify(Verify verify, StringUtil stringUtil) {
		this.verify = verify;
		this.stringUtil = stringUtil;
	}

	public void contains(String searchIn, String searchFor) {
		if (searchIn == null) {
			verify.fail("<searchIn> is null !");
		}
		else if (searchFor == null) {
			verify.fail("<searchFor> is null !");	
		}
		else if (!stringUtil.contains(searchIn, searchFor)) {
			verify.fail("'" + searchIn + "' does not contain '" + searchFor + "'");
		}
	}

	public void doesNotContain(String searchIn, String searchFor) {
		if (searchIn == null) {
			verify.fail("<searchIn> is null !");
		}
		else if (searchFor == null) {
			verify.fail("<searchFor> is null !");	
		}
		else if (stringUtil.contains(searchIn, searchFor)) {
			verify.fail("'" + searchIn + "' contains '" + searchFor + "'");
		}
	}
}
