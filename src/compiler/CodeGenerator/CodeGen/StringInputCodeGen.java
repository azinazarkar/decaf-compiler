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
		CodeGen.getInstance().addToText( "# reading a string from input" );
		CodeGen.getInstance().addToText( "li $v0, 8" );
		CodeGen.getInstance().addToText( "la $a0, " + temp.getName() );
		CodeGen.getInstance().addToText( "li $a1, " + STRING_MAX_LEN );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
