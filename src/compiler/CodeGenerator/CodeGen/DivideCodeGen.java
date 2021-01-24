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
		CodeGen.getInstance().addToText( "# Dividing " + e1.getName() + " by " + e2.getName() + " and using " + answerPart );
		if ( e1.getType() != e2.getType() )
			throw new CalculationTypeMismatch( "*", e1.getType(), e2.getType() );
		if ( e1.getType() == Type.BOOL )
			throw new InvalidOperator("*", Type.BOOL);
		if ( e1.getType() == Type.INT ) {
			Descriptor temp = new Descriptor(
					IDGenerator.getInstance().getNextID(),
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
	}

}
