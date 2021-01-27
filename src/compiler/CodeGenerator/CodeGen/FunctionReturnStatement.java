package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.ReturnValueTypeMismatch;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.FunctionDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class FunctionReturnStatement {
	private static FunctionReturnStatement ourInstance = new FunctionReturnStatement();

	public static FunctionReturnStatement getInstance() {
		return ourInstance;
	}

	private FunctionReturnStatement() {
	}

	public void cgen() {
		FunctionDescriptor funcDscp = (FunctionDescriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# this is a return statement" );
		if ( e == null ) {
			if ( funcDscp.getType() != Type.VOID )
				throw new ReturnValueTypeMismatch( funcDscp.getType(), Type.VOID );
		}
		else {
			if ( e.getType() != funcDscp.getType() )
				throw new ReturnValueTypeMismatch( funcDscp.getType(), e.getType() );
			else {
				CodeGen.getInstance().addToText( "lw $v0, " + e.getName() );
			}
		}
		CodeGen.getInstance().addToText( "jr $ra" );
		CodeGen.getInstance().addEmptyLine();
	}

}
