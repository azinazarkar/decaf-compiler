package compiler.CodeGenerator.SymbolTable;

import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.Exceptions.NameAlreadyExistsException;
import compiler.CodeGenerator.Exceptions.NameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableNode {

	SymbolTableNode prev;
	SymbolTableNode next;
	private Map<String, Descriptor> table;
	private int level;
	private String scopeName;

	public SymbolTableNode( String scopeName ) {
		this( null, scopeName, 0 );
	}

	public SymbolTableNode( SymbolTableNode symbolTableNode, String scopeName, int level ) {
		prev = symbolTableNode;
		table = new HashMap<>();
		this.level = level;
		this.scopeName = scopeName;
	}

	public void addEntry( String id, Descriptor descriptor ) {
		if ( table.containsKey( id ) )
			throw new NameAlreadyExistsException( id );
		table.put( id, descriptor );
	}

	public Descriptor getDescriptor( String id ) {
		if ( table.containsKey( id ) )
			return table.get( id );
		else if ( prev == null )
			throw new NameNotFoundException( id );
		else
			return prev.getDescriptor( id );
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getScopeName() {
		return scopeName;
	}

	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}

	@Override
	public String toString() {
		String returnValue = scopeName + ", " + level + ", ";
		for ( Map.Entry<String, Descriptor> e : table.entrySet() )
			returnValue = returnValue + " (" + e.getKey() + "," + e.getValue() + ")";
		return returnValue;
	}
}
