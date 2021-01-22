package compiler.CodeGenerator.SymbolTable;

public class SymbolTable {
	private static SymbolTable ourInstance = new SymbolTable();
	private SymbolTableNode currentNode;

	public static SymbolTable getInstance() {
		return ourInstance;
	}

	private SymbolTable() {
		currentNode = new SymbolTableNode( "root" );
	}

	public SymbolTableNode getSymbolTable() {
		return currentNode;
	}

//	// Gets an ID and returns a new string with extra information about scope
//	public String reformat( String id ) {
//		return currentNode.getScopeName() + "_" + currentNode.getLevel() + "_" + id;
//	}

//	public String getIdFromName( String name ) {
//		return name.split( "->", 3 )[2];
//	}

	public void makeNextAndSwitch() {
		makeNextAndSwitch( SymbolTable.getInstance().currentNode.getScopeName() );
	}

	public void makeNextAndSwitch( String newName ) {
		SymbolTableNode temp = SymbolTable.getInstance().currentNode;
		temp.next = new SymbolTableNode( temp, newName, temp.getLevel() + 1 );
		SymbolTable.getInstance().currentNode = temp.next;
	}

	public void goBack() {
		SymbolTable.getInstance().currentNode = SymbolTable.getInstance().currentNode.prev;
		SymbolTable.getInstance().currentNode.next = null;
	}

}
