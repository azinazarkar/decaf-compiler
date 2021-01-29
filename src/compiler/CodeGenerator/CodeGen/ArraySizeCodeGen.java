package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.ArrayDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class ArraySizeCodeGen {
	private static ArraySizeCodeGen ourInstance = new ArraySizeCodeGen();

	public static ArraySizeCodeGen getInstance() {
		return ourInstance;
	}

	private ArraySizeCodeGen() {
	}

	public void cgen() {
		ArrayDescriptor descriptor = (ArrayDescriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor temp = new Descriptor(
				"_" + IDGenerator.getInstance().getNextID(),
				Type.INT
		);
		SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
		CodeGen.getInstance().addToData( temp.getName(), Type.getMipsType( temp.getType() ), 0 );
		CodeGen.getInstance().addToText( "# getting size of array " + descriptor.getName() );
		CodeGen.getInstance().addToText( "lw $a0, " + descriptor.getName() );
		CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
		CodeGen.getInstance().addToText( "sw $a0, " + temp.getName() );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( temp );
	}

}
