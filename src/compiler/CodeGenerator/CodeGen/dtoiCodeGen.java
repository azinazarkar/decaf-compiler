package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class dtoiCodeGen {
	private static dtoiCodeGen ourInstance = new dtoiCodeGen();

	public static dtoiCodeGen getInstance() {
		return ourInstance;
	}

	private dtoiCodeGen() {
	}

	public void cgen() {
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# dtoi is called for " + e.getName() );
		if ( e.getType() != Type.DOUBLE )
			throw new InvalidOperator( "dtoi", e.getType() );
		Descriptor temp = new Descriptor(
				"_" + IDGenerator.getInstance().getNextID(),
				Type.INT
		);
		SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
		CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
		String lessthan = "_dtoi_isless_" + IDGenerator.getInstance().getNextID();
		String endLabel = "_dtoi_end_" + IDGenerator.getInstance().getNextID();
		CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
		if ( e.isFromArray() )
			CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
		CodeGen.getInstance().addToText( "mtc1 $a0, $f0" );
		CodeGen.getInstance().addToText( "lwc1 $f1, _dtoi_border_value" );
		CodeGen.getInstance().addToText( "c.lt.s $f0, $f1" );
		CodeGen.getInstance().addToText( "abs.s $f0, $f0" );
		CodeGen.getInstance().addToText( "round.w.s $f0, $f0" );
		CodeGen.getInstance().addToText( "mfc1 $s0, $f0" );
		CodeGen.getInstance().addToText( "bc1t " + lessthan );
		CodeGen.getInstance().addToText( "j " + endLabel );
		CodeGen.getInstance().addToText( lessthan + ": ", true );
		CodeGen.getInstance().addToText( "neg $s0, $s0" );
		CodeGen.getInstance().addToText( endLabel + ": ", true );
		CodeGen.getInstance().addToText( "la $s1, " + temp.getName() );
		CodeGen.getInstance().addToText( "sw $s0, 0($s1)" );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
