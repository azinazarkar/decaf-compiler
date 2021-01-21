package compiler.CodeGenerator.SymbolTable;

public class SymbolTable {
	private static SymbolTable ourInstance = new SymbolTable();
	private SymbolTableNode currentNode;

	public static SymbolTable getInstance() {
		return ourInstance;
	}

	private SymbolTable() {
	}

	public SymbolTableNode getSymbolTable() {
		return currentNode;
	}

}
