package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class StringInputCodeGen {
	private static StringInputCodeGen ourInstance = new StringInputCodeGen();
	private int STRING_MAX_LEN = 1000;

	public static StringInputCodeGen getInstance() {
		return ourInstance;
	}

	private StringInputCodeGen() {
	}

	public void cgen() {
		Descriptor temp = new Descriptor(
				"_" + IDGenerator.getInstance().getNextID(),
				Type.STRINGLITERAL
		);
		SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
		CodeGen.getInstance().addToData( temp.getName(), Type.getMipsType( temp.getType() ), STRING_MAX_LEN + 1 );
		String loopLabel = "_string_input_clean_loop_" + IDGenerator.getInstance().getNextID();
		String stringInputEnd = "_string_input_end_" + IDGenerator.getInstance().getNextID();
		CodeGen.getInstance().addToText( "# reading a string from input" );
		CodeGen.getInstance().addToText( "li $v0, 8" );
		CodeGen.getInstance().addToText( "la $a0, " + temp.getName() );
		CodeGen.getInstance().addToText( "li $a1, " + STRING_MAX_LEN );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addToText( "xor $a2, $a2, $a2" ); // $a2 is index (first 0)
		CodeGen.getInstance().addToText( "la $a0, " + temp.getName() ); // $a0 has the address of string
		CodeGen.getInstance().addToText( loopLabel + ": ", true );
		CodeGen.getInstance().addToText( "add $a1, $a2, $a0" ); // address of current char of string is in $a1
		CodeGen.getInstance().addToText( "lbu $a3, 0($a1)" );    // current char of string is in $a1
		CodeGen.getInstance().addToText( "beq $a3, $zero, " + stringInputEnd );
		CodeGen.getInstance().addToText( "li $s4, 10" );       // $s4 has \n in it
		CodeGen.getInstance().addToText( "addi $a2, $a2, 1" );
		CodeGen.getInstance().addToText( "bne $a3, $s4, " + loopLabel );
		CodeGen.getInstance().addToText( "sb $zero, 0($a1)" );
		CodeGen.getInstance().addToText( stringInputEnd + ": ", true );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
