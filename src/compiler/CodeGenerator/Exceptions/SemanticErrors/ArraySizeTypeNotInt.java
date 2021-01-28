package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class ArraySizeTypeNotInt extends SemanticError {

	private Type t;

	public ArraySizeTypeNotInt( Type t ) {
		this.t = t;
	}

	@Override
	public String getMessage() {
		return "Array size can not be a " + t;
	}
}
