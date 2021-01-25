package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.AssignmentTypeMismatch;
import compiler.CodeGenerator.Exceptions.InvalidOperator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class AssignmentCodeGen{
	private static AssignmentCodeGen ourInstance = new AssignmentCodeGen();

	public static AssignmentCodeGen getInstance() {
		return ourInstance;
	}

	private AssignmentCodeGen() {
	}

	public void cgen() {
		Descriptor lv = (Descriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor expr = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Assigning " + expr.getName() + " to " + lv.getName() );
		if ( lv.getType() != expr.getType() && ( lv.getType() != Type.STRING && expr.getType() != Type.STRINGLITERAL ) )
			throw new AssignmentTypeMismatch( lv.getType(), expr.getType() );
		if ( expr.getType() == Type.INT || expr.getType() == Type.BOOL ) {
			CodeGen.getInstance().addToText( "lw " + "$a0, " + expr.getName() );
			CodeGen.getInstance().addToText( "la " + "$a1, " + lv.getName() );
			CodeGen.getInstance().addToText( "sw $a0, 0($a1)" );
		}
		else if ( expr.getType() == Type.DOUBLE ) {
			CodeGen.getInstance().addToText( "lwc1 $f0, " + expr.getName() );
			CodeGen.getInstance().addToText( "la $a0, " + lv.getName() );
			CodeGen.getInstance().addToText( "swc1 $f0, 0($a0)" );
		}
		else if ( expr.getType() == Type.STRINGLITERAL ) {
			CodeGen.getInstance().addToText( "la $s0, " + expr.getName() );
			CodeGen.getInstance().addToText( "la $s1, " + lv.getName() );
			CodeGen.getInstance().addToText( "sw $s0, 0($s1)" );
		}
		else if ( expr.getType() == Type.STRING ) {
			CodeGen.getInstance().addToText( "lw $s0, " + expr.getName() );
			CodeGen.getInstance().addToText( "la $s1, " + lv.getName() );
			CodeGen.getInstance().addToText( "sw $s0, 0($s1)" );
		}
		else
			System.out.println( "I DONT KNOW" );
		CodeGen.getInstance().addEmptyLine();
	}

}
