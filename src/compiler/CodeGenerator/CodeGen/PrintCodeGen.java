package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class PrintCodeGen {
	private static PrintCodeGen ourInstance = new PrintCodeGen();

	public static PrintCodeGen getInstance() {
		return ourInstance;
	}

	private PrintCodeGen() {
	}

	public void cgen() {
		Descriptor e = SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Printing " + e.getName() );
		if ( e.getType() == Type.INT ) {
			CodeGen.getInstance().addToText( "li $v0, 1" );
			CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
			CodeGen.getInstance().addToText( "syscall" );
			CodeGen.getInstance().addToText( "li $a0, 0xA" );
			CodeGen.getInstance().addToText( "li $v0, 0xB" );
			CodeGen.getInstance().addToText( "syscall" );
			CodeGen.getInstance().addToText( "" );
		}
	}

}
