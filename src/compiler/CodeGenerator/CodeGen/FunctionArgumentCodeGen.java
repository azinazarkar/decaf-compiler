package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class FunctionArgumentCodeGen {
	private static FunctionArgumentCodeGen ourInstance = new FunctionArgumentCodeGen();

	public static FunctionArgumentCodeGen getInstance() {
		return ourInstance;
	}

	private FunctionArgumentCodeGen() {
	}

	public void cgen() {
		Descriptor formalDescriptor = (Descriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor actualDescriptor = (Descriptor) SemanticStack.getInstance().popDescriptor();
		int actualIndex = (int) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Assigning actual " + actualDescriptor.getName() + " to formal " + formalDescriptor.getName() );
		CodeGen.getInstance().addToText( "lw $a0, " + actualDescriptor.getName() );
		if ( actualDescriptor.isFromArray() )
			CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
		CodeGen.getInstance().addToText( "la $a1, " + formalDescriptor.getName() );
		CodeGen.getInstance().addToText( "sw $a0, 0($a1)" );
		CodeGen.getInstance().addEmptyLine();
	}

}
