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

	public void makeNextAndSwitch() {
		makeNextAndSwitch( SymbolTable.getInstance().currentNode.getScopeName() );
	}

	public void makeNextAndSwitch( String newName ) {
		SymbolTableNode temp = SymbolTable.getInstance().currentNode;
		temp.addNext( newName );
		SymbolTable.getInstance().currentNode = temp.getNext();
	}

	public void resetIndexes() {
		resetIndexesOfANode( currentNode );
	}

	private void resetIndexesOfANode( SymbolTableNode symbolTableNode ) {
		symbolTableNode.resetIndex();
		for ( int i = 0; i < symbolTableNode.next.size(); i++ )
			resetIndexesOfANode( symbolTableNode.next.get(i) );
	}


	public void goBack() {
		SymbolTable.getInstance().currentNode = SymbolTable.getInstance().currentNode.prev;
	}

	@Override
	public String toString() {
		String returnValue = "";
		returnValue += currentNode.toString() + "\n";
		for ( SymbolTableNode i : currentNode.next )
			returnValue += "\t" + i.toString();
		return returnValue;
	}
}
