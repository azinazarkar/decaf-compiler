package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.FunctionDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class FunctionValueRetrieveCodeGen {
	private static FunctionValueRetrieveCodeGen ourInstance = new FunctionValueRetrieveCodeGen();

	public static FunctionValueRetrieveCodeGen getInstance() {
		return ourInstance;
	}

	private FunctionValueRetrieveCodeGen() {
	}

	public void cgen() {
		FunctionDescriptor funcDscp = (FunctionDescriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Retrieving value of function " + funcDscp.getFunctionName() + " call" );
		Descriptor temp = new Descriptor(
				"_" + IDGenerator.getInstance().getNextID(),
				funcDscp.getType()
		);
		SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
		CodeGen.getInstance().addToData( temp.getName(), Type.getMipsType(temp.getType()), 0 );
		CodeGen.getInstance().addToText( "sw $v0, " + temp.getName() );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
