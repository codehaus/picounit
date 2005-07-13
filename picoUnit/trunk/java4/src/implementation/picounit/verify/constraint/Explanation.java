/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.verify.constraint;

public class Explanation<VerifyStage> {
	private final Evaluator evaluator;

	private VerifyStage verifyStage;

	public Explanation(Evaluator evaluator) {
		this.evaluator = evaluator;
	}

	public VerifyStage because(String reason) {
		evaluator.setReason(reason);

		return verifyStage;
	}

	protected void setVerifyStage(VerifyStage verifyStage) {
		this.verifyStage = verifyStage;
	}

	protected final <T> void setValue(T value) {
		evaluator.setValue(value);
	}
}
