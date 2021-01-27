package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.SymbolTableNode;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.FunctionDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.FunctionSavedVariables;

import java.util.*;

public class LoadLocalVariablesCodeGen {
	private static LoadLocalVariablesCodeGen ourInstance = new LoadLocalVariablesCodeGen();

	public static LoadLocalVariablesCodeGen getInstance() {
		return ourInstance;
	}

	private LoadLocalVariablesCodeGen() {
	}

	public void cgen() {
		CodeGen.getInstance().addToText( "# loading local variables" );
		ArrayList<String> temp = FunctionSavedVariables.getInstance().stack.pop();
		Collections.reverse( temp );
		System.out.println( "Loading" );
		for ( String i : temp ) {
			System.out.println( "\t" + i );
			CodeGen.getInstance().addToText( "lw $a1, 0($sp)" );
			CodeGen.getInstance().addToText( "sw $a1, " + i );
			CodeGen.getInstance().addToText( "addi $sp, $sp, 4" );
		}
//		SymbolTableNode temp = SymbolTable.getInstance().getSymbolTable();
//		int level;
//		do {
//			loadVariablesIntoNode( temp );
//			temp = temp.getPrev();
//			level = temp.getLevel();
//		} while ( level > 0 );

		CodeGen.getInstance().addEmptyLine();
	}

//	private void loadVariablesIntoNode( SymbolTableNode symbolTableNode ) {
//		Set<Map.Entry<String, Descriptor>> variables = symbolTableNode.getAllEntries();
//		ArrayList<Map.Entry<String, Descriptor>> temp = new ArrayList<>( variables );
//		Collections.reverse( temp );
//		for ( Map.Entry<String, Descriptor> i : temp )
//			if ( ! ( i.getValue() instanceof FunctionDescriptor) )
//				System.out.println( "\t" + i.getKey() );
//		for ( Map.Entry<String, Descriptor> i : temp ) {
//			if ( ! ( i.getValue() instanceof FunctionDescriptor ) ) {
//				CodeGen.getInstance().addToText( "lw $a1, 0($sp)" );
//				CodeGen.getInstance().addToText( "sw $a1, " + i.getValue().getName() );
//				CodeGen.getInstance().addToText( "addi $sp, $sp, 4" );
//			}
//		}
//	}

}
