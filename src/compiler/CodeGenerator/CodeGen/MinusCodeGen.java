package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.CalculationTypeMismatch;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class MinusCodeGen {
	private static MinusCodeGen ourInstance = new MinusCodeGen();

	public static MinusCodeGen getInstance() {
		return ourInstance;
	}

	private MinusCodeGen() {
	}

	public void cgen() {
		Descriptor e2 = SemanticStack.getInstance().popDescriptor();
		Descriptor e1 = SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "Subtracting " + e2.getName() + " from " + e1.getName() );
		if ( e1.getType() != e2.getType() )
			throw new CalculationTypeMismatch( "-", e1.getType(), e2.getType() );
		if ( e1.getType() == Type.INT ) {
			Descriptor temp = new Descriptor(
					IDGenerator.getInstance().getNextID(),
					Type.INT,
					(int) e1.getValue() - (int) e2.getValue()
			);
			SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), temp.getValue().toString());
			CodeGen.getInstance().addToText("lw " + "$a0, " + e1.getName());
			CodeGen.getInstance().addToText("lw " + "$a1, " + e2.getName());
			CodeGen.getInstance().addToText("sub $t0, $a0, $a1");
			CodeGen.getInstance().addToText("la " + "$a2, " + temp.getName());
			CodeGen.getInstance().addToText("sw $t0, 0($a2)");
			CodeGen.getInstance().addToText( "" );
			SemanticStack.getInstance().pushDescriptor( temp );
		}
	}

}
