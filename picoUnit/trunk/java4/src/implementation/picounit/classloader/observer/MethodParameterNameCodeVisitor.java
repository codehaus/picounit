/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.classloader.observer;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.CodeVisitor;
import org.objectweb.asm.Label;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParameterNameCodeVisitor implements CodeVisitor {
	public static final String PRIMATIVE_TYPE_REGEX = "\\[*[ZBCDFIJSV]";
	public static final String OBJECT_TYPE_REGEX = "\\[*L[^;]*;";
	
	private int numParameters = 0;
	private List parameters = new LinkedList();
	private String methodName;
	private String className;
	private final MethodParameterListener methodParameterListener;
	
	public MethodParameterNameCodeVisitor(MethodParameterListener methodParameterListener) {
		this.methodParameterListener = methodParameterListener;
	}
	
	public CodeVisitor visit(String className, String methodName, String description) {
		this.className = className;
		this.methodName = methodName;
		this.numParameters = getNumParameters(description);
		this.parameters.clear();

		return this;
	}

	private int getNumParameters(String signature) {
		String parametersString = signature.substring(1, signature.indexOf(')'));
		int numParameters = 0;
		
		while (parametersString.length() > 0) {
			Pattern primativeTypePattern = Pattern.compile("^(" + PRIMATIVE_TYPE_REGEX + ")(.*)");
			Pattern objectTypePattern = Pattern.compile("^(" + OBJECT_TYPE_REGEX + ")(.*)");
	
			String nextMatch = "";
			
			Matcher primativeMatcher = primativeTypePattern.matcher(parametersString);
			if (primativeMatcher.matches()) {
				nextMatch = primativeMatcher.group(1);
			}
	
			Matcher objectMatcher = objectTypePattern.matcher(parametersString);
			if (objectMatcher.matches()) {
				nextMatch = objectMatcher.group(1);
			}
			
			numParameters++;
	
			parametersString = parametersString.substring(nextMatch.length());
		}

		return numParameters;
	}

	public void visitInsn(int opcode) {
	}

	public void visitIntInsn(int opcode, int operand) {
	}

	public void visitVarInsn(int opcode, int var) {
	}

	public void visitTypeInsn(int opcode, String desc) {
	}

	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
	}

	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
	}

	public void visitJumpInsn(int opcode, Label label) {
	}

	public void visitLabel(Label label) {
	}

	public void visitLdcInsn(Object cst) {
	}

	public void visitIincInsn(int var, int increment) {
	}

	public void visitTableSwitchInsn(int min, int max, Label dflt, Label[] labels) {
	}

	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
	}

	public void visitMultiANewArrayInsn(String desc, int dims) {
	}

	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
	}

	public void visitMaxs(int maxStack, int maxLocals) {
	}

	public void visitLocalVariable(String name, String desc, Label start, Label end, int index) {
		if ("this".equals(name)) {
			return;
		}

		if (numParameters > 0) {
			addParameter(name);
			numParameters--;
			if (numParameters == 0) {
				registerMethod();
			}
		}
	}

	private void addParameter(String name) {
		parameters.add(name);
	}

	private void registerMethod() {
		methodParameterListener.methodEvent(className, methodName,
			(String[]) parameters.toArray(new String[0]));
	}

	public void visitLineNumber(int line, Label start) {
	}

	public void visitAttribute(Attribute attr) {
	}
}
