package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.CalculationTypeMismatch;
import compiler.CodeGenerator.Exceptions.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class DivideCodeGen {
	private static DivideCodeGen ourInstance = new DivideCodeGen();

	public static DivideCodeGen getInstance() {
		return ourInstance;
	}

	private DivideCodeGen() {
	}

	public void cgen() {
		String answerPart = (String) SemanticStack.getInstance().popDescriptor();
		Descriptor e2 = (Descriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor e1 = (Descriptor) SemanticStack.getInstance().popDescriptor();
		if ( e1.getType() != e2.getType() )
			throw new CalculationTypeMismatch( "*", e1.getType(), e2.getType() );
		if ( e1.getType() == Type.BOOL )
			throw new InvalidOperator("*", Type.BOOL);
		if ( e1.getType() == Type.INT ) {
			CodeGen.getInstance().addToText( "# Dividing " + e1.getName() + " by " + e2.getName() + " and using " + answerPart );
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.INT,
					(int) e1.getValue() / (int) e2.getValue()
			);
			SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), temp.getValue().toString());
			CodeGen.getInstance().addToText("lw " + "$a0, " + e1.getName());
			CodeGen.getInstance().addToText("lw " + "$a1, " + e2.getName());
			CodeGen.getInstance().addToText("div $a0, $a1");
			CodeGen.getInstance().addToText("la " + "$a2, " + temp.getName());
			if ( answerPart.equals( "quotient" ) )
				CodeGen.getInstance().addToText("mflo $t0" );
			else if ( answerPart.equals( "mod" ) )
				CodeGen.getInstance().addToText("mfhi $t0" );
			CodeGen.getInstance().addToText("sw $t0, 0($a2)");
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.DOUBLE ) {
			CodeGen.getInstance().addToText( "# Dividing " + e1.getName() + " by " + e2.getName() );
			float floatAnswer = Float.intBitsToFloat( (int) e1.getValue() ) / Float.intBitsToFloat( (int) e2.getValue() );
			int answer = Float.floatToIntBits( floatAnswer );
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.DOUBLE,
					answer
			);
			SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), temp.getValue().toString());
			CodeGen.getInstance().addToText( "lwc1 $f0, " + e1.getName() );
			CodeGen.getInstance().addToText( "lwc1 $f1, " + e2.getName() );
			CodeGen.getInstance().addToText( "div.s $f2, $f0, $f1" );
			CodeGen.getInstance().addToText( "la $a0, " + temp.getName() );
			CodeGen.getInstance().addToText( "swc1 $f2, 0($a0)" );
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );

		}
	}

}
