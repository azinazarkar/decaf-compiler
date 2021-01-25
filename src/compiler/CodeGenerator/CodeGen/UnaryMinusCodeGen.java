package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.AssignmentTypeMismatch;
import compiler.CodeGenerator.Exceptions.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class UnaryMinusCodeGen {
	private static UnaryMinusCodeGen ourInstance = new UnaryMinusCodeGen();

	public static UnaryMinusCodeGen getInstance() {
		return ourInstance;
	}

	private UnaryMinusCodeGen() {
	}

	public void cgen() {
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Getting negative value of " + e.getName() );
		if ( e.getType() == Type.STRING
				|| e.getType() == Type.OBJECT
				|| e.getType() == Type.VOID
				|| e.getType() == Type.NULL )
			throw new InvalidOperator( "unary -", e.getType() );
		if ( e.getType() == Type.INT ) {
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.INT
			);
			SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToText( "lw $s0, " + e.getName() );
			CodeGen.getInstance().addToText( "neg $s1, $s0" );
			CodeGen.getInstance().addToText( "sw $s1, " + temp.getName() );
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e.getType() == Type.DOUBLE ) {
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.DOUBLE
			);
			SymbolTable.getInstance().getSymbolTable().addEntry( temp.getName(), temp );
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToText( "lwc1 $f0, " + e.getName() );
			CodeGen.getInstance().addToText( "neg.s $f1, $f0" );
			CodeGen.getInstance().addToText( "swc1 $f1, " + temp.getName() );
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
	}

}
