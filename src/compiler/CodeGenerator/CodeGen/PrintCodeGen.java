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
			if ( e.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
			CodeGen.getInstance().addToText( "syscall" );
		}
		else if ( e.getType() == Type.BOOL ) {
			String printFalseLabel = "_print_false_" + IDGenerator.getInstance().getNextID();
			String printTrueLabel = "_print_true_" + IDGenerator.getInstance().getNextID();
			String printEndLabel = "_print_bool_end_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "lw $s0, " + e.getName() );
			if ( e.isFromArray() )
				CodeGen.getInstance().addToText( "lw $s0, 0($s0)" );
			CodeGen.getInstance().addToText( "beq $s0, $zero, " + printFalseLabel );
			CodeGen.getInstance().addToText( "bne $s0, $zero, " + printTrueLabel );
			CodeGen.getInstance().addToText( printFalseLabel + ":", true );
			CodeGen.getInstance().addToText( "sub $sp, $sp, 4" );
			CodeGen.getInstance().addToText( "sw $ra, 0($sp)" );
			CodeGen.getInstance().addToText( "jal _print_false" );
			CodeGen.getInstance().addToText( "lw $ra, 0($sp)" );
			CodeGen.getInstance().addToText( "addi $sp, $sp, 4" );
			CodeGen.getInstance().addToText( "j " + printEndLabel );
			CodeGen.getInstance().addToText( printTrueLabel + ":", true );
			CodeGen.getInstance().addToText( "sub $sp, $sp, 4" );
			CodeGen.getInstance().addToText( "sw $ra, 0($sp)" );
			CodeGen.getInstance().addToText( "jal _print_true" );
			CodeGen.getInstance().addToText( "lw $ra, 0($sp)" );
			CodeGen.getInstance().addToText( "addi $sp, $sp, 4" );
			CodeGen.getInstance().addToText( "j " + printEndLabel );
			CodeGen.getInstance().addToText( printEndLabel + ": ", true );

		}
		else if ( e.getType() == Type.DOUBLE ) {
			CodeGen.getInstance().addToText( "li $v0, 2" );
			CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
			if ( e.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
			CodeGen.getInstance().addToText( "mtc1 $a0, $f12" );
			CodeGen.getInstance().addToText( "syscall" );
		}
		else if ( e.getType() == Type.STRING ) {
			CodeGen.getInstance().addToText( "li $v0, 4" );
			CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
			if ( e.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
			CodeGen.getInstance().addToText( "syscall" );
		}
		else if ( e.getType() == Type.STRINGLITERAL ) {
			CodeGen.getInstance().addToText( "li $v0, 4" );
			CodeGen.getInstance().addToText( "la $a0, " + e.getName() );
			CodeGen.getInstance().addToText( "syscall" );
		}
		CodeGen.getInstance().addEmptyLine();
	}

	public void printEnter() {
		CodeGen.getInstance().addToText( "# Printing new line" );
		CodeGen.getInstance().addToText( "li $a0, 0xA" );
		CodeGen.getInstance().addToText( "li $v0, 0xB" );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addEmptyLine();
	}

}
