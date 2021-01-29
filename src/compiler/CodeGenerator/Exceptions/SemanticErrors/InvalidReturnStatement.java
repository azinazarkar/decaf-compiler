package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;

public class InvalidReturnStatement extends SemanticError {

	@Override
	public String getMessage() {
		return "You can't have a return statement here!";
	}
}
