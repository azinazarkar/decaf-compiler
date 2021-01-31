package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.SymbolTableNode;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.FunctionDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.FunctionSavedVariables;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class SaveLocalVariablesCodeGen {
	private static SaveLocalVariablesCodeGen ourInstance = new SaveLocalVariablesCodeGen();

	public static SaveLocalVariablesCodeGen getInstance() {
		return ourInstance;
	}

	private SaveLocalVariablesCodeGen() {
	}

	public void cgen() {
		CodeGen.getInstance().addToText( "# storing local variables" );
		SymbolTableNode temp = SymbolTable.getInstance().getSymbolTable();
		FunctionSavedVariables.getInstance().stack.push( new ArrayList<>() );
		int level;
		do {
			storeVariablesOfNode(temp);
			temp = temp.getPrev();
			level = temp.getLevel();
		} while ( level > 0 );
		CodeGen.getInstance().addEmptyLine();
	}

	private void storeVariablesOfNode(SymbolTableNode symbolTableNode ) {
		Set<Map.Entry<String, Descriptor>> variables = symbolTableNode.getAllEntries();
		ArrayList<Map.Entry<String, Descriptor>> temp = new ArrayList<>( variables );
		for ( Map.Entry<String, Descriptor> i : temp ) {
			if ( ! ( i.getValue() instanceof FunctionDescriptor ) ) {
				CodeGen.getInstance().addToText( "addi $sp, $sp, -4" );
				CodeGen.getInstance().addToText( "lw $a0, " + i.getValue().getName() );
				CodeGen.getInstance().addToText( "sw $a0, 0($sp)" );
				FunctionSavedVariables.getInstance().stack.peek().add( i.getValue().getName() );
			}
		}
	}

}
