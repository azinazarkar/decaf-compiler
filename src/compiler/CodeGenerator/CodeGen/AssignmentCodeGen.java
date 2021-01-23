package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.AssignmentTypeMismatch;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class AssignmentCodeGen{
	private static AssignmentCodeGen ourInstance = new AssignmentCodeGen();

	public static AssignmentCodeGen getInstance() {
		return ourInstance;
	}

	private AssignmentCodeGen() {
	}

	public void cgen() {
		Descriptor lv = SemanticStack.getInstance().popDescriptor();
		Descriptor expr = SemanticStack.getInstance().popDescriptor();
		if ( lv.getType() != expr.getType() )
			throw new AssignmentTypeMismatch( lv.getType(), expr.getType() );
		lv.setValue( expr.getValue() );
		if ( expr.getType().toString().equals( "INT" ) )
			CodeGen.getInstance().addToText( "lw " + "$a0, " + expr.getName() );
		else
			System.out.println( "I DONT KNOW" );
		CodeGen.getInstance().addToText( "la " + "$a1, " + lv.getName() );
		CodeGen.getInstance().addToText( "move $a2, $a0" );
		CodeGen.getInstance().addToText( "sw $a2, 0($a1)" );
	}

}
