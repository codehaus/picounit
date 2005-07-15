/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package example.verify.custom;

import picounit.verify.BooleanArrayConstraints;
import picounit.verify.BooleanConstraints;
import picounit.verify.ByteArrayConstraints;
import picounit.verify.ByteConstraints;
import picounit.verify.CharacterArrayConstraints;
import picounit.verify.CharacterConstraints;
import picounit.verify.DoubleArrayConstraints;
import picounit.verify.FileConstraints;
import picounit.verify.FloatArrayConstraints;
import picounit.verify.FloatConstraints;
import picounit.verify.IntegerArrayConstraints;
import picounit.verify.IntegerConstraints;
import picounit.verify.LongArrayConstraints;
import picounit.verify.LongConstraints;
import picounit.verify.ShortArrayConstraints;
import picounit.verify.ShortConstraints;
import picounit.verify.StringArrayConstraints;
import picounit.verify.TypedArrayConstraints;
import picounit.verify.TypedConstraints;
import picounit.verify.constraint.Evaluator;
import picounit.verify.constraint.Explanation;

import java.io.File;

public class FrogVerify extends Explanation implements FrogVerifyStage {
	private BooleanConstraints booleanConstraints;
	private final ByteConstraints byteConstraints;
	private final CharacterConstraints charConstraints;
	private final CustomDoubleConstraints customDoubleConstraints;
	private final FloatConstraints floatConstraints;
	private final IntegerConstraints intConstraints;
	private final LongConstraints longConstraints;
	private final ShortConstraints shortConstraints;
	
	private final FileConstraints fileConstraints;
	private final CustomStringConstraints stringConstraints;
	private final TypedConstraints typedConstraints;

	private final FrogConstraints frogConstraints;
	
	private final BooleanArrayConstraints booleanArrayConstraints;
	private final ByteArrayConstraints byteArrayConstraints;
	private final CharacterArrayConstraints characterArrayConstraints;
	private final DoubleArrayConstraints doubleArrayConstraints;
	private final FloatArrayConstraints floatArrayConstraints;
	private final IntegerArrayConstraints integerArrayConstraints;
	private final LongArrayConstraints longArrayConstraints;
	private final ShortArrayConstraints shortArrayConstraints;
	
	private final StringArrayConstraints stringArrayConstraints;
	private final TypedArrayConstraints typedArrayConstraints;

	public FrogVerify(Evaluator evaluator, BooleanConstraints booleanConstraints,
		ByteConstraints byteConstraints,
		CharacterConstraints charConstraints, CustomDoubleConstraints customDoubleConstraints,
		FloatConstraints floatConstraints, IntegerConstraints intConstraints,
		LongConstraints longConstraints, ShortConstraints shortConstraints,
		FileConstraints fileConstraints, CustomStringConstraints stringConstraints,
		TypedConstraints typedConstraints,
		BooleanArrayConstraints booleanArrayConstraints,
		ByteArrayConstraints byteArrayConstraints,
		CharacterArrayConstraints characterArrayConstraints,
		DoubleArrayConstraints doubleArrayConstraints,
		FloatArrayConstraints floatArrayConstraints,
		IntegerArrayConstraints integerArrayConstraints,
		LongArrayConstraints longArrayConstraints,
		ShortArrayConstraints shortArrayConstraints,
		StringArrayConstraints stringArrayConstraints,
		TypedArrayConstraints typedArrayConstraints) {

		super(evaluator);

		this.booleanConstraints = booleanConstraints;
		this.byteConstraints = byteConstraints;
		this.charConstraints = charConstraints;
		this.customDoubleConstraints = customDoubleConstraints;
		this.floatConstraints = floatConstraints;
		this.intConstraints = intConstraints;
		this.longConstraints = longConstraints;
		this.shortConstraints = shortConstraints;

		this.fileConstraints = fileConstraints;
		this.stringConstraints = stringConstraints;
		this.typedConstraints = typedConstraints;		

		this.frogConstraints = new FrogConstraints(evaluator);
		
		this.booleanArrayConstraints = booleanArrayConstraints;
		this.byteArrayConstraints = byteArrayConstraints;
		this.characterArrayConstraints = characterArrayConstraints;
		this.doubleArrayConstraints = doubleArrayConstraints;
		this.floatArrayConstraints = floatArrayConstraints;
		this.integerArrayConstraints = integerArrayConstraints;
		this.longArrayConstraints = longArrayConstraints;
		this.shortArrayConstraints = shortArrayConstraints;

		this.stringArrayConstraints = stringArrayConstraints;
		this.typedArrayConstraints = typedArrayConstraints;
	}

	public FrogVerifyStage because(String reason) {
		setReason(reason);

		return this;
	}
	
	public BooleanConstraints thatBoolean(boolean value) {
		setValue(value);
		
		return booleanConstraints;
	}

	public ByteConstraints that(byte value) {
		setValue(value);
		
		return byteConstraints;
	}
	
	public CharacterConstraints that(char value) {
		setValue(value);
		
		return charConstraints;
	}
	
	public CustomDoubleConstraints that(double value) {
		setValue(value);
		
		return customDoubleConstraints;
	}
	
	public FloatConstraints that(float value) {
		setValue(value);
		
		return floatConstraints;
	}
	
	public IntegerConstraints that(int value) {
		setValue(value);
		
		return intConstraints;
	}
	
	public LongConstraints that(long value) {
		setValue(value);
		
		return longConstraints;
	}
	
	public ShortConstraints that(short value) {
		setValue(value);
		
		return shortConstraints;
	}
	
	public FileConstraints that(File value) {
		setValue(value);

		return fileConstraints;
	}
	
	public CustomStringConstraints that(String value) {
		setValue(value);
		
		return stringConstraints;
	}
	
	@SuppressWarnings("unchecked")
	public <T> TypedConstraints<T> that(T type) {
		setValue(type);

		return typedConstraints;
	}
	
	public BooleanArrayConstraints that(boolean ... value) {
		setValue(value);
		
		return booleanArrayConstraints;
	}
	
	public ByteArrayConstraints that(byte ... value) {
		setValue(value);

		return byteArrayConstraints;
	}
	
	public CharacterArrayConstraints that(char ... value) {
		setValue(value);

		return characterArrayConstraints;
	}
	
	public DoubleArrayConstraints that(double ... value) {
		setValue(value);

		return doubleArrayConstraints;
	}
	
	public FloatArrayConstraints that(float ... value) {
		setValue(value);

		return floatArrayConstraints;
	}
	
	public IntegerArrayConstraints that(int ... value) {
		setValue(value);

		return integerArrayConstraints;
	}
	
	public LongArrayConstraints that(long ... value) {
		setValue(value);

		return longArrayConstraints;
	}
	
	public ShortArrayConstraints that(short ... value) {
		setValue(value);
		
		return shortArrayConstraints;
	}
	
	public StringArrayConstraints that(String ... value) {
		setValue(value);

		return stringArrayConstraints;
	}

	@SuppressWarnings("unchecked")
	public <T> TypedArrayConstraints<T> that(T ... value) {
		setValue(value);
		
		return typedArrayConstraints;
	}

	public FrogConstraints that(Frog frog) {
		setValue(frog);

		return frogConstraints;
	}
}