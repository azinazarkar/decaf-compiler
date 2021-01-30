package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class InvalidArrayType extends SemanticError {

	private Type type;

	public InvalidArrayType( Type type ) {
		this.type = type;
	}

	@Override
	public String getMessage() {
		return "Cannot create an array with type " + type.name() + "!";
	}
}
