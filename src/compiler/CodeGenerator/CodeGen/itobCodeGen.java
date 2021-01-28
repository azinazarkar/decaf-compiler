package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class itobCodeGen {
	private static itobCodeGen ourInstance = new itobCodeGen();

	public static itobCodeGen getInstance() {
		return ourInstance;
	}

	private itobCodeGen() {
	}

	public void cgen() {
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# itob is called for " + e.getName() );
		if ( e.getType() != Type.INT )
			throw new InvalidOperator( "itob", e.getType() );
		Descriptor temp = new Descriptor(
				"_" + IDGenerator.getInstance().getNextID(),
				Type.BOOL
		);
		SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
		CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
		String falseGenerationLabel = "_itob_false_generation_" + IDGenerator.getInstance().getNextID();
		String itobEnd = "_itob_end_" + IDGenerator.getInstance().getNextID();
		CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
		if ( e.isFromArray() )
			CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
		CodeGen.getInstance().addToText( "la $a1, " + temp.getName() );
		CodeGen.getInstance().addToText( "beqz $a0, " + falseGenerationLabel );
		CodeGen.getInstance().addToText( "li $t0, 1" );
		CodeGen.getInstance().addToText( "sw $t0, 0($a1)" );
		CodeGen.getInstance().addToText( "j " + itobEnd );
		CodeGen.getInstance().addToText( falseGenerationLabel + ": ", true );
		CodeGen.getInstance().addToText( "sw $zero, 0($a1)" );
		CodeGen.getInstance().addToText( itobEnd + ": ", true );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
