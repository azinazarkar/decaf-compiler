package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class btoiCodeGen {
	private static btoiCodeGen ourInstance = new btoiCodeGen();

	public static btoiCodeGen getInstance() {
		return ourInstance;
	}

	private btoiCodeGen() {
	}

	public void cgen() {
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# btoi is called for " + e.getName() );
		if ( e.getType() != Type.BOOL )
			throw new InvalidOperator( "btoi", e.getType() );
		Descriptor temp = new Descriptor(
				"_" + IDGenerator.getInstance().getNextID(),
				Type.INT
		);
		SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
		CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
		String isFalseLabel = "_btoi_is_false_" + IDGenerator.getInstance().getNextID();
		String btoiEndLabel = "_btoi_end_" + IDGenerator.getInstance().getNextID();
		CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
		if ( e.isFromArray() )
			CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
		CodeGen.getInstance().addToText( "la $a1, " + temp.getName() );
		CodeGen.getInstance().addToText( "beqz $a0, " + isFalseLabel );
		CodeGen.getInstance().addToText( "li $t0, 1" );
		CodeGen.getInstance().addToText( "sw $t0, 0($a1)" );
		CodeGen.getInstance().addToText( "j " + btoiEndLabel );
		CodeGen.getInstance().addToText( isFalseLabel + ": ", true );
		CodeGen.getInstance().addToText( "sw $zero, 0($a1)" );
		CodeGen.getInstance().addToText( btoiEndLabel + ": ", true );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
