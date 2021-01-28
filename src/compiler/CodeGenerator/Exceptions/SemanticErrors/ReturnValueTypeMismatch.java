package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class ReturnValueTypeMismatch extends SemanticError {

	private Type functionReturnType;
	private Type returnedType;

	public ReturnValueTypeMismatch( Type functionReturnType, Type returnedType ) {
		this.functionReturnType = functionReturnType;
		this.returnedType = returnedType;
	}

	@Override
	public String getMessage() {
		return "You tried to return a " + returnedType + " while function's return type was " + functionReturnType;
	}
}
