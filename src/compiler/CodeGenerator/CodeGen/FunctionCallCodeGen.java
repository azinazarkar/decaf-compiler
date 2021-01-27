package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.FunctionDescriptor;

public class FunctionCallCodeGen {
	private static FunctionCallCodeGen ourInstance = new FunctionCallCodeGen();

	public static FunctionCallCodeGen getInstance() {
		return ourInstance;
	}

	private FunctionCallCodeGen() {
	}

	public void cgen() {
		FunctionDescriptor funcDscp = (FunctionDescriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# calling " + funcDscp.getName() );
		CodeGen.getInstance().addToText( "sub $sp, $sp, 4" );
		CodeGen.getInstance().addToText( "sw $ra, 0($sp)" );
		CodeGen.getInstance().addToText( "jal " + funcDscp.getName() );
		CodeGen.getInstance().addEmptyLine();
	}

}
