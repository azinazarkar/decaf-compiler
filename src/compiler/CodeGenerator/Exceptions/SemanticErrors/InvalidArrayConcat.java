package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;
import compiler.CodeGenerator.SymbolTable.Utility.ArrayDescriptor;

public class InvalidArrayConcat extends SemanticError {

	private ArrayDescriptor e1, e2;

	public InvalidArrayConcat( ArrayDescriptor e1, ArrayDescriptor e2 ) {
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public String getMessage() {
		return "Invalid array concat. First array has subtype " + e1.getSubType() + " and dimension " + e1.getDimensionCount()
				+ " and second array has subtype " + e2.getSubType() + " and dimension " + e2.getDimensionCount();
	}
}
