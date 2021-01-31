package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.AssignmentTypeMismatch;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.ArrayDescriptor;
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
		if ( ! ( expr instanceof ArrayDescriptor ) ) {
			if ( lv.getType() != expr.getType() ) {
				if ( !( lv.getType() == Type.STRING && expr.getType() == Type.STRINGLITERAL ) )
					throw new AssignmentTypeMismatch(lv.getType(), expr.getType());
			}
			if ( !lv.isFromArray() ) {
				if (expr.getType() == Type.INT || expr.getType() == Type.BOOL) {
					CodeGen.getInstance().addToText("lw " + "$a0, " + expr.getName());
					if ( expr.isFromArray() )
						CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
					CodeGen.getInstance().addToText("la " + "$a1, " + lv.getName());
					CodeGen.getInstance().addToText("sw $a0, 0($a1)");
				} else if (expr.getType() == Type.DOUBLE) {
					CodeGen.getInstance().addToText("lw $a0, " + expr.getName());
					if ( expr.isFromArray() )
						CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
					CodeGen.getInstance().addToText( "mtc1 $a0, $f0" );
					CodeGen.getInstance().addToText("la $a0, " + lv.getName());
					CodeGen.getInstance().addToText("swc1 $f0, 0($a0)");
				} else if (expr.getType() == Type.STRINGLITERAL) {
					CodeGen.getInstance().addToText("la $s0, " + expr.getName());
					CodeGen.getInstance().addToText("la $s1, " + lv.getName());
					CodeGen.getInstance().addToText("sw $s0, 0($s1)");
				} else if (expr.getType() == Type.STRING) {
					CodeGen.getInstance().addToText("lw $s0, " + expr.getName());
					if ( expr.isFromArray() )
						CodeGen.getInstance().addToText( "lw $s0, 0($s0)" );
					CodeGen.getInstance().addToText("la $s1, " + lv.getName());
					CodeGen.getInstance().addToText("sw $s0, 0($s1)");
				}
			}
			else {
				if (expr.getType() == Type.INT || expr.getType() == Type.BOOL) {
					CodeGen.getInstance().addToText("lw " + "$a0, " + expr.getName());
					if ( expr.isFromArray() )
						CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
					CodeGen.getInstance().addToText("lw " + "$a1, " + lv.getName());
					CodeGen.getInstance().addToText("sw $a0, 0($a1)");
				} else if (expr.getType() == Type.DOUBLE) {
					CodeGen.getInstance().addToText("lw $a0, " + expr.getName());
					if ( expr.isFromArray() )
						CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
					CodeGen.getInstance().addToText( "mtc1 $a0, $f0" );
					CodeGen.getInstance().addToText("lw $a0, " + lv.getName());
					CodeGen.getInstance().addToText("swc1 $f0, 0($a0)");
				} else if (expr.getType() == Type.STRING) {
					CodeGen.getInstance().addToText("lw $s0, " + expr.getName());
					if ( expr.isFromArray() )
						CodeGen.getInstance().addToText( "lw $s0, 0($s0)" );
					CodeGen.getInstance().addToText("lw $s1, " + lv.getName());
					CodeGen.getInstance().addToText("sw $s0, 0($s1)");
				} else if ( expr.getType() == Type.STRINGLITERAL ) {
					CodeGen.getInstance().addToText( "la $s0, " + expr.getName() );
					CodeGen.getInstance().addToText("lw $s1, " + lv.getName());
					CodeGen.getInstance().addToText("sw $s0, 0($s1)");
				}
			}
		}
		else {
			if ( ! (lv instanceof ArrayDescriptor ) )
				throw new AssignmentTypeMismatch( lv.getType(), expr.getType() );
			ArrayDescriptor lv2 = (ArrayDescriptor) lv;
			ArrayDescriptor expr2 = (ArrayDescriptor) expr;
			if ( lv2.getDimensionCount() != expr2.getDimensionCount()
					|| lv2.getSubType() != expr2.getSubType() )
				throw new AssignmentTypeMismatch( Type.ARRAY, Type.ARRAY );
			CodeGen.getInstance().addToText( "lw $s0, " + expr2.getName() );
			if ( lv.isFromArray() )
				CodeGen.getInstance().addToText( "lw $s1, " + lv.getName() );
			else
				CodeGen.getInstance().addToText( "la $s1, " + lv.getName() );
			CodeGen.getInstance().addToText( "sw $s0, 0($s1)" );
		}
		CodeGen.getInstance().addEmptyLine();
	}

}
