package compiler.CodeGenerator.SymbolTable;

public class SymbolTable {
	private static SymbolTable ourInstance = new SymbolTable();
	private SymbolTableNode currentNode;

	public static SymbolTable getInstance() {
		return ourInstance;
	}

	private SymbolTable() {
		currentNode = new SymbolTableNode();
	}

	public SymbolTableNode getSymbolTable() {
		return currentNode;
	}

	public void makeNextAndSwitch() {
		SymbolTable.getInstance().currentNode.next = new SymbolTableNode( SymbolTable.getInstance().currentNode );
		SymbolTable.getInstance().currentNode = SymbolTable.getInstance().currentNode.next;
	}

	public void goBack() {
		SymbolTable.getInstance().currentNode = SymbolTable.getInstance().currentNode.prev;
		SymbolTable.getInstance().currentNode.next = null;
	}

}
