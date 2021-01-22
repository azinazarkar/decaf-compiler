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

	public void addEntry( String name, Descriptor descriptor ) {
		if ( table.containsKey( name ) )
			throw new NameAlreadyExistsException( name );
		table.put( name, descriptor );
	}

	public Descriptor getDescriptor( String name ) {
		if ( table.containsKey( name ) )
			return table.get( name );
		else if ( prev == null )
			throw new NameNotFoundException( name );
		else
			return prev.getDescriptor( name );
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
