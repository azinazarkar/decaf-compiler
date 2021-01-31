package compiler.CodeGenerator.SymbolTable.Utility;

import java.util.ArrayList;
import java.util.Stack;

public class FunctionSavedVariables {
	private static FunctionSavedVariables ourInstance = new FunctionSavedVariables();
	public Stack<ArrayList<String>> stack;

	public static FunctionSavedVariables getInstance() {
		return ourInstance;
	}

	private FunctionSavedVariables() {
		stack = new Stack<>();
	}

}
