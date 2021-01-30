package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidArrayType;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.ArrayDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.ArrayType;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class NewArrayCodeGen {
	private static NewArrayCodeGen ourInstance = new NewArrayCodeGen();

	public static NewArrayCodeGen getInstance() {
		return ourInstance;
	}

	private NewArrayCodeGen() {
	}

	public void cgen() {
		Object type = SemanticStack.getInstance().popDescriptor();
		Descriptor e = (Descriptor) SemanticStack.getInstance().popDescriptor();
		ArrayDescriptor temp = null;
		if ( type instanceof Type ) {
			if ( ((Type) type) == Type.VOID )
				throw new InvalidArrayType( (Type) type );
			temp = new ArrayDescriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					new ArrayType((Type) type, 1)
			);
		}
		else if ( type instanceof ArrayType ) {
			ArrayType t = (ArrayType) type;
			temp = new ArrayDescriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					new ArrayType( t.getSubType(), t.getDimensionCount() + 1 )
			);
		}
		SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
		CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
		CodeGen.getInstance().addToText( "# Creating a new array on heap");
		CodeGen.getInstance().addToText( "li $v0, 9");
		CodeGen.getInstance().addToText( "lw $a0, " + e.getName());
		if ( e.isFromArray() )
			CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
		CodeGen.getInstance().addToText( "li $s0, 4" );
		CodeGen.getInstance().addToText( "mult $a0, $s0" );
		CodeGen.getInstance().addToText( "mflo $a0");
		CodeGen.getInstance().addToText( "addi $a0, $a0, 4" );
		CodeGen.getInstance().addToText( "syscall");
		CodeGen.getInstance().addToText( "sw $v0, " + temp.getName() );
		CodeGen.getInstance().addToText( "lw $a0, " + e.getName() );
		CodeGen.getInstance().addToText( "lw $a1, " + temp.getName() );
		CodeGen.getInstance().addToText( "sw $a0, 0($a1)" );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
