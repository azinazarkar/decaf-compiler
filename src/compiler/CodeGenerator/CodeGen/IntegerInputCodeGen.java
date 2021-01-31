package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class IntegerInputCodeGen {
	private static IntegerInputCodeGen ourInstance = new IntegerInputCodeGen();

	public static IntegerInputCodeGen getInstance() {
		return ourInstance;
	}

	private IntegerInputCodeGen() {
	}

	public void cgen() {
		Descriptor temp = new Descriptor(
				"_" + IDGenerator.getInstance().getNextID(),
				Type.INT
		);
		SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
		CodeGen.getInstance().addToData( temp.getName(), Type.getMipsType( temp.getType() ), 0 );
		CodeGen.getInstance().addToText( "# Reading an integer" );
		CodeGen.getInstance().addToText( "li $v0, 5" );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addToText( "la $s0, " + temp.getName() );
		CodeGen.getInstance().addToText( "sw $v0, 0($s0)" );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
