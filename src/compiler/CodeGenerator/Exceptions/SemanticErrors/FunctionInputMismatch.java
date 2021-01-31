package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;

public class FunctionInputMismatch extends SemanticError {

	private String functionName;

	public FunctionInputMismatch(String functionName) {
		this.functionName = functionName;
	}

	@Override
	public String getMessage() {
		return "Input mismatch on calling function " + this.functionName;
	}
}
