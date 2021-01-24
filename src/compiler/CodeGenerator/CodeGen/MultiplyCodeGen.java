package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.CalculationTypeMismatch;
import compiler.CodeGenerator.Exceptions.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class MultiplyCodeGen {
	private static MultiplyCodeGen ourInstance = new MultiplyCodeGen();

	public static MultiplyCodeGen getInstance() {
		return ourInstance;
	}

	private MultiplyCodeGen() {
	}

	public void cgen() {
		Descriptor e2 = (Descriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor e1 = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Multiplying " + e1.getName() + " by " + e2.getName() );
		if ( e1.getType() != e2.getType() )
			throw new CalculationTypeMismatch( "*", e1.getType(), e2.getType() );
		if ( e1.getType() == Type.BOOL )
			throw new InvalidOperator("*", Type.BOOL);
		if ( e1.getType() == Type.INT ) {
			long answer = Long.valueOf( (int) e1.getValue() ) * Long.valueOf( (int) e2.getValue() );
			int answer32Bit = (int) ( (answer << 32) >>> 32 );
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.INT,
					answer32Bit
			);
			SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), temp.getValue().toString());
			CodeGen.getInstance().addToText("lw " + "$a0, " + e1.getName());
			CodeGen.getInstance().addToText("lw " + "$a1, " + e2.getName());
			CodeGen.getInstance().addToText("mult $a0, $a1");
			CodeGen.getInstance().addToText("la " + "$a2, " + temp.getName());
			CodeGen.getInstance().addToText("mflo $t0" );
			CodeGen.getInstance().addToText("sw $t0, 0($a2)");
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.DOUBLE ) {
			double doubleAnswer = Float.intBitsToFloat( (int) e1.getValue() ) * Float.intBitsToFloat( (int) e2.getValue() );
			int intAnswer = Float.floatToIntBits( (float) doubleAnswer );
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.DOUBLE,
					intAnswer
			);
			SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), temp.getValue().toString());
			CodeGen.getInstance().addToText( "lwc1 $f0, " + e1.getName() );
			CodeGen.getInstance().addToText( "lwc1 $f1, " + e2.getName() );
			CodeGen.getInstance().addToText( "mul.s $f2, $f0, $f1" );
			CodeGen.getInstance().addToText( "la $a0, " + temp.getName() );
			CodeGen.getInstance().addToText( "swc1 $f2, 0($a0)" );
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
	}

}
