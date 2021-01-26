package compiler.CodeGenerator.Exceptions;

import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class ArraySizeTypeNotInt extends RuntimeException {

	private Type t;

	public ArraySizeTypeNotInt( Type t ) {
		this.t = t;
	}

	@Override
	public String getMessage() {
		return "Array size can not be a " + t;
	}
}
