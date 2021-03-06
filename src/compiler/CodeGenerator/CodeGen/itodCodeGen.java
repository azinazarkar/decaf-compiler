package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class itodCodeGen {
	private static itodCodeGen ourInstance = new itodCodeGen();

	public static itodCodeGen getInstance() {
		return ourInstance;
	}

	private itodCodeGen() {
	}

	public void cgen() {
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# itod is called for " + e.getName() );
		if ( e.getType() != Type.INT )
			throw new InvalidOperator( "itod", e.getType() );
		Descriptor temp = new Descriptor(
				"_" + IDGenerator.getInstance().getNextID(),
				Type.DOUBLE
		);
		SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
		CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
		CodeGen.getInstance().addToText( "lw $s0, " + e.getName() );
		if ( e.isFromArray() )
			CodeGen.getInstance().addToText( "lw $s0, 0($s0)" );
		CodeGen.getInstance().addToText( "mtc1 $s0, $f0" );
		CodeGen.getInstance().addToText( "cvt.s.w $f0, $f0" );
		CodeGen.getInstance().addToText( "la $s1, " + temp.getName() );
		CodeGen.getInstance().addToText( "swc1 $f0, 0($s1)" );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
