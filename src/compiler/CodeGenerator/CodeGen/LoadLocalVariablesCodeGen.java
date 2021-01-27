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
		for ( String i : temp ) {
			CodeGen.getInstance().addToText( "lw $a1, 0($sp)" );
			CodeGen.getInstance().addToText( "sw $a1, " + i );
			CodeGen.getInstance().addToText( "addi $sp, $sp, 4" );
		}

		CodeGen.getInstance().addEmptyLine();
	}

}
