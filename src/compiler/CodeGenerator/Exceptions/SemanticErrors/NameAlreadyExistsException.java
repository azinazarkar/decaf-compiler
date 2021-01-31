package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;

public class NameAlreadyExistsException extends SemanticError {

	private String name;

	public NameAlreadyExistsException(String name ) {
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
		return "variable " + name + " already exists in this scope!";
	}
}
