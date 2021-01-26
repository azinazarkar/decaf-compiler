package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.ArraySizeTypeNotInt;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class ValidArraySizeCheckerCodeGen {
	private static ValidArraySizeCheckerCodeGen ourInstance = new ValidArraySizeCheckerCodeGen();

	public static ValidArraySizeCheckerCodeGen getInstance() {
		return ourInstance;
	}

	private ValidArraySizeCheckerCodeGen() {
	}

	public void cgen() {
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		if ( e.getType() != Type.INT )
			throw new ArraySizeTypeNotInt( e.getType() );
		String arraySizeCheckEnd = "_array_size_ok_" + IDGenerator.getInstance().getNextID();
		String arraySizeNegative = "_array_size_negative_" + IDGenerator.getInstance().getNextID();
		CodeGen.getInstance().addToText( "# Checking if the size of array is positive" );
		CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
		if ( e.isFromArray() )
			CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
		CodeGen.getInstance().addToText( "blt $a0, $zero, " + arraySizeNegative );
		CodeGen.getInstance().addToText( "j " + arraySizeCheckEnd );
		CodeGen.getInstance().addToText( arraySizeNegative + ": ", true );
		CodeGen.getInstance().addToText( "li $v0, 4" );
		CodeGen.getInstance().addToText( "la $a0, _array_size_error_message" );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addToText( "li $v0, 10" );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addToText( arraySizeCheckEnd + ": ", true );
		CodeGen.getInstance().addEmptyLine();
	}

}
