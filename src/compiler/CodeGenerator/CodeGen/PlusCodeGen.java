package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.CalculationTypeMismatch;
import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.CompileTimeDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class PlusCodeGen {
	private static PlusCodeGen ourInstance = new PlusCodeGen();

	public static PlusCodeGen getInstance() {
		return ourInstance;
	}

	private PlusCodeGen() {
	}

	public void cgen() {
		Descriptor e2 = (Descriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor e1 = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Adding " + e1.getName() + " and " + e2.getName() );
		if ( e1.getType() != e2.getType() &&
				( ( e1.getType() != Type.STRINGLITERAL && e2.getType() != Type.STRING )
				&& ( e1.getType() != Type.STRING && e2.getType() != Type.STRINGLITERAL ) )
		)
			throw new CalculationTypeMismatch( "+", e1.getType(), e2.getType() );
		if ( e1.getType() == Type.BOOL)
			throw new InvalidOperator("+", Type.BOOL);
		if ( e1.getType() == Type.INT ) {
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.INT
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToText("lw " + "$a0, " + e1.getName());
			if ( e1.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
			CodeGen.getInstance().addToText("lw " + "$a1, " + e2.getName());
			if ( e2.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a1, 0($a1)" );
			CodeGen.getInstance().addToText("add $t0, $a0, $a1");
			CodeGen.getInstance().addToText("la " + "$a2, " + temp.getName());
			CodeGen.getInstance().addToText("sw $t0, 0($a2)");
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.DOUBLE ) {
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.DOUBLE
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToText( "lw $a0, " + e1.getName() );
			if ( e1.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
			CodeGen.getInstance().addToText( "mtc1 $a0, $f0" );
			CodeGen.getInstance().addToText( "lw $a1, " + e2.getName() );
			if ( e2.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a1, 0($a1)" );
			CodeGen.getInstance().addToText( "mtc1 $a1, $f1" );
			CodeGen.getInstance().addToText( "add.s $f2, $f0, $f1" );
			CodeGen.getInstance().addToText( "la $a0, " + temp.getName() );
			CodeGen.getInstance().addToText( "swc1 $f2, 0($a0)" );
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.STRINGLITERAL && e2.getType() == Type.STRINGLITERAL ) {
			CompileTimeDescriptor d1 = (CompileTimeDescriptor) e1;
			CompileTimeDescriptor d2 = (CompileTimeDescriptor) e2;
			CompileTimeDescriptor temp = new CompileTimeDescriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.STRINGLITERAL,
					d1.getValue().toString().substring( 0, d1.getValue().toString().length() - 1 )
							+ d2.getValue().toString()
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			SemanticStack.getInstance().pushDescriptor( temp );
			StringLiteralCodeGen.getInstance().cgen();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.STRINGLITERAL && e2.getType() == Type.STRING ) {
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.STRING
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToText( "lw $a0, " + e2.getName() );
			CodeGen.getInstance().addToText( "jal _get_string_size" );
			Descriptor result = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.STRING
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(result.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(result.getType()), 0);
			// TODO build the string
			SemanticStack.getInstance().pushDescriptor( result );
		}
	}

}
