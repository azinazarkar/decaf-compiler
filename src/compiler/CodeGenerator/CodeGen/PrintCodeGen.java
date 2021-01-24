package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
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
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Printing " + e.getName() );
		if ( e.getType() == Type.INT ) {
			CodeGen.getInstance().addToText( "li $v0, 1" );
			CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
			CodeGen.getInstance().addToText( "syscall" );
			CodeGen.getInstance().addEmptyLine();
		}
		else if ( e.getType() == Type.BOOL ) {
			CodeGen.getInstance().addToText( "li $v0, 4" );
			if ( (boolean) e.getValue() )
				CodeGen.getInstance().addToText( "la $a0, true_print_string" );
			else
				CodeGen.getInstance().addToText( "la $a0, false_print_string" );
			CodeGen.getInstance().addToText( "syscall" );
			CodeGen.getInstance().addEmptyLine();
		}
		else if ( e.getType() == Type.DOUBLE ) {
			CodeGen.getInstance().addToText( "li $v0, 2" );
			CodeGen.getInstance().addToText( "lwc1 $f12, " + e.getName() );
			CodeGen.getInstance().addToText( "syscall" );
			CodeGen.getInstance().addEmptyLine();
		}
	}

	public void printEnter() {
		CodeGen.getInstance().addToText( "# Printing new line" );
		CodeGen.getInstance().addToText( "li $a0, 0xA" );
		CodeGen.getInstance().addToText( "li $v0, 0xB" );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addEmptyLine();
	}

}
