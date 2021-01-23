package compiler.CodeGenerator.Exceptions;

import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class AssignmentTypeMismatch extends RuntimeException {

	private Type t1, t2;

	public AssignmentTypeMismatch( Type t1, Type t2 ) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public String getMessage() {
		return "Can't assign " + t2.name() + " to " + t1.name() + "!";
	}
}
