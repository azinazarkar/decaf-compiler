package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;

public class NameNotFoundException extends SemanticError {

	private String name;

	public NameNotFoundException(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getMessage() {
		return "variable " + name + " not found in this scope.";
	}
}
